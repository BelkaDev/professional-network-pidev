package com.esprit.services;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IPostServiceLocal;
import com.esprit.Iservice.IPostServiceRemote;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.POST_TYPE;

@Stateless
@LocalBean
public class PostService implements IPostServiceLocal,IPostServiceRemote {
	

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@EJB
	FollowingService followingservice = new FollowingService();
	@EJB
	NotificationService notificationservice = new NotificationService();

	@Override
	public void addPost(int idUser,String content, POST_TYPE typePost) {


		User poster = em.find(User.class,idUser);
		Post post = new Post();
		
		Timestamp date = new Timestamp(System.currentTimeMillis());
		post.setDate(date);
		post.setContent(content);
		post.setUser(poster);
		post.setAuthor(poster.getId());
		post.setType(typePost);	
		if ((post.getUser() == null )){
			System.out.println("The user doesn't exist.");}
		else {
				em.persist(post);
				em.flush();
			 }
		// notifying followers about the new Post
		int idPost = post.getId();
		List<User> followers = followingservice.UserFollowers(idUser);
		String notif_message = poster.getFirstName()+" "+poster.getLastName()+
				" shared a new Post.";

		for (User usr : followers) {
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Follow;
		notificationservice.CreateNotification(usr.getId(),notif_message,type ,idPost, poster.getId());
		}
		
		}

	@Override
	public boolean updatePost(int id,String content,POST_TYPE typePost) {

		Post post = em.find(Post.class,id);
		if (post == null ) {
			return false;
		}
		Timestamp date = new Timestamp(System.currentTimeMillis());
		post.setContent(content +" [edited on "+" "+new SimpleDateFormat("dd/MM/YY - HH:mm").format(date)+"]");
		post.setType(typePost);
		em.merge(post);
		return true;
	}

	@Override
	public boolean deletePost(int id) {
		Post post = em.find(Post.class, id);
		if (post == null)
		{
			return false;
		}
		em.remove(post);
		
		// other posts that share this
		List<Post> posts = sharingPost(id);
		for (Post p : posts) {
			p.setType(POST_TYPE.REMOVED);
			p.setContent("The original post wasn't retrieved.");
			em.merge(p);
		}
		return true;
	}



	@Override
	public boolean sharePost(int idPost,int idUser) {
		Post sharedPost = em.find(Post.class, idPost);
		User user = em.find(User.class, idUser);
		if (sharedPost == null) {
			return false;
		}
		Post post = new Post();
		post.setUser(user);
		post.setAuthor(sharedPost.getAuthor());
		Timestamp date = new Timestamp(System.currentTimeMillis());
		post.setContent(sharedPost.getContent()+" [shared on "+new SimpleDateFormat("dd/MM/YY - HH:mm").format(date)+"]");
		post.setType(sharedPost.getType());

		post.setDate(date);
		em.persist(post);
		return true;
	}

	
	@Override
	public Post findPost(int id) {
		return em.find(Post.class, id);
	}


	@Override
	public List<Post> findAllPosts() {
		return em
				.createQuery("select post from Post post", Post.class)
				.getResultList();
	}


	@Override
	public List<Post> findAllUserPosts(int id) {
		List<Post> posts = em.createQuery("select post from Post post where post.user.id=:Id",Post.class)
				.setParameter("Id", id).getResultList();
		return posts;
	}



	@Override
	public List<Post> findFriendsPosts(int Userid) {
		return null;
	}

	
	@Override
	public List<Post> sharingPost(int id) {
		List<Post> posts = em.createQuery("select post from Post post where post.author=:Id AND post.user.id<>:Id",Post.class)
				.setParameter("Id", id).getResultList();
		return posts;
	}
	
	@Override
	public boolean checkPostType(POST_TYPE postType) {
	       POST_TYPE[] types = POST_TYPE.values();
	       for (POST_TYPE type : types)
	           if (type.equals(postType))
	               return true;
	       return false;
	}
}
	

