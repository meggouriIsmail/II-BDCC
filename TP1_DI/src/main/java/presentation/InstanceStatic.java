package presentation;

import dao.DaoImpl;
import dao.DaoImpl2;
import metier.MetierImpl;

public class InstanceStatic {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcule());

        metier.setDao(new DaoImpl2());
        System.out.println(metier.calcule());
    }
}
