package employeei.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import employeei.dao.UsersDao;
import employeei.model.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void add(Users users) {
		sessionFactory.getCurrentSession().save(users);

	}

	@Override
	public void update(Users users) {
		sessionFactory.getCurrentSession().update(users);

	}

	@Override
	public void delete(String rid) {
		sessionFactory.getCurrentSession().delete(getUsers(rid));

	}

	@Override
	public Users getUsers(String rid) {
		return (Users)sessionFactory.getCurrentSession().get(Users.class,rid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllUsers() {
		return sessionFactory.getCurrentSession().createQuery("from Users").list();
	}

}
