package org.flyonsky.boot.autoconfigure.cat;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnBean({RestTemplate.class})
public class RestAutoConfigure {

	@Bean
	public CatRestConfig catRestTemplate(RestTemplate restTemplate) {
		CatRestConfig config = new CatRestConfig(restTemplate);
		// 初始化Cat
		config.initCat();
		return config;
	}
	
	protected static class CatRestConfig{
		
		private RestTemplate restTemplate;
		
		CatRestConfig(RestTemplate restTemplate){
			this.restTemplate = restTemplate;
		}
		
		/**
		 * 初始化Cat
		 */
		public void initCat() {
			// 设置Cat 上下文拦截器
			restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
		}
	}
}
