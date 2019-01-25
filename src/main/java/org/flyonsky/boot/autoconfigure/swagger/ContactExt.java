package org.flyonsky.boot.autoconfigure.swagger;

public class ContactExt {
    /**
     * 联系人
     **/
    private String name = "";
    /**
     * 联系人url
     **/
    private String url = "";
    /**
     * 联系人email
     **/
    private String email = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
}
