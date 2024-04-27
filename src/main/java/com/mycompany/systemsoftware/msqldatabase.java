/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.systemsoftware;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;



/**
 *
 * @author ntu-user
 */
public class msqldatabase extends  Httpserver{
     private String dataBaseName = "COMP20081";
     private String dataBaseTableName = "Users";
     private String userName = "admin";
     private String userPassword = "t4eSTAuIrOSt";
      Connection conn = null;
     //Statement  s = null;
      PreparedStatement prepareStat = null;
    public void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            log("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, userName, userPassword);
            if (conn != null) {
                log("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            //e.printStackTrace();
            //return;
        }
    }

   
         public  String encodePassword(String plainPassword) {
        byte[] bPass = plainPassword.getBytes(StandardCharsets.UTF_8);
        byte[] passBase64 = Base64.getEncoder().encode(bPass);
        String encodedPassword = new String(passBase64, StandardCharsets.UTF_8);
        return encodedPassword;
    }
          public  String decodePassword(String encodedPassword) {
        String decodedString = new String(Base64.getDecoder().decode(encodedPassword));
        return decodedString;
    }
    public  void addDataToDB(String user, String password, String permission) {

        try {
            String insertQueryStatement = "INSERT  IGNORE INTO " + dataBaseTableName + " VALUES  (?,?,?)";

            prepareStat = conn.prepareStatement(insertQueryStatement);
            prepareStat.setString(1, user);
            prepareStat.setString(2, password);
            prepareStat.setString(3, permission);
            // execute insert SQL statement
            prepareStat.executeUpdate();
            log(user + " added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void deleteDatafromDB(String user, String password){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, userName, userPassword);
        String deleteUser = "DELETE FROM " + dataBaseTableName + " WHERE User = ? and Password = ?"  ;
        prepareStat = conn.prepareStatement(deleteUser);
        prepareStat.setString(1, user);
        prepareStat.setString(2, encodePassword(password));
        
        
        prepareStat.executeUpdate();
            
         } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void updatepassfromDB(String user, String password, String newpassword ){
        try{
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, userName, userPassword);
        String updatepass = "UPDATE " + dataBaseTableName + " SET Password = ? " + "WHERE User = ?" ; 
        prepareStat = conn.prepareStatement(updatepass);
        prepareStat.setString(1, encodePassword(newpassword));
        prepareStat.setString(2, user);
        prepareStat.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public  void updatepermissionfromDB(String user, String password, String newpermission){
        try{
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, userName, userPassword);
        String updatepass = "UPDATE " + dataBaseTableName + " SET Permission = ? " + "WHERE User = ?" ; 
        prepareStat = conn.prepareStatement(updatepass);
        prepareStat.setString(1, newpermission);
        prepareStat.setString(2, user);
        prepareStat.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
      
        public  boolean validateUser(String user, String pass) {
        try {
            // MySQL Select Query Tutorial
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, userName, userPassword);
            String getQueryStatement = "SELECT User, Password , Permission FROM " + dataBaseTableName;

            prepareStat = conn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = prepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                if (user.equals(rs.getString("User")) && pass.equals(decodePassword(rs.getString("Password")))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return false;
    }
            private void log(String message) {
        System.out.println(message);

    }
}
