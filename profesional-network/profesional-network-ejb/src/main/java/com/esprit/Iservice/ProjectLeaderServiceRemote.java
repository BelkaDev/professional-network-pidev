package com.esprit.Iservice;

import javax.ejb.Remote;

import com.esprit.beans.ProjectLeader;

@Remote
public interface ProjectLeaderServiceRemote {

	public int AddProjectLeader(ProjectLeader Pleader);

	
}
