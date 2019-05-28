package cn.binarytom.spring.commons.logging.servlet.filter;

import cn.binarytom.spring.commons.logging.LogId;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lieh
 */
@Order(1000)
@Component
public class LogIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // assert request instanceof HttpServletRequest;
        LogId.put(LogId.generate((HttpServletRequest) request));
        try {
            chain.doFilter(request, response);
        } finally {
            LogId.remove();
        }
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig config) throws ServletException {}

}

