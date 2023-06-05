package top.kwseeker.spring.mvc;

import org.springframework.web.bind.annotation.*;
import top.kwseeker.spring.mvc.model.CommonResult;
import top.kwseeker.spring.mvc.model.User;
import top.kwseeker.spring.mvc.model.UserReqVO;
import top.kwseeker.spring.mvc.model.UserUpdateReqVO;

@RestController	//包含了 @ResponseBody
@RequestMapping("/rb")
public class ResponseBodyController {

	@GetMapping("/user")
	public CommonResult<User> getUser(@RequestBody UserReqVO reqVO) {
		String username = reqVO.getUsername();
		User user = new User();
		user.setUsername(username);
		user.setAge(18);
		System.out.println("User query");
		return CommonResult.success(user);
	}

	@PostMapping("/user")
	public CommonResult<Void> updateUser(@RequestBody UserUpdateReqVO reqVO) {
		User user = new User();
		user.setUsername(reqVO.getUsername());
		user.setAge(reqVO.getAge());
		System.out.println("User update");
		return CommonResult.success(null);
	}

	/**
	 * 添加请求参数 params 匹配条件，其他匹配条件（如：headers, consumes, produces）用法类似不一一列举了
	 */
	@PostMapping(value = "/more_cond", params = {"os=android","ver=8.0"})
	public CommonResult<Void> moreCondAndroid(@RequestBody UserUpdateReqVO reqVO) {
		User user = new User();
		user.setUsername(reqVO.getUsername());
		user.setAge(reqVO.getAge());
		System.out.println("os: android, ver:8.0");
		return CommonResult.success(null);
	}

	@PostMapping(value = "/more_cond", params = {"os=ios","ver=3.2"})
	public CommonResult<Void> moreCondIos(@RequestBody UserUpdateReqVO reqVO) {
		User user = new User();
		user.setUsername(reqVO.getUsername());
		user.setAge(reqVO.getAge());
		System.out.println("os: ios, ver:3.2");
		return CommonResult.success(null);
	}
}
