package com.esprit.services;


import java.util.Date;
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
import com.esprit.beans.State;
import com.esprit.beans.User;
import com.esprit.utils.UserSession;




@Stateless
@LocalBean
public class ClaimService implements IClaimServiceLocal,IClaimServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@SuppressWarnings("deprecation")
	@Override
	public void addClaim(String description, String type, int Whoclaim, int claimOn) {
		UserSession user=UserSession.getInstance();
		User whoClaim=em.find(User.class, user.getId());
		User claimsOn=em.find(User.class, claimOn);
		Claim c =new Claim();
		Date date=new Date();
		c.setDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDay()));
		c.setDescription(description);
		c.setState(State.untreated);
		c.setType("intreated");
		c.setClaimsOn(claimsOn);
		c.setWhoClaim(whoClaim);
		em.persist(c);
		
	}

	@Override
	public void treatClaim(int id,State etat) {
	System.out.println("IN : Update Calim");
		
		Claim p = em.find(Claim.class, id);
		
	    p.setState(etat);
	    
					System.out.println("OUUUT : Update Claim");
					
		
	}

	@Override
	public List<Claim> allClaims() {
		
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
	public List<Claim> getClaimTreated() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.state= 0", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}

	@Override
	public List<Claim> getClaimUntreated() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.state= 1", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}

	@Override
	public List<Claim> getClaimInProgress() {
		TypedQuery<Claim> query = em.createQuery(
			      "select e from Claim e where e.state= 2", Claim.class);
			  List<Claim> results = query.getResultList();
		return results;
	}
	
	@Override
	public String findUserInClaimById(int id)
	{
		User u=em.find(User.class, id);
		return u.getFirstName()+" "+u.getLastName();
	}
	
	

}
