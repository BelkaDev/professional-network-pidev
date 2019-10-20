package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Claim;

@Local
public interface IClaimServiceLocal {
	
	public void addClaim(Claim c);
	public void treatClaim(Claim c);
	public List<Claim> afficherClaim();
	public void removeClaim(Claim c);
	
}
