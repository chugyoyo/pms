package com.xin.configure;

import com.xin.config.AppConfig;
import com.xin.web.AdminServiceInterceptor;
import com.xin.web.WebServiceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * web配置<br>
 * 用于注入拦截器
 *
 * @author tanghuang 2020年02月25日
 */
@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurationSupport {

    @Resource
    private AppConfig appConfig;

    @Bean
    WebServiceInterceptor webServiceInterceptor() {
        return new WebServiceInterceptor();
    }

    @Bean
    AdminServiceInterceptor adminServiceInterceptor() {
        return new AdminServiceInterceptor();
    }


    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // api的拦截规则
        // 没有拦截的就会导致token取到的是client
        registry.addInterceptor(webServiceInterceptor())
                .addPathPatterns("/client/**","/project/**","/task/**")
                .excludePathPatterns("/client/add","/client/login");
        // 后台管理拦截规则规则
        registry.addInterceptor(adminServiceInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");
    }


    /**
     * 需要重新指定静态资源，解决swagger与WebMvcConfigurationSupport冲突导致404的问题
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 测试时上传路径转发
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + appConfig.getUploadBasePath());
        super.addResourceHandlers(registry);
    }

}
