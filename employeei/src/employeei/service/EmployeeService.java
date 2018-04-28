package employeei.service;

import java.util.List;

import employeei.model.Employee;

public interface EmployeeService {
	public void add(Employee employee);
	public void update(Employee employee);
	public void delete(int rid);
	public Employee getEmployee(int rid);
	@SuppressWarnings("rawtypes")
	public List getAllEmployee(); 
}
