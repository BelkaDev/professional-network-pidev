package com.esprit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.EnterpriseUserServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.EnterpriseUser;
import com.esprit.utils.BCrypt;
import com.esprit.utils.SendingMail;

@Stateless
@LocalBean
public class EnterpriseUserService implements EnterpriseUserServiceRemote {

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	
	
	@Override
	public int AddEnterpriseUser(EnterpriseUser Euser,int enterpriseId) {
		Enterprise enterpriseManagedEntity = em.find(Enterprise.class, enterpriseId);
		Euser.setEnterprise(enterpriseManagedEntity);
		
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(Euser.getPassword(), salt);
		Euser.setPassword(paass);
		
		String contenu ="Votre inscription est bien enregistée!!";
		SendingMail sm = new SendingMail(contenu, Euser.getEmail(), "inscription");
		SendingMail.envoyer();
		
		
		em.persist(Euser);
		return Euser.getEUid();
	}
	
	

	@Override
	public boolean authenticate(String username, String password) {
		TypedQuery<EnterpriseUser> q=  em.createQuery("SELECT eu from EnterpriseUser eu where eu.username=:username ",EnterpriseUser.class); 
		q.setParameter("username", username);
		
		EnterpriseUser Euser=q.getSingleResult();
		if(BCrypt.checkpw(password,Euser.getPassword() ))
			return true;
		else
			return false;
	}

	@Override
	public void updateToken(String username, String token) {
		TypedQuery<EnterpriseUser> q=  em.createQuery("SELECT ea from EnterpriseUser ea where ea.username= :username ",EnterpriseUser.class);
		q.setParameter("username", username);
		EnterpriseUser u=q.getSingleResult();
		u.setToken(token);
		em.merge(u);
	}
	
	
	public void Logout(int EuserId) {
		EnterpriseUser Euser =em.find(EnterpriseUser.class, EuserId);
		Euser.setToken(null);
		em.merge(Euser);
		
	}
	

}
