package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IMessageServiceLocal;
import com.esprit.Iservice.IMessageServiceRemote;
import com.esprit.beans.Message;
import com.esprit.beans.Quiz;
import com.esprit.beans.User;


@Stateless
@LocalBean
public class MessageService implements IMessageServiceLocal,IMessageServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addMessage(Timestamp date, int idSender, int idRecipient, String body, int status) {

		User sender = em.find(User.class,idSender);
		Message msg = new Message();
		msg.setDate(date);
		msg.setBody(body);
		msg.setSender(sender);
		msg.setStatus(status);
		msg.setRecipient(idRecipient);
			
		
		if ((msg.getRecipient() == 0 )){
			System.out.println("The destinator doesn't exist.");}
			else {
				em.persist(msg);
			}
		}
		
	@Override
	public List<Message> findAllMessages() {
		return em
				.createQuery("select msg from Message msg", Message.class)
				.getResultList();

	}

	@Override
	public void editMessage(Timestamp date, int idSender, int idRecipient, String body, int status) {
		User sender = em.find(User.class,idSender);
		Message msg = new Message();
		msg.setDate(date);
		msg.setBody(body);
		msg.setSender(sender);
		msg.setStatus(status);
		msg.setRecipient(idRecipient);
			
		
		if ((msg.getRecipient() == 0 )){
			System.out.println("The destinator doesn't exist.");}
			else {
				em.merge(msg);
			}

	}

	@Override
	public void deleteMessage(int id) {
		em.remove(em.find(Message.class, id));

	}

	@Override
	public Message findMessage(int id) {
		return em.find(Message.class, id);
	}

	@Override
	public List<Message> findMsgBySender(int iduser) {
		List<Message> msgs = em.createQuery("select msg from Message msg  where msg.sender.id=:Id", Message.class)
				.setParameter("Id", iduser).getResultList();

		return msgs;
	}
	
	@Override
	public List<Message> findMsgByReciever(int iduser) {
		List<Message> msgs = em.createQuery("select msg from Message msg where msg.recipient=:Id",Message.class)
				.setParameter("Id", iduser).getResultList();
		for (Message msg : msgs){
			msg.setStatus(1);
		}
		
		return msgs;
	}

	@Override
	public String fetchContent(int iduser,int iddest) {
		String Newligne=System.getProperty("line.separator"); 
		String content="";
		List<Message> msgs = em.createQuery("select msg from Message msg where msg.recipient=:Id OR msg.sender.id=:Id ORDER BY msg.date", Message.class)
				.setParameter("Id", iduser).getResultList();
		for (Message msg : msgs){
;			
			
			if(msg.getSender().getId()==iddest || msg.getRecipient()==iddest){
			if (msg.getSender().getId()==iduser){
				if(msg.getStatus()==1){
				content=content+("MOI"+Newligne+msg.getBody()+Newligne+"****seen****"+Newligne);
				}
				else {
					content=content+("MOI"+Newligne+msg.getBody()+Newligne);
				}
				
			}
			else if(msg.getSender().getId()!=iduser) {
				content=content+(msg.getSender().getFirstName()+Newligne+msg.getBody()+Newligne);
			
			}
			}
			}
		System.out.println(content);
		return content;
		
	}


}
