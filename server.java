import java.io.*;
import java.net.*;
public class server{
    final int WIN_VALUE = 1;
    final int LOSE_VALUE = 0;
    final int TIE_VALUE = -1;
    
    private ServerSocket ss;
    private int numPlayers;
    private int playerOneChoice = 0;
    private int playerTwoChoice = 0;
    private int playerThreeChoice = 0;

    private boolean playerOneWin;
    private boolean playerTwoWin;
    private boolean playerThreeWin;
    private boolean tie;

    private ServerSideConnections player1;
    private ServerSideConnections player2;
    private ServerSideConnections player3;

    public server(){
        System.out.println("------Server online-----"); //Prints server is online
        numPlayers = 0; 
        try{
            ss = new ServerSocket(51734); 
        }catch(IOException e){
            System.out.println("---Error in server Constructor---");
        }
    }

    public static void main(String[] args) {
        server ser = new server();
        ser.acceptConnections();  
        
    }

    public void acceptConnections(){
        try{
            while(numPlayers < 3){
                Socket socket = ss.accept();
                numPlayers++;

                System.out.println("Player #: " + numPlayers + " has connectioned.");
                ServerSideConnections ssc = new ServerSideConnections(socket, numPlayers);

                //Creates new Server Side Connections per Player
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
            System.out.println("---Please Upgrage to Platnium Server Package to enable more players to join---");
        }catch(IOException e){
            System.out.println("---Error in acceptConnections() Method---");
        }
    }
    //Class to handle Server Side Connections
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
            }catch (IOException e){
                System.out.println("---Error in ServerSideConnections Constructor---");
            }
        }

        //Method to Reset all PlayerChoices recorded in Server
        private void resetPlayerChoice(){
            playerOneChoice = 0;
            playerTwoChoice = 0;
            playerThreeChoice = 0;
        }

        @Override
        public void run(){
            try{
                dos.writeInt(playerID); //Sends playerID intomation to Client
                dos.flush();

                while(true){//in a loop so enable players to keep playing
                    if(playerID == 1){
                        playerOneChoice = dis.readInt();
                        System.out.println("Player One has chosen: " + playerOneChoice);
                    }
                    if(playerID == 2){
                        playerTwoChoice = dis.readInt();
                        System.out.println( "Player Two has chosen: " + playerTwoChoice);
                    }
                    if(playerID == 3){
                        playerThreeChoice = dis.readInt();
                        System.out.println("Player Three has chosen: " + playerThreeChoice);
                    }
                    
                    //Resets Win or Tie Conditions to false every loop
                    tie = false;
                    playerOneWin = false;
                    playerTwoWin = false;
                    playerThreeWin = false;

                    //calls checkWin method
                    checkWin(playerOneChoice, playerTwoChoice, playerThreeChoice);
                        
                    //if and else block to send correct infomation to each user
                    if(tie == true){
                        player1.dos.writeInt(TIE_VALUE);
                        player2.dos.writeInt(TIE_VALUE);
                        player3.dos.writeInt(TIE_VALUE);

                        resetPlayerChoice();
                        
                    }else if(playerOneWin == true && playerTwoWin == true){
                        player1.dos.writeInt(WIN_VALUE);
                        player2.dos.writeInt(WIN_VALUE);
                        player3.dos.writeInt(LOSE_VALUE);

                        resetPlayerChoice();
                        
                    }else if(playerTwoWin == true && playerThreeWin == true){
                        player1.dos.writeInt(LOSE_VALUE);
                        player2.dos.writeInt(WIN_VALUE);
                        player3.dos.writeInt(WIN_VALUE);
                        
                        resetPlayerChoice();
                        
                    }else if(playerOneWin == true){
                        player1.dos.writeInt(WIN_VALUE);
                        player2.dos.writeInt(LOSE_VALUE);
                        player3.dos.writeInt(LOSE_VALUE);

                        resetPlayerChoice();
                        
                    }else if(playerTwoWin == true){
                        player1.dos.writeInt(LOSE_VALUE);
                        player2.dos.writeInt(WIN_VALUE);
                        player3.dos.writeInt(LOSE_VALUE);

                        resetPlayerChoice();
                        
                    }else if(playerThreeWin == true){
                        player1.dos.writeInt(LOSE_VALUE);
                        player2.dos.writeInt(LOSE_VALUE);
                        player3.dos.writeInt(WIN_VALUE);

                        resetPlayerChoice();
                    }
                }
                
            
            }catch(IOException e){
                System.out.println("---Error in SeverSideConnections, Run() Method---");
            }
        }

        //method to check Win condition of RPSLS and sets boolean values accordingly
    public void checkWin(int playerOneChoice, int playerTwoChoice, int playerThreeChoice){
        if(playerOneChoice == 1){
            if(playerTwoChoice == 1){
                if(playerThreeChoice == 1){
                    System.out.println("It is a tie");
                    tie = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
                }

            }else if(playerTwoChoice == 2){
                if(playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 & 3 Win");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("It is a tie");
                    tie = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("It is a tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
                }

            }else if(playerTwoChoice == 3){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 3 Wins");
                    playerThreeWin = true;
                    playerOneWin = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("It is a tie");
                    tie = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("It is a tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
                }

            }else if(playerTwoChoice == 4){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 3 Wins");
                    playerThreeWin = true;
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 2){
                    System.out.println("It is a tie");
                    tie = true;
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a tie");
                    tie = true;
                }

            }else if(playerTwoChoice == 5){
                if(playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a tie");
                    tie = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 & 3 Wins");
                    playerTwoWin = true;
                    playerThreeWin = true;
                }

            }

        }else if(playerOneChoice == 2){
            if(playerTwoChoice == 1){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 1 & 3 Wins");
                    playerThreeWin = true;
                    playerOneWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
                }

            }else if(playerTwoChoice == 2){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 & 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;
                }

            }else if(playerTwoChoice == 3){
                if(playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 & 3 Wins");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                }

            }else if(playerTwoChoice == 4){
                if(playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 & 3 Wins");
                    playerTwoWin = true;
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
                }

            }else if(playerTwoChoice == 5){
                if(playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 2){
                    System.out.println("Player 1 & 3 Wins");
                    playerThreeWin = true;
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
                }
            }
        } else if (playerOneChoice == 3){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 & Player 3 Win");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                    tie = true;
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
                }
            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;
                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 3 Win");
                    playerThreeWin = true;
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
                } 

            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Win");
                    playerOneWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 2 Win");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                } 

            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 & Player 3 Win");
                    playerThreeWin = true;
                } 
            }
        } else if (playerOneChoice == 4){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 & Player 3 Win");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                }
            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 3 Win");
                    playerOneWin = true;
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 3 Win");
                    playerOneWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                } 
            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 & Player 2 Win");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 2 Win");
                    playerOneWin = true;
                    playerTwoWin = true;
                } 
            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 1 & Player 3 Win");
                    playerOneWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
                } 
            }
        } else if (playerOneChoice == 5){
            if (playerTwoChoice == 1) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 3");
                    playerOneWin = true;
                    playerThreeWin = true;
                }

            } else if (playerTwoChoice == 2) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2 & Player 3 Win");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;
    
                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;
    
                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2 Wins");
                    playerTwoWin = true;
                } 
            } else if (playerTwoChoice == 3) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 Wins");
                    playerOneWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 1 & Player 3 Win");
                    playerOneWin = true;
                    playerThreeWin = true;
                }

            } else if (playerTwoChoice == 4) {
                if (playerThreeChoice == 1){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 2");
                    playerTwoWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Its a Tie");
                    tie = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 2 & Player 3");
                    playerTwoWin = true;
                    playerThreeWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Player 2");
                    playerTwoWin = true;
                } 

            } else if (playerTwoChoice == 5) {
                if (playerThreeChoice == 1){
                    System.out.println("Player 1 & Player 2");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if (playerThreeChoice == 2){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 3){
                    System.out.println("Player 1 & Player 2 Wins");
                    playerOneWin = true;
                    playerTwoWin = true;

                }else if(playerThreeChoice == 4){
                    System.out.println("Player 3 Wins");
                    playerThreeWin = true;

                }else if(playerThreeChoice == 5){
                    System.out.println("Its a Tie");
                    tie = true;
                } 
            }
        }
    }
    
    }
}