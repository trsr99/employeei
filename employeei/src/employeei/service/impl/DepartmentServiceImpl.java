package employeei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employeei.dao.DepartmentDao;
import employeei.model.Department;
import employeei.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentdao;

	@Transactional
	public void add(Department department) {
		departmentdao.add(department);
		
	}

	@Transactional
	public void update(Department department) {
		departmentdao.update(department);

	}

	@Transactional
	public void delete(int rid) {
		departmentdao.delete(rid);

	}

	@Transactional
	public Department getDepartment(int rid) {
		return departmentdao.getDepartment(rid);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllDepartment() {
		return departmentdao.getAllDepartment();
	}

}

