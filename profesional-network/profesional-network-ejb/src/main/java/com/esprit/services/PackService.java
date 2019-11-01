package com.esprit.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IPackServiceLocal;
import com.esprit.Iservice.IPackServiceRemote;

import com.esprit.beans.Pack;
import com.esprit.beans.User;
import com.esprit.beans.UserPack;
import com.esprit.enums.PackType;

@Stateless
@LocalBean
public class PackService implements IPackServiceLocal, IPackServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addPack(String title, String description, double price, PackType type) {
		// @SuppressWarnings("deprecation")
		Pack a = new Pack();

		a.setTitle(title);
		a.setDescription(description);
		a.setPrice(price);
		a.setType(type);
		em.persist(a);

	}

	@Override
	public void addReduction(int id, double reduction, Date start, Date end) {
		Pack p = em.find(Pack.class, id);
		Pack p1 = new Pack();
		p1.setStartDate(start);
		p1.setEndDate(end);
		p1.setReduction(reduction);
		double price = p.getPrice() * (1 - reduction);
		p1.setPrice(price);
		p1.setDescription(p.getDescription());
		p1.setTitle(p.getTitle());
		p1.setType(p.getType());
		em.persist(p1);
		
	}

	@Override
	public void updatePack(int id, String title, PackType type, double price, Date startDate, Date endDate,
			double reduction) {
		Pack p = em.find(Pack.class, id);
		p.setTitle(title);
		p.setType(type);
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		p.setReduction(reduction);
		p.setPrice(price);

	}

	@Override
	public void deletePack(int id) {
		em.remove(em.find(Pack.class, id));

	}

	@Override
	public List<Pack> allpacks() {
		TypedQuery<Pack> query = em.createQuery("select e from Pack e", Pack.class);
		List<Pack> results = query.getResultList();
		return results;
	}

	@Override
	public Pack getPack(int id) {
		return findPackById(id);
	}
	
	@Override
	public void payPack(int id, int packId) {
		User u = em.find(User.class, id);
		Pack p = em.find(Pack.class, packId);
		System.out.println("/**********************************"+u.getId()+" "+ p.getId());
		UserPack up = new UserPack();
		u.setPpremimum(true);
		up.setPack(p);
		up.setUser(u);
		System.out.println("*************"+up.getPack()+" "+up.getUser());
		up.setValid(true);
		
		up.setStartDate(p.getStartDate());
		up.setEndDate(p.getEndDate());
		em.persist(up);
		u.getPacks().add(up);
		p.getUsers().add(up);

	}
	public UserPack getUserPack(int id)
	{
		
		TypedQuery<UserPack> query = em.createQuery("select e from UserPack e where e.", UserPack.class);
		UserPack results = query.getSingleResult();
		return results;
		
	}

	public double daysLeft(Date debut, Date fin) {

		long diff = debut.getTime() - fin.getTime();
		float res = (diff / (1000 * 60 * 60 * 24));

		return (double) res;
	}

	@Override
	public Pack findPackById(int id) {
		return em.find(Pack.class, id);
	}

	public List<User> getUsers() {

		TypedQuery<User> query = em.createQuery("select e from User e", User.class);
		List<User> results = query.getResultList();

		return results;
	}

}
