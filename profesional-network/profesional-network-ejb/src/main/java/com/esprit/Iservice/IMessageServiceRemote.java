package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Message;


@Remote
public interface IMessageServiceRemote {
	
	void addMessage(Message msg);
	void editMessage(Message msg);
	void deleteMessage(Message  msg);
	public Message findMessage(Integer id);
	List<Message> findMsgBySender(Integer id);
	List<Message> findMsgByReciever(Integer id);
	String fetchContent(Integer idUser,Integer idDest);
	List<Message> findAllMessages();	

}
