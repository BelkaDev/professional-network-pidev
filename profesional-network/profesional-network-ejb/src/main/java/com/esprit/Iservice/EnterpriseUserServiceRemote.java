package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.EnterpriseUser;

@Remote
public interface EnterpriseUserServiceRemote {

	
	public int AddEnterpriseUser(EnterpriseUser Euser, int enterpriseId);
	public boolean authenticate(String username, String password);
	public void updateToken(String username, String token);
	
}
