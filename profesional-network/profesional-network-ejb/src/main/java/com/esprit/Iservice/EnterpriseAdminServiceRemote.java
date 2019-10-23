package com.esprit.Iservice;


import javax.ejb.Remote;

import com.esprit.beans.EnterpriseAdmin;

@Remote
public interface EnterpriseAdminServiceRemote {

	public int AddEnterpriseAdmin(EnterpriseAdmin Eadmin);

}
