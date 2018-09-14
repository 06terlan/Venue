package bll.dal;


import java.sql.*;

public class DBConnection {

    private static DBConnection instance = new DBConnection();

    private static final String DRIVER = DB.DRIVER;
    private static final String URL = DB.URL;

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
    	Class.forName(DB.DRIVER).newInstance();
        connection = DriverManager.getConnection(DB.URL);
    }

    public int update(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKey = statement.getGeneratedKeys();
        if(generatedKey.next()) {
            return generatedKey.getInt(1);
        }else  {
            return 1;
        }
    }

    public ResultSet executeQuery(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

}
