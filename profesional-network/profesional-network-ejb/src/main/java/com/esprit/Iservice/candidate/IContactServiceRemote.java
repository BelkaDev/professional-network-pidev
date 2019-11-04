package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.Enterprise;
import com.esprit.beans.JobOffer;
import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Contact;
import com.esprit.beans.candidate.Subscription;

@Remote
public interface IContactServiceRemote {

	public void requestConnection(int senderId,int receiverId);
	public void acceptRequest(int requestId);
	public void cancelRequest(int requestId);
	public void blockCandidate(int blocker,int toBlock);
	public Set<Contact> getRequests(int receiverId);
	public Set<Candidate> getFriendsList(int candidateId);
	public List<Candidate> searchForCandidates(String criteria);
	public List<Enterprise> searchForEnterprise(String criteria);
	public List<JobOffer> getOffersByEnterprise(int enterpriseId);
	public List<Candidate> getContactsInEnterprise(int candidateId,int enterpriseId);
	
	public void subscribeToEnterprise(int candidateId,int enterpriseId);
	public Set<Subscription> getSubscriptions(int candidateId);
	public void cancelSubscription(int subscriptionId);
}
