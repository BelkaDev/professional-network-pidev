package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Local;

import com.esprit.beans.Message;
import com.esprit.beans.User;

@Local
public interface IUserServiceLocal {
	
	void addUser(User usr);
	//void editUser(int id);
	void deleteUser(int id);
	public User findUser(int id);
	List<User> findAllUsers();
	
}
