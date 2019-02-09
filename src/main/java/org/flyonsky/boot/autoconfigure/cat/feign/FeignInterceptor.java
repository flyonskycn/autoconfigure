package org.flyonsky.boot.autoconfigure.cat.feign;

import org.flyonsky.boot.autoconfigure.cat.CatConstantsExt;
import org.flyonsky.boot.autoconfigure.cat.CatContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;

import feign.RequestInterceptor;
import feign.RequestTemplate;

class FeignInterceptor implements RequestInterceptor{
	
	private static final Logger LOG = LoggerFactory.getLogger(FeignInterceptor.class);

	@Override
	public void apply(RequestTemplate requestTemplate) {
		LOG.info("Cat FeignInterceptor initialize");
		Context ctx = new CatContext();
        
		Cat.logRemoteCallClient(ctx, Cat.getManager().getDomain());
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID,ctx.getProperty(Cat.Context.ROOT));
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID,ctx.getProperty(Cat.Context.PARENT));
		requestTemplate.header(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID,ctx.getProperty(Cat.Context.CHILD));
	}

}
