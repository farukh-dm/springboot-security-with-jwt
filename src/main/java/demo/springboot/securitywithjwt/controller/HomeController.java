package demo.springboot.securitywithjwt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String hi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentName = authentication.getName();
		
		return "Welcome " + currentName;
	}
	
}
