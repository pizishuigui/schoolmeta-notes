module com.schoolmeta.schoolmetanotes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.schoolmeta.schoolmetanotes to javafx.fxml;
    exports com.schoolmeta.schoolmetanotes;
}