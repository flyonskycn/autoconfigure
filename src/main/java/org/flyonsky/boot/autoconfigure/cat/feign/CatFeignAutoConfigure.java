package org.flyonsky.boot.autoconfigure.cat.feign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;

@ConditionalOnClass({Feign.class})
@Configuration
public class CatFeignAutoConfigure {

	@Bean
	public FeignInterceptor feginInterceptor() {
		return new FeignInterceptor();
	}
}
