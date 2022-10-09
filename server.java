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

//    private static void CheckWin(String PlayerOneMove, String PlayerTwoMove) {
//        
////        if (PlayerOneMove.equals("r") || PlayerOneMove.equals("p") || PlayerOneMove.equals("s") || PlayerOneMove.equals("l") || PlayerOneMove.equals("k")) {
////          break;
////        } 
//        System.out.println(PlayerOneMove + " is not a valid move.");
//       
//      
//      System.out.println("Computer played: " + PlayerTwoMove);
//      
//      if (PlayerOneMove.equals(PlayerTwoMove)) {
//        System.out.println("The game was a tie!");
//      }
//      else if (PlayerOneMove.equals("r")) {
//        if (PlayerTwoMove.equals("p")) {
//          System.out.println("Paper covers rock. You lose!");
//        } 
//        else if (PlayerTwoMove.equals("s")) {
//          System.out.println("Rock crushes scissors. You win!");
//        } 
//        else if (PlayerTwoMove.equals("l")) {
//          System.out.println("Rock crushes lizard. You win!");
//        } 
//        else if (PlayerTwoMove.equals("k")) {
//          System.out.println("Spock vaporizes rock. You lose!");
//        }
//      }
//      
//      else if (PlayerOneMove.equals("p")) {
//        if (PlayerTwoMove.equals("r")) {
//          System.out.println("Paper covers rock. You win!");
//        } 
//        else if (PlayerTwoMove.equals("s")) {
//          System.out.println("Scissors cuts paper. You lose!");
//        }
//        else if (PlayerTwoMove.equals("l")) {
//          System.out.println("Lizard eats paper. You lose!");
//        }
//        else if (PlayerTwoMove.equals("k")) {
//          System.out.println("Paper disproves Spock. You win!");
//        }
//      }
//      
//      else if (PlayerOneMove.equals("s")) {
//        if (PlayerTwoMove.equals("p")) {
//          System.out.println("Scissors cuts paper. You win!");
//        }
//        else if (PlayerTwoMove.equals("r")) {
//          System.out.println("Rock crushes scissors. You lose!");
//        }
//        else if (PlayerTwoMove.equals("l")) {
//          System.out.println("Scissors decapitates lizard. You win!");
//        }
//        else if (PlayerTwoMove.equals("k")) {
//          System.out.println("Spock smashes scissors. You lose!");
//        }
//      }
//      
//      else if (PlayerOneMove.equals("l")) {
//        if (PlayerTwoMove.equals("r")) {
//          System.out.println("Rock crushes lizard. You lose!");
//        }
//        else if (PlayerTwoMove.equals("p")) {
//          System.out.println("Lizard eats paper. You win!");
//        }
//        else if (PlayerTwoMove.equals("s")) {
//          System.out.println("Scissors decapitates lizard. You lose!");
//        }
//        else if (PlayerTwoMove.equals("k")) {
//          System.out.println("Lizard poisons Spock. You win!");
//        }
//      }
//
//      else if (PlayerOneMove.equals("k")) {
//        if (PlayerTwoMove.equals("p")) {
//          System.out.println("Paper disproves Spock. You lose!");
//        }
//        else if (PlayerTwoMove.equals("r")) {
//          System.out.println("Spock vaporizes rock. You win!");
//        }
//        else if (PlayerTwoMove.equals("s")) {
//          System.out.println("Spock smashes scissors. You win!");
//        }
//        else if (PlayerTwoMove.equals("l")) {
//          System.out.println("Lizard poisons Spock. You lose!");
//        }
//      }
//    }
}
