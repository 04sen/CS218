/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package rpsls;

import java.io.*;
import java.net.*;

public class server {

    
    public static void main(String args[]) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;
        
        serverSocket = new ServerSocket (1234);
        
        while (true) {
            try{
                socket = serverSocket.accept();
                
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter (socket.getOutputStream());
                
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                
                while (true) {
                    String PlayerMove = bufferedReader.readLine();
                    
                    System.out.println("Client: " + PlayerMove);
                    
                    bufferedWriter.write ("Move saved");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    
                    if (PlayerMove.equalsIgnoreCase("Quit"))
                        break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
