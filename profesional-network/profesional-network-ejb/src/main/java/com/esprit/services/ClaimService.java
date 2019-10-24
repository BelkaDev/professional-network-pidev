package com.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IClaimServiceLocal;
import com.esprit.Iservice.IClaimServiceRemote;
import com.esprit.beans.Claim;
import com.esprit.beans.Etat;
import com.esprit.beans.User;




@Stateless
@LocalBean
public class ClaimService implements IClaimServiceLocal,IClaimServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addClaim(int id, String description, Etat etat, String type, int Whoclaim, int claimOn) {
		User whoClaim=em.find(User.class, Whoclaim);
		User claimsOn=em.find(User.class, claimOn);
		Claim c =new Claim();
		c.setId(id);
		c.setDescription(description);
		c.setEtat(etat);
		c.setType(type);
		c.setClaimOn(claimsOn);
		c.setWhoClaim(whoClaim);
		em.persist(c);
		
	}

	@Override
	public void treatClaim(int id,Etat etat) {
	System.out.println("IN : Update Calim");
		
		Claim p = em.find(Claim.class, id);
		
	    p.setEtat(Etat.streated);
	    
					System.out.println("OUUUT : Update Claim");
					
		
	}

	@Override
	public List<Claim> afficherClaim() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}

	@Override
	public void removeClaim(int id) {
		Query q = em.createQuery("DELETE FROM Claim p WHERE p.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
		
	}
	@Override
	public Claim findClaimById(int id)
	{
		Claim c = em.find(Claim.class, id);
		return c;
	
	}

	@Override
	public List<Claim> afficherClaimTreated() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.etat= 0", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}

	@Override
	public List<Claim> afficherClaimUntreated() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.etat= 1", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}

	@Override
	public List<Claim> afficherClaimInProgress() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.etat= 2", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}
	
	

}
