module MAMS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.initial.main;  // Ensure the main package is exported
    exports com.initial.controller;  // Export the controller package to allow access from FXML

    opens com.initial.main to javafx.fxml;  // This is necessary for JavaFX to instantiate classes with reflection
    opens com.initial.controller to javafx.fxml;  // Open the controller package for FXML access
}
