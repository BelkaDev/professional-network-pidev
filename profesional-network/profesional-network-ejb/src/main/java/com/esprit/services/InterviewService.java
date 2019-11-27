package com.esprit.services;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IInterviewServiceLocal;
import com.esprit.Iservice.IInterviewServiceRemote;
import com.esprit.beans.Interview;
import com.esprit.beans.InterviewState;
import com.esprit.beans.JobOffer;
import com.esprit.beans.Quiz;
import com.esprit.enums.NOTIFICATION_TARGET;
import com.esprit.enums.NOTIFICATION_TYPE;

@Stateless
@LocalBean
public class InterviewService implements IInterviewServiceLocal, IInterviewServiceRemote {
	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	@EJB
	 QuizService qs = new QuizService();
	@EJB
	 NotificationService ns = new NotificationService();

	@Override
	public boolean setDate(int interview_id, String date, int candidate_id, int joboffer_id) {
		System.out.println("************HEY" + candidate_id);
		if (isWeekend(date) || isOlderThanToday(date) || qs.checkCandidateDate(candidate_id, Date.valueOf(date))
				|| qs.checkHRDate(joboffer_id, Date.valueOf(date))) {
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

	@Override
	public Interview displayInterview(int interview_id) {
		Interview in = em.find(Interview.class, interview_id);
		return in;
	}

	@Override
	public boolean validTime(String time) {
		int hours = Integer.parseInt(time.substring(0, 2));
		if (hours > 9 && hours < 18)
			return true;
		return false;
	}

	@Override
	public boolean setTime(int interview_id, String time) {
		if (validTime(time)) {
			Interview in = em.find(Interview.class, interview_id);
			in.setTime(Time.valueOf(time));
			return true;
		}
		return false;
	}

	@Override
	public void acceptCandidate(int joboffer_id) {
		Set<Interview> interviews = getInterviewsForOffer(joboffer_id);
		if (!interviews.isEmpty()) {
			for (Interview i : interviews) {
				setStateRejected(i.getId());
			}
			setStateAccepted(interviews.iterator().next().getId());
		}
		sendNotifToCandidates(joboffer_id);
	}

	@Override
	public Set<Interview> getInterviewsForOffer(int jobOffer_id) {
		JobOffer o = em.find(JobOffer.class, jobOffer_id);
		System.out.println("*********************" + o.getJOdescription() + "*************************");
		if (o.getQuizs().isEmpty())
			return null;
		Set<Interview> interviews = new TreeSet<Interview>(new Comparator<Interview>() {
			@Override
			public int compare(Interview o1, Interview o2) {
				if (o1.getScore() > o2.getScore())
					return -1;
				if (o1.getScore() < o2.getScore())
					return 1;
				return 0;
			}
		});
		for (Quiz q : o.getQuizs()) {
			if (q.getInterview() != null)
				interviews.add(q.getInterview());
		}
		return interviews;
	}

	@Override
	public void sendNotifToCandidates(int jobOffer_id) {
		System.out.println("*********************************************NotifTest******************************");
		System.out.println(jobOffer_id);
		Set<Quiz> quizs = em.find(JobOffer.class, jobOffer_id).getQuizs();
		 for (Quiz q : quizs) {
		ns.CreateNotification(q.getCandidate().getId() ,
				q.getInterview().getState().toString(), NOTIFICATION_TYPE.Offer,
				q.getInterview().getId());
		 }
	}

	@Override
	public void setStateAccepted(int interview_id) {
		Interview in = em.find(Interview.class, interview_id);
		in.setState(InterviewState.Accepted);
	}

	@Override
	public void setStateRejected(int interview_id) {
		Interview in = em.find(Interview.class, interview_id);
		in.setState(InterviewState.Rejected);

	}

	@Override
	public void setScore(int interview_id, double score) {
		Interview in = em.find(Interview.class, interview_id);
		in.setScore(in.getScore() + score);

	}
}
