package org.flyonsky.boot.autoconfigure.cat.feign;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.flyonsky.boot.autoconfigure.cat.CatContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.dianping.cat.Cat;
import com.netflix.hystrix.HystrixCommand;

import feign.Feign;
import feign.RequestInterceptor;

@ConditionalOnClass({Feign.class})
@Configuration
public class CatFeignAutoConfigure {

	@ConditionalOnExpression("${feign.hystrix.enabled:false}.equals(false)")
	@Bean
	public RequestInterceptor feginInterceptor() {
		return new FeignInterceptor();
	}
	
	@ConditionalOnProperty(name= {"feign.hystrix.enabled"},matchIfMissing=false)
	@Bean
	public RequestInterceptor hystrixFeginInterceptor() {
		return new HystrixFeignRequestInterceptor();
	}

	@ConditionalOnProperty(name= {"feign.hystrix.enabled"},matchIfMissing=false)
	@ConditionalOnClass({HystrixCommand.class})
	@Configuration
	@Aspect
	public static class FeignHystrixConfig {
		
		private static final Logger LOG = LoggerFactory.getLogger(FeignHystrixConfig.class);

		@Around(value="@within(org.springframework.cloud.openfeign.FeignClient)")
	    public Object around(ProceedingJoinPoint pjp) throws Throwable {
			LOG.info("FeignHystrixConfig initialize");
	        createMessageTree();
	        Object proceed = pjp.proceed();
	        return proceed;
	    }

	    /**
	     * 统一设置消息编号的messageId
	     */
	    private void createMessageTree() {
	        CatContext context = new CatContext();
	        Cat.logRemoteCallClient(context);
	        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	        requestAttributes.setAttribute(Cat.Context.PARENT, context.getProperty(Cat.Context.PARENT), 0);
	        requestAttributes.setAttribute(Cat.Context.ROOT, context.getProperty(Cat.Context.ROOT), 0);
	        requestAttributes.setAttribute(Cat.Context.CHILD, context.getProperty(Cat.Context.CHILD), 0);
	    }
	}
}