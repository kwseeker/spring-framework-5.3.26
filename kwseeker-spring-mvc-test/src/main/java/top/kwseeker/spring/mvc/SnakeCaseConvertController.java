package top.kwseeker.spring.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import top.kwseeker.spring.mvc.model.CommonResult;
import top.kwseeker.spring.mvc.model.UserInfo;

@RestController
@RequestMapping("/scc")
public class SnakeCaseConvertController implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@PostMapping("/user_info")
	public CommonResult<UserInfo> getUser(@RequestBody UserInfo user) {

		ClassLoader classLoader = SnakeCaseConvertController.class.getClassLoader();
		boolean jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader) &&
				ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", classLoader);
		System.out.println("jackson2Present: " + jackson2Present);

		System.out.println("user: " + user);
		user.setUserName("Arvin");
		user.setAge(18);
		user.setImgUrl("http://233.jpg");
		return CommonResult.success(user);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
