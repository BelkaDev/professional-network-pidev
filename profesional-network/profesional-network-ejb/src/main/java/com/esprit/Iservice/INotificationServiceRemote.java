package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Notification;
import com.esprit.enums.NOTIFICATION_TYPE;

@Remote
public interface INotificationServiceRemote {
	
	void CreateNotification(int idReciever, String body, 
			NOTIFICATION_TYPE type,int target);
	boolean deleteNotif(int id);
	boolean updateNotif(int idNotif,String notif_message);

	Notification get(int id);	
	List<Notification> listNotifications();
	List<Notification> userNotifications(int id);
	List<Notification> typeNotifications(int id, NOTIFICATION_TYPE type);

}