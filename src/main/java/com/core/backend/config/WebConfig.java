package com.core.backend.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

import com.core.backend.auth.config.AuthArgumentResolver;
import com.core.backend.auth.config.AuthInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;
	private final AuthArgumentResolver authArgumentResolver;

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer
			.addPathPrefix("/api/",
				HandlerTypePredicate.forBasePackage("com.core.backend")
					.and(HandlerTypePredicate.forAnnotation(RestController.class)))
			.setPathMatcher(new AntPathMatcher());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.addPathPatterns("/api/**")
			.excludePathPatterns("/api/health/**")
			.excludePathPatterns("/api/users/auth/**")
			.excludePathPatterns("/api/users/signup");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authArgumentResolver);
	}
}
