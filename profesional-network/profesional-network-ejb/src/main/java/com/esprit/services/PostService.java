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
import com.esprit.beans.Reaction;
import com.esprit.beans.User;
import com.esprit.enums.POST_TYPE;

@Stateless
@LocalBean
public class PostService implements IPostServiceLocal,IPostServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addPost(String content, Timestamp date ,int idUser, POST_TYPE typePost) {


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
	public void updatePost(String content, Timestamp date ,int idUser, POST_TYPE typePost) {

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
		return em
				.createQuery("select post from Post post", Post.class)
				.getResultList();
	}


	@Override
	public List<Post> findAllUserPosts(int id) {
		List<Post> posts = em.createQuery("select post from Post post where usr.id=:Id",Post.class)
				.setParameter("Id", id).getResultList();
		return posts;
	}


	@Override
	public Post find(int id) {
		Post p = em.find(Post.class, id);
		return p;
	}


	@Override
	public List<Post> findFriendsPosts(int Userid) {
		return null;
	}
	

}
