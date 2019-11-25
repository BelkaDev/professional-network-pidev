package com.esprit.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.esprit.Iservice.IUserServiceLocal;
import com.esprit.Iservice.IUserServiceRemote;
import com.esprit.beans.Enterprise;
import com.esprit.beans.User;
import com.esprit.beans.candidate.Candidate;
import com.esprit.enums.Role;
import com.esprit.utils.BCrypt;
import com.esprit.utils.SendingMail;
import com.esprit.utils.UserSession;
import com.esprit.utils.codeGen;

@Stateless
@LocalBean
public class UserService implements IUserServiceLocal, IUserServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;


	@Override
	public void addEnterpriseUser(User user, int EnterpriseId) {
		Enterprise enterpriseManagedEntity = em.find(Enterprise.class, EnterpriseId);
		user.setEnterprise(enterpriseManagedEntity);
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(user.getPassword(), salt);
		user.setPassword(paass);
		System.out.print(paass);
		user.setEnable(false);
		user.setConfirm(codeGen.getInstance().randomString(5));
		em.persist(user);
	}
	
	@Override
	public void addCandidateUser(User user, int candidateId) {
		
		
	}
	
	

	@Override
	public void addUser(User user) {
		 
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(user.getPassword(), salt);
		user.setPassword(paass);
		System.out.print(paass);
		user.setEnable(false);
		user.setConfirm(codeGen.getInstance().randomString(5));
		
		
		if(user.getRole()==Role.Candidate)
		{
			Candidate c = new Candidate();
			c.setEmail(user.getEmail());
			c.setFirstName(user.getFirstName());
			c.setLastName(user.getLastName());
			c.setPassword(user.getPassword());
			c.setGender(user.getGender());
			c.setBirthDate(user.getBirthDate());
			c.setAddress(user.getAddress());
			c.setUsername(user.getUsername());
			c.setRole(user.getRole());
			c.setInterests(user.getInterests());
			em.persist(c);
		}
		else
		{
			em.persist(user);
		}
		
			
	}
	
	
	@Override
	public boolean UsernameMailUnique(String username,String email)
	{
		TypedQuery<User> q=  em.createQuery("SELECT u from User u ",User.class); 
		
		List<User> user=q.getResultList();
		for(User u:user)
		{
		if((u.getUsername().equals(username))||(u.getEmail().equals(email)))		{
			return true;
		}
		
		}
		return false;
	}
	@Override
	public boolean authenticate(String username, String password) {

		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.username= :username ",User.class); 
		q.setParameter("username", username);
		
		User user=q.getSingleResult();
		
		if(BCrypt.checkpw(password, user.getPassword())&& user.isEnable()==true)
		{
			UserSession.getInstance().cleanUserSession();
			UserSession.getInstance(user);
			return true;
		}

		else
			return false;

	}
	@Override
	public void updateToken(String username,String token)
	{
		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.username= :username ",User.class);
		q.setParameter("username", username);
		User u=q.getSingleResult();
		u.setToken(token);
		em.merge(u);
	}

	@Override
	public void confirmCode(String code, int idUser) {
		User user=em.find(User.class, idUser);
		if(code.equals(user.getConfirm()))
		{
			user.setEnable(true);
			em.merge(user);
		}
		
	}
	@Override
	public void logout() {
		System.out.println("------------------------------------------- "+UserSession.getInstance().getUsername());
		User user=em.find(User.class, UserSession.getInstance().getId());
		user.setToken(null);
		em.merge(user);
		UserSession.getInstance().cleanUserSession();
		System.out.println("********************"+UserSession.getInstance().getId()+"***********************");
	}
	@Override
	public List<User> getAdmin() {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.role = :role", User.class);
		q.setParameter("role", Role.Admin);
		return (List<User>) q.getResultList();
	}
	@Override
	public User getUserById() {
		User user=em.find(User.class, UserSession.getInstance().getId());
		return user;
	}
	
	public List<User> getUserByRole(){
		
		return em.createQuery("select u from User u where u.role= :role", User.class)
				.setParameter("role", Role.Candidate)
				.getResultList();
		
	}
	@Override
	public void ResetingPassword(String userName) {
		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.username= :username ",User.class);
		q.setParameter("username", userName);
		User u=q.getSingleResult();
		System.out.println("************************"+u.getEmail());
		u.setEnable(false);		
		u.setConfirm(codeGen.getInstance().randomString(5));
		em.merge(u);
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(u.getConfirm(), salt);
		u.setPassword(paass);
		em.merge(u);
		
			
			String contenu ="Password Reset :Your new password: "+u.getConfirm();
			SendingMail sm = new SendingMail(contenu, u.getEmail(), "inscription");
			SendingMail.envoyer();
				
			
		
		
	}
	@Override
	public void UpdatePassword(String userName, String NewPassword) {
		TypedQuery<User> q=  em.createQuery("SELECT u from User u where u.username= :username ",User.class);
		q.setParameter("username", userName);
		User u=q.getSingleResult();
		
		
		
		String salt=BCrypt.gensalt();
		String paass=BCrypt.hashpw(NewPassword, salt);
		u.setPassword(paass);
		em.merge(u);
	}
	
	@Override
	public void disableMailNotifications()
	{
		User user=em.find(User.class, UserSession.getInstance().getId());
		user.setRecieveMailNotifs(false);
		em.merge(user);
	}
	@Override
	public void enableMailNotifications()
	
	{
		User user = getUserById();
		user.setRecieveMailNotifs(true);
		em.merge(user);
	}


	@Override
	public List<User> allUsers() {
		return em
				.createQuery("select user from User user", User.class)
				.getResultList();
	}

	@Override
	public List<String> fetchUserInterests(int idUser) {
		User user= em.find(User.class,idUser);
		String interests = user.getInterests();
		List<String> interestsList = new ArrayList<String>
		(Arrays.asList(interests.split(",")));
		System.out.print(interestsList);
		return interestsList;
	}



	
}
