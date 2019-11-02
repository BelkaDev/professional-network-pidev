package com.esprit.Iservice;


import java.util.List;
import javax.ejb.Local;

import com.esprit.beans.FileUpload;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.POST_TYPE;


@Local
public interface IPostServiceLocal {
	 
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
