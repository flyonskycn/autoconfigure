package org.flyonsky.boot.autoconfigure.swagger;

import java.util.ArrayList;
import java.util.List;

public class GlobalResponseMessage {
    /**
     * POST 响应消息体
     **/
    List<GlobalResponseMessageBody> post = new ArrayList<>();

    /**
     * GET 响应消息体
     **/
    List<GlobalResponseMessageBody> get = new ArrayList<>();

    /**
     * PUT 响应消息体
     **/
    List<GlobalResponseMessageBody> put = new ArrayList<>();

    /**
     * PATCH 响应消息体
     **/
    List<GlobalResponseMessageBody> patch = new ArrayList<>();

    /**
     * DELETE 响应消息体
     **/
    List<GlobalResponseMessageBody> delete = new ArrayList<>();

    /**
     * HEAD 响应消息体
     **/
    List<GlobalResponseMessageBody> head = new ArrayList<>();

    /**
     * OPTIONS 响应消息体
     **/
    List<GlobalResponseMessageBody> options = new ArrayList<>();

    /**
     * TRACE 响应消息体
     **/
    List<GlobalResponseMessageBody> trace = new ArrayList<>();

	public List<GlobalResponseMessageBody> getPost() {
		return post;
	}

	public void setPost(List<GlobalResponseMessageBody> post) {
		this.post = post;
	}

	public List<GlobalResponseMessageBody> getGet() {
		return get;
	}

	public void setGet(List<GlobalResponseMessageBody> get) {
		this.get = get;
	}

	public List<GlobalResponseMessageBody> getPut() {
		return put;
	}

	public void setPut(List<GlobalResponseMessageBody> put) {
		this.put = put;
	}

	public List<GlobalResponseMessageBody> getPatch() {
		return patch;
	}

	public void setPatch(List<GlobalResponseMessageBody> patch) {
		this.patch = patch;
	}

	public List<GlobalResponseMessageBody> getDelete() {
		return delete;
	}

	public void setDelete(List<GlobalResponseMessageBody> delete) {
		this.delete = delete;
	}

	public List<GlobalResponseMessageBody> getHead() {
		return head;
	}

	public void setHead(List<GlobalResponseMessageBody> head) {
		this.head = head;
	}

	public List<GlobalResponseMessageBody> getOptions() {
		return options;
	}

	public void setOptions(List<GlobalResponseMessageBody> options) {
		this.options = options;
	}

	public List<GlobalResponseMessageBody> getTrace() {
		return trace;
	}

	public void setTrace(List<GlobalResponseMessageBody> trace) {
		this.trace = trace;
	}
    
}
