package com.meggouri.tp_javafx.presentation.views;

import com.meggouri.tp_javafx.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    public void showDashboard() throws IOException {
        TabPane root = FXMLLoader.load(Main.class.getResource("fxml/main_pane.fxml"));
        Scene scene= null;
        try{
            scene = new Scene(root);
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();

        stage.setTitle("TP JAVA FX");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
