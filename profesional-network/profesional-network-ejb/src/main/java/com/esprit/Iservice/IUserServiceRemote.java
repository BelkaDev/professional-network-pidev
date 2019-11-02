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
	public void logout() ;
	public List<User> getAdmin();
	public User getUserById();
	public void ResetingPassword(String userName);
	public void UpdatePassword(String userName, String NewPassword);
	public boolean UsernameMailUnique(String username,String email);
	
}
