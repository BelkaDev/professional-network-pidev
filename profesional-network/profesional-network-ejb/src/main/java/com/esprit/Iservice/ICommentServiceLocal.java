package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Comment;
import com.esprit.beans.Reaction;


@Local
public interface ICommentServiceLocal {
	
	 void addComment(String content,Timestamp date
				,int idPost, int idUser);
	 void updateComment(String content,Timestamp date
				,int idPost, int idUser);
	 void deleteComment(int id);
	 Comment findComment(int id);
	 List<Comment> findPostComments(int id);
	 List<Comment> findUserComments(int id);
	 List<Comment> findCommentsByDate(Timestamp date);
	 List<Comment> findUserCommentsOnPost(int idUser,int idPost);
}
