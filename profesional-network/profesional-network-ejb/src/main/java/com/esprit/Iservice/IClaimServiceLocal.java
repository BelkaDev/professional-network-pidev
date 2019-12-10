package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Claim;
import com.esprit.beans.State;
import com.esprit.beans.User;


@Local
public interface IClaimServiceLocal {
	
	public void addClaim(String description,String type,int Whoclaim,int claimOn);
	public void treatClaim(int id,State etat);
	public List<Claim> allClaims();
	public void removeClaim(int id);	
	public Claim findClaimById(int id);
	public List<Claim> getClaimTreated();
	public List<Claim> getClaimUntreated();
	public List<Claim> getClaimInProgress();
	public String findUserInClaimById(int id);
	public User findUser(int id);
	
}
