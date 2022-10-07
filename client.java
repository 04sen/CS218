/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpsls;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class client {

  public static void main(String[] args){
      
	// initialize socket and input output streams
	Socket socket		 = null;
	InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        
        

		// establish a connection
		try
		{
			socket = new Socket ("localhost", 1234);
                        
                        inputStreamReader = new InputStreamReader (socket.getInputStream());
                        outputStreamWriter = new OutputStreamWriter (socket.getOutputStream());
                        
                        bufferedReader = new BufferedReader(inputStreamReader);
                        bufferedWriter = new BufferedWriter (outputStreamWriter);
                        
                        Scanner scanner = new Scanner (System.in);
                        
                        while (true) {
                            String GameServerMessage = scanner.nextLine();
                            
                            bufferedWriter.write(GameServerMessage);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            
                            System.out.println("Server: " + bufferedReader.readLine());
                            
                            if (GameServerMessage.equalsIgnoreCase("Over"))
                                break;
                            
                        }
                } catch (IOException e)
                    {
                        e.printStackTrace();
                    } finally 
                        {
                            try {
                                    if (socket!= null)
                                        socket.close();
                                    if (inputStreamReader != null)
                                        inputStreamReader.close();
                                    if (outputStreamWriter != null)
                                        outputStreamWriter.close();
                                    if (bufferedReader != null)
                                        bufferedReader.close();
                                    if (bufferedWriter != null)
                                        bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                         }
         
    }
  
}