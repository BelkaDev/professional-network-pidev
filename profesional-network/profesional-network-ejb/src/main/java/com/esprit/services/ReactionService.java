package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IReactionServiceLocal;
import com.esprit.Iservice.IReactionServiceRemote;
import com.esprit.beans.Reaction;
import com.esprit.beans.Message;
import com.esprit.beans.Post;
import com.esprit.beans.Quiz;
import com.esprit.beans.User;
import com.esprit.enums.Reactions;


@Stateless
@LocalBean
public class ReactionService implements IReactionServiceLocal,IReactionServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addReaction(Timestamp date, Reactions type
			 ,int idPost,int idUser) {
		
		
		User reacter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		Reaction react = new Reaction();
		react.setDate(date);
		react.setType(type);
		react.setReactingUser(reacter);
		react.setReactedPost(post);
			
		
		if ((react.getReactedPost() == null && react.getReactingUser() == null)){
			System.out.println("can't add the reaction");}
			else {
				em.persist(react);
			}

		}
		
	@Override
	public void updateReaction(Timestamp date,Reactions type
			 ,int idPost,int idUser) {

		User reacter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		Reaction react = new Reaction();
		react.setDate(date);
		react.setType(type);
		react.setReactingUser(reacter);
		react.setReactedPost(post);
			
		
		if ((react.getReactedPost() == null && react.getReactingUser() == null)){
			System.out.println("error");}
			else {
				em.merge(react);
			}

	}

	@Override
	public void deleteReaction(int id) {
		em.remove(em.find(Reaction.class, id));

	}

	@Override
	public Reaction findReaction(int id) {
		return em.find(Reaction.class, id);
	}




	@Override
	public List<Reaction> findReactionsByDate(Timestamp date) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Reaction> findAllPostReactions(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Reaction> findAllUserReactions(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
