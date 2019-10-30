package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.ICommentServiceLocal;
import com.esprit.Iservice.ICommentServiceRemote;
import com.esprit.beans.Comment;
import com.esprit.beans.Message;
import com.esprit.beans.Post;
import com.esprit.beans.Quiz;
import com.esprit.beans.Comment;
import com.esprit.beans.Comment;
import com.esprit.beans.User;


@Stateless
@LocalBean
public class CommentService implements ICommentServiceLocal,ICommentServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addComment(String content,Timestamp date
						,int idPost, int idUser) {
		

		User commenter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		Comment com = new Comment();
		com.setDate(date);
		com.setContent(content);
		com.setCommentedPost(post);
		com.setCommentingUser(commenter);
			
		
		if ((com.getCommentedPost() == null )){
			System.out.println("The post doesn't exist.");}
			else {
				em.persist(com);
			}

		}
		

	@Override
	public void updateComment(String content,Timestamp date
			,int idPost, int idUser) {
		

		User commenter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		Comment com = new Comment();
		com.setDate(date);
		com.setContent(content);
		com.setCommentedPost(post);
		com.setCommentingUser(commenter);
			
		
		if ((com.getCommentedPost() == null )){
			System.out.println("The post doesn't exist.");}
			else {
				em.merge(com);
			}

	}

	@Override
	public void deleteComment(int id) {
		em.remove(em.find(Comment.class, id));

	}

	@Override
	public Comment findComment(int id) {
		return em.find(Comment.class, id);
	}


	@Override
	public List<Comment> findPostComments(int id) {
		List<Comment> reacts = em.createQuery("select c from Comment c where c.post.id=:Id",Comment.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}


	@Override
	public List<Comment> findUserComments(int id) {
		List<Comment> reacts = em.createQuery("select c from Comment c where c.commenter.id=:Id",Comment.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}


	@Override
	public List<Comment> findUserCommentsOnPost(int idUser, int idPost) {
		List<Comment> reacts = em.createQuery("select c from Comment c where c.commenter.id=:IdU AND c.post.id=:IdP",Comment.class)
				.setParameter("IdU", idUser).setParameter("IdP", idPost ).getResultList();
		return reacts;
	}
	

	@Override
	public List<Comment> findCommentsByDate(Timestamp date) {
		return null;
	}



}
