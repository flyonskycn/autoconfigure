package org.flyonsky.boot.autoconfigure.cat;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dianping.cat.Cat;

@Configuration
@ConditionalOnClass({Cat.class, Filter.class})
public class CatServletAutoConfigure {
	
    @Bean
    public FilterRegistrationBean<CatServletFilter> catContextFilter(){
        FilterRegistrationBean<CatServletFilter> registration = new FilterRegistrationBean<CatServletFilter>();
        CatServletFilter filter = new CatServletFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }
}
