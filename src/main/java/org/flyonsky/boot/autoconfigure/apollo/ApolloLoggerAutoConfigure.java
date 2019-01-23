package org.flyonsky.boot.autoconfigure.apollo;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;

/**
 * 动态更新日志级别
 * @author Administrator
 *
 */
@ConditionalOnProperty({PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED})
@Configuration
public class ApolloLoggerAutoConfigure {
	
	@Component
	public static class LoggerLevelRefresher implements ApplicationContextAware{
		
		private static final String LOG_PREFIX = "logging.level.";
		
		private static final Logger LOG = LoggerFactory.getLogger(LoggerLevelRefresher.class);
		
		private ApplicationContext applicationContext;
		
		@ApolloConfig
		private Config config;

		@PostConstruct
	 	private void initialize() {
			refreshLoggingLevels(config.getPropertyNames());
		}

		@ApolloConfigChangeListener
		private void onChange(ConfigChangeEvent changeEvent) {
			refreshLoggingLevels(changeEvent.changedKeys());
		}

		private void refreshLoggingLevels(Set<String> changedKeys) {
			boolean loggingLevelChanged = false;
			for (String changedKey : changedKeys) {
				if (changedKey.startsWith(LOG_PREFIX)) {
					loggingLevelChanged = true;
					break;
				}
			}

			if (loggingLevelChanged) {
				LOG.info("Refreshing logging levels");
				// refresh logging levels
				this.applicationContext.publishEvent(new EnvironmentChangeEvent(changedKeys));

				LOG.info("Logging levels refreshed");
			}
		}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}
	}
}
