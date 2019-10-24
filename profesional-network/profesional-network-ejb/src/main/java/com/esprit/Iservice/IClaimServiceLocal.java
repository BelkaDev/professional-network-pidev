package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Claim;
import com.esprit.beans.Etat;

@Local
public interface IClaimServiceLocal {
	
	public void addClaim(int id,String description,Etat etat,String type,int Whoclaim,int claimOn);
	public void treatClaim(int id,Etat etat);
	public List<Claim> afficherClaim();
	public void removeClaim(int id);	
	public Claim findClaimById(int id);
	public List<Claim> afficherClaimTreated();
	public List<Claim> afficherClaimUntreated();
	public List<Claim> afficherClaimInProgress();
	
}
