package com.example.crud_teb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

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
    private Label messageLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    void addButton() throws SQLException {

        DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
        Connection connection = dataBaseFunctions.connectToDB();
        if (dataBaseFunctions.addUser(connection, nameTextField.getText(), surnameTextField.getText(), peselTextField.getText(), Double.parseDouble(salaryTextField.getText()))) {

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("User saved");
        } else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Try again");
        }

        clearTextFields();
        refreshTable();
    }

    @FXML
    void deleteButton() throws SQLException {

        UserInformation userInformation = tableView.getSelectionModel().getSelectedItem();

        connection = dataBaseFunctions.connectToDB();

        if (tableView.getSelectionModel().getSelectedItem() == null) {

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("No item selected");
        } else {

            dataBaseFunctions.deleteUser(connection, userInformation.getId());
            refreshTable();
        }
    }

    @FXML
    void editButton() throws SQLException {

        UserInformation userInformation = tableView.getSelectionModel().getSelectedItem();

        connection = dataBaseFunctions.connectToDB();

        if (tableView.getSelectionModel().getSelectedItem() == null) {

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("No item selected");
        } else {

            if (nameTextField != null) {
                dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "name", nameTextField.getText());
            }

            if (surnameTextField != null) {
                dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "surname", surnameTextField.getText());
            }

            if (peselTextField != null) {
                dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "pesel", peselTextField.getText());
            }

            if (salaryTextField != null) {
                dataBaseFunctions.updateUserSalary(connection, userInformation.getId(), Double.parseDouble(salaryTextField.getText()));
            }
        }

        clearTextFields();
        refreshTable();
    }

    @FXML
    void refreshButton() throws SQLException {

        refreshTable();

    }
    @FXML
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

    public void clearTextFields() {
        nameTextField.clear();
        surnameTextField.clear();
        peselTextField.clear();
        salaryTextField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            refreshTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}