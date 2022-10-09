import java.io.*;
import java.net.*;
import java.util.Scanner;
public class client {
    
	// initialize socket and input output streams
	private Socket	socket;
	private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private String username;
        
        public client (Socket socket, String username){
            try {
                this.socket = socket;
                this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.username = username;
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
        
        public void sendMessage(){
            try{
                bufferedWriter.write(username);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                Scanner scanner = new Scanner (System.in);
                while(socket.isConnected()){
                    String messageToSend = scanner.nextLine();
                    bufferedWriter.write(username + ": " + messageToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
        
        public void listenForMessage(){
            new Thread(new Runnable(){
                @Override
                public void run(){
                    String msgFromClient;
                    
                    while(socket.isConnected()){
                        try{
                            msgFromClient = bufferedReader.readLine();
                            System.out.println(msgFromClient);
                            RockPaperScissorsLizard_();
                        } catch (IOException e) {
                            closeEverything(socket, bufferedReader, bufferedWriter);
                        }
                    }
                }

                private void RockPaperScissorsLizard_() {
                    Scanner scanner = new Scanner(System.in);
                    
                    while (true) {
   
                    String playerMove;
                    String player2Move;
                    String player3Move;

                    while(true) {
                        System.out.println("Please enter your move Player 1 (r, p, s, l, or k)");
                        playerMove = scanner.nextLine();
                        System.out.println("Please enter your move PLAYER 2 (r, p, s, l, or k)");
                        player2Move = scanner.nextLine();
                        System.out.println("Please enter your move PLAYER 3 (r, p, s, l, or k)");
                        player3Move = scanner.nextLine();

                        if (playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s") || playerMove.equals("l") || playerMove.equals("k")) {
                             break;
                         }
                          System.out.println(playerMove + " is not a valid move.");
                        }

                        System.out.println("Player 2 played: " + player2Move);

                        System.out.println("Player 3 played: " + player3Move);

                        if (playerMove.equals(player2Move) && playerMove.equals(player3Move)) {
                          System.out.println("The game was a tie!");
                        }
                      }
                     }
            }).start(); 
}
        public void closeEverything (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
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
        
        public static void main(String[] args) throws IOException{
            Scanner scanner = new Scanner (System.in);
            System.out.println("Enter your game name");
            String username = scanner.nextLine();
            Socket socket = new Socket("localhost", 1234);
            client client = new client (socket, username);
            client.listenForMessage();
            client.sendMessage();
        }
}
        
