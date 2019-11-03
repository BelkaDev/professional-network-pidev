package com.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IPayementServiceLocal;
import com.esprit.Iservice.IPayementServiceRemote;

import com.esprit.beans.Payement;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.utils.EmailUtil;
import com.esprit.utils.UserSession;

@LocalBean
@Stateless
public class PaymentService implements IPayementServiceLocal,IPayementServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	EmailUtil mail = new EmailUtil();
	NotificationService ns=new NotificationService();
	
	@Override
	public boolean payPack(int idPack,String numCard,int cvv,Date expirationDate) {
		UserPack up=em.find(UserPack.class, idPack);
		Payement p=new Payement();
		int length=numCard.length();
		int length1=Integer.toString(cvv).length();
		Date d =Date.valueOf(LocalDate.now());
		p.setCardExpirationDate(expirationDate);
		//int a=Integer.parseInt(numCard);
		
		
	
		if((length==16)&& (length1==3) && (expirationDate.after(d)) )
		{
			p.setCardExpirationDate(expirationDate);
			p.setCvv(cvv);
			p.setCanceled(false);
			p.setValidation(false);
			p.setNumCard(numCard);
			p.setUserPack(up);
			
			em.persist(p);
			up.setPayment(p);
			em.merge(up);
			mail.sendEmail(UserSession.getInstance().getEmail(), "your payment will be treated ", "instead of your payment :"+up.getPack().getTitle()+" /n"+"the Adminastrator will treat your payment details");
			
			return true;
		}
		
		
		return false;
		
	}

	@Override
	public void cancelPayment(int idPack) {
		Payement p=em.find(Payement.class, idPack);
		p.setCanceled(true);
		em.merge(p);
		
	}

	@Override
	public List<Payement> consultYourPaymentDetails(){
		
		User s=em.find(User.class, UserSession.getInstance().getId());
		List<Payement> p=new ArrayList<Payement>();
		
		TypedQuery<UserPack> list= em.createQuery(
			      "select e from UserPack e where e.idUser=: id", UserPack.class);
		list.setParameter("id", s.getId());
			  List<UserPack> results = list.getResultList();
			  for(UserPack up:results)
			  {
				  p.add(em.find(Payement.class,up.getPayment()));
				  
			  }
			  
		return p;
	}

	@Override
	public List<Payement> ConsultPayments() {
		
		TypedQuery<Payement> list= em.createQuery(
			      "select e from Payement e ", Payement.class);
		
			  return list.getResultList();
	}

	@Override
	public void ValidateCanceledPayment(int idUserPack) {
		//Notification p =new Notification();
		Payement p=em.find(Payement.class, idUserPack);
		if(p.isCanceled())
		{
			p.setValidation(false);
			ns.CreateNotification(p.getUserPack().getUser().getId(), "your notification has been canceled", NOTIFICATION_TYPE.Payment,p.getId() );
			
		}
		
	}

	@Override
	public void validatePayment(int idUserPack) {
		
		Payement p=em.find(Payement.class, idUserPack);
		if(!p.isCanceled())
		{
			p.setValidation(true);
			ns.CreateNotification(p.getUserPack().getUser().getId(), "your notification has been validated", NOTIFICATION_TYPE.Payment,p.getId() );
			
		}
	}
	@Override
	public boolean removePayment(int id)
	{
		Payement p=em.find(Payement.class, id);
		if(p.isCanceled())
		{
			em.remove(p);
			return true;
		}
		return false;
	}

}
