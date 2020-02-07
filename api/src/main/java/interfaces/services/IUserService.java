package services;

import javax.ws.rs.core.Response;

import com.blackfriday.api.DTOs.Token;
import com.blackfriday.api.data.models.UserModel;

public interface IUserService {
	Response register(UserModel user);
	
	Response login(UserModel userToLogIn);
	
	UserModel getUserById(int id);
	
	int checkIfUserExists(String username);
}
