package com.meggouri.tp_javafx;

import com.meggouri.tp_javafx.presentation.views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();

        viewFactory.showDashboard();
    }

    public static void main(String[] args) {
        launch();
    }
}