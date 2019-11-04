package com.esprit.Iservice;

import java.util.List;

import javax.ejb.Local;

import com.esprit.beans.User;


@Local
public interface IUserServiceLocal {
	
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
	void addEnterpriseUser(User user, int EnterpriseId);
	List<String> fetchUserInterests(int idUser);
	List<User> allUsers();
	
	
}
