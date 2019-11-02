package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Message;

@Local
public interface IMessageServiceLocal {
	
	void sendMessage(int idSender, int idRecipient, String body);
	void editMessage(int id,String body);
	void deleteMessage(int id);
	void setStatus(int id);
	boolean checkBlocked(int idSender,int idReciever);
	public Message findMessage(int id);
	List<Message> findMsgBySender(int id);
	List<Message> findMsgByReciever(int id);
	String fetchContent(int idUser,int idDest);
	List<Message> findAllMessages();
	
}
