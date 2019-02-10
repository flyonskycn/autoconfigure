package org.flyonsky.boot.autoconfigure.xxl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JobConstants.PREFIX)
public class JobProperties {
	
	// 执行器通讯TOKEN，非空时启用
	private String accessToken;

	// 执行器配置
	private Executor executor;
	
	// 调度中心的配置
	private Admin admin;
	
	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public static class Executor{
		// 执行器"AppName"和地址信息配置：AppName执行器心跳注册分组依据，地址信息用于"调度中心请求并触发任务"和"执行器注册"
		private String appname;
		// 执行器IP默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用
		private String ip;
		// 执行器默认端口为9999
		private int port = 9999;
		// 执行器运行日志文件存储的磁盘位置，需要对该路径拥有读写权限
		private String logpath;
		// days：执行器Log文件定期清理功能，指定日志保存天数，日志文件过期自动删除。限制至少保持3天，否则功能不生效；
		private int logretentiondays = -1;

		public String getAppname() {
			return appname;
		}

		public void setAppname(String appname) {
			this.appname = appname;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getLogpath() {
			return logpath;
		}

		public void setLogpath(String logpath) {
			this.logpath = logpath;
		}

		public int getLogretentiondays() {
			return logretentiondays;
		}

		public void setLogretentiondays(int logretentiondays) {
			this.logretentiondays = logretentiondays;
		}
	}
	
	public static class Admin{
		private String addresses;

		public String getAddresses() {
			return addresses;
		}

		public void setAddresses(String addresses) {
			this.addresses = addresses;
		}
	}
}
