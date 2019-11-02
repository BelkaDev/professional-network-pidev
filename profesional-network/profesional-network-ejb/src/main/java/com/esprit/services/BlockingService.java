package com.esprit.services;



import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IBlacklistServiceLocal;
import com.esprit.Iservice.IBlacklistServiceRemote;
import com.esprit.beans.Blacklist;
import com.esprit.beans.User;

import java.util.List;


@Stateless
@LocalBean
public class BlockingService implements IBlacklistServiceLocal,IBlacklistServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;


	@Override
	public void blockUser(int idBlocking, int idBlocked) {
		Blacklist blacklist = new Blacklist();
		blacklist.setBlockedId(idBlocked);
		User blocker = em.find(User.class,idBlocking);
		blacklist.setBlocking(blocker);
		em.persist(blacklist);
		
	}

	@Override
	public void unblockUser(int idBlocking, int idBlocked) {
		int id = em.createQuery("select b from Blacklist b where b.idBlocked=:IdBlocked AND r.blocking.id=:IdBlocking "
				+ "AND follower.target=:type",Blacklist.class)
				.setParameter("IdBlocked", idBlocked).setParameter("IdBlocking", idBlocking ).getSingleResult().getId();		
		em.remove(em.find(Blacklist.class, id));
	}

	@Override
	public List<Blacklist> blockedBy(int idUser) {
		return em.createQuery("select b from Blacklist b where b.blocking.id=:IdU "
				,Blacklist.class).setParameter("IdU", idUser ).getResultList();
	}
}

