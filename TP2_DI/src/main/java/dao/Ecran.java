package dao;

import org.springframework.stereotype.Component;

@Component
public class Ecran implements VGA{
    @Override
    public void print(String message) {
        System.out.println("Ecran : " +message);
    }
}
