package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Comment;

@Remote
public interface ICommentServiceRemote {
	
	 void addComment(Comment comment);
	 void updateComment(Comment comment);
	 void deleteComment(Comment comment);
	 Comment findComment(Integer id);
	 List<Comment> findAllPostComments(Integer id);
	 List<Comment> findAllUserComments(Integer id);
	 List<Comment> findCommentsByDate(Timestamp date);
}
