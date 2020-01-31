package DAOs;

import com.blackfriday.api.data.models.UserModel;

public interface IUserDAO<T> extends DAO<T> {
	UserModel getUserByEmail(String email);
	
	UserModel getUserByUsername(String username);
	
	void updateUsername(String newUsername);
	
	void updatePassword(String newPassword);
}
