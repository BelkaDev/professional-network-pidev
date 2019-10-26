package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Comment;

@Remote
public interface ICommentServiceRemote {
	
	 void addComment(String content,Timestamp date
				,int idPost, int idUser);
	 void updateComment(String content,Timestamp date
				,int idPost, int idUser);
	 void deleteComment(int id);
	 Comment findComment(int id);
	 List<Comment> findAllPostComments(int id);
	 List<Comment> findAllUserComments(int id);
	 List<Comment> findCommentsByDate(Timestamp date);
	
}
