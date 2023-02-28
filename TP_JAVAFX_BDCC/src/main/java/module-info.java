module com.meggouri.tpjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;

    opens com.meggouri.tp_javafx to javafx.fxml;
    exports com.meggouri.tp_javafx;
    exports com.meggouri.tp_javafx.dao;
    exports com.meggouri.tp_javafx.dao.entities;
    exports com.meggouri.tp_javafx.services;
    exports com.meggouri.tp_javafx.presentation.views;
    exports com.meggouri.tp_javafx.presentation.controllers;
    opens com.meggouri.tp_javafx.presentation.controllers to javafx.fxml;
}