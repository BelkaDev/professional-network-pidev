package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.Interview;

@Remote
public interface IInterviewServiceRemote {
	public boolean isWeekend(String date);

	public boolean isOlderThanToday(String date);

	public boolean setDate(int interview_id, String date,int candidate_id,int joboffer_id);

	public void CancelInterview(int interview_id);

	public Interview displayInterview(int interview_id);
	
	public boolean validTime(String time);
	
	public boolean setTime(int interview_id,String time);
}
