package dao;

import org.springframework.stereotype.Component;

@Component
public class HdmiVgaAdapter implements VGA{
    private HDMI hdmi;
    public HdmiVgaAdapter(HDMI hdmi) {
        this.hdmi = hdmi;
    }

    @Override
     public void print(String message) {
        byte[] b=message.getBytes();
        hdmi.print(b);
     }
}
