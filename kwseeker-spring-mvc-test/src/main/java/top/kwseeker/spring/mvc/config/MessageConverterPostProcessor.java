package top.kwseeker.spring.mvc.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * 通过源码知道 处理JSON请求与返回的 MappingJackson2HttpMessageConverter 位于 RequestMappingHandlerAdapter 内
 * 那个就可以通过下面方式达到和实现 WebMvcConfigurationSupport 一样的效果， 对比 MvcConfig
 */
@Component
public class MessageConverterPostProcessor implements BeanPostProcessor, BeanFactoryAware {

	private BeanFactory beanFactory;	//只是调试用的

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//代码中发现有多个 MappingJackson2HttpMessageConverter 类型的Bean,
		//但是这里只想改 RequestMappingHandlerAdapter#messageConverters 中的 MappingJackson2HttpMessageConverter Bean
		//if (bean instanceof MappingJackson2HttpMessageConverter) {
		//	System.out.println("设置 bean: " + beanName + " 属性命名策略为 SNAKE_CASE");
		//	((MappingJackson2HttpMessageConverter) bean).getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		//}
		//另外发现某个Bean的内部bean类型也是RequestMappingHandlerAdapter，比如会打印下面两个日志
		//设置 bean: org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter (1515271115) 属性命名策略为 SNAKE_CASE
		//设置 bean: (inner bean)#73486da3 (1689399191) 属性命名策略为 SNAKE_CASE
		//这个内部bean的外部bean是 mvcUriComponentsContributor 类型 AnnotationDrivenBeanDefinitionParser$CompositeUriComponentsContributorFactoryBean
		if (bean instanceof RequestMappingHandlerAdapter
				&& beanName.equals("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter")) {	//排除修改内部bean, 不过侵入性好像有点高
			List<HttpMessageConverter<?>> messageConverters = ((RequestMappingHandlerAdapter) bean).getMessageConverters();
			for (HttpMessageConverter<?> messageConverter : messageConverters) {
				if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
					System.out.println("设置 bean: " + beanName + " ("+ bean.hashCode() + ")" + " 属性命名策略为 SNAKE_CASE");
					((MappingJackson2HttpMessageConverter) messageConverter).getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
				}
			}
		}
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
