package com.spring.cloud.netflixzuulapigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {

        // pre : before request is executed
        // post : after request is executed
        // error : if error occurred than only filter

        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        // filter logic will come here

        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // business logic will come here
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("Request -----> {} & Request URI -----> {} ", request, request.getRequestURI());

        return null;
    }
}
