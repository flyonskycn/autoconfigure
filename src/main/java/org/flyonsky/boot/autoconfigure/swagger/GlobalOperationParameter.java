package org.flyonsky.boot.autoconfigure.swagger;

public class GlobalOperationParameter {
    /**
     * 参数名
     **/
    private String name;

    /**
     * 描述信息
     **/
    private String description;

    /**
     * 指定参数类型
     **/
    private String modelRef;

    /**
     * 参数放在哪个地方:header,query,path,body.form
     **/
    private String parameterType;

    /**
     * 参数是否必须传
     **/
    private String required;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelRef() {
		return modelRef;
	}

	public void setModelRef(String modelRef) {
		this.modelRef = modelRef;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
    
}
