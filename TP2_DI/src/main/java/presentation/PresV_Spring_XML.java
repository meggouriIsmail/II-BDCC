package presentation;

import metier.UniteCentral;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresV_Spring_XML {
    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("Config.xml");
        UniteCentral uc=context.getBean(UniteCentral.class);
        System.out.println(uc.readData());
    }
}
