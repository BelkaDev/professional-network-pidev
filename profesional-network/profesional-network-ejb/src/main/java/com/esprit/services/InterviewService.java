package com.esprit.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IInterviewServiceLocal;
import com.esprit.Iservice.IInterviewServiceRemote;
import com.esprit.beans.Interview;

@Stateless
@LocalBean
public class InterviewService implements IInterviewServiceLocal, IInterviewServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;

	@Override
	public boolean setDate(int interview_id, String date) {
		if (isWeekend(date)||isOlderThanToday(date)) {
			Interview in = em.find(Interview.class, interview_id);
			in.setDate(Date.valueOf(date));
			return true;
		}
		return false;
	}

	@Override
	public void CancelInterview(int interview_id) {
		Interview in = em.find(Interview.class, interview_id);
		em.remove(in);
	}

	@Override
	public boolean isWeekend(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		Calendar cal = new GregorianCalendar(year, month, day);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek);
	}

	@Override
	public boolean isOlderThanToday(String date) {
		System.out.println(Date.valueOf(LocalDate.now()));
		if (Date.valueOf(date).after(Date.valueOf(LocalDate.now()))) {
			System.out.println("date is older than today");
			return true;
		}
		return false;
	}

}
