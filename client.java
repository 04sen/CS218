/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpsls;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
        
        //declare and initialize socket 
	private Socket	socket;
	private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private String username;
        private int playerID;
        
        
        
        public client (Socket socket, String username){
            try
            {
                this.socket = socket;
                this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.username = username;
            } 
            catch (IOException e) 
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
        
        public void sendMessage()
        {
            try
            {

                bufferedWriter.write(username);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                Scanner scanner = new Scanner (System.in);
                while(socket.isConnected())
                {
                    String messageToSend = scanner.nextLine();
                    bufferedWriter.write(username + ": " + messageToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } 
            catch (IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
        
        public void listenForMessage()
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    String msgFromClient;
                    
                    while(socket.isConnected())
                    {
                        try
                        {
                            msgFromClient = bufferedReader.readLine();
                            System.out.println(msgFromClient);
                            server.Game();
                            if (username.equals("Quit Game"))
                            {
                                socket.close();
                                System.out.println("Connection closed");
                            }
                                
                        } catch (IOException e)
                        {
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
//                        if (playerID == 1) 
//                        {
//                            player1Button = DataInput.readInt();
//                        }
                    }
                }

            }).start(); 
        }
        
        public void closeEverything (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
        {
            try 
            {
                if (bufferedReader != null)
                {
                    bufferedReader.close();
                }
                if (bufferedWriter != null)
                {
                    bufferedWriter.close();
                }
                if (socket != null)
                {
                    socket.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
        
        public static void main(String[] args) throws IOException
        {
            Scanner scanner = new Scanner (System.in);
            System.out.println("Enter your game name");
            String username = scanner.nextLine();
            Socket socket = new Socket("localhost", 1234);
            client client = new client (socket, username);
            client.listenForMessage();
            client.sendMessage();
        }      
}
        