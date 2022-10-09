import java.io.*;
import java.net.*;
import java.util.*;

public class server
{
     
        private ServerSocket serverSocket;
        private ClientHandler player1;
        private ClientHandler player2;
        private ClientHandler player3;
        
        int numPlayers;
        
        public server(ServerSocket serverSocket) 
        {
            this.serverSocket = serverSocket;
            System.out.println("Game server online");
             numPlayers = 0;
        }
        
         public static void main(String[] args) throws IOException
            {
                
                ServerSocket serverSocket = new ServerSocket (1234);
                server server = new server (serverSocket);
                server.startServer();
                
            }
         
        public void startServer(){
            try 
            {
                while (!serverSocket.isClosed())
                {
                    Socket socket = serverSocket.accept();
                    numPlayers++;
                    System.out.println("Player# "+ numPlayers + " has connected");
                    ClientHandler clientHandler = new ClientHandler(socket, numPlayers);
                 
                    if (numPlayers == 1)
                    {
                        player1 = clientHandler;
                    }
                    else if (numPlayers == 2)
                    {
                        player2 = clientHandler;

                    } else 
                        player3 = clientHandler;
                    
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                   
                }
                
            } catch (IOException e) 
            {
                
            } 
        }
        public void closeServerSocket()
        {
                try { 
                        if (serverSocket != null)
                        {
                            serverSocket.close();
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
        }
        
        
       
        
        
    public static void Game()
    {
       int numPlayer = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Player " + numPlayer + ": Choose rock, scissors, or paper:");
        String player1 = scan.next().toLowerCase();
        
        String player2 = scan.next().toLowerCase();

          if (player1.equals(player2))
          {
            System.out.println("It is a tie");
          }
    }
  
        
           

    
}