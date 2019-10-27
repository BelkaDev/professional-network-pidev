package com.esprit.Iservice;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import javax.ejb.Local;

import com.esprit.beans.Post;
import com.esprit.beans.Reaction;
import com.esprit.beans.User;
import com.esprit.enums.Reactions;


@Local
public interface IReactionServiceLocal {
	
	 void addReaction(Timestamp date, Reactions type
			 ,int idPost,int idUser);
	 void updateReaction(Timestamp date, Reactions type
			 ,int idPost,int idUser);
	 void deleteReaction(int it);
	 Reaction findReaction(int id);
	 List<Reaction> findPostReactions(int id);
	 List<Reaction> findUserReactions(int id);
	 List<Reaction> findReactionsByDate(Timestamp date);
	 Reaction findUserReactionOnPost(int idUser,int idPost);


}