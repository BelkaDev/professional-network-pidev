package com.esprit.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.Iservice.IUserServiceLocal;
import com.esprit.Iservice.IUserServiceRemote;
import com.esprit.beans.Answer;
import com.esprit.beans.User;

@Stateless
@LocalBean
public class UserService implements IUserServiceLocal, IUserServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public void addUser(User usr) {
		em.persist(usr);
	}

	@Override
	public void deleteUser(int idUser) {
		em.remove(em.find(User.class, idUser));
	}


	@Override
	public User findUser(int idUser) {
	User user= em.find(User.class, idUser);
	return user;

	}

}
