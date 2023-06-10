package com.example.crud_teb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.SQLException;

public class AddUserController {

    @FXML
    private Label messageLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    void addUserButton() throws SQLException {

        DataBaseFunctions dataBaseFunctions = new DataBaseFunctions();
        Connection connection = dataBaseFunctions.connectToDB();
        if (dataBaseFunctions.addUser(connection, nameTextField.getText(), surnameTextField.getText(), peselTextField.getText(), Double.parseDouble(salaryTextField.getText()))) {

            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("User saved");
        } else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Try again");
        }

        nameTextField.clear();
        surnameTextField.clear();
        peselTextField.clear();
        salaryTextField.clear();

    }

}
