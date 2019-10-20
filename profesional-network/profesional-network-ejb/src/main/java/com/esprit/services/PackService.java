package com.esprit.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IPackServiceLocal;
import com.esprit.Iservice.IPackServiceRemote;
import com.esprit.beans.Pack;
import com.esprit.beans.User;

@Stateful
public class PackService implements IPackServiceLocal,IPackServiceRemote{
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	@Override
	public void ajouterPack(Pack p) {
		Set<User> users=p.getListUser();
		@SuppressWarnings("deprecation")
		Date date = new Date(2019,02,20);
		p.setDatedebut(date);
		p.setDatefin(date);
		User u =new User();
		u.setId(1);
		users.add(u);
		p.setListUser(users);
		p.setReduction(0);
		
		em.persist(p);
		
	}

	@Override
	public void ajouterReduction(Pack p, double reduction, Date debut, Date fin) {
		Pack a = em.find(Pack.class, p.getId());
		a.setDatedebut(debut);
		a.setDatefin(fin);
		a.setReduction(reduction);
		a.setPrix(p.getPrix()*(1-reduction));
	}

	@Override
	public void modifierPack(Pack p) {
		Pack a = em.find(Pack.class, p.getId());
		a.setDatedebut(p.getDatedebut());
		a.setDatefin(p.getDatefin());
		a.setDescription(p.getDescription());
		a.setPrix(p.getPrix());
		a.setReduction(p.getReduction());
		a.setReduction(p.getReduction());
		a.setTitre(p.getTitre());
		a.setType(p.getType());
	}

	@Override
	public void supprimerPack(Pack p) {
		Pack a=em.find(Pack.class, p.getId());
		em.remove(a);
		
	}

	@Override
	public List<Pack> afficherPacks() {
		List<Pack> packs = em.createQuery("from Utilisateur", Pack.class).getResultList(); 
		return packs;
		
	}

	@Override
	public Pack afficherPack(Pack p) {
	Pack a=em.find(Pack.class,p.getId());
	return a;
		
	}

}
