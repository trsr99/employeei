package employeei.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import employeei.dao.LoginDao;
import employeei.model.LoginBean;

@Controller
public class LoginController {
	@Autowired
	//private EmployeeService employeeservice;

	@RequestMapping("/login")
	public ModelAndView loginPage()
	{  
	    ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping("/loginprocess")
	public ModelAndView loginProcess(HttpServletRequest request)
	{  
		LoginBean obj = new LoginBean();
		obj.setEmail(request.getParameter("email"));
		obj.setPass(request.getParameter("pass"));
		HttpSession session1 = request.getSession(true);
		boolean status=LoginDao.validate(obj);
		session1.setAttribute("strack",session1.getId());

		if(status){
		session1.setAttribute("strack",session1.getId());
		String email = request.getParameter("email");
		session1.setAttribute("userid",email); 
	    ModelAndView model = new ModelAndView("index");
		return model;
		}
		else
			{
			    session1.setAttribute("loginstatus","Invalid email or password credentials...!");
			    ModelAndView model = new ModelAndView("login");
				return model;
			}
	}
}
