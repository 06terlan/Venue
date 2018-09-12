package bll.dal;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBConnection {

    private static DBConnection instance = new DBConnection();

    private static final String DRIVER = "com.mysql.jdbc.Driver";
//    private static final String URL = "dbc:mysql://localhost/venue?user=minty&password=greatsqldb";
    private static final String URL = "jdbc:mysql://localhost:3306/venue?user=root&password=r00t";

    private Connection connection;
    private Statement statement;

    private DBConnection() {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        return instance;
    }

    private void connect() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName(DRIVER).newInstance();
        connection = DriverManager.getConnection(URL);
    }

    public void update(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
    }

    public ResultSet executeQuery(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

}
