/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BLVCK
 */
public class MyDb {

    final String URL = "jdbc:mysql://127.0.0.1:3306/achwek";
    final String USER = "root";
    final String PASSWORD = "";
    private static Connection cnx;
    static private MyDb Instance;

    public MyDb() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("cnx reussie");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public static MyDb getInstance() {

        if (Instance == null) {
            Instance = new MyDb();
        }
        return Instance;

    }

    public static Connection getCnx() {
        return cnx;
    }
    
}
