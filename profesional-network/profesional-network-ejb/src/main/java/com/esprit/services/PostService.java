package com.esprit.services;


import java.sql.Timestamp;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IPostServiceLocal;
import com.esprit.Iservice.IPostServiceRemote;
import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.Posts;

@Stateless
@LocalBean
public class PostService implements IPostServiceLocal,IPostServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addPost(String content, Timestamp date ,int idUser, Posts typePost) {


		User poster = em.find(User.class,idUser);
		com.esprit.beans.Post post = new Post();
		post.setDate(date);
		post.setContent(content);
		post.setUser(poster);
		post.setType(typePost);
			
		
		if ((post.getUser() == null )){
			System.out.println("The user doesn't exist.");}
			else {
				em.persist(post);
			}
		}
		

	@Override
	public void updatePost(String content, Timestamp date ,int idUser, Posts typePost) {

		User poster = em.find(User.class,idUser);
		com.esprit.beans.Post post = new Post();
		post.setDate(date);
		post.setContent(content);
		post.setUser(poster);
		post.setType(typePost);
			
		
		if ((post.getUser() == null )){
			System.out.println("The user doesn't exist.");}
			else {
				em.merge(post);
			}
	}

	@Override
	public void deletePost(int id) {
		em.remove(em.find(Post.class, id));

	}

	@Override
	public Post findPost(int id) {
		return em.find(Post.class, id);
	}


	@Override
	public List<Post> findAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Post> findAllUserPosts(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Post find(int id) {
		Post p = em.find(Post.class, id);

		return p;
	}


	@Override
	public List<Post> findFriendsPosts(int Userid) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
