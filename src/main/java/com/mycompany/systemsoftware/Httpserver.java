
package com.mycompany.systemsoftware;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStream;
import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Httpserver extends Thread {
    private final int port = 1110;
    public  static PrintWriter out;
//    public static void main(String[] args) {
//        Httpserver gtp = new Httpserver();
//        gtp.start();
//    }
    public void run() {
        try {
            var processBuilder = new ProcessBuilder();
    	BuiltIns command = new BuiltIns();
         msqldatabase connect = new msqldatabase();
         var homeDir = System.getProperty("user.home");
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server is listening port: " + port);
        boolean shutdown = true;
        while (shutdown) {
            Socket socket = server.accept();
            InputStream is = socket.getInputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line;
            line = in.readLine();
            String auxLine = line;
            line = "";
            //data
            int postDataI = -1;
            while ((line = in.readLine()) != null && (line.length() != 0)) {
                System.out.println(line);
                if (line.indexOf("Content-Length:") > -1) {
                    postDataI = new Integer(line
                            .substring(
                                line.indexOf("Content-Length:") + 16,
                                line.length())).intValue();
                    }
                }
                String postData = "";
                for (int i = 0; i < postDataI; i++) {
                    int intParser = in.read();
                    postData += (char) intParser;
                }
                if(line.equals("ls")) {
        try {
        
        processBuilder.command("/bin/bash", "-c", "ls "+homeDir );
        var process = processBuilder.inheritIO().start();
        

        }
        catch(Exception e) {
        	System.out.println("its is wrong");
        }
       }
                // replace + by " "
                int index=postData.indexOf('+');
                while(index>-1){
             
                    postData = postData.substring(0, index) + ' ' + postData.substring(index + 1);
                    index=postData.indexOf('+');
                } 
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html; charset=utf-8");
                out.println("");
                out.println("<H1>Systems Software - Echo server</H1>");
                out.println("<H2>Example of a echo server</H2>");
                out.println("<p style=\"margin-left:8px\">GET->" + auxLine + "</H1>");
                out.println("<p style=\"margin-left:8px\" >Post->" + postData + "</p>");
                out.println("<form name=\"input\" action=\"imback\" method=\"post\">");
                out.println("Input: <input type=\"text\" name=\"user\"><input type=\"submit\" value=\"Submit\"></form>");                 

                if (auxLine.indexOf("?shutdown") > -1) {
                    shutdown = false;
                }
                out.close();
                socket.close();
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
