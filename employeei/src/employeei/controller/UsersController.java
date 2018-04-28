package employeei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import employeei.model.Users;
import employeei.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersservice;

	@RequestMapping("/profile")
	public ModelAndView submitUsers()
	{  
	    ModelAndView model = new ModelAndView("profile");
		model.addObject("userslist", usersservice.getAllUsers());
		return model;
	}
	
	@RequestMapping("/AddProfile")
	public ModelAndView addProfile(@ModelAttribute Users users, BindingResult result,HttpServletRequest request)
	{  
		usersservice.add(users);
	    ModelAndView model = new ModelAndView("profile");
		model.addObject("userslist", usersservice.getAllUsers());
		HttpSession session = request.getSession(true);
		session.setAttribute("status","Successfully Added...!");
		return model;
	}
	
	@RequestMapping("/ProfileGrid")
	public ModelAndView submitUsersGrid(HttpServletRequest request)
	{  
		int i;
		HttpSession session = request.getSession(true);
		Users users = new Users();
		if (request.getParameter("submit").equals("Delete"))
		{
			for (i=1;i <= Integer.parseInt(request.getParameter("nrows"));i++)
			{
				if (request.getParameter("check"+i) != null)
				{
					users.setEmail(request.getParameter("erid"+i));
					usersservice.delete(users.getEmail());
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
					users.setEmail(request.getParameter("erid"+i));
					users.setName(request.getParameter("name"+i));
					users.setPass(request.getParameter("pass"+i));
					users.setActive(Integer.parseInt(request.getParameter("active"+i)));
					usersservice.update(users);
					k++;
				}
			}
			if (k>0) { session.setAttribute("status","Successfully Updated...!");}
			else {session.setAttribute("status","No rows Updated...!");}
		}
		
	    ModelAndView model = new ModelAndView("profile");
		model.addObject("userslist", usersservice.getAllUsers());
		return model;
	}	
}
