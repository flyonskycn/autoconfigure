package org.flyonsky.boot.autoconfigure.apollo.archaius;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.flyonsky.boot.autoconfigure.apollo.ApolloIdProperties;
import org.flyonsky.boot.autoconfigure.apollo.ApolloProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.boot.ApolloAutoConfiguration;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.netflix.config.ConfigurationManager;

@ConditionalOnClass({Config.class})
@ConditionalOnProperty({PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED})
@AutoConfigureAfter({ApolloAutoConfiguration.class})
@EnableConfigurationProperties({ApolloProperties.class,ApolloIdProperties.class})
@Configuration
public class ArchaiousAutoConfigure {

	@Autowired
	public void initArchaious(ApolloIdProperties apolloId, ApolloProperties apollo) {
//		String[] apolloUrls = StringUtils.split(apollo.getMeta(), ArchaiousConstants.ARCHAIUS_SEPARATOR);
//		String[] nameSpaces = StringUtils.split(apollo.getBootstrap().getNamespaces(), ArchaiousConstants.ARCHAIUS_SEPARATOR);
//		Set<String> archaiousUrlList = new LinkedHashSet<String>();
//		String url = "";
//		for(String ap : apolloUrls) {
//			for(String nameSpace : nameSpaces) {
//				url = MessageFormat.format(ArchaiousConstants.ARCHAIUS_APOLLO_URL_TEMPLATE, ap, apolloId.getId(), apollo.getCluster(), nameSpace);
//				archaiousUrlList.add(url);
//			}
//		}
//		String archaiousUrl = StringUtils.join(archaiousUrlList, ArchaiousConstants.ARCHAIUS_SEPARATOR);
//		System.setProperty(ArchaiousConstants.ARCHAIUS_ADD_ITIONAL_URLS, archaiousUrl);
		Set<String> set = ConfigurationManager.getLoadedPropertiesURLs();
		System.out.println(set);
	}
}
