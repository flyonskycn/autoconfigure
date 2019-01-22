package org.flyonsky.boot.autoconfigure.apollo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="apollo")
public class ApolloProperties {

	private String cluster = "default";
	
	private String meta;
	
	private Bootstrap bootstrap;

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
	
	public Bootstrap getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(Bootstrap bootstrap) {
		this.bootstrap = bootstrap;
	}

	public static class Bootstrap{
		
		private String namespaces;

		public String getNamespaces() {
			return namespaces;
		}

		public void setNamespaces(String namespaces) {
			this.namespaces = namespaces;
		}
		
	}
}
