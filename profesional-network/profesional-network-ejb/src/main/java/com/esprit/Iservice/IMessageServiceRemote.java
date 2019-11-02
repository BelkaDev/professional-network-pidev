package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Message;


@Remote
public interface IMessageServiceRemote {
	
	void sendMessage(int sender, int idRecipient, String body);
	void editMessage(int id, String body);
	void deleteMessage(int id);
	void setStatus(int id);
	public Message findMessage(int id);
	List<Message> findMsgBySender(int id);
	List<Message> findMsgByReciever(int id);
	String fetchContent(int idUser,int idDest);
	List<Message> findAllMessages();	

}
