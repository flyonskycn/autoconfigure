package org.flyonsky.boot.autoconfigure.cat;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

public class CatServletFilter implements Filter{
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();

        //若header中有context相关属性，则生成调用链，若无，仅统计请求的Transaction
        if(null != request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID)){
            CatContext catContext = new CatContext();
            catContext.addProperty(Cat.Context.ROOT,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.PARENT,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.CHILD,request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID));
            Cat.logRemoteCallServer(catContext);
        }

        Transaction filterTransaction = Cat.newTransaction(CatConstantsExt.TYPE_URL,uri);

        try{
            Cat.logEvent(CatConstantsExt.Type_URL_METHOD,request.getMethod(), Message.SUCCESS,request.getRequestURL().toString());
            Cat.logEvent(CatConstantsExt.Type_URL_CLIENT,request.getRemoteHost());

            filterChain.doFilter(servletRequest,servletResponse);

            filterTransaction.setSuccessStatus();
        }catch (Exception e){
            filterTransaction.setStatus(e);
            Cat.logError(e);
        }finally {
            filterTransaction.complete();
        }
    }

    @Override
    public void destroy() {

    }
}
