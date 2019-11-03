package com.esprit.Iservice;
import com.esprit.beans.FileUpload;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.enums.POST_TYPE;


@Remote
public interface IPostServiceRemote {
	
	
	 void addPost(int idUser,String content, FileUpload file);
	 boolean updatePost(int id,String content,FileUpload file);
	 boolean deletePost(int id);
	 boolean sharePost(int idPost,int idUser);
	 boolean checkPostType(POST_TYPE postType);

	 
	 Post findPost(int id);
	 List<Post> sharingPost(int idPost);
	 List<Post> findAllPosts();
	 List<Post> findAllUserPosts(int id);
	 List<Post> findFriendsPosts(int Userid); 
	
	
}
