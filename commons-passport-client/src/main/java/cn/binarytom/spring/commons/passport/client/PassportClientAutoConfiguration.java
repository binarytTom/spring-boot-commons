package cn.binarytom.spring.commons.passport.client;

import cn.binarytom.spring.commons.passport.client.inteceptor.FuncAuthInterceptor;
import cn.binarytom.spring.commons.passport.client.inteceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lieh
 * @date 2019-05-14 21:12
 **/
@Configuration
public class PassportClientAutoConfiguration {
    private static final Logger logger =
            LoggerFactory.getLogger(PassportClientAutoConfiguration.class);

    @Bean
    public LoginInterceptor loginInterceptor() {
        logger.info("[Bean]----->loginInterceptor");
        return new LoginInterceptor();
    }

    @Bean
    public FuncAuthInterceptor funcAuthInterceptor() {
        logger.info("[Bean]----->apiAuthInterceptor");
        return new FuncAuthInterceptor();
    }

    @Component
    class LoginConfigurerAdapter implements WebMvcConfigurer {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            logger.info("[INIT]:--------> PassportClient");
            processInterceptorRegistration(registry.addInterceptor(loginInterceptor()),
                    registry.addInterceptor(funcAuthInterceptor()));
        }

        /**
         * process interceptor registration
         * addPatterns
         * excludePathPatterns user/**
         * excludePathPatterns swagger/**
         * @param registrations
         */
        private void processInterceptorRegistration (InterceptorRegistration... registrations) {
            if (registrations == null) {
                return;
            }
            for (InterceptorRegistration registration : registrations) {
                registration.addPathPatterns("/**")
                        .excludePathPatterns("/user/login", "/user/callback", "/user/verification")
                        .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
            }

        }

        /**
         * override addResourceHandlers for swagger
         * @param registry
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
            registry.addResourceHandler("/templates/**.js")
                    .addResourceLocations("classpath:/templates/");
            registry.addResourceHandler("/templates/**.css")
                    .addResourceLocations("classpath:/templates/");
            //其他静态资源
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/");
            //swagger增加url映射
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
}
