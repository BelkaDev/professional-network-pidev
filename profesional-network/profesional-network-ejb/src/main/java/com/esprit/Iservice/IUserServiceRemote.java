package com.esprit.Iservice;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.User;

@Remote
public interface IUserServiceRemote {
	
	void addUser(User user);
	//void editUser(int id);
	void deleteUser(int id);
	public User findUser(int id);
	//List<User> findAllUsers();
	
}
