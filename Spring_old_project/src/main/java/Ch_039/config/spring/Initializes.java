package Ch_039.config.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by igortsapyak on 28.04.16.
 */

//клас-аналог web.xml
public class Initializes extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{DispatcherServlet.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
