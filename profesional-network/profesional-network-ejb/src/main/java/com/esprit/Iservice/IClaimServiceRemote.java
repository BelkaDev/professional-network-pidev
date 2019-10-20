package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.Claim;

@Remote
public interface IClaimServiceRemote {
	public void addClaim(Claim c);
	public void treatClaim(Claim c);
	public List<Claim> afficherClaim();
	public void removeClaim(Claim c);
}
