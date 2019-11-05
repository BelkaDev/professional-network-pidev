package com.esprit.Iservice;



import java.util.List;

import javax.ejb.Local;

import com.esprit.enums.Tags;

@Local
public interface IInterestsServiceLocal {

	 void userAddInterest(String interest);
	 void offerAddInterest(int idOffer,String interest);
	 public void candidateAddInterest(String interest,int id);

}
