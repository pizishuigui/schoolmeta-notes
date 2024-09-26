module com.schoolmeta.schoolmetanotes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.schoolmeta.notes to javafx.fxml;
    exports com.schoolmeta.notes;
}