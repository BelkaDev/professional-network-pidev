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
	
	 void addReaction(Timestamp date, REACTION_TYPE type
			 ,int idPost,int idUser);
	 void updateReaction(Timestamp date, REACTION_TYPE type
			 ,int idPost,int idUser);
	 void deleteReaction(int it);
	 Reaction findReaction(int id);
	 List<Reaction> findPostReactions(int id);
	 List<Reaction> findUserReactions(int id);
	 List<Reaction> findReactionsByDate(Timestamp date);
	 Reaction findUserReactionOnPost(int idUser,int idPost);
	
}
