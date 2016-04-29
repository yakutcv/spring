package Ch_039.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by igortsapyak on 28.04.16.
 */
//конфігураційний клас спрінга (Деспатчер Сервлет)
@Configuration
@EnableWebMvc
@EnableWebSecurity
@ComponentScan(basePackages = {"Ch_039.Controlers"})
public class DispatcherServlet extends WebMvcConfigurerAdapter{

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        return new InternalResourceViewResolver(){{
            setPrefix("/WEB-INF/Pages/");
            setSuffix(".jsp");
            setViewClass(JstlView.class);
        }};
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*").addResourceLocations("/");
    }
}
