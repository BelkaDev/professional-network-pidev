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
import com.esprit.beans.Notification;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TARGET;
import com.esprit.enums.NOTIFICATION_TYPE;

import com.esprit.utils.EmailUtil;

import java.util.concurrent.TimeUnit;

@Stateless
@LocalBean
public class NotificationService implements INotificationServiceLocal {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	EmailUtil mail = new EmailUtil();
	UserService userService = new UserService();
	@Override
	public void CreateNotification(int idReciever,String body,
			NOTIFICATION_TYPE type,int trigger,int targetId) {
			

		Set<NOTIFICATION_TYPE> POST_TYPE = EnumSet.of(NOTIFICATION_TYPE.Comment, NOTIFICATION_TYPE.Share, NOTIFICATION_TYPE.Reaction);
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
		newNotification.setTargetId(targetId);
		User reciever= em.find(User.class,idReciever);
		newNotification.setMessage(body);
		newNotification.setReciever(reciever);
		newNotification.setTarget(target);
		Timestamp date = new Timestamp(System.currentTimeMillis());
		newNotification.setDate(date);
		em.persist(newNotification);
		
		/* mail notification */
		if (reciever.getRecieveMailNotifs()) {
			
			
			String format ="<h4>You have a new Notification on Professional Network</h4><br>"
					+ "<p> %s </p> </br>"
					+ "<p> on %s . </p> </br>"
					+ "<p> click <a href='http://localhost:9080/profesional-network-web/rest/%s/show?id=%s'>here</a> to see it. </p> </br>"
					+ "<p> You can disable these notifications. </p> </br>"
					+ "<b>Automatic Message by Professional Network </b>";
		
			String content = String.format(format,newNotification.getMessage(),
					"test","post","1");
	

			String subject = "You have a new Notification!";
			mail.sendEmail(reciever.getEmail(), subject, content);	
		}
		
	}
	
	public String parseText(NOTIFICATION_TYPE type,String body,int triggerId,int target) {
		String name = em.find(User.class,triggerId).getFirstName();
		String content="";
		//String formattedDate = new SimpleDateFormat("MMdd").format(date);
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
			
            content = "you have a new notification.";
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

		// The list always contains 2 items, that will have incrementing ids
		return result;
	}

	public Notification get(int id) {
		return em.find(Notification.class,id);
	}

	@Override
	public void deleteNotif(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editNotif(Notification notif) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Notification> userNotifications(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> typeNotifications(int id, NOTIFICATION_TYPE type) {
		// TODO Auto-generated method stub
		return null;
	}
}