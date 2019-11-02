package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Blacklist;

@Remote
public interface IBlacklistServiceRemote {
	
	void blockUser(int idBlocking,int idBlocked);
	void unblockUser(int idBlocking,int idBlocked);
	List<Blacklist> blockedBy (int idUser);
	
}