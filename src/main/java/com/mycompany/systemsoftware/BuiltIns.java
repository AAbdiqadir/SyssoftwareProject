/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.systemsoftware;

/**
 *
 * @author ntu-user
 */

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.locks.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.file.Files;
public class BuiltIns {
    public  void move(String OlderFile, String NewerFile){
      try {
            	File myObj = new File(OlderFile);
                File myObjs = new File(NewerFile);
            	if (myObj.exists()) {
            		
            		myObjs.delete();
            		  Files.copy(myObj.toPath(), myObjs.toPath());
            		  myObj.delete();
            	  }
            	else {
            		System.out.println("File not found");
            	}}
            	  catch(Exception e) {
            		  System.out.println("Error file not found");	  }
            	}
    public void copy(String OlderFile, String NewerFile){
        
        	try {
            	File myObj = new File(OlderFile);
                File myObjs = new File(NewerFile);
            	if (myObj.exists()) {
            		
            		myObjs.delete();
            		  Files.copy(myObj.toPath(), myObjs.toPath());
            		  
            	  }
            	else {
            		System.out.println("File not found");
            	}}
            	  catch(Exception e) {
            		  System.out.println("Error file not found");	  }
            	}
}
