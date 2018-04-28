package employeei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employeei.dao.UsersDao;
import employeei.model.Users;
import employeei.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersdao;

	@Transactional
	public void add(Users users) {
		usersdao.add(users);
		
	}

	@Transactional
	public void update(Users users) {
		usersdao.update(users);

	}

	@Transactional
	public void delete(String rid) {
		usersdao.delete(rid);

	}

	@Transactional
	public Users getUsers(String rid) {
		return usersdao.getUsers(rid);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllUsers() {
		return usersdao.getAllUsers();
	}

}
