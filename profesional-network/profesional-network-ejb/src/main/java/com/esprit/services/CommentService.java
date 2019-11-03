package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.ICommentServiceLocal;
import com.esprit.Iservice.ICommentServiceRemote;
import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;


@Stateless
@LocalBean
public class CommentService implements ICommentServiceLocal,ICommentServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@EJB
	FollowingService followingservice = new FollowingService();
	@EJB
	NotificationService notificationservice = new NotificationService();

	@Override
	public boolean addComment(int idUser,int idPost,String content) {

		User commenter = em.find(User.class,idUser);
		Post post = em.find(Post.class,idPost);
		if (commenter == null || post == null) {
			return false;
		}
		Comment com = new Comment();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		com.setDate(date);
		com.setContent(content);
		com.setCommentedPost(post);
		com.setCommentingUser(commenter);
		em.persist(com);
		em.flush();
		
		//Automatically add commenter to following the post
		followingservice.followPost(idUser, idPost);
		
		// notifying the post followers about the new Comment

		List<User> followers = followingservice.PostFollowers(idPost);
		String notif_message = commenter.getFirstName()+" "+commenter.getLastName()+
				" commented on a post you follow.";
		if (followers!=null)
		{
		for (User follower : followers) {
			if (post.getUser().getId() == follower.getId())
			{
				notif_message = commenter.getFirstName()+" "+commenter.getLastName()+
						" commented on your Post.";
			}
			NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Comment;
			notificationservice.CreateNotification(follower.getId(),notif_message,type ,com.getCommentedPost().getId());
		}
		}
		
		return true;
		}


	@Override
	public boolean updateComment(int id,String content) {
		
		Comment com = em.find(Comment.class,id);
		if (com == null ) {
			return false;
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		com.setDate(date);
		com.setContent(content);
		return true;
	}

	@Override
	public boolean deleteComment(int id) {
		Comment com = em.find(Comment.class, id);
		if (com == null)
		{
			return false;
		}
		em.remove(com);
		return true;

	}

	@Override
	public Comment findComment(int id) {
		return em.find(Comment.class, id);
	}


	@Override
	public List<Comment> findPostComments(int id) {
		List<Comment> reacts = em.createQuery("select c from Comment c where c.commentedPost.id=:Id",Comment.class)
				.setParameter("Id", id).getResultList();
		return reacts;
	}


	@Override
	public List<Comment> findUserComments(int id) {
		List<Comment> reacts = em.createQuery("select c from Comment c where c.commentingUser.id=:Id",Comment.class)
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
