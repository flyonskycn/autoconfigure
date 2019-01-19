package org.flyonsky.boot.autoconfigure.cat;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnBean({RestTemplate.class})
public class RestAutoConfigure {
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	public void createRestTemplate(RestTemplate restTemplate) {
		// 保存和传递调用链上下文
		restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
		
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override 
			public boolean hasError(ClientHttpResponse response)
					throws IOException {
				try {
					return super.hasError(response);
				} catch (Exception e) {
					return true;
				}
			}

			@Override 
			public void handleError(ClientHttpResponse response)
					throws IOException {
				try {
					super.handleError(response);
				} catch (Exception e) {
					log.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
					throw e;
				}
			}
		});
	}
}
