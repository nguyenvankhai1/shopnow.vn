// 
// Decompiled by Procyon v0.5.36
// 

package com.xdevs.shopnow.enduser.config;

import com.xdevs.shopnow.enduser.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

public class CorsConfig
{
    @Bean
    public FilterRegistrationBean corsFilter() {
        final FilterRegistrationBean bean = new FilterRegistrationBean((Filter)new CORSFilter(), new ServletRegistrationBean[0]);
        bean.setOrder(0);
        return bean;
    }
}
