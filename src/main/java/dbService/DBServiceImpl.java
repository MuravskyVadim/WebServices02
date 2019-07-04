package dbService;

import base.DBService;
import base.UserProfile;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServiceImpl implements DBService {
    private static Connection connection;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/mydb?useUnicode=true&" +
        "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345";

    public DBServiceImpl() {
        getConnection();
    }

    public void create() throws DBException {
        try {
            System.out.println("Creating table users if needed");
            (new UsersDAO(connection)).createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public UserProfile getUser(String login) throws DBException {
        try {
            UsersDAO dao = new UsersDAO(connection);
            UsersDataSet dataSet = dao.get(login);
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long addUser(UserProfile userProfile) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.insertUser(userProfile);
            connection.commit();
            return dao.getUserById(userProfile.getLogin());
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    private static void getConnection() {
        try {
            System.out.println("Start connection");
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection successful");
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | SQLException e) {
            System.out.println("SQL exception " + e.getMessage());
            e.printStackTrace();
        }
    }
}
