package employeei.dao;

import java.util.List;

import employeei.model.Users;

public interface UsersDao {
	public void add(Users users);
	public void update(Users users);
	public void delete(String rid);
	public Users getUsers(String rid);
	@SuppressWarnings("rawtypes")
	public List getAllUsers(); 
}
