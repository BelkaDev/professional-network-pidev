package com.esprit.Iservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.EnterpriseEvent;

@Remote
public interface EnterpriseEventServiceRemote {

	public int AddEnterpriseEvent(EnterpriseEvent event,int enterpriseId );
	public void DeleteEnterpriseEvent(int id);
	public int ModifyEnterpriseEvent(int id, String title, String place,Date sdate, Date edate, String descrip, int minpart, int maxpart,float eprice  );
	public List<EnterpriseEvent> getAllEnterpriseEvent();
	public EnterpriseEvent geteventById(int EEid);
}
