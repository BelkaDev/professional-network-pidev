package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.Following;
import com.esprit.beans.User;



@Local
public interface IFollowingServiceLocal {
	
	void followPost(int idPost,int idUser);
	void followUser(int idFollwing,int idFollower);
	void unFollowPost(int idPost,int idUser);
	void unFollowUser(int idFollwing,int idFollower);
	
	List<User> PostFollowers(int idPost);
	List<User> UserFollowers(int idUser);

}