package org.flyonsky.boot.autoconfigure.cat.feign;

import org.flyonsky.boot.autoconfigure.cat.CatConstantsExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.dianping.cat.Cat;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class HystrixFeignRequestInterceptor implements RequestInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(HystrixFeignRequestInterceptor.class);
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		LOG.info("Cat HystrixFeignRequestInterceptor initialize");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        String rootId = requestAttributes.getAttribute(Cat.Context.ROOT, RequestAttributes.SCOPE_REQUEST).toString();
        String childId = requestAttributes.getAttribute(Cat.Context.CHILD, RequestAttributes.SCOPE_REQUEST).toString();
        String parentId = requestAttributes.getAttribute(Cat.Context.PARENT, RequestAttributes.SCOPE_REQUEST).toString();
        
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, rootId);
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, parentId);
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, childId);
	}

}
