package top.kwseeker.spring.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;

class JacksonSnakeCaseTest {

	@Test
	void testToSnakeCaseString() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

		A a = new A();
		a.setUserName("Arvin");
		a.setAge(18);
		a.setImgUrl("http://xxx.jpg");

		String s = objectMapper.writeValueAsString(a);
		System.out.println(s);

		A b = objectMapper.readValue("{\"user_name\":\"Alex\",\"age\":20,\"img_url\":\"http://123.jpg\"}", A.class);
		System.out.println(b.getUserName());
		System.out.println(b.getAge());
		System.out.println(b.getImgUrl());
	}

	static class A {
		private String userName;
		private Integer age;
		private String imgUrl;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
	}
}
