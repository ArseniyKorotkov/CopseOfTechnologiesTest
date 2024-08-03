module by.arsy.techtest {
    requires javafx.controls;
    requires javafx.fxml;


    exports by.arsy.techtest;
    opens by.arsy.techtest to javafx.fxml;
    exports by.arsy.techtest.entity;
    opens by.arsy.techtest.entity to javafx.fxml;
    exports by.arsy.techtest.controller;
    opens by.arsy.techtest.controller to javafx.fxml;
}