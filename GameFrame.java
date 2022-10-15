import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.imageio.ImageIO;
public class GameFrame extends JFrame{
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 700;
    final int WIN_CONDITION = 1;
    final int LOSE_CONDITION = 0;
    final int TIE_CONDITION = -1;

    JButton rock_btn = new JButton();
    JButton paper_btn = new JButton();
    JButton scissors_btn = new JButton();
    JButton lizard_btn = new JButton();
    JButton spock_btn = new JButton();
    JButton playMusic_btn = new JButton();
    JButton stopMusic_btn = new JButton();

    ImageIcon sound_img = new ImageIcon("Imgs\\sound.png");
    ImageIcon mute_img = new ImageIcon("Imgs\\mute.png");
    ImageIcon rock_img = new ImageIcon("Imgs\\Rock.png");
    ImageIcon paper_img = new ImageIcon("Imgs\\paper.png");
    ImageIcon scissors_img = new ImageIcon("Imgs\\scissors.png");
    ImageIcon lizard_img = new ImageIcon("Imgs\\lizard.png");
    ImageIcon spock_img = new ImageIcon("Imgs\\spock.png");
    ImageIcon background = new ImageIcon("Imgs\\background.jpg");

    JLabel rock_lb = new JLabel();
    JLabel paper_lb = new JLabel();
    JLabel scissors_lb = new JLabel();
    JLabel lizard_lb = new JLabel();
    JLabel spock_lb = new JLabel();

    JLabel title_lb = new JLabel();
    JLabel player_lb = new JLabel();
    JLabel rules_lb = new JLabel();
    JLabel waiting_lb = new JLabel();
    JLabel lost_lb = new JLabel();
    JLabel win_lb = new JLabel();
    JLabel tie_lb = new JLabel();
    JLabel points_lb = new JLabel();
    JLabel playAgain_lb = new JLabel();

    File audio_file;
    AudioInputStream audioStream;
    Clip clip;
  
    int playerID;
    static int playerChoice;
    static int points;
    static int result;

    private ClientSideConnection csc;
   
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        GameFrame player = new GameFrame(); //creating object player
        player.connectionToServer(); //connecting player to Server
        player.setUpGUI(); //calling SetUpGUI to present the GUI to user
        player.setUpButtons(); //Setting up functionality for all buttons
        player.showResult(); //shows the results of the game within the GUI
    }

    public void setUpGUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
         //Setting Background Audio 
         audio_file = new File("Audio\\background.wav");
         audioStream = AudioSystem.getAudioInputStream(audio_file);
         clip = AudioSystem.getClip();
         clip.open(audioStream);

         //Setting Bounds for Labels
         rock_lb.setText("You have chosen Rock!    ");
         rock_lb.setIcon(rock_img);
         rock_lb.setHorizontalTextPosition(JLabel.LEFT);
         rock_lb.setBounds(370, 500, 300,140);
         rock_lb.setVisible(false);
         rock_lb.setForeground(Color.decode("#ffb7c5"));
         rock_lb.setFont(new Font("Nyala", Font.BOLD , 16));
 
         paper_lb.setText("You have chosen Paper!    ");
         paper_lb.setIcon(paper_img);
         paper_lb.setHorizontalTextPosition(JLabel.LEFT);
         paper_lb.setBounds(370, 500, 500,130);
         paper_lb.setVisible(false);
         paper_lb.setForeground(Color.decode("#ffb7c5"));
         paper_lb.setFont(new Font("Nyala", Font.PLAIN  , 16));
 
         scissors_lb.setText("You have chosen Scissors!    ");
         scissors_lb.setIcon(scissors_img);
         scissors_lb.setHorizontalTextPosition(JLabel.LEFT);
         scissors_lb.setBounds(370, 500, 500,130);
         scissors_lb.setVisible(false);
         scissors_lb.setForeground(Color.decode("#ffb7c5"));
         scissors_lb.setFont(new Font("Nyala", Font.BOLD  , 16));
 
         lizard_lb.setText("You have chosen Lizard!    ");
         lizard_lb.setIcon(lizard_img);
         lizard_lb.setHorizontalTextPosition(JLabel.LEFT);
         lizard_lb.setBounds(370, 500, 500,130);
         lizard_lb.setVisible(false);
         lizard_lb.setForeground(Color.decode("#ffb7c5"));
         lizard_lb.setFont(new Font("Nyala", Font.BOLD  , 16));
 
         spock_lb.setText("You have chosen Spock!    ");
         spock_lb.setIcon(spock_img);
         spock_lb.setHorizontalTextPosition(JLabel.LEFT);
         spock_lb.setBounds(370, 500, 500,150);
         spock_lb.setVisible(false);
         spock_lb.setForeground(Color.decode("#ffb7c5"));
         spock_lb.setFont(new Font("Nyala", Font.BOLD  , 16));
 
         title_lb.setText("<html><u>Welcome to Rock Paper Scissors Lizard Spock!</u></html>");
         title_lb.setBounds(300, 10, 450, 50);
         title_lb.setHorizontalAlignment(SwingConstants.CENTER);
         title_lb.setVisible(true);
         title_lb.setForeground(Color.decode("#ffb7c5"));
         title_lb.setFont(new Font("Nyala", Font.PLAIN  , 20));
         
         player_lb.setText("You are Player #" + playerID );
         player_lb.setBounds(400, 75, 250 , 50);
         player_lb.setHorizontalAlignment(SwingConstants.CENTER);
         player_lb.setVisible(true);
         player_lb.setForeground(Color.decode("#ffb7c5"));
         player_lb.setFont(new Font("Nyala", Font.PLAIN  , 16));
 
         rules_lb.setText("<html> <u>Rules:</u> <br> Scissors cuts Paper<br>Paper covers Rock<br>Rock crushes Lizard<br>Lizard poisons Spock<br>Spock smashes Scissors<br>Scissors decapitates Lizard<br>Lizard eats Paper<br>Paper disproves Spock<br>Spock vaporizes Rock<br>Rock crushes Scissors<html>");
         rules_lb.setOpaque(true);
         rules_lb.setBounds(20, 100, 210, 250);
         rules_lb.setBackground(new java.awt.Color(50, 50, 50, 100));
         rules_lb.setHorizontalAlignment(SwingConstants.CENTER);
         rules_lb.setForeground(Color.decode("#ffb7c5"));
         rules_lb.setFont(new Font("Nyala", Font.PLAIN , 16));
         rules_lb.setVisible(true);

         waiting_lb.setText(".....Waiting for Other Players......");
         waiting_lb.setBounds(380, 250, 280,50);
         waiting_lb.setVisible(false);
         waiting_lb.setOpaque(true);
         waiting_lb.setHorizontalAlignment(SwingConstants.CENTER);
         waiting_lb.setBackground(new java.awt.Color(40, 40, 40, 140));
         waiting_lb.setForeground(Color.decode("#ffb7c5"));
         waiting_lb.setFont(new Font("Nyala", Font.BOLD  , 18));
 
         lost_lb.setText(".....You Have Lost!......");
         lost_lb.setBounds(380, 250, 280,50);
         lost_lb.setVisible(false);
         lost_lb.setOpaque(true);
         lost_lb.setHorizontalAlignment(SwingConstants.CENTER);
         lost_lb.setBackground(new java.awt.Color(40, 40, 40, 140));
         lost_lb.setForeground(Color.decode("#ffb7c5"));
         lost_lb.setFont(new Font("Nyala", Font.BOLD , 18));
 
         win_lb.setText(".....You Have Won!......");
         win_lb.setBounds(380, 250, 280,50);
         win_lb.setVisible(false);
         win_lb.setOpaque(true);
         win_lb.setHorizontalAlignment(SwingConstants.CENTER);
         win_lb.setBackground(new java.awt.Color(40, 40, 40, 140));
         win_lb.setForeground(Color.decode("#ffb7c5"));
         win_lb.setFont(new Font("Nyala", Font.BOLD , 18));
 
         tie_lb.setText(".....Its a Tie! Try Again!......");
         tie_lb.setBounds(380, 250, 280,50);
         tie_lb.setVisible(false);
         tie_lb.setHorizontalAlignment(SwingConstants.CENTER);
         tie_lb.setForeground(Color.decode("#ffb7c5"));
         tie_lb.setFont(new Font("Nyala", Font.BOLD , 18));

         playAgain_lb.setText(".....Choose a Move to Play Again......");
         playAgain_lb.setBounds(300, 325, 400,50);
         playAgain_lb.setVisible(false);
         playAgain_lb.setOpaque(true);
         playAgain_lb.setHorizontalAlignment(SwingConstants.CENTER);
         playAgain_lb.setBackground(new java.awt.Color(40, 40, 40, 140));
         playAgain_lb.setForeground(Color.decode("#ffb7c5"));
         playAgain_lb.setFont(new Font("Nyala", Font.BOLD , 18));

         points_lb.setText("Points: " + points);
         points_lb.setBounds(835, 90, 80,50);
         points_lb.setHorizontalAlignment(SwingConstants.CENTER);
         points_lb.setOpaque(true);
         points_lb.setBackground(new java.awt.Color(50, 50, 50, 100));
         points_lb.setVisible(true);
         points_lb.setForeground(Color.decode("#ffb7c5"));
         points_lb.setFont(new Font("Nyala", Font.PLAIN , 16));
 
 
         //Setting Bounds for Buttons
         rock_btn.setBounds(250, 400, 90, 50); 
         rock_btn.setText("Rock");
         rock_btn.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
         rock_btn.setBackground(Color.decode("#dca9fc"));
         rock_btn.setUI(new styledButton());
         rock_btn.setFocusable(false); 
         
         paper_btn.setBounds(360, 400, 90, 50); 
         paper_btn.setText("Paper"); 
         paper_btn.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
         paper_btn.setBackground(Color.decode("#dca9fc"));
         paper_btn.setUI(new styledButton());
         paper_btn.setFocusable(false); 
         
         scissors_btn.setBounds(470, 400, 90, 50); 
         scissors_btn.setText("Scissors");
         scissors_btn.setFont(new Font("Harlow Solid Italic", Font.BOLD, 16));
         scissors_btn.setBackground(Color.decode("#dca9fc"));
         scissors_btn.setUI(new styledButton());
         scissors_btn.setFocusable(false); 
        
         lizard_btn.setBounds(580, 400, 90, 50); 
         lizard_btn.setText("Lizard"); 
         lizard_btn.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
         lizard_btn.setBackground(Color.decode("#dca9fc"));
         lizard_btn.setUI(new styledButton());
         lizard_btn.setFocusable(false); 
         
         spock_btn.setBounds(690, 400, 90, 50); 
         spock_btn.setText("Spock"); 
         spock_btn.setFont(new Font("Harlow Solid Italic", Font.BOLD, 18));
         spock_btn.setBackground(Color.decode("#dca9fc"));
         spock_btn.setUI(new styledButton());
         spock_btn.setFocusable(false); 
         
         stopMusic_btn.setBounds(850,10,50,50);
         stopMusic_btn.setIcon(sound_img);
         stopMusic_btn.setFocusable(false);
         stopMusic_btn.setBackground(Color.decode("#dca9fc"));
         stopMusic_btn.setUI(new styledButton());
         
         playMusic_btn.setBounds(850,10,50,50);
         playMusic_btn.setIcon(mute_img);
         playMusic_btn.setVisible(false);
         playMusic_btn.setFocusable(false);
         playMusic_btn.setBackground(Color.decode("#dca9fc"));
         playMusic_btn.setUI(new styledButton());
         
         //Setting Bounds for the JFrame
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(false);
         this.setLayout(null);
         this.setSize(1000,700);
         
         //Allows the JFrame to appear in the  middle of the screeen
         setLocationRelativeTo(null);
 
         //Allows the JFrame to be Visible
         setVisible(true); 

         //Adding Background Img to JFrame
         this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Imgs\\background.jpg")))));
         
         //Adding buttons to Frame
         this.add(rock_btn);
         this.add(paper_btn);
         this.add(scissors_btn);
         this.add(lizard_btn);
         this.add(spock_btn);
         this.add(stopMusic_btn);
         this.add(playMusic_btn);
 
         //Adding labels to Frame
         this.add(rock_lb);
         this.add(paper_lb);
         this.add(scissors_lb);
         this.add(lizard_lb);
         this.add(spock_lb);
         this.add(title_lb);
         this.add(player_lb);
         this.add(rules_lb);
         this.add(waiting_lb);
         this.add(win_lb);
         this.add(lost_lb);
         this.add(tie_lb);
         this.add(points_lb);
         this.add(playAgain_lb);
          
         //Starting Background Audio once JFrame is opened
         clip.start();
    }

    //Method for implementing changes due to user input
    public void setUpButtons(){
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == rock_btn){ //if button is pressed by user
            
                    buttonsOff();//calls buttonsOff method
                    labelsOff();//calls labelsOff method
                    rock_lb.setVisible(true); //shows user has chosen rock in GUI
                    waiting_lb.setVisible(true);//displays a waiting message in GUi
                    playerChoice = 1; //Sets playerChoice as 1
                    csc.sendChoice(playerChoice);//calls sendChoice method to send player Choice to Server
                    
                }else if(e.getSource() == paper_btn){ 
                    
                    buttonsOff();//calls buttonsOff method
                    labelsOff();
                    tie_lb.setVisible(false);
                    paper_lb.setVisible(true);//shows user has chosen paper in GUI
                    waiting_lb.setVisible(true);
                    playerChoice = 2; //sets playerChoice as 2
                    csc.sendChoice(playerChoice);
                    
                }else if(e.getSource() == scissors_btn){ 
                    
                    buttonsOff();
                    labelsOff();
                    scissors_lb.setVisible(true);//shows user has chosen scissors in GUI
                    waiting_lb.setVisible(true);
                    playerChoice = 3; //sets playerChoice as 3
                    csc.sendChoice(playerChoice);
                    
                }else if(e.getSource() == lizard_btn){ 
                    
                    buttonsOff();//calls buttonsOff method
                    labelsOff();
                    lizard_lb.setVisible(true);//shows user has chosen lizard in GUI
                    waiting_lb.setVisible(true);
                    playerChoice = 4; //sets playerChoice as 4
                    csc.sendChoice(playerChoice);
                    
                }else if(e.getSource() == spock_btn){ 
                    
                    buttonsOff();//calls buttonsOff method
                    labelsOff();
                    spock_lb.setVisible(true);//shows user has chosen Spock in GUI
                    waiting_lb.setVisible(true);
                    playerChoice = 5; //sets playerChoice as 5
                    csc.sendChoice(playerChoice);
                    
                }else if(e.getSource() == stopMusic_btn){
                    clip.stop(); //stops background music
                    stopMusic_btn.setVisible(false); //disables stopMusic button
                    playMusic_btn.setVisible(true); //enables playMusic button

                }else if(e.getSource() == playMusic_btn){
                    clip.start(); //resumes background music
                    playMusic_btn.setVisible(false); //disables playMusic button
                    stopMusic_btn.setVisible(true); //enables stopMusicbutton

                }
            }
        };

        //Adds buttons to ActionListener
        rock_btn.addActionListener(al);
        paper_btn.addActionListener(al);
        scissors_btn.addActionListener(al);
        lizard_btn.addActionListener(al);
        spock_btn.addActionListener(al);
        stopMusic_btn.addActionListener(al);
        playMusic_btn.addActionListener(al);
    }

    //Method to create a ClientSideConnection Object
    public void connectionToServer(){
        csc = new ClientSideConnection();
    }

    class styledButton extends BasicButtonUI {
        @Override
        public void installUI (JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorder(new EmptyBorder(5, 15, 5, 15));
        }
    
        @Override
        public void paint (Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }
    
        private void paintBackground (Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(c.getBackground().darker());
            g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
            g.setColor(c.getBackground());
            g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
        }
    }

    //Inner Class for Client Side Connections
    private class ClientSideConnection{ 
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket socket;

        public ClientSideConnection(){

            System.out.println("----Client---");
            try {
                socket = new Socket("localhost",51734);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                playerID = dis.readInt(); //reads data from server and stores as playerID 
                
            } catch (IOException e) {
                System.out.println("Error in ClientSideConnection constructor");
            }
        }

        //Method to send user picked button choice to server
        public void sendChoice(int n){
            try{
                dos.writeInt(n);
                dos.flush();
            }catch(IOException e){
                System.out.println("Error in sendChoice()");
            }
        }

        //method to read result from server
        public void getResult(){
            try { 
                result = dis.readInt(); //reads data from server and stores as result

                if(result == TIE_CONDITION){//if Tie
                    waiting_lb.setVisible(false);//diables waiting message
                    tie_lb.setVisible(true);//shows user that it was a tie
                    buttionsOn();//turns all buttons on 

                }else if(result == WIN_CONDITION){// if User Wins
                    waiting_lb.setVisible(false);
                    win_lb.setVisible(true);
                    playAgain_lb.setVisible(true);

                    points++; //updates points value by 1
                    points_lb.setText("Points: " + points); //allows user to see new points value
                    buttionsOn();//Turns buttons back on to allow for continuation of game

                }else if(result == LOSE_CONDITION){
                    waiting_lb.setVisible(false);
                    lost_lb.setVisible(true);
                    playAgain_lb.setVisible(true);
                    buttionsOn();
                }

            } catch (IOException e) {
                System.out.println("Error in getResult()");
            }
        }
    }

    //Method for implementing Result tracking from Server
    public void showResult(){
        while(true){// in a while loop to ensure game can keep going. 
            csc.getResult();
        }
    }

    //Method to Disable Middle Labels
    public void labelsOff(){
        tie_lb.setVisible(false);
        win_lb.setVisible(false);
        lost_lb.setVisible(false);
        playAgain_lb.setVisible(false);
    }

    //Method to Disable all Buttons
    public void buttonsOff(){
        rock_btn.setEnabled(false);
        paper_btn.setEnabled(false);
        scissors_btn.setEnabled(false);
        lizard_btn.setEnabled(false);
        spock_btn.setEnabled(false);
    }

    //Method to Enable all Buttons
    public void buttionsOn(){
        //sets buttons to be enabled
        rock_btn.setEnabled(true);
        paper_btn.setEnabled(true);
        scissors_btn.setEnabled(true);
        lizard_btn.setEnabled(true);
        spock_btn.setEnabled(true);

        //clears any previous user choice
        rock_lb.setVisible(false);
        paper_lb.setVisible(false);
        scissors_lb.setVisible(false);
        lizard_lb.setVisible(false);
        spock_lb.setVisible(false);
        waiting_lb.setVisible(false);
    }
}
