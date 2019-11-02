package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Following;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;

@Remote
public interface IFollowingServiceRemote {
	
	
	void followPost(int idUser,int idPost);
	void followUser(int idFollower,int idFollowed);
	void unFollowPost(int idUser,int idPost);
	void unFollowUser(int idFollower,int idFollowed);
	
	List<User> PostFollowers(int idPost);
	List<User> UserFollowers(int idUser);
}