package com.esprit.services;



import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IFollowingServiceLocal;
import com.esprit.Iservice.IFollowingServiceRemote;
import com.esprit.beans.Following;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TARGET;


import java.util.List;

@Stateless
@LocalBean
public class FollowingService implements IFollowingServiceLocal,IFollowingServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void followPost(int idUser, int idPost) {
		Following following = new Following();
		following.setTarget(NOTIFICATION_TARGET.Post);
		following.setTargetId(idPost);
		User follower = em.find(User.class,idUser);
		following.setFollower(follower);
		em.persist(following);
	}

	@Override
	public void followUser(int idFollower, int idFollowed) {
		Following following = new Following();
		following.setTarget(NOTIFICATION_TARGET.Profile);
		following.setTargetId(idFollowed);
		User follower = em.find(User.class,idFollower);
		following.setFollower(follower);
		em.persist(following);
		
	}

	@Override
	public void unFollowPost(int idUser, int idPost) {
		int id = em.createQuery("select f from Following f where f.targetId=:IdT AND r.follower.id=:IdU "
				+ "AND follower.target=:type",Following.class)
				.setParameter("IdU", idUser).setParameter("IdT", idPost ).setParameter("type",NOTIFICATION_TARGET.Post).getSingleResult().getId();		
		em.remove(em.find(Following.class, id));
	}

	@Override
	public void unFollowUser(int idFollower, int idFollowed) {
			int id = em.createQuery("select f from Following f where f.targetId=:IdP AND r.follower.id=:IdU "
					+ "AND follower.target=:type",Following.class)
					.setParameter("IdI", idFollower).setParameter("IdT", idFollowed ).setParameter("type",NOTIFICATION_TARGET.Profile).getSingleResult().getId();		
			em.remove(em.find(Following.class, id));
		
	}

	@Override
	public List<User> PostFollowers(int idPost) {
		return em.createQuery("select f.follower from Following f where f.targetId=:IdT "
				+ "AND f.target=:type",User.class).setParameter("IdT", idPost ).setParameter("type",NOTIFICATION_TARGET.Post).getResultList();
		
	}

	@Override
	public List<User> UserFollowers(int idUser) {
			return em.createQuery("select f.follower from Following f where f.targetId=:IdT "
					+ "AND f.target=:type",User.class).setParameter("IdT", idUser ).setParameter("type",NOTIFICATION_TARGET.Profile).getResultList();

	}

	
}