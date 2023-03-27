package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("version capteurs");
        double data = 50;
        return data;
    }
}
