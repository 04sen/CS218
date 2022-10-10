import java.io.*;
import java.net.*;

public class server{
    private ServerSocket ss;
    private int numPlayers;
    private int result;
    public static int playerOneChoice;
    public static int playerTwoChoice;
    public static int playerThreeChoice;
    
    private ServerSideConnections player1;
    private ServerSideConnections player2;
    private ServerSideConnections player3;

    public server(){
        System.out.println("------Server online-----");
        numPlayers = 0;
        try{
            ss = new ServerSocket(51734);
        }catch(IOException e){}
    }

    public static void main(String[] args) {
        server ser = new server();
        ser.acceptConnections();  
        
    }

    public void acceptConnections(){
        try{
            while(numPlayers < 3){
                Socket s = ss.accept();
                numPlayers++;
                System.out.println("Player #: " + numPlayers + " has connectioned.");
                ServerSideConnections ssc = new ServerSideConnections(s, numPlayers);
                if (numPlayers == 1)
                {
                    player1 = ssc;
                }
                else if (numPlayers == 2)
                {
                    player2 = ssc;

                } else if (numPlayers == 3){
                    player3 = ssc;
                }

                Thread thread = new Thread(ssc);
                thread.start();
            }
        }catch(IOException e){}
    }

    private class ServerSideConnections implements Runnable{
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private int playerID;

        public ServerSideConnections(Socket s, int id){
            socket = s;
            playerID = id;

            try{
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            }catch (IOException e){}
        }

        @Override
        public void run(){
            try{
                dos.writeInt(playerID);
                dos.flush();

                if(playerID == 1){
                    playerOneChoice = dis.readInt();
                    System.out.println("Player One has chosen: " + playerOneChoice);
                }else if(playerID == 2){
                    playerTwoChoice = dis.readInt();
                    System.out.println( "Player Two has chosen: " + playerTwoChoice);
                }else if(playerID == 3){
                    playerThreeChoice = dis.readInt();
                    System.out.println("Player Three has chosen: " + playerThreeChoice);
                }

                if(playerOneChoice != 0 && playerTwoChoice != 0 && playerThreeChoice != 0){
                    checkWin(playerOneChoice, playerTwoChoice, playerThreeChoice);
                }
            }catch(IOException e){}
        }
    }

    //method to check Win condition of RPSLS.
    public void checkWin(int playerOneChoice, int playerTwoChoice, int playerThreeChoice){
        if(playerOneChoice == 1){
            if(playerTwoChoice == 1){
                if(playerThreeChoice == 1){
                    System.out.println("It is a tie");

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & 2 Wins");

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & 2 Wins");

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                }

            }else if(playerTwoChoice == 2){
                if(playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 & 3 Win");

                }else if(playerThreeChoice == 3){
                    System.out.println("It is a tie");

                }else if(playerThreeChoice == 4){
                    System.out.println("It is a tie");

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                }

            }else if(playerTwoChoice == 3){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 3 Wins");

                }else if(playerThreeChoice == 2){
                    System.out.println("It is a tie");

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");

                }else if(playerThreeChoice == 4){
                    System.out.println("It is a tie");

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                }

            }else if(playerTwoChoice == 4){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 3 Wins");
    
                }else if(playerThreeChoice == 2){
                    System.out.println("It is a tie");
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a tie");
                }

            }else if(playerTwoChoice == 5){
                if(playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a tie");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 & 3 Wins");
                }

            }

        }else if(playerOneChoice == 2){
            if(playerTwoChoice == 1){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 1 & 3 Wins");

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");

                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                }

            }else if(playerTwoChoice == 2){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 2 Wins");

                }else if(playerThreeChoice == 2){
                    System.out.println("Its a Tie");

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 wins");

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & 2 Wins");
                }

            }else if(playerTwoChoice == 3){
                if(playerThreeChoice == 1){
                    System.out.println("Its a Tie");

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 & 3 Wins");

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");

                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                }

            }else if(playerTwoChoice == 4){
                if(playerThreeChoice == 1){
                    System.out.println("Its a Tie");
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 & 3 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                }

            }else if(playerTwoChoice == 5){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 1 & 3 Wins");
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                }
            }
        } else if (playerOneChoice == 3){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 & Player 3 Win");
                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                }
            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 3 Win");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 2 Wins");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 2 Wins");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                } 
            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Win");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 2 Win");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                } 
            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 & Player 3 Win");
                } 
            }
        } else if (playerOneChoice == 4){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 & Player 3 Win");
                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                }
            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 3 Win");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 3 Win");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                } 
            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 2 Win");
                } 
            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 3 Win");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                } 
            }
        } else if (playerOneChoice == 5){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 3");
                }
            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2 & Player 3 Win");
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 3 Win");
                }
            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2");
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 & Player 3");
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2");
                } 
            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 & Player 2");
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 3");
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 2 Wins");
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                } 
            }
        }
   }
}
