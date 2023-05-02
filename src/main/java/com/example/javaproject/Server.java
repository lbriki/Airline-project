package com.example.javaproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started.");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                // Create input and output streams for the socket
                DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

                // Send the menu to the client
                toClient.writeUTF("Menu: 1) About  2)Help 3)Description 4)Rules  5) Exit");

                // Loop until the client chooses to exit
                while (true) {
                    int choice = fromClient.readInt();
                    String message = "";

                    switch (choice) {
                        case 1:
                            message = "\nThis is an Airline java application to manage travel in our company so you can edit \n planes" +
                                    " ,pilotes, passengers and vols  ";
                            break;
                        case 2:
                            message = "\nWelcome to our Airline Java Application! if you have any error touch us user@gmail.com \n  ,Thank you for using our Airline Java Application!";
                            break;
                        case 3:

                            message = "\nWelcome to our Airline Java Application! Our application is designed to help you manage travel in our company.\n" +
                                    "We hope that our application will help make managing air travel easier for \nyou and your team. Thank you for using our Airline Java Application!\n";
                            break;
                        case 4:

                            message = "1)don't forget your id or our pwd\n 2)Use strong passwords \n 3)respect all conditions\n";
                            break;
                        case 5:
                            message ="Exiting ......................................\n";
                            break;
                        default:
                            message = "Invalid choice.";
                    }

                    toClient.writeUTF(message);
                    if (choice == 5) {
                        break;
                    }
                }

                // Close the socket
                socket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException ex) {
            System.err.println("Server exception: " + ex.getMessage());
        }
    }
}


