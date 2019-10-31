package com.esprit.Iservice;


import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;

@Remote
public interface EnterpriseSeviceRemote {

	public int AddEnterprise(Enterprise enterprise);
	public void DeleteEnterprise(int id);
	public int ModifyEnterprise(int id, String name, String domain,String location,int empnumber, String descrip);
	
	
	
	
	
	
}
