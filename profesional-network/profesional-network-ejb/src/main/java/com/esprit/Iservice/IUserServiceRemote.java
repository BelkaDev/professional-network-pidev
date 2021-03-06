package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Remote;
import com.esprit.beans.User;

@Remote
public interface IUserServiceRemote {
	
	public void addUser(User user);
	public boolean authenticate(String username, String password);
	public void updateToken(String username,String token);
	public void confirmCode(String code, int idUser);
	public void logout(int id) ;
	public void disableMailNotifications();
	public void enableMailNotifications();
	public List<User> getAdmin();
	public User getUserById();
	public void ResetingPassword(String userName);
	public void UpdatePassword(String userName, String NewPassword);
	public boolean UsernameMailUnique(String username,String email);
	void addEnterpriseUser(User user, int EnterpriseId);
	void addCandidateUser(User user, int candidateId);
	List<User> allUsers();
	
}
