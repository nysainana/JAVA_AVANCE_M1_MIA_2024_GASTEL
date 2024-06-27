module com.java.avance.javaavance2024 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.coreui;
    requires com.jfoenix;
    requires java.desktop;
    requires javafx.swing;

    opens com.java.avance.javaavance2024 to javafx.fxml;
    exports com.java.avance.javaavance2024;
    exports com.java.avance.javaavance2024.ui;
    opens com.java.avance.javaavance2024.ui to javafx.fxml;
}