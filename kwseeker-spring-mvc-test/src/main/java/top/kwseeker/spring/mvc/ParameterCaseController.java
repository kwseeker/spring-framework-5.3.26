package top.kwseeker.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParameterCaseController {

	/**
	 * 请求参数封装的分析
	 */
	@RequestMapping("testParam1")
	public String testParam1(String username, Integer age) {
		System.out.println(username + "," + age);
		return "success";
	}

	/**
	 * 请求参数封装的分析
	 */
	@RequestMapping("testParam2")
	public String testParam2(@RequestParam("username") String name, Integer age) {
		System.out.println(name + "," + age);
		return "success";
	}

	/**
	 * 请求参数封装的分析
	 */
	@RequestMapping("testParam3/{username}/{age}")
	public void testParam3(@PathVariable("username") String username, @PathVariable Integer age) {
		System.out.println(username + "," + age);
	}

	/**
	 * 请求参数封装的分析
	 */
	@RequestMapping("testParam4")
	public void testParam4(@RequestBody String body) {
		System.out.println(body);
	}
}
