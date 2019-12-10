package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IReactionServiceLocal;
import com.esprit.Iservice.IReactionServiceRemote;
import com.esprit.beans.Reaction;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.REACTION_TYPE;
import com.esprit.utils.UserSession;


@Stateless
@LocalBean
public class ReactionService implements IReactionServiceLocal,IReactionServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@EJB
	FollowingService followingservice = new FollowingService();
	@EJB
	NotificationService notificationservice = new NotificationService();

	@Override
	public boolean addReaction(int idUser,int idPost,
			REACTION_TYPE type){
		
		User reactingUser = em.find(User.class,idUser);
		Post reactedPost = em.find(Post.class,idPost);
		Reaction react = new Reaction();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		react.setDate(date);
		react.setType(type);
		react.setReactingUser(reactingUser);
		react.setReactedPost(reactedPost);
			
		
		if ((react.getReactedPost() == null && react.getReactingUser() == null)){
			return false;
			}
		else if (this.userAlreadyReacted(idUser, idPost)){
			System.out.println(idUser + "already reacted on " + idPost);
			return false;
			}
			else {
				em.persist(react);
			}
		
		// notifying the reactedPost followers about the new Reaction

		List<User> followers = followingservice.PostFollowers(idPost);
		String notif_message = reactingUser.getFirstName()+" "+reactingUser.getLastName()+
				" reacted on a reactedPost you follow.";
		
		if (followers!=null)
		{
		for (User follower : followers) {
			if (reactedPost.getUser().getId() == follower.getId())
			{
				notif_message = reactingUser.getFirstName()+" "+reactingUser.getLastName()+
						" reacted on your Post.";
			}
			NOTIFICATION_TYPE notif_type = NOTIFICATION_TYPE.Reaction;
			notificationservice.CreateNotification(follower.getId(),notif_message,notif_type ,react.getReactedPost().getId());
		}
		}
		
		return true;
	}
		
	@Override
	public boolean updateReaction(int idReaction, REACTION_TYPE type)
	{
		Reaction react = em.find(Reaction.class,idReaction);
		if (react == null || react.getReactingUser().getId() != UserSession.getInstance().getId()) {
			return false;
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		react.setDate(date);
		react.setType(type);
		return true;
	}

	@Override
	public boolean deleteReaction(int id) {
		Reaction react = em.find(Reaction.class,id);
		if (react == null  || react.getReactingUser().getId() != UserSession.getInstance().getId() ) {
			return false;
		}
		em.remove(react);
		return true;
	}

	@Override
	public Reaction findReaction(int id) {
		return em.find(Reaction.class, id);
	}

	@Override
	public List<Reaction> findPostReactions(int id) {
		List<Reaction> reacts = em.createQuery("select r from Reaction r where r.reactedPost.id=:Id",Reaction.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}



	@Override
	public List<Reaction> findUserReactions(int id) {
		List<Reaction> reacts = em.createQuery("select r from Reaction r where r.reactingUser.id=:Id",Reaction.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}

	
	@Override
	public boolean userAlreadyReacted(int idUser, int idPost) {
		List<Reaction> react = em.createQuery("select r from Reaction r where r.reactingUser.id=:IdU AND r.reactedPost.id=:IdP",Reaction.class)
				.setParameter("IdU", idUser).setParameter("IdP", idPost ).getResultList();
		return (!react.isEmpty());
	}

	@Override
	public List<Reaction> findReactionsByDate(Timestamp date) {
		return null;
	}


}
