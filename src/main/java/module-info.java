module com.example.crud_teb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.crud_teb to javafx.fxml;
    exports com.example.crud_teb;
}