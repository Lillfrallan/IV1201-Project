package com.iv1201.group10.springInit.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
public class Thymeleaf implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
  

    /**
     * Configuration for the Thymeleaf template engine.
     *
     * @return SpringTemplateEngine object with a template resolver and Spring Security dialect
     */

    @Bean

    public SpringTemplateEngine temp() {
        SpringTemplateEngine temp = new SpringTemplateEngine();
        temp.setTemplateResolver(templateResolver());
        temp.setEnableSpringELCompiler(true);

        temp.addDialect(new SpringSecurityDialect()); // add Spring Security dialect
        return temp;
    }
    /**
     * Configuration for the SpringResourceTemplateResolver which resolves the HTML templates.
     *
     * @return SpringResourceTemplateResolver object with classpath as prefix, HTML as suffix and UTF-8 encoding
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }


    /**
     * Configuration for the ThymeleafViewResolver which resolves the Thymeleaf views.
     *
     * @return ThymeleafViewResolver object with UTF-8 encoding
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(temp());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
}
