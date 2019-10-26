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
	public List<Comment> findAllPostComments(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Comment> findAllUserComments(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Comment> findCommentsByDate(Timestamp date) {
		// TODO Auto-generated method stub
		return null;
	}


}
