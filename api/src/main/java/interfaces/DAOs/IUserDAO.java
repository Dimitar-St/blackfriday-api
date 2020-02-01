package DAOs;

import com.blackfriday.api.data.models.UserModel;

public interface IUserDAO<T> extends DAO<T> {
	UserModel getUserBy(String porperty, String email);
}
