package top.kwseeker.spring.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.List;

//@Disabled
class SimpleTypeConverterTest {

	/**
	 * SimpleTypeConverter甚至 TypeConverterSupport 都不是单纯的 TypeConverter , 而是 ConversionService
	 * PropertyEditor 等转换器的集合；因为它们内部有 PropertyEditorRegistry 成员，是可以 setConversionService() 以及
	 * registerCustomEditor()
	 */
	@Test
	void testConvertByCustomPropertyEditor() throws JsonProcessingException {
		User user = new User();
		user.setName("Arvin");
		user.setAge(18);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println("json: " + json);

		//默认是无法直接将String转成对象类型的
		SimpleTypeConverter converter = new SimpleTypeConverter();
		converter.registerCustomEditor(User.class, new StringToUserPropertyEditor());
		User user1 = converter.convertIfNecessary(json, User.class);
		System.out.println(user1);
	}

	@Test
	void testConvertByCustomConversionService() throws JsonProcessingException {
		User user = new User();
		user.setName("Arvin");
		user.setAge(18);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		System.out.println("json: " + json);


	}

	@Test
	void testConvertListByDefaultPropertyEditor() {
		//String array = "[1,2,3]";		//做不到将字符串形式的数组转成List
		int[] array = {1,2,3};			//这种可以转成 List<Integer>
		SimpleTypeConverter converter = new SimpleTypeConverter();
		List<?> list = converter.convertIfNecessary(array, List.class);
		System.out.println(list);
	}

	/**
	 * 使用 ConversionService 实现自定义类型转换，只需要定义一个 Converter 然后注册到 ConversionService 中，
	 * 其实不需要定义 StringToUserConversionService，注册到 DefaultConversionService 中也可以
	 * TODO
	 */
	static class StringToUserConversionService extends GenericConversionService {
		@Override
		public <S, T> void addConverter(Class<S> sourceType, Class<T> targetType, Converter<? super S, ? extends T> converter) {
			super.addConverter(sourceType, targetType, converter);
		}

		@Override
		public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
			return super.canConvert(sourceType, targetType);
		}

		@Override
		public <T> T convert(Object source, Class<T> targetType) {
			return super.convert(source, targetType);
		}
	}

	/**
	 * 可以参考 Spring 提供的 PropertyEditor 实现
	 * 主要是继承 PropertyEditorSupport 然后重写
	 *  void setValue(Object value);
	 *  Object getValue();
	 *  void setAsText(String text) throws java.lang.IllegalArgumentException;
	 *  String getAsText();
	 * 这里是要将 String 转成 User
	 */
	static class StringToUserPropertyEditor extends PropertyEditorSupport {
		@Override
		public void setValue(Object value) {
			if (value instanceof String) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					User user = mapper.readValue((String)value, User.class);
					super.setValue(user);
				} catch (Exception e) {
					throw new RuntimeException("read value failed", e);
				}
			} else {
				throw new RuntimeException("unsupported value type");
			}
		}

		@Override
		public User getValue() {
			return (User) super.getValue();
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			setValue(text);
		}
	}

	static class User {
		private String name;
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
}
