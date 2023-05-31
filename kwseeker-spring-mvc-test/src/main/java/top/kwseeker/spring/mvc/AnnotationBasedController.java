package top.kwseeker.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/example")
public class AnnotationBasedController {

	@RequestMapping("/hello")
	public String sayHello(String name){
		System.out.println("Request AnnotationBasedController /hello, name=" + name);
		return "success";
	}
}