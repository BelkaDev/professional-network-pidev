package com.esprit.Iservice.candidate;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import com.esprit.beans.candidate.Candidate;
import com.esprit.beans.candidate.Contact;

@Remote
public interface IContactServiceRemote {

	public void requestConnection(int senderId,int receiverId);
	public void acceptRequest(int requestId);
	public void cancelRequest(int requestId);
	public void blockCandidate(int blocker,int toBlock);
	public Set<Contact> getRequests(int receiverId);
	public Set<Candidate> getFriendsList(int candidateId);
	public List<Candidate> searchForCandidates(String criteria);
}
