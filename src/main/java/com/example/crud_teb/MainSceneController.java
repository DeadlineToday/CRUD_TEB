package com.example.crud_teb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {

    DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
    Connection connection;
    ObservableList<UserInformation> userInformationObservableList = FXCollections.observableArrayList();
    Validation validation = new Validation();

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

        messageLabel.setText(null);


        if (validation.isValidNameAndSurname(nameTextField.getText()) &&
                validation.isValidNameAndSurname(surnameTextField.getText()) &&
                validation.isValidPesel(peselTextField.getText()) &&
                validation.isValidSalary(salaryTextField.getText())) {

            DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
            Connection connection = dataBaseFunctions.connectToDB();
            if (dataBaseFunctions.addUser(connection, nameTextField.getText(), surnameTextField.getText(), peselTextField.getText(), Double.parseDouble(salaryTextField.getText()))) {

                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("User saved");

                clearTextFields();
                clearBackgroundsColor();
                refreshTable();

            } else {

                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Try again");
            }
        } else {

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Try again");

            if (!nameTextField.getText().isEmpty() ) {
                if (validation.isValidNameAndSurname(nameTextField.getText())) {
                    nameTextField.setStyle("-fx-background-radius: 0;");
                } else {
                    nameTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!surnameTextField.getText().isEmpty()) {
                if (validation.isValidNameAndSurname(surnameTextField.getText())) {
                    surnameTextField.setStyle("-fx-background-radius: 0;");
                } else {
                    surnameTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!peselTextField.getText().isEmpty()) {
                if (validation.isValidPesel(peselTextField.getText())) {
                    peselTextField.setStyle("-fx-background-radius: 0;");
                } else {
                    peselTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!salaryTextField.getText().isEmpty()) {
                if (validation.isValidSalary(salaryTextField.getText())) {
                    salaryTextField.setStyle("-fx-background-radius: 0;");
                } else {
                    salaryTextField.setStyle("-fx-border-color: red;");
                }
            }
        }
    }

    @FXML
    void deleteButton() throws SQLException {

        messageLabel.setText(null);

        UserInformation userInformation = tableView.getSelectionModel().getSelectedItem();



        connection = dataBaseFunctions.connectToDB();

        if (tableView.getSelectionModel().getSelectedItem() == null) {

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("No item selected");
        } else {

            Alert customConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            customConfirmation.setTitle("Custom Confirmation Dialog");
            customConfirmation.setHeaderText(null);
            customConfirmation.setContentText("Do you really want to delete user id " + userInformation.getId() + "?");

            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            customConfirmation.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> resultCustom = customConfirmation.showAndWait();

            if (resultCustom.get() == yesButton) {

                dataBaseFunctions.deleteUser(connection, userInformation.getId());
                refreshTable();

                messageLabel.setTextFill(Color.GREEN);
                messageLabel.setText("User deleted");
            }
        }
    }

    @FXML
    void editButton() throws SQLException {

        messageLabel.setText(null);
        clearBackgroundsColor();

        UserInformation userInformation = tableView.getSelectionModel().getSelectedItem();

        connection = dataBaseFunctions.connectToDB();

        if (tableView.getSelectionModel().getSelectedItem() == null) {

            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("No items selected");
        } else {

            if (!nameTextField.getText().isEmpty() ) {
                if (validation.isValidNameAndSurname(nameTextField.getText())) {
                    dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "name", nameTextField.getText());
                    nameTextField.setStyle("-fx-background-radius: 0;");
                    nameTextField.clear();
                } else {
                    nameTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!surnameTextField.getText().isEmpty()) {
                if (validation.isValidNameAndSurname(surnameTextField.getText())) {
                    dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "surname", surnameTextField.getText());
                    surnameTextField.setStyle("-fx-background-radius: 0;");
                    surnameTextField.clear();
                } else {
                    surnameTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!peselTextField.getText().isEmpty()) {
                if (validation.isValidPesel(peselTextField.getText())) {
                    dataBaseFunctions.updateUserInfo(connection, userInformation.getId(), "pesel", peselTextField.getText());
                    peselTextField.setStyle("-fx-background-radius: 0;");
                    peselTextField.clear();
                } else {
                    peselTextField.setStyle("-fx-border-color: red;");
                }
            }

            if (!salaryTextField.getText().isEmpty()) {
                if (validation.isValidSalary(salaryTextField.getText())) {
                    dataBaseFunctions.updateUserSalary(connection, userInformation.getId(), Double.parseDouble(salaryTextField.getText()));
                    salaryTextField.setStyle("-fx-background-radius: 0;");
                    salaryTextField.clear();
                } else {
                    salaryTextField.setStyle("-fx-border-color: red;");
                }
            }
        }

        refreshTable();
    }

    @FXML
    void refreshButton() throws SQLException {

        messageLabel.setText(null);
        refreshTable();
        clearBackgroundsColor();
        clearTextFields();
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

    public void clearBackgroundsColor() {
        nameTextField.setStyle("-fx-background-radius: 0;");
        surnameTextField.setStyle("-fx-background-radius: 0;");
        peselTextField.setStyle("-fx-background-radius: 0;");
        salaryTextField.setStyle("-fx-background-radius: 0;");
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