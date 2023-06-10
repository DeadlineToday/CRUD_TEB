package com.example.crud_teb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainSceneController {

    DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
    Connection connection;
    ObservableList<UserInformation> userInformationObservableList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<UserInformation, String> idColumn;

    @FXML
    private TableColumn<UserInformation, String> nameColumn;

    @FXML
    private TableColumn<UserInformation, String> peselColumn;

    @FXML
    private TableColumn<UserInformation, String> salaryColumn;

    @FXML
    private TableColumn<UserInformation, String> surnameColumn;

    @FXML
    private TableView<UserInformation> tableView;



    @FXML
    void addButton() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-user-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 170, 220);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Order");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteButton() {

    }

    @FXML
    void editButton() throws SQLException {


    }

    @FXML
    void refreshButton() throws SQLException {

        refreshTable();

    }

    public void refreshTable() throws SQLException {
        userInformationObservableList.clear();

        connection = dataBaseFunctions.connectToDB();
        ResultSet resultSet = dataBaseFunctions.selectInformation(connection);

        while (resultSet.next()) {

            userInformationObservableList.add(new UserInformation(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("pesel"),
                    resultSet.getDouble("salary")
            ));

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        }

        tableView.setItems(userInformationObservableList);
    }


}