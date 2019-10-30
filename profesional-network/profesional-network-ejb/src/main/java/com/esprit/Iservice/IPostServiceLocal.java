package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Comment;
import com.esprit.beans.Post;
import com.esprit.enums.Posts;


@Local
public interface IPostServiceLocal {
	
	 void addPost(String content, Timestamp date ,int idUser, Posts typeIn);
	 void updatePost(String content,Timestamp date,int idUser, Posts type);
	 void deletePost(int id);
	 Post find(int id);
	 List<Post> findAllPosts();
	 List<Post> findAllUserPosts(int id);
	 List<Post> findFriendsPosts(int Userid);
	
}
