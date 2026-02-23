package nazih.youssef.presentation;

import nazih.youssef.dao.DaoImpl;
import nazih.youssef.metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        DaoImpl dao=new DaoImpl();
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
