module com.java.avance.javaavance2024 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.jfoenix;

    opens com.java.avance.javaavance2024 to javafx.fxml;
    exports com.java.avance.javaavance2024;
    exports com.java.avance.javaavance2024.ui;
    opens com.java.avance.javaavance2024.ui to javafx.fxml;
}