package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Comment;
import com.esprit.beans.Reaction;


@Local
public interface ICommentServiceLocal {
	
		
	 boolean addComment(int idUser,int idPost, String content);
	 boolean updateComment(int id, String content);
	 boolean deleteComment(int id);
	 Comment findComment(int id);
	 List<Comment> findPostComments(int id);
	 List<Comment> findUserComments(int id);
	 List<Comment> findCommentsByDate(Timestamp date);
	 List<Comment> findUserCommentsOnPost(int idUser,int idPost);
}
