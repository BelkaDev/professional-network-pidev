package com.esprit.Iservice;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import javax.ejb.Remote;

import com.esprit.beans.Post;
import com.esprit.beans.Reaction;
import com.esprit.beans.User;
import com.esprit.enums.REACTION_TYPE;


@Remote
public interface IReactionServiceRemote {
	
	
	 boolean addReaction(int idUser,int idPost, REACTION_TYPE type);
	 boolean updateReaction(int idReaction, REACTION_TYPE type);
	 boolean deleteReaction(int it);
	 Reaction findReaction(int id);
	 List<Reaction> findPostReactions(int id);
	 List<Reaction> findUserReactions(int id);
	 List<Reaction> findReactionsByDate(Timestamp date);
	 
	 boolean userAlreadyReacted(int idUser,int idPost);
	
}
