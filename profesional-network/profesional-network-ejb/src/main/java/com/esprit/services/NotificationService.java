package com.esprit.services;


import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.INotificationServiceLocal;
import com.esprit.Iservice.INotificationServiceRemote;
import com.esprit.beans.Comment;
import com.esprit.beans.Notification;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TARGET;
import com.esprit.enums.NOTIFICATION_TYPE;

import com.esprit.utils.EmailUtil;

import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
public class NotificationService implements INotificationServiceLocal,INotificationServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	EmailUtil mail = new EmailUtil();
	UserService userService = new UserService();
	@Override
	public void CreateNotification(int idReciever,String body,
			NOTIFICATION_TYPE type,int trigger,int targetId) {
			

		Set<NOTIFICATION_TYPE> POST_TYPE = EnumSet.of(NOTIFICATION_TYPE.Comment, NOTIFICATION_TYPE.Share, NOTIFICATION_TYPE.Reaction
				,NOTIFICATION_TYPE.Follow);
		NOTIFICATION_TARGET target =  NOTIFICATION_TARGET.None;
		
			if (POST_TYPE.contains(type)) { 
			 target = NOTIFICATION_TARGET.Post; 
			}  else if (type.equals(NOTIFICATION_TYPE.Message))
			{
				 target = NOTIFICATION_TARGET.Discussion;
			}  else if (type.equals(NOTIFICATION_TYPE.Relation))
			{
				 target =  NOTIFICATION_TARGET.Profile;
			} else if  (type.equals(NOTIFICATION_TYPE.Offer))
			{
				 target = NOTIFICATION_TARGET.Offer;
			}
			
		Notification newNotification = new Notification();
		User reciever= em.find(User.class,idReciever); 
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		newNotification.setTargetId(targetId);
		newNotification.setMessage(body);
		newNotification.setReciever(reciever); 
		newNotification.setTarget(target); 
		newNotification.setDate(date);
		
		
		/* check if notification exists */
		int duplicateId = this.findDuplicate(newNotification);
		if (duplicateId != -1)
		{
			this.updateNotif(duplicateId,body);
		} else
		{
		em.persist(newNotification);
		}
		
		/* mail notification */
		if (reciever.getRecieveMailNotifs()) {
			
			
			String format ="<h4>You have a new Notification on Professional Network</h4><br>"
					+ "<p> %s </p> </br>"
					+ "<p> on %s . </p> </br>"
					+ "<p> click <a href='http://localhost:9080/profesional-network-web/rest/%s/show?id=%s'>here</a> to see it. </p> </br>"
					+ "<p> You can disable these notifications. </p> </br>"
					+ "<b>Automatic Message by Professional Network </b>";
		
			String content = String.format(format,newNotification.getMessage(),
				new SimpleDateFormat("MMdd").format(date),body,"post","1");
	

			String subject = "You have a new Notification!";
			mail.sendEmail(reciever.getEmail(), subject, content);	
		}
		
	}
	
	public String parseText(NOTIFICATION_TYPE type,String body,int triggerId,int target) {
		String name = em.find(User.class,triggerId).getFirstName()+" "+em.find(User.class,triggerId).getLastName();
		String content="";
		 switch (type) {
         case Message:
                  content += name +" : "+ body;
                 break;
	    
         case Relation:
                  content += name +" sent you a friend request.";
                 break;

         case Comment:
             	  content +=  name + " commented on your post "; 
             break;
             
         case Reaction:
                  content =  name + " has reacted to your post ";
                  break;

         case Share:
             	  content =  name + " has shared your post" ;
             	   break;
         case Offer:
        	 
        	 	  content =  "Your offer has been approved.";
        	 	  break;
         default:
			
        	 	  content = "You have a new notification.";
        	 	  break;
     }
		 return content;
	}

	public List<Notification> listNotifications() {
		List<Notification> result =  em
				.createQuery("select notif from Notification notif", Notification.class)
				.getResultList();
		try {
			System.out.println("Wait for 2 seconds before returning " + result);
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		} catch (InterruptedException e) {
		}

		return result;
	}

	public Notification get(int id) {
		return em.find(Notification.class,id);
	}

	@Override
	public boolean deleteNotif(int id) {
		Notification notif = em.find(Notification.class, id);
		if (notif == null)
		{
			return false;
		}
		em.remove(notif);
		return true;
		
	}

	@Override
	public boolean updateNotif(int id,String notif_message) {
		
		Notification notif = em.find(Notification.class, id);
		if (notif == null)
		{
			return false;
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		notif.setDate(date);
		notif.setMessage(notif_message);
		return true;
		
	}

	@Override
	public List<Notification> userNotifications(int idUser) {
		List<Notification> notifs = em.createQuery("select n from Notification n where n.reciever.id=:Id",Notification.class)
				.setParameter("Id", idUser).getResultList();
		return notifs;
	}

	@Override
	public List<Notification> typeNotifications(int id, NOTIFICATION_TYPE type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findDuplicate(Notification notif) {
		List<Notification> duplicate =  em.createQuery("select n from Notification n where n.reciever.id=:reciever AND "
				+ "n.targetId=:targetId AND n.target=:target",Notification.class)
				.setParameter("reciever", notif.getReciever().getId()).setParameter("targetId", notif.getTargetId())
				.setParameter("target", notif.getTarget()).getResultList();
		if (duplicate.isEmpty())
		{
		return (-1);
		}
		return duplicate.get(0).getId();
}

	@Override
	public boolean setSeen(int id) {
		Notification notif = em.find(Notification.class,id);
		if (notif == null)
		{
			return false;
		}
		notif.setSeen(true);
		em.merge(notif);
		return true;
	}	
}