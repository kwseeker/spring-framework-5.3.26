package top.kwseeker.spring.mvc.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * SpringMVC 驼峰下划线互转换的方式：
 * 1 这里展示的 (适用于入参和返回结果)
 * 2 POJO对象字段上 @JsonProperty (返回结果)
 * 3 POJO对象类上 @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) (返回结果)
 * 4 分析源码流程，可以自行定义相关控制组件
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

	/**
	 * TODO 未生效原因
	 */
	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter<?> messageConverter : converters) {
			if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
				System.out.println("设置 MappingJackson2HttpMessageConverter 属性命名策略为 SNAKE_CASE");
				((MappingJackson2HttpMessageConverter) messageConverter).getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
			}
		}

		//System.out.println("设置 MappingJackson2HttpMessageConverter 属性命名策略为 SNAKE_CASE");
		//MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		//ObjectMapper objectMapper = converter.getObjectMapper();
		//// 设置驼峰标志转下划线
		//objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		//// 设置格式化内容
		//converter.setObjectMapper(objectMapper);
		//converters.add(0, converter);

		super.extendMessageConverters(converters);
	}
}
