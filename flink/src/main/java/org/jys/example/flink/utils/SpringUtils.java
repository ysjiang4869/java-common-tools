package org.jys.example.flink.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author YueSong Jiang
 * @date 2019/3/15
 */
@Component
public class SpringUtils implements EmbeddedValueResolverAware, ApplicationContextAware {

    private final Environment env;

    private static ApplicationContext applicationContext;
    private static StringValueResolver stringValueResolver;
    private static final String KEY_FORMAT = "${%s}";

    @Autowired
    public SpringUtils(Environment env) {
        this.env = env;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        if (SpringUtils.stringValueResolver == null) {
            SpringUtils.stringValueResolver = stringValueResolver;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> aClass) {
        return applicationContext.getBean(aClass);
    }

    public static <T> T getBean(String name, Class<T> aClass) {
        return applicationContext.getBean(name, aClass);
    }

    public static String getProperty(String key) {
        return stringValueResolver.resolveStringValue(String.format(KEY_FORMAT, key));
    }
}
