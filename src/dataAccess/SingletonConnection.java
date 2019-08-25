package dataAccess;

import exception.DbConnectionException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws DbConnectionException {
        if (connection == null) {
            // ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC     https://www.developpez.net/forums/d1876029/java/general-java/server-time-zone-non-reconnu/
            // ?useSSL=false     cours
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/incocar?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root","mdpSQL007/*-");
            } catch (SQLException e) {
                throw new DbConnectionException();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            if(connection != null) connection.close();
        }
        catch (SQLException exception){
            JOptionPane.showMessageDialog(null,"Erreur fermeture connection");
        }
    }
}