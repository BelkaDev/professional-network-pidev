package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;

import com.esprit.beans.Comment;
import com.esprit.beans.Post;


@Remote
public interface IPostServiceRemote {
	
	 void addPost(Comment comment);
	 void updatePost(Comment comment);
	 void deletePost(Comment comment);
	 Comment find(Integer id);
	 List<Post> findAllPosts();
	 List<Post> findAllUserPosts(Integer id);
	 List<Post> findFriendsPosts(Integer Userid);
	
}
