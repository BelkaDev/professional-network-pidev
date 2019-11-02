package com.esprit.Iservice;
import java.util.List;

import java.sql.Timestamp;
import javax.ejb.Local;

import com.esprit.beans.Reaction;
import com.esprit.enums.REACTION_TYPE;


@Local
public interface IReactionServiceLocal {
	
	
	 boolean addReaction(int idUser,int idPost, REACTION_TYPE type);
	 boolean updateReaction(int idReaction, REACTION_TYPE type);
	 boolean deleteReaction(int it);
	 Reaction findReaction(int id);
	 List<Reaction> findPostReactions(int id);
	 List<Reaction> findUserReactions(int id);
	 List<Reaction> findReactionsByDate(Timestamp date);
	 
	 boolean userAlreadyReacted(int idUser,int idPost);


}