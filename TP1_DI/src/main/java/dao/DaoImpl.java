package dao;

import org.springframework.stereotype.Component;

//@Component
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Version db");
        double data = 42;
        return data;
    }
}
