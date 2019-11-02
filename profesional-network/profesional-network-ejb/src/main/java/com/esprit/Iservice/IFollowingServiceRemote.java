package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Following;
import com.esprit.beans.User;
import com.esprit.enums.NOTIFICATION_TYPE;

@Remote
public interface IFollowingServiceRemote {
	
	void followPost(int idPost,int idUser);
	void followUser(int idFollwing,int idFollower);
	void unFollowPost(int idPost,int idUser);
	void unFollowUser(int idFollwing,int idFollower);
	
	List<User> PostFollowers (int idPost);
	List<User> UserFollowers (int idUser);

}