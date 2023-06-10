package com.example.crud_teb;

import java.sql.*;

public class DataBaseFunctions {

    Statement statement;
    String query;
    ResultSet resultSet;

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
                "salary DECIMAL(10,2), " +
                "PRIMARY KEY(id))";

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public boolean addUser(Connection connection, String name, String surname, String pesel, double salary) throws SQLException {

        statement = connection.createStatement();

        query = "SELECT MAX(id) AS maxId FROM user_information";
        resultSet = statement.executeQuery(query);
        resultSet.next();

        try {
        query = String.format("INSERT INTO user_information (id, name, surname, pesel, salary)VALUES ('%s', '%s','%s', '%s', '%s')", resultSet.getInt("maxId") + 1, name, surname, pesel, salary );

        statement.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public ResultSet selectInformation(Connection connection) throws SQLException {

        statement = connection.createStatement();

        query = "SELECT * FROM user_information";

        resultSet = statement.executeQuery(query);

        return resultSet;
    }

}
