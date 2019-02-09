package org.flyonsky.boot.autoconfigure.cat.hystrix;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class CatWrappedCallable<T> implements Callable<T>{

    private final Callable<T> target;
    private final RequestAttributes requestAttributes;

    public CatWrappedCallable(Callable<T> target, RequestAttributes requestAttributes) {
        this.target = target;
        this.requestAttributes = requestAttributes;
    }

    @Override
    public T call() throws Exception {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            return target.call();
        }finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}