module by.arsy.techtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens by.arsy.techtest to javafx.fxml;
    exports by.arsy.techtest;
}