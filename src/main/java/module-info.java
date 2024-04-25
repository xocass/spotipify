module spoti.pruebabd {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens gui to javafx.fxml;
    exports gui;
}