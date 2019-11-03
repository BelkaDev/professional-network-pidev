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
<<<<<<< HEAD:professional-network-pidev/profesional-network/profesional-network-ejb/src/main/java/com/esprit/Iservice/EnterpriseSeviceRemote.java
	public Enterprise getenterpriseById(int Eid);
=======
	
>>>>>>> 7ad7a43e382664e2df6cad4821e459f540aa5abb:profesional-network/profesional-network-ejb/src/main/java/com/esprit/Iservice/EnterpriseSeviceRemote.java
	
	
	
	
	
}
