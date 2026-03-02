package demoapp;

import demoapp.metier.IMetier;
import framework.AnnotationApplicationContext;
import framework.ApplicationContext;

public class PresAnnotations {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationApplicationContext("demoapp");
        IMetier metier = (IMetier) ctx.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
