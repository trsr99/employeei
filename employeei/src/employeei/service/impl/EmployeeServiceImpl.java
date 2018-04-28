package employeei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employeei.dao.EmployeeDao;
import employeei.model.Employee;
import employeei.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeedao;

	@Transactional
	public void add(Employee employee) {
		employeedao.add(employee);
		
	}

	@Transactional
	public void update(Employee employee) {
		employeedao.update(employee);

	}

	@Transactional
	public void delete(int rid) {
		employeedao.delete(rid);

	}

	@Transactional
	public Employee getEmployee(int rid) {
		return employeedao.getEmployee(rid);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllEmployee() {
		return employeedao.getAllEmployee();
	}

}
