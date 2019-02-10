package org.flyonsky.boot.autoconfigure.xxl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.XxlJobExecutor;

@ConditionalOnProperty(value=JobConstants.ENABLED, matchIfMissing=false)
@EnableConfigurationProperties({JobProperties.class})
@Configuration
public class JobAutoConfigure {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobAutoConfigure.class);

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor(JobProperties jobProperties) {
    	LOG.info(">>>>>>>>>>> xxl-job init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(jobProperties.getAdmin().getAddresses());
        xxlJobExecutor.setAppName(jobProperties.getExecutor().getAppname());
        xxlJobExecutor.setIp(jobProperties.getExecutor().getIp());
        xxlJobExecutor.setPort(jobProperties.getExecutor().getPort());
        xxlJobExecutor.setAccessToken(jobProperties.getAccessToken());
        xxlJobExecutor.setLogPath(jobProperties.getExecutor().getLogpath());
        xxlJobExecutor.setLogRetentionDays(jobProperties.getExecutor().getLogretentiondays());
        return xxlJobExecutor;
    }
}
