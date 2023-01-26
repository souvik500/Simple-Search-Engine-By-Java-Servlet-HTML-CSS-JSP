package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection_MySQL {
    static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        String db = "search"; //Database Name
        String user = "root"; //UserName
        String pwd = "root"; //Password
        return getConnection(db, user, pwd); // overLoading function of previous one
    }
    private static Connection getConnection(String db, String user, String pwd){
        try{
            /*MySQL JDBC connector library(External)---> connect mysql database */
            /*DriverManager which JDBC drivers to try to make Connections with.
            * The easiest way to do this is to use Class.forName() on the class that implements
            * the java.sql.Driver interface. With MySQL Connector/J,
            * the name of this class is com.mysql.cj.jdbc.Driver.
            * With this method, you could use an external configuration file to supply the driver
            * class name and driver parameters to use when connecting to a database.
            * */
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Driver class
            //The method DriverManager.getConnection(String url, String user, String password) is not used.
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pwd);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return connection;
    }
}