package org.flyonsky.boot.autoconfigure.cat;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

@Configuration
@ConditionalOnClass({Cat.class})
public class CatAspectAutoConfigure {
	
	@Component
	@Aspect
	public static class CatAspect{
		
		@Around(value = "@annotation(org.flyonsky.boot.autoconfigure.cat.CatAnnotation)")
		public Object aroundMethod(ProceedingJoinPoint pjp) {
			MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
			Method method = joinPointObject.getMethod();
			
			Transaction t = Cat.newTransaction(CatConstantsExt.Type_Service_METHOD, method.getName());
			Object obj = null;

			try {
				obj = pjp.proceed();
				t.setSuccessStatus();
			} catch (Throwable e) {
				t.setStatus(e);
				Cat.logError(e);
			} finally {
				t.complete();
			}
			return obj;
		}
	}
}
