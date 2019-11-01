package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Local;

import com.esprit.beans.Message;
import com.esprit.beans.Notification;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;

@Local
public interface INotificationServiceLocal {
	
	void CreateNotification(int idReciever, String body, 
			NOTIFICATION_TYPE type, int trigger, int target);
	void deleteNotif(int id);
	void editNotif(Notification notif);

	Notification get(int id);	
	List<Notification> listNotifications();
	List<Notification> userNotifications(int id);
	List<Notification> typeNotifications(int id, NOTIFICATION_TYPE type);

}