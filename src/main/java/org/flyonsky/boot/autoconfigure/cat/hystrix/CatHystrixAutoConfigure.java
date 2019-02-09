package org.flyonsky.boot.autoconfigure.cat.hystrix;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.HystrixCommand;

@Configuration
@ConditionalOnClass({HystrixCommand.class})
public class CatHystrixAutoConfigure {
	
	@Bean
	public CatHystrixConcurrencyStrategy catHystrixConcurrencyStrategy() {
		return new CatHystrixConcurrencyStrategy();
	}
}
