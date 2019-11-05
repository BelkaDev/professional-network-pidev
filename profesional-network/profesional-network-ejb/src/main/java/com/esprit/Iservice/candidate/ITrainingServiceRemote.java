package com.esprit.Iservice.candidate;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.beans.candidate.Training;

@Remote
public interface ITrainingServiceRemote {
	
	public void addTraining(Training t);
	public void updateTraining(Training t,int trainingId);
	public void deteleTraining(int trainingId);
	public List<Training> displayTraining(int candidateId);
}
