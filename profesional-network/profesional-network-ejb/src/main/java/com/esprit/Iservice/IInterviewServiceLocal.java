package com.esprit.Iservice;

import javax.ejb.Local;

import com.esprit.beans.Interview;

@Local
public interface IInterviewServiceLocal {
	public boolean isWeekend(String date);

	public boolean isOlderThanToday(String date);

	public boolean setDate(int interview_id, String date,int candidate_id,int joboffer_id);

	public void CancelInterview(int interview_id);

	public Interview displayInterview(int interview_id);

	public boolean validTime(String time);
	
	public boolean setTime(int interview_id,String time);

}
