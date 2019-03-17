package com.nulltemp.config;

import java.lang.reflect.Method;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.nulltemp.constant.UrlConstant;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public WebMvcRegistrations webMvcRegistrationsHandlerMapping() {
		return new WebMvcRegistrations() {
			@Override
			public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
				return new RequestMappingHandlerMapping() {
					@Override
					protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
						Class<?> beanType = method.getDeclaringClass();
						if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
							PatternsRequestCondition apiPattern = new PatternsRequestCondition(UrlConstant.URL_API_BASE)
									.combine(mapping.getPatternsCondition());

							mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
									mapping.getMethodsCondition(), mapping.getParamsCondition(),
									mapping.getHeadersCondition(), mapping.getConsumesCondition(),
									mapping.getProducesCondition(), mapping.getCustomCondition());
						}

						super.registerHandlerMethod(handler, method, mapping);
					}
				};
			}
		};
	}
}
