import java.io.*;
import java.net.*;

public class server {
          
        private ServerSocket serverSocket;
        
        public server(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }
        
        public void startServer(){
            try {
                while (!serverSocket.isClosed()){
                    Socket socket = serverSocket.accept();
                    System.out.println("A new player has connected");
                    ClientHandler clientHandler = new ClientHandler(socket);
                    
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                }
            } catch (IOException e) {
                
            } 
        }
        public void closerServerSocket(){
                try { 
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        
        public static void main(String[] args) throws IOException{
            ServerSocket serverSocket = new ServerSocket (1234);
            server server = new server (serverSocket);
            server.startServer();
        }
}