package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.enums.POST_TYPE;


@Remote
public interface IPostServiceRemote {
	
	
	 void addPost(String content, Timestamp date ,int idUser, POST_TYPE typeIn);
	 void updatePost(String content,Timestamp date,int idUser, POST_TYPE type);
	 void deletePost(int id);
	 Post findPost(int id);
	 List<Post> findAllPosts();
	 List<Post> findAllUserPosts(int id);
	 List<Post> findFriendsPosts(int Userid);
	
	
}
