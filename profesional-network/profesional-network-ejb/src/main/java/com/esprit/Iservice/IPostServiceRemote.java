package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.enums.POST_TYPE;


@Remote
public interface IPostServiceRemote {
	
	
	 void addPost(int idUser,String content,POST_TYPE typeIn);
	 boolean updatePost(int id,String content,POST_TYPE typePost);
	 boolean deletePost(int id);
	 boolean sharePost(int idPost,int idUser);
	 Post findPost(int id);
	 List<Post> sharingPost(int idPost);
	 List<Post> findAllPosts();
	 List<Post> findAllUserPosts(int id);
	 List<Post> findFriendsPosts(int Userid);
	
	
}
