package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class InstanceDynamic {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));

        String daoClassName = scanner.next();
        Class classDao = Class.forName(daoClassName);
        IDao dao = (IDao) classDao.getConstructor().newInstance();

        String metierClassName = scanner.next();
        Class metierClass = Class.forName(metierClassName);
        IMetier metier = (IMetier) metierClass.getConstructor().newInstance();

        Method method = metierClass.getMethod("setDao", IDao.class);
        method.invoke(metier, dao);

        System.out.println(metier.calcule());
    }
}
