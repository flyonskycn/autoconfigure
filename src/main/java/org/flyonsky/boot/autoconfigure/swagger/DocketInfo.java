package org.flyonsky.boot.autoconfigure.swagger;

import java.util.ArrayList;
import java.util.List;

public class DocketInfo {
    /**
     * 标题
     **/
    private String title = "";
    /**
     * 描述
     **/
    private String description = "";
    /**
     * 版本
     **/
    private String version = "";
    /**
     * 许可证
     **/
    private String license = "";
    /**
     * 许可证URL
     **/
    private String licenseUrl = "";
    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";

    private ContactExt contact = new ContactExt();

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();
    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    private List<GlobalOperationParameter> globalOperationParameters;

    /**
     * 忽略的参数类型
     **/
    private List<Class<?>> ignoredParameterTypes = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public void setTermsOfServiceUrl(String termsOfServiceUrl) {
		this.termsOfServiceUrl = termsOfServiceUrl;
	}

	public ContactExt getContact() {
		return contact;
	}

	public void setContact(ContactExt contact) {
		this.contact = contact;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public List<String> getBasePath() {
		return basePath;
	}

	public void setBasePath(List<String> basePath) {
		this.basePath = basePath;
	}

	public List<String> getExcludePath() {
		return excludePath;
	}

	public void setExcludePath(List<String> excludePath) {
		this.excludePath = excludePath;
	}

	public List<GlobalOperationParameter> getGlobalOperationParameters() {
		return globalOperationParameters;
	}

	public void setGlobalOperationParameters(List<GlobalOperationParameter> globalOperationParameters) {
		this.globalOperationParameters = globalOperationParameters;
	}

	public List<Class<?>> getIgnoredParameterTypes() {
		return ignoredParameterTypes;
	}

	public void setIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {
		this.ignoredParameterTypes = ignoredParameterTypes;
	}
}
