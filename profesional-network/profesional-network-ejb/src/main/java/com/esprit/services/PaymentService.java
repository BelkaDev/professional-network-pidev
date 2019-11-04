package com.esprit.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

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
		
		
	if(up.getEndDate().equals(null)) {
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
			
			/* CREATE NEW PDF + SEND IT TO EMAIL */

		    File dir = new File(System.getProperty("user.dir")+"/payments/");
		    if (!dir.exists()) dir.mkdirs();
			
			String filepath =  System.getProperty("user.dir")+"/payments/"+p.getCardExpirationDate()+".pdf";
			Document document = new Document();

			try {
				PdfWriter.getInstance(document, new FileOutputStream(filepath));
			} catch (FileNotFoundException | DocumentException e1) {
				e1.printStackTrace();
			}
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);
			try {
				document.add(chunk);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			document.close();
			
			mail.sendAttachmentEmail(UserSession.getInstance().getEmail(), "Professional Network : Your payment has been scheduled","Your payment has been scheduled\nYou can find the details in the attachment." ,
					filepath,p.getCardExpirationDate().toString()+".pdf");
			return true;
		}
		else if((length==16)&& (length1==3) && (expirationDate.after(d)) &&(up.getEndDate().before(d)) )
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
	}
		
		
		return false;
		
	}

	@Override
	public boolean cancelPayment(int idPack) {
		Payement p=em.find(Payement.class, idPack);
		if(p.isValidation())
		{
			return false;
		}
		p.setCanceled(true);
		em.merge(p);
		return true;
		
	}

	@Override
	public List<Payement> consultYourPaymentDetails(){
		
		/*System.out.println("***************"+UserSession.getInstance().getId());
		List<Payement> p=new ArrayList<Payement>();
		
		TypedQuery<UserPack> list= em.createQuery(
			      "select e from UserPack e where e.user= :id", UserPack.class);
		list.setParameter("id", u);
			  List<UserPack> results = list.getResultList();
			  for(UserPack up:results)
			  {
				  System.out.println("************************"+up.getPayment().getId());
				  Payement pp=em.find(Payement.class, up.getPayment().getId());
				  p.add(pp.getUserPack().getUser());
				  
				  
			  }
			  
		return p;*/
		User u=em.find(User.class, UserSession.getInstance().getId());
		TypedQuery<Payement> listPayment= em.createQuery(
			      "select e from Payement e where e.userPack.user= :id", Payement.class);
		listPayment.setParameter("id", u);
			  return listPayment.getResultList();
		
		
	}

	@Override
	public List<Payement> ConsultPayments() {
		
		TypedQuery<Payement> list= em.createQuery(
			      "select e from Payement e ", Payement.class);
		
			  return list.getResultList();
	}

	@Override
	public boolean ValidateCanceledPayment(int idUserPack) {
		//Notification p =new Notification();
		Payement p=em.find(Payement.class, idUserPack);
		if(p.isCanceled() && p.isValidation())
		{
			p.setValidation(false);
			em.merge(p);
			//ns.CreateNotification(p.getUserPack().getUser().getId(), "your notification has been canceled", NOTIFICATION_TYPE.Payment,p.getId() );
			return true;
		}
		return false;
		
	}

	@Override
	public boolean validatePayment(int idUserPack) {
		
		Payement p=em.find(Payement.class, idUserPack);
		if(!p.isCanceled())
		{
			p.setValidation(true);
			em.merge(p);
		//ns.CreateNotification(p.getUserPack().getUser().getId(), "your notification has been validated", NOTIFICATION_TYPE.Payment,p.getId() );
		return true;	
		}
		return false;
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
