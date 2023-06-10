package com.example.crud_teb;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.SQLException;

public class MainSceneController {

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> peselColumn;

    @FXML
    private TableColumn<?, ?> salaryColumn;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private TableView<?> tableView;

    @FXML
    void addButton() throws SQLException, ClassNotFoundException {

        DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
        Connection connection = dataBaseFunctions.connectToDB();
        dataBaseFunctions.createTable(connection);

    }

    @FXML
    void deleteButton() {

    }

    @FXML
    void editButton() {

    }


}