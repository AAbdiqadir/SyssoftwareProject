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
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class main {

    private static final int PORT = 2220;
    public static void main(String[] args) throws IOException, InterruptedException {
     
        Scanner input = new Scanner(System.in);
//		Console console = System.console();
//user Users = new user("","","");
var processBuilder = new ProcessBuilder();
    	BuiltIns command = new BuiltIns();
         msqldatabase connect = new msqldatabase();
         var homeDir = System.getProperty("user.home");
         
        String username;
        ArrayList<String> users = new ArrayList();
        user User1 = new user("","");
        
        
        user[] Users = new user[2];

        while (true) {
            
            System.out.println("Prompt$ ");
            String Command = input.nextLine().trim();
            
           
            
            if (Command.equals("super addUser")) {

                
                connect.makeJDBCConnection();

               
                log("Create Username");
                String Username = input.nextLine().trim();

                log("Create a password");
                String PassWord = input.nextLine().trim();
                
                log("Create a permission");
                String permission =input.nextLine().toLowerCase().trim();
                
                
                if(permission.equals("standard") || permission.equals("super")){
                   
                connect.addDataToDB(Username, connect.encodePassword(PassWord),permission);
            }
                else{
                log("Wrong permission entered");
                }
                

                
                

            }
            if(Command.equals("super delUser")){
                log("Enter username ");
                    String user1 = input.nextLine().trim();
                    log("Enter  password");
                    String pass1 = input.nextLine().trim();
                    if(User1.Username.equals(user1) &&User1.Password.equals(pass1)){
                        
                    connect.deleteDatafromDB(user1,pass1);
                    }
                    else{
                    log("No user logged in. Please login to your account to make changes");
                    }
            }
            if(Command.equals("super chPass")){
            log("Enter username ");
                    String user1 = input.nextLine();
                    log("Enter  password");
                    String pass1 = input.nextLine();
                    
                    
                    if(User1.Username.equals(user1) &&User1.Password.equals(pass1)){
                        log("Enter  new password");
                    String pass2 = input.nextLine();
                    User1.Password = pass2;
                    connect.updatepassfromDB(user1, pass1, pass2);
                    }
                    else{
                    log("No user logged in. Please login to your account to make changes");
                    }
                    
            }
            if(Command.equals("super chUserType")){
            log("Enter username ");
                    String user1 = input.nextLine();
                    log("Enter  password");
                    String pass1 = input.nextLine();
                    log("Enter  new permission");
                    String permission = input.nextLine().toLowerCase();
                    if(User1.Username.equals(user1) &&User1.Password.equals(pass1)){
                    if(permission.equals("standard") || permission.equals("super")){
                    connect.updatepermissionfromDB(user1, pass1, permission);}
                    }
                    else{
                    log("No user logged in. Please login to your account to make changes");
                    }
                    
            }
            if (Command.equals("login")) {
              if(User1.Username == "" ){
                    log("Enter username login");
                    String user1 = input.nextLine().trim();
                    log("Enter username password");
                    String pass1 = input.nextLine().trim();
                    
                    
                    if (connect.validateUser(user1, pass1)) {
                        User1.Username = user1;
                        User1.Password = pass1;
                        log("++++++++++VALID credentials!++++++++++++");
                    } else {
                        log("----------INVALID credentials!----------");
                    }
                    
                    }
                    else{
                        log("There is already a user logged in");
                    }
//            }
                    // connection close

                }
            if(Command.equals("whoami")){
                if(User1.Username != ""){
                log(User1.Username);}
                else{
                    log("no user found ");
                }
            }
                if (Command.equals("logout")) {
                    
                    if(User1.Username != "" && User1.Password != ""){
                        User1.Username = "";
                    User1.Password = "";
                    log("log out successful");
                    }
                }
                 if(Command.contains("cp ")) {
    	String[] parts1 = Command.split(" ");
        command.copy(parts1[1], parts1[2]);
    	
    	}
    	
      
    
   
   
  
        if(Command.contains("mv ")) {
    	String[] parts1 = Command.split(" ");
        command.move(parts1[1], parts1[2]);
    	    	}
                if(Command.contains("cd ")){
        	if(Command.equals("cd ..")) {
            	if(homeDir.equals(System.getProperty("user.home"))) {
            		System.out.println("home directory is reached");
            	}
            	else {
            		String[] parts1 = homeDir.split("/");
                    String s = "";
                    homeDir = "";
                    for(int i = 1; i < parts1.length-1; i++) {
                     
                   	 homeDir = homeDir + "/" + parts1[i];
                   	
                    }
                    
            	}
            }
        	else {
        String[] parts = Command.split(" ");
        String r= "/" + parts[1];
        
        homeDir = homeDir + r;
        	}
        }
       

        if(Command.equals("showdir")) {
        	System.out.println(homeDir);
        }
        if(Command.equals("ls")) {
        try {
        
        processBuilder.command("/bin/bash", "-c", "ls "+homeDir );
        var process = processBuilder.inheritIO().start();
        

        }
        catch(Exception e) {
        	System.out.println("its is wrong");
        }
       }
        if(Command.contains("mkdir")){
            try{
        String[] parts = Command.split(" ");
        File myObj = new File(parts[1]); 
    if (myObj.mkdir()) { 
      System.out.println("Created the folder: " + myObj.getName());
    } else {
      System.out.println("Failed to create the folder.");
    }
        }
        catch(Exception e) {
        	System.out.println("its is wrong");
        }
            }
        if(Command.contains("rmdir")){
            try{
        String[] parts = Command.split(" ");
        File myObj = new File(parts[1]); 
    if (myObj.delete()) { 
      System.out.println("deleted the folder: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the folder.");
    }
        }
        catch(Exception e) {
        	System.out.println("its is wrong");
        }
            }

    
        }}
         


    private static void log(String message) {
        System.out.println(message);

    }
}

   

