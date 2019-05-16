package model.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ehsan on 8/10/2018.
 */
public class SpringProvider {

    public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/model/util/spring.xml");

    public static ApplicationContext getApplicationContext(){return applicationContext;}
}
