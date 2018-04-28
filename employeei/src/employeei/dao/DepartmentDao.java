package employeei.dao;

import java.util.List;

import employeei.model.Department;

public interface DepartmentDao {
	public void add(Department department);
	public void update(Department department);
	public void delete(int rid);
	public Department getDepartment(int rid);
	@SuppressWarnings("rawtypes")
	public List getAllDepartment(); 
}
