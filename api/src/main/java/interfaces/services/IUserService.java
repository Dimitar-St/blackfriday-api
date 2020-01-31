package services;

import com.blackfriday.api.data.models.UserModel;

public interface IUserService {
	String register(UserModel user);
	
	UserModel login(UserModel userToLogIn);
}
