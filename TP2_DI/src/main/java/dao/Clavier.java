package dao;

import org.springframework.stereotype.Component;

@Component
public class Clavier implements USB{
    @Override
    public int read() {
        System.out.println("Clavier");
        return (int) (Math.random()*100);

    }
}
