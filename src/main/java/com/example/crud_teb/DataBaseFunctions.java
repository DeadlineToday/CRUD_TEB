package com.example.crud_teb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseFunctions {

    Statement statement;
    String query;

    public Connection connectToDB() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

            throw new RuntimeException(e);
        }
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "crud_teb_db", "postgres","1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*    public void createTable(Connection connection) throws SQLException {

        statement = connection.createStatement();

        query = "CREATE TABLE user_information (" +
                "id INTEGER, " +
                "name VARCHAR(50), " +
                "surname VARCHAR(50), " +
                "pesel VARCHAR(11), " +
                "salary DOUBLE PRECISION, " +
                "PRIMARY KEY(id))";

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

}
