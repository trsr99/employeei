package employeei.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import employeei.model.Employee;
import employeei.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	
	@RequestMapping("/index")
	public ModelAndView setupEmployee()
	{
		ModelAndView model = new ModelAndView("index");
		return model;
	}

	@RequestMapping("/employee")
	public ModelAndView submitEmployee()
	{  
	    ModelAndView model = new ModelAndView("employee");
		model.addObject("employeelist", employeeservice.getAllEmployee());
		return model;
	}
	
	@RequestMapping("/AddEmployee")
	public ModelAndView addEmployee(@ModelAttribute Employee employee, BindingResult result,HttpServletRequest request)
	{  
		java.util.Date fdt = null;
		java.sql.Date doj = null;
		String dojdt = request.getParameter("doj");
		
		if (dojdt != ""){
		try {
			fdt = new SimpleDateFormat("MM/dd/yyyy").parse(dojdt);
			doj = new java.sql.Date(fdt.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employee.setDoj(doj);
		}
		else
		{
			employee.setDoj(null);
		}
		employeeservice.add(employee);
	    ModelAndView model = new ModelAndView("employee");
		model.addObject("employeelist", employeeservice.getAllEmployee());
		HttpSession session = request.getSession(true);
		session.setAttribute("status","Successfully Added...!");
		return model;
	}
	
	@RequestMapping("/EmpGrid")
	public ModelAndView submitEmpGrid(HttpServletRequest request)
	{  
		int i;
		HttpSession session = request.getSession(true);
		Employee employee = new Employee();
		if (request.getParameter("submit").equals("Delete"))
		{
			for (i=1;i <= Integer.parseInt(request.getParameter("nrows"));i++)
			{
				if (request.getParameter("check"+i) != null)
				{
					employee.setEmpid(Integer.parseInt(request.getParameter("erid"+i)));
					employeeservice.delete(employee.getEmpid());
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
					java.util.Date fdt = null;
					java.sql.Date doj = null;
					String dojdt = request.getParameter("edoj"+i);
					
					if (dojdt != ""){
					try {
						fdt = new SimpleDateFormat("MM/dd/yyyy").parse(dojdt);
						doj = new java.sql.Date(fdt.getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					employee.setDoj(doj);
					}
					else
					{
						employee.setDoj(null);
					}
					
					employee.setEmpid(Integer.parseInt(request.getParameter("erid"+i)));
					employee.setEmpname(request.getParameter("ename"+i));
					employee.setEmpphone(request.getParameter("eph"+i));
					employee.setAddress(request.getParameter("eaddr"+i));
					employeeservice.update(employee);
					k++;
				}
			}
			if (k>0) { session.setAttribute("status","Successfully Updated...!");}
			else {session.setAttribute("status","No rows Updated...!");}
		}
		
	    ModelAndView model = new ModelAndView("employee");
		model.addObject("employeelist", employeeservice.getAllEmployee());
		return model;
	}
}
