package top.kwseeker.spring.mvc.model;

public class UserInfo {

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

	@Override
	public String toString() {
		return "UserInfo{" +
				"userName='" + userName + '\'' +
				", age=" + age +
				", imgUrl='" + imgUrl + '\'' +
				'}';
	}
}
