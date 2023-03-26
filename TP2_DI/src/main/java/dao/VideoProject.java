package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class VideoProject implements VGA{
    @Override
    public void print(String message) {
        System.out.println("video project: " +message);
    }
}
