/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpsls;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable
{
    
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername; 
    private DataInputStream DataInput;
    private DataOutputStream DataOutput;
    private int playerID;
    private int Player1Button;
        private int Player2Button;
        private int Player3Button;
        
        
    public ClientHandler(Socket socket, int id)
    {
        playerID = id;
        try{
            this.socket = socket;
             this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has entered the game!");
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        
    }
    
    
    
    
    @Override
    public void run()
    {
        String messageFromClient;
       
        while(socket.isConnected())
        {
            try
            {
               messageFromClient = bufferedReader.readLine();
               broadcastMessage(messageFromClient);
               if (playerID == 1)
            {
                Player1Button = DataInput.readInt();
            }
            } catch (IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
            
        }
    }
    
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers){
            
            try{
               if (!clientHandler.clientUsername.equals(clientUsername)){
                   clientHandler.bufferedWriter.write(messageToSend);
                      clientHandler.bufferedWriter.newLine();
                      clientHandler.bufferedWriter.flush();
            }
          } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    } 
    
    
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + "has left the game");
    
    }
    
    
    
    
    public void closeEverything (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
