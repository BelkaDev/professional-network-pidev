package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Message;


@Remote
public interface IMessageServiceRemote {
	
	void addMessage(Timestamp timestamp, int sender, int idRecipient, String body, int status);
	void editMessage(Timestamp timestamp, int sender, int idRecipient, String body, int status);
	void deleteMessage(int id);
	public Message findMessage(int id);
	List<Message> findMsgBySender(int id);
	List<Message> findMsgByReciever(int id);
	String fetchContent(int idUser,int idDest);
	List<Message> findAllMessages();	

}
