package employeei.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import employeei.dao.EmployeeDao;
import employeei.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void add(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);

	}

	@Override
	public void update(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);

	}

	@Override
	public void delete(int rid) {
		sessionFactory.getCurrentSession().delete(getEmployee(rid));

	}

	@Override
	public Employee getEmployee(int rid) {
		return (Employee)sessionFactory.getCurrentSession().get(Employee.class,rid);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllEmployee() {
		return sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

}
