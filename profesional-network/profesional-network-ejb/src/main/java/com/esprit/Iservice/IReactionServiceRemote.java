package com.esprit.Iservice;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import javax.ejb.Remote;

import com.esprit.beans.Reaction;


@Remote
public interface IReactionServiceRemote {
	
	 void addReaction(Reaction reaction);
	 void updateReaction(Reaction reaction);
	 void deleteReaction(Reaction reaction);
	 Reaction findReaction(Integer id);
	 List<Reaction> findAllPostReactions(Integer id);
	 List<Reaction> findAllUserReactions(Integer id);
	 List<Reaction> findReactionsByDate(Timestamp date);
	
}