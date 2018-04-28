package employeei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import employeei.model.Mapemployee;
import employeei.service.MapemployeeService;

@Controller
public class MapemployeeController {
	@Autowired
	private MapemployeeService mapemployeeservice;

	@RequestMapping("/mapemployee")
	public ModelAndView submitMapemployee()
	{  
	    ModelAndView model = new ModelAndView("mapemployee");
		model.addObject("mapemployeelist", mapemployeeservice.getAllMapemployee());
		return model;
	}
	
	@RequestMapping("/AddMapemployee")
	public ModelAndView addMapemployee(@ModelAttribute Mapemployee mapemployee, BindingResult result,HttpServletRequest request)
	{  
		mapemployeeservice.add(mapemployee);
	    ModelAndView model = new ModelAndView("mapemployee");
		model.addObject("mapemployeelist", mapemployeeservice.getAllMapemployee());
		HttpSession session = request.getSession(true);
		session.setAttribute("status","Successfully Added...!");
		return model;
	}
	
	@RequestMapping("/MapemployeeGrid")
	public ModelAndView submitMapemployeeGrid(HttpServletRequest request)
	{  
		int i;
		HttpSession session = request.getSession(true);
		Mapemployee mapemployee = new Mapemployee();
		if (request.getParameter("submit").equals("Delete"))
		{
			for (i=1;i <= Integer.parseInt(request.getParameter("nrows"));i++)
			{
				if (request.getParameter("check"+i) != null)
				{
					mapemployee.setEmpdeptid(Integer.parseInt(request.getParameter("erid"+i)));
					mapemployeeservice.delete(mapemployee.getEmpdeptid());
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
					mapemployee.setEmpdeptid(Integer.parseInt(request.getParameter("erid"+i)));
					mapemployee.setEmpid(Integer.parseInt(request.getParameter("empid"+i)));
					mapemployee.setDeptid(Integer.parseInt(request.getParameter("deptid"+i)));
					mapemployeeservice.update(mapemployee);
					k++;
				}
			}
			if (k>0) { session.setAttribute("status","Successfully Updated...!");}
			else {session.setAttribute("status","No rows Updated...!");}
		}
		
	    ModelAndView model = new ModelAndView("mapemployee");
		model.addObject("mapemployeelist", mapemployeeservice.getAllMapemployee());
		return model;
	}	
}
