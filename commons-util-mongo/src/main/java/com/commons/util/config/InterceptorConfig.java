//package com.commons.util.config;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.stereotype.Component;
//        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    private ServiceConfig serviceConfig;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(serviceConfig);
////                .addPathPatterns("/**")
////                .excludePathPatterns("/api/userLogin/**", "/api/userRegistration/**");
//    }
//
//}
