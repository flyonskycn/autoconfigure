package org.flyonsky.boot.autoconfigure.apollo.archaius;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.flyonsky.boot.autoconfigure.apollo.ApolloConstants;
import org.flyonsky.boot.autoconfigure.apollo.ApolloIdProperties;
import org.flyonsky.boot.autoconfigure.apollo.ApolloProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.boot.ApolloAutoConfiguration;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.URLConfigurationSource;

@ConditionalOnClass({ApolloAutoConfiguration.class})
@ConditionalOnProperty({PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED})
@EnableConfigurationProperties({ApolloProperties.class,ApolloIdProperties.class})
@Configuration
public class ArchaiousAutoConfigure {
	
	private static final Logger logger = LoggerFactory.getLogger(ArchaiousAutoConfigure.class);

	@Bean
	public AbstractConfiguration  archaiousConfig(ApolloIdProperties apolloId, ApolloProperties apollo) {
		String[] apolloUrls = StringUtils.split(apollo.getMeta(), ApolloConstants.APOLLO_SEPARATOR);
		String[] nameSpaces = StringUtils.split(apollo.getBootstrap().getNamespaces(), ApolloConstants.APOLLO_SEPARATOR);
		Set<String> archaiousUrlList = new LinkedHashSet<String>();
		String url = "";
		for(String ap : apolloUrls) {
			for(String nameSpace : nameSpaces) {
				url = MessageFormat.format(ArchaiousConstants.ARCHAIUS_APOLLO_URL_TEMPLATE, ap, apolloId.getId(), apollo.getCluster(), nameSpace);
				archaiousUrlList.add(url);
				logger.info(url);
			}
		}
		String[] a = new String[archaiousUrlList.size()];
		PolledConfigurationSource source = new URLConfigurationSource(archaiousUrlList.toArray(a));
		return new DynamicConfiguration(source, new FixedDelayPollingScheduler());
	}
}
