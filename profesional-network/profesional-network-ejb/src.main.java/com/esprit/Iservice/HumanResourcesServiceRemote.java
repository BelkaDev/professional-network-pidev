package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.HumanResources;

@Remote
public interface HumanResourcesServiceRemote {
	
	public int AddHumanResources(HumanResources Hresources);
	


}
