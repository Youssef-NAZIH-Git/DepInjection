package nazih.youssef.presentation;

import nazih.youssef.dao.IDao;
import nazih.youssef.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-ioc.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println(metier.calcul());
    }
}
