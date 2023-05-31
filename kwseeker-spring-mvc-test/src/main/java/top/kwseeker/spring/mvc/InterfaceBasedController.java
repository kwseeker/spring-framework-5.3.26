package top.kwseeker.spring.mvc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterfaceBasedController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		System.out.println("Request InterfaceBasedController");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("success2");
		mv.addObject("hello","你好");
		return mv;
	}
}