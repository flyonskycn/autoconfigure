package org.flyonsky.boot.autoconfigure.apollo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix=ApolloConstants.APP_PREFIX)
public class ApolloIdProperties {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
