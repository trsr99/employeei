package employeei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import employeei.model.Department;
import employeei.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService departmentservice;

	@RequestMapping("/department")
	public ModelAndView submitDepartment()
	{  
	    ModelAndView model = new ModelAndView("department");
		model.addObject("departmentlist", departmentservice.getAllDepartment());
		return model;
	}
	
	@RequestMapping("/AddDepartment")
	public ModelAndView addDepartment(@ModelAttribute Department department, BindingResult result,HttpServletRequest request)
	{  
		departmentservice.add(department);
	    ModelAndView model = new ModelAndView("department");
		model.addObject("departmentlist", departmentservice.getAllDepartment());
		HttpSession session = request.getSession(true);
		session.setAttribute("status","Successfully Added...!");
		return model;
	}
	
	@RequestMapping("/DeptGrid")
	public ModelAndView submitDeptGrid(HttpServletRequest request)
	{  
		int i;
		HttpSession session = request.getSession(true);
		Department department = new Department();
		if (request.getParameter("submit").equals("Delete"))
		{
			for (i=1;i <= Integer.parseInt(request.getParameter("nrows"));i++)
			{
				if (request.getParameter("check"+i) != null)
				{
					department.setDeptid(Integer.parseInt(request.getParameter("erid"+i)));
					departmentservice.delete(department.getDeptid());
					session.setAttribute("status","Successfully Deleted...!");
				}
			}
		}
		
		if (request.getParameter("submit").equals("Apply Changes"))
		{   int k = 0;
			for (i=1;i <= Integer.parseInt(request.getParameter("nrows"));i++)
			{
				if (request.getParameter("apply"+i).equals("1"))
				{			
					department.setDeptid(Integer.parseInt(request.getParameter("erid"+i)));
					department.setDeptname(request.getParameter("ename"+i));
					department.setDescription(request.getParameter("edesc"+i));
					departmentservice.update(department);
					k++;
				}
			}
			if (k>0) { session.setAttribute("status","Successfully Updated...!");}
			else {session.setAttribute("status","No rows Updated...!");}
		}
		
	    ModelAndView model = new ModelAndView("department");
		model.addObject("departmentlist", departmentservice.getAllDepartment());
		return model;
	}	
}
