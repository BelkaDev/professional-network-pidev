package com.esprit.Iservice;


import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseEvent;
import com.esprit.beans.FileUpload;
//import com.esprit.beans.candidate.Subscription;

@Remote
public interface EnterpriseSeviceRemote {

	public int AddEnterprise(Enterprise enterprise,int userid,String Filename);
	public void DeleteEnterprise(int id);
	public int ModifyEnterprise(int id, String name, String domain,String location,int empnumber, String descrip);
	public Enterprise getenterpriseById(int Eid);
	public List<Enterprise> getAllEnterprise();
	//public List<Subscription> getsubscriberByEnt(int entid);
	
	
	
	




}
