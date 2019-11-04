package com.esprit.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IInterestsServiceLocal;
import com.esprit.beans.User;
import com.esprit.beans.JobOffer;
import com.esprit.utils.UserSession;
@Stateless
@LocalBean

public class InterestsService implements IInterestsServiceLocal{
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;


	@Override
	public void userAddInterest(String interest) {
		User user= em.find(User.class, UserSession.getInstance().getId());
		user.setInterests(interest);
		em.merge(user);
	}

	@Override
	public void offerAddInterest(int idOffer,String interest) {
		JobOffer offer= em.find(JobOffer.class,idOffer);
		offer.setInterests(interest);
	}


}
