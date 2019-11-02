package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Blacklist;

@Local
public interface IBlacklistServiceLocal {
	
	void blockUser(int idBlocking,int idBlocked);
	void unblockUser(int idBlocking,int idBlocked);
	List<Blacklist> blockedBy (int idUser);
	
}