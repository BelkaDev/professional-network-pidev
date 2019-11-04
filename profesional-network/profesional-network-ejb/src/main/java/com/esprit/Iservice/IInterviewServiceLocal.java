package com.esprit.Iservice;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import com.esprit.beans.Interview;

@Local
public interface IInterviewServiceLocal {
	public boolean isWeekend(String date);

	public boolean isOlderThanToday(String date);

	public boolean setDate(int interview_id, String date, int candidate_id, int joboffer_id);

	public void CancelInterview(int interview_id);

	public Interview displayInterview(int interview_id);

	public boolean validTime(String time);

	public boolean setTime(int interview_id, String time);

	public void setStateAccepted(int interview_id);

	public void setStateRejected(int interview_id);

	public void acceptCandidate(int joboffer_id);

	public Set<Interview> getInterviewsForOffer(int jobOffer_id);

	public void sendNotifToCandidates(int jobOffer_id);
	
	public void setScore(int interview_id,double score);

}
