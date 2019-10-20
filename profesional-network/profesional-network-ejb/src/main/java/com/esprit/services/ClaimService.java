package com.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IClaimServiceLocal;
import com.esprit.Iservice.IClaimServiceRemote;
import com.esprit.beans.Claim;

@Stateless
public class ClaimService implements IClaimServiceLocal,IClaimServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@Override
	public void addClaim(Claim c) {
		
		em.persist(c);
		System.out.println("Out of addClaim" + c.getId());
	}

	@Override
	public void treatClaim(Claim c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Claim> afficherClaim() {
		List<Claim> claims = em.createQuery("from Claim", Claim.class).getResultList(); 
		System.out.println("Out of findAllUsers : "); 
		return claims; 
	}

	@Override
	public void removeClaim(Claim c) {
		
		em.remove(em.find(Claim.class, c.getId()));
		System.out.println("Out of Claim removed : "); 
		
	}

}
