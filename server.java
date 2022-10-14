import java.io.*;
import java.net.*;
import java.util.*;
public class server{
    private ServerSocket ss;
    private int numPlayers;
    public static int playerOneChoice = 0;
    public static int playerTwoChoice = 0;
    public static int playerThreeChoice = 0;
    public static int result;
    private boolean playerOneWin;
    private boolean playerTwoWin;
    private boolean playerThreeWin;
    private boolean tie;
    List<DataOutputStream> doss = new ArrayList<>();

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
                Socket socket = ss.accept();
                numPlayers++;
                System.out.println("Player #: " + numPlayers + " has connectioned.");
                ServerSideConnections ssc = new ServerSideConnections(socket, numPlayers);
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

                while(true){
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
                    
                    tie = false;
                    checkWin(playerOneChoice, playerTwoChoice, playerThreeChoice);
                        
                    if(tie == true){
                        player1.dos.writeInt(-1);
                        player2.dos.writeInt(-1);
                        player3.dos.writeInt(-1);

                        playerOneChoice = 0;
                        playerTwoChoice = 0;
                        playerThreeChoice = 0;
                        
                    }else if(playerOneWin == true && playerTwoWin == true){
                        player1.dos.writeInt(1);
                        player2.dos.writeInt(1);
                        player3.dos.writeInt(0);
                    }else if(playerTwoWin == true && playerThreeWin == true){
                        player1.dos.writeInt(0);
                        player2.dos.writeInt(1);
                        player3.dos.writeInt(1);

                    }else if(playerOneWin == true){
                        player1.dos.writeInt(1);
                        player2.dos.writeInt(0);
                        player3.dos.writeInt(0);

                    }else if(playerTwoWin == true){
                        player1.dos.writeInt(0);
                        player2.dos.writeInt(1);
                        player3.dos.writeInt(0);

                    }else if(playerThreeWin == true){
                        player1.dos.writeInt(0);
                        player2.dos.writeInt(0);
                        player3.dos.writeInt(1);
                    }
                }
                
            
            }catch(IOException e){}
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