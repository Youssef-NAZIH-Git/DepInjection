package nazih.youssef.presentation;

import nazih.youssef.metier.IMetier;
import nazih.youssef.metier.MetierImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Presentation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("nazih.youssef.dao", "nazih.youssef.metier");
        IMetier metier = context.getBean(MetierImpl.class);
        System.out.println(metier.calcul());
    }
}
