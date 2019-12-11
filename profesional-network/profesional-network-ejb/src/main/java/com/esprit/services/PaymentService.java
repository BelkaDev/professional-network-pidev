package com.esprit.services;


import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IPayementServiceLocal;
import com.esprit.Iservice.IPayementServiceRemote;
import com.esprit.beans.Pack;
import com.esprit.beans.Payement;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.Role;
import com.esprit.utils.EmailUtil;
import com.esprit.utils.UserSession;


@LocalBean
@Stateless
public class PaymentService implements IPayementServiceLocal, IPayementServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	EmailUtil mail = new EmailUtil();
	@EJB
	NotificationService ns = new NotificationService();

	@SuppressWarnings("deprecation")
	public Date endDatePack(String title) {
		Date d = Date.valueOf(LocalDate.now());
		Date end = d;
		if (title.equals("standard account for 3 months")) {
			if (end.getMonth() + 3 > 12) {
				end.setYear(d.getYear() + 1);
				end.setMonth((end.getMonth() + 3) - 12);
			} else
				end.setMonth(d.getMonth() + 3);
		}
		if (title.equals("standard account for 6 months")) {
			System.out.println("***********************"+end.getMonth() + 3);

			if (end.getMonth() + 6 > 12) {
				end.setYear(d.getYear() + 1);
				end.setMonth((end.getMonth() + 6) - 12);
			} else
				end.setMonth(d.getMonth() + 6);
		}
		if (title.equals("standard account for 12 months")) {
			end.setYear(d.getYear() + 1);
		}
		if (title.equals("premuim account for 3 months")) {
			System.out.println("***********************"+end.getMonth() + 3);
			if (end.getMonth() + 3 > 12) {
				end.setYear(d.getYear() + 1);
				end.setMonth((end.getMonth() + 3) - 12);
			} else
				end.setMonth(d.getMonth() + 3);
		}
		if (title.equals("premuim account for 6 months")) {
			System.out.println("***********************"+end.getMonth() + 3);
			if (end.getMonth() + 6 > 11) {
				end.setYear(d.getYear() + 1);
				end.setMonth((end.getMonth() + 6) - 11);
			}
			end.setMonth(end.getMonth() + 6);
		}
		if (title.equals("standard account for 12 months")) {
			end.setYear(end.getYear() + 1);
		}
		if (title.equals("standard account for 3 months")) {
			if (end.getMonth() + 3 > 11) {
				end.setYear(d.getYear() + 1);
				end.setMonth((end.getMonth() + 3) - 11);
			} else
				end.setMonth(end.getMonth() + 3);
		}
		if (title.equals("standard account for 6 months")) {
			if (end.getMonth() + 6 > 11) {
				end.setYear(end.getYear() + 1);
				end.setMonth((end.getMonth() + 6) - 11);
			} else
				end.setMonth(end.getMonth() + 6);
		}
		if (title.equals("standard account for 12 months")) {
			end.setYear(end.getYear() + 1);
		}
		return end;
	}
	@Override
	public boolean numCardvalid(String num)
	{
		TypedQuery<Payement> query=em.createQuery("select e from Payement e",Payement.class);
		List<Payement> results=query.getResultList();
		for(Payement a :results)
		{
			if(a.getNumCard().equals(num))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean payPack(int idPack, String numCard, int cvv, Date expirationDate) {
		UserPack up = this.getUserPack(idPack);
		Payement p = new Payement();
		int length = numCard.length();
		int length1 = Integer.toString(cvv).length();
		Date d = Date.valueOf(LocalDate.now());
		p.setCardExpirationDate(expirationDate);
		// int a=Integer.parseInt(numCard);
//		if(!(up.getUser().getId()==UserSession.getInstance().getId())) {
//			return false;
//		}
//		else 
			if(!(numCardvalid(numCard))) {
			return false;
		}
		else if ((length == 16) && (length1 == 3) && (expirationDate.after(d))) {
			p.setCardExpirationDate(expirationDate);
			p.setCvv(cvv);
			p.setCanceled(false);
			p.setValidation(false);
			p.setNumCard(numCard);
			p.setUserPack(up);

			em.persist(p);
//			up.setStartDate(Date.valueOf(LocalDate.now()));
//		up.setEndDate(endDatePack(up.getPack().getTitle()));
			//up.setPayment(em.find(Payement.class,p));
			TypedQuery<Payement> query=em.createQuery("select e from Payement e where e.numCard=:k",Payement.class);
			query.setParameter("k", numCard);
			Payement l=query.getSingleResult();
			up.setPayment(l);
			
			return true;
		}

		return false;

	}

	@Override
	public boolean cancelPayment(int idPack) {
		
		
		Payement p = em.find(Payement.class, idPack);
		
		if (p.isValidation()) {
			return false;
		}
		else{p.setCanceled(true);
		em.merge(p);
		return true;
		}
	}

	@Override
	public List<Payement> consultYourPaymentDetails() {
		if(UserSession.getInstance().equals(null)){
			return null;
		}
		
		User u = em.find(User.class, UserSession.getInstance().getId());
		TypedQuery<Payement> listPayment = em.createQuery("select e from Payement e where e.userPack.user= :id",
				Payement.class);
		listPayment.setParameter("id", u);
		return listPayment.getResultList();
		

	}

	@Override
	public List<Payement> ConsultPayments() {
		User u =em.find(User.class,UserSession.getInstance().getId());
//		if(UserSession.getInstance().equals(null)){
//			return null;
//		}
//		else if(u.getRole().equals(Role.Admin)) {
		TypedQuery<Payement> list = em.createQuery("select e from Payement e ", Payement.class);

		return list.getResultList();
		
//		}
//		return null;
	}

	@Override
	public boolean ValidateCanceledPayment(int idUserPack) {
		
		Payement p = em.find(Payement.class, idUserPack);
		if(UserSession.getInstance().equals(null)){
			return false;
		}
		else if(!(UserSession.getInstance().getRole().equals(Role.Admin)))
		{
			return false;
		}
		else if (p.isCanceled() && p.isValidation()) {
			p.setValidation(false);
			em.merge(p);
		
			return true;
		}
		return false;

	}

	@Override
	public boolean validatePayment(int idUserPack) {

		Payement p = em.find(Payement.class, idUserPack);
		
			p.setValidation(true);
			p.getUserPack().setValid(true);
			em.merge(p);
			//ns.CreateNotification(p.getUserPack().getUser().getId(), "your payment was validated", NOTIFICATION_TYPE.Payment, p.getId());
			return true;
		
		
	}

	@Override
	public boolean removePayment(int id) {
		Payement p = em.find(Payement.class, id);
		if(!(UserSession.getInstance().getRole().equals(Role.Admin))) {
			return false;
		}
		else if (p.isCanceled()) {
			em.remove(p);
			return true;
		}
		return false;
	}

	@Override
	public boolean isPremium(int userId) {
		User u = em.find(User.class, userId);
		TypedQuery<UserPack> query = em.createQuery("select up from UserPack up where up.user=:id", UserPack.class);
		query.setParameter("id", u);
		
		List<UserPack> results = query.getResultList();
		for (UserPack ur : results) {
			if (ur.isValid())
				return true;
		}
		return false;
	}
	
	public Payement getPaymentById(int id) {
		return em.find(Payement.class, id); 
	}
	public User getUserByPayment(int id) {
		TypedQuery<User> query=em.createQuery("select e.user from UserPack e where e.payment=:id",User.class);
		query.setParameter("id", em.find(Payement.class, id));
		return query.getSingleResult();
	}
	public UserPack getUserPack(int id) {
		
		User u=em.find(User.class, 1);
		TypedQuery<UserPack> query=em.createQuery("select e from UserPack e where e.user=:id",UserPack.class);
		query.setParameter("id",u);
		
			UserPack a=query.getSingleResult();				
		
	return a;
	}
	
}
