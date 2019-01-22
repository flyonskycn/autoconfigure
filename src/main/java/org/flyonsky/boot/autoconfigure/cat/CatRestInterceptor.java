package org.flyonsky.boot.autoconfigure.cat;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.dianping.cat.Cat;
import com.dianping.cat.Cat.Context;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;

public class CatRestInterceptor implements ClientHttpRequestInterceptor {
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		Transaction t = Cat.newTransaction(CatConstants.TYPE_CALL, request.getURI().toString());

		try {
			HttpHeaders headers = request.getHeaders();

			// Cat上下文
			Context ctx = new CatContext();
			Cat.logRemoteCallClient(ctx);
			headers.add(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, ctx.getProperty(Cat.Context.ROOT));
			headers.add(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, ctx.getProperty(Cat.Context.PARENT));
			headers.add(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, ctx.getProperty(Cat.Context.CHILD));

			// 继承执行逻辑
			ClientHttpResponse response =  execution.execute(request, body);
			t.setStatus(Transaction.SUCCESS);
			return response;
		} catch (Exception e) {
			Cat.getProducer().logError(e);
			t.setStatus(e);
			throw e;
		} finally {
			t.complete();
		}
	}
}
