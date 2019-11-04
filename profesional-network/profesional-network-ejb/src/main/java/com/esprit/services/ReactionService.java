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
		
		User reacter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		Reaction react = new Reaction();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		react.setDate(date);
		react.setType(type);
		react.setReactingUser(reacter);
		react.setReactedPost(post);
			
		
		if ((react.getReactedPost() == null && react.getReactingUser() == null)){
			return false;}
			else {
				em.persist(react);
			}
		
		// notifying the post followers about the new Reaction

		List<User> followers = followingservice.PostFollowers(idPost);
		String notif_message = reacter.getFirstName()+" "+reacter.getLastName()+
				" reacted on a post you follow.";
		
		if (followers!=null)
		{
		for (User follower : followers) {
			if (post.getUser().getId() == follower.getId())
			{
				notif_message = reacter.getFirstName()+" "+reacter.getLastName()+
						" reactmented on your Post.";
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
		List<Reaction> reacts = em.createQuery("select r from Reaction r where r.post.id=:Id",Reaction.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}



	@Override
	public List<Reaction> findUserReactions(int id) {
		List<Reaction> reacts = em.createQuery("select r from Reaction r where r.reacter.id=:Id",Reaction.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}

	
	@Override
	public boolean userAlreadyReacted(int idUser, int idPost) {
		Reaction react = em.createQuery("select r from Reaction r where r.reacter.id=:IdU AND r.post.id=:IdP",Reaction.class)
				.setParameter("IdU", idUser).setParameter("IdP", idPost ).getSingleResult();
		return (react!=null);
	}

	@Override
	public List<Reaction> findReactionsByDate(Timestamp date) {
		return null;
	}


}
