package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Souris implements  USB{
    @Override
    public int read() {
        System.out.println("Souris");
        return (int) (Math.random()*100);
    }
}
