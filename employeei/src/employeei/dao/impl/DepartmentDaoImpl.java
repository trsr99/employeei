package employeei.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import employeei.dao.DepartmentDao;
import employeei.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void add(Department department) {
		sessionFactory.getCurrentSession().save(department);

	}

	@Override
	public void update(Department department) {
		sessionFactory.getCurrentSession().update(department);

	}

	@Override
	public void delete(int rid) {
		sessionFactory.getCurrentSession().delete(getDepartment(rid));

	}

	@Override
	public Department getDepartment(int rid) {
		return (Department)sessionFactory.getCurrentSession().get(Department.class,rid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllDepartment() {
		return sessionFactory.getCurrentSession().createQuery("from Department").list();
	}

}
