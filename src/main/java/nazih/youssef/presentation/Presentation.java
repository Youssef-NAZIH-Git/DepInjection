package nazih.youssef.presentation;

import nazih.youssef.dao.IDao;
import nazih.youssef.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation {
    public static void main(String[] args) {
        try {
            Scanner scanner=new Scanner(new File("config.txt"));
            String daoClassname=scanner.next();
            String metierClassName=scanner.next();
            Class<?> cdao= Class.forName(daoClassname);
            IDao dao= (IDao) cdao.newInstance();
            Class<?> cmetier= Class.forName(metierClassName);
            IMetier metier=(IMetier) cmetier.newInstance();
            Method meth=cmetier.getMethod("setDao",IDao.class);
            meth.invoke(metier,dao);
            System.out.println(metier.calcul());
        } catch (Exception e) { e.printStackTrace(); }
    }
}
