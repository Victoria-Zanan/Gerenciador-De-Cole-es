module start {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
   
    opens controller to javafx.fxml;
    opens model to javafx.base;
    exports controller;
    exports start;
}
