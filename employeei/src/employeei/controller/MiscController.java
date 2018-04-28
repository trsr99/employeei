package employeei.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import employeei.model.FileModel;

@Controller
public class MiscController {
	@Autowired
	ServletContext context; 
	
	@RequestMapping("/miscellaneous")
	public ModelAndView submitDepartment()
	{  
	    ModelAndView model = new ModelAndView("miscellaneous");
		return model;
	}
	
	@RequestMapping("/UploadFile")
	public ModelAndView uploadFile(@Validated FileModel file, BindingResult result, ModelMap model1) throws IOException {
		  ModelAndView model = new ModelAndView("miscellaneous");
	      if (result.hasErrors()) {
	          System.out.println("validation errors");
	          return model;
	       } else {            
	          System.out.println("Fetching file");
	          MultipartFile multipartFile = file.getFile();
	          String uploadPath = "c:\\t\\";
	          //Now do something with file...
	          FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getFile().getOriginalFilename()));
	          String fileName = multipartFile.getOriginalFilename();
	          model1.addAttribute("fileName", fileName);
	          return model;
	       }
	    }
	
	@RequestMapping("/DownloadFile")
	public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    ModelAndView model = new ModelAndView("miscellaneous");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String gfile = request.getParameter("gfile");
			String gpath = "c:/t/";
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ gfile + "\"");
	 
			FileInputStream fileInputStream = new FileInputStream(gpath
					+ gfile);
	 
			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
	        return model;
	       }
}
