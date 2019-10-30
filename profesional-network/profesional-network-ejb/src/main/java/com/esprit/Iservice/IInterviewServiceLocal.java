package com.esprit.Iservice;

import javax.ejb.Local;

@Local
public interface IInterviewServiceLocal {
	public boolean isWeekend(String date);

	public boolean isOlderThanToday(String date);

	public boolean setDate(int interview_id, String date);

	public void CancelInterview(int interview_id);

}
