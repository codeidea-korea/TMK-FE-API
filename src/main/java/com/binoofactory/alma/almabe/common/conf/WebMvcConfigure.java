package com.binoofactory.alma.almabe.common.conf;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.binoofactory.alma.almabe.common.conf.handler.BfPageableHandlerInArgument;

@DependsOn(value = {"jasyptStringEncryptor"})
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
	@Bean
	public BfPageableHandlerInArgument bfPageableResolver() {
		return new BfPageableHandlerInArgument();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(bfPageableResolver());
	}
}