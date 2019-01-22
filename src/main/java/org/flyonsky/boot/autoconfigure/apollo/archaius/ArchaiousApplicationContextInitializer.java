package org.flyonsky.boot.autoconfigure.apollo.archaius;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.spring.config.PropertySourcesConstants;
import com.netflix.config.ConfigurationManager;

public class ArchaiousApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
	
	private static final Logger logger = LoggerFactory.getLogger(ArchaiousApplicationContextInitializer.class);

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		ConfigurableEnvironment environment = context.getEnvironment();
	    
	    String enabled = environment.getProperty(PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED, "false");
	    if (!Boolean.valueOf(enabled).booleanValue())
	    {
	    	logger.debug("Apollo bootstrap config is not enabled for context {}, see property: ${{}}", context, "apollo.bootstrap.enabled");
	    	return;
	    }
	    logger.debug("Apollo bootstrap config is enabled for context {}", context);
	    
	    initialize(environment);
	}
	
	protected void initialize(ConfigurableEnvironment environment) {
		String apolloMeta = environment.getProperty(ConfigConsts.APOLLO_META_KEY, "http://localhost:10000");
		String[] apolloUrls = StringUtils.split(apolloMeta, ArchaiousConstants.ARCHAIUS_SEPARATOR);
		String apolloNameSpace = environment.getProperty(PropertySourcesConstants.APOLLO_BOOTSTRAP_NAMESPACES,ConfigConsts.NAMESPACE_APPLICATION);
		String[] nameSpaces = StringUtils.split(apolloNameSpace, ArchaiousConstants.ARCHAIUS_SEPARATOR);
		String appid = environment.getProperty(ArchaiousConstants.APOLLO_APP_ID,"test");
		String cluster = environment.getProperty(ConfigConsts.APOLLO_CLUSTER_KEY,ConfigConsts.CLUSTER_NAME_DEFAULT);
		Set<String> archaiousUrlList = new LinkedHashSet<String>();
		String url = "";
		for(String ap : apolloUrls) {
			for(String nameSpace : nameSpaces) {
				url = MessageFormat.format(ArchaiousConstants.ARCHAIUS_APOLLO_URL_TEMPLATE, ap, appid, cluster, nameSpace);
				archaiousUrlList.add(url);
			}
		}
		String archaiousUrl = StringUtils.join(archaiousUrlList, ArchaiousConstants.ARCHAIUS_SEPARATOR);
		System.setProperty(ArchaiousConstants.ARCHAIUS_ADD_ITIONAL_URLS, archaiousUrl);
		Set<String> set = ConfigurationManager.getLoadedPropertiesURLs();
		logger.info("ContextInitializer");
		logger.info(String.valueOf(set));
	}
}
