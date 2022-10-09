import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame implements ActionListener{
    JButton rock_btn = new JButton();
    JButton paper_btn = new JButton();
    JButton scissors_btn = new JButton();
    JButton lizard_btn = new JButton();
    JButton spock_btn = new JButton();

    ImageIcon rock_img = new ImageIcon("Imgs\\Rock.png");
    JLabel rock_lb = new JLabel();
    ImageIcon paper_img = new ImageIcon("Imgs\\paper.png");
    JLabel paper_lb = new JLabel();
    ImageIcon scissors_img = new ImageIcon("Imgs\\scissors.png");
    JLabel scissors_lb = new JLabel();
    ImageIcon lizard_img = new ImageIcon("Imgs\\lizard.png");
    JLabel lizard_lb = new JLabel();
    ImageIcon spock_img = new ImageIcon("Imgs\\spock.png");
    JLabel spock_lb = new JLabel();
   
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        new GameFrame();
    }

    public GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File audio_file = new File("Audio\\background.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio_file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        //Setting Parameters for Labels
        rock_lb.setText("You have chosen Rock!");
        rock_lb.setIcon(rock_img);
        rock_lb.setHorizontalTextPosition(JLabel.LEFT);
        rock_lb.setBounds(350, 500, 500,130);
        rock_lb.setVisible(false);

        paper_lb.setText("You have chosen Paper!");
        paper_lb.setIcon(paper_img);
        paper_lb.setHorizontalTextPosition(JLabel.LEFT);
        paper_lb.setBounds(350, 500, 500,130);
        paper_lb.setVisible(false);

        scissors_lb.setText("You have chosen Scissors!");
        scissors_lb.setIcon(scissors_img);
        scissors_lb.setHorizontalTextPosition(JLabel.LEFT);
        scissors_lb.setBounds(350, 500, 500,130);
        scissors_lb.setVisible(false);

        lizard_lb.setText("You have chosen Lizard!");
        lizard_lb.setIcon(lizard_img);
        lizard_lb.setHorizontalTextPosition(JLabel.LEFT);
        lizard_lb.setBounds(350, 500, 500,130);
        lizard_lb.setVisible(false);

        spock_lb.setText("You have chosen Spock!");
        spock_lb.setIcon(spock_img);
        spock_lb.setHorizontalTextPosition(JLabel.LEFT);
        spock_lb.setBounds(350, 500, 500,150);
        spock_lb.setVisible(false);

        //Setting Parameetrs for Buttons
        rock_btn.setBounds(200, 400, 90, 50); 
        rock_btn.addActionListener(this); 
        rock_btn.setText("Rock"); 
        rock_btn.setFocusable(false); 
        
        paper_btn.setBounds(310, 400, 90, 50); 
        paper_btn.addActionListener(this); 
        paper_btn.setText("Paper"); 
        paper_btn.setFocusable(false); 
        
        scissors_btn.setBounds(420, 400, 90, 50); 
        scissors_btn.addActionListener(this); 
        scissors_btn.setText("Scissors"); 
        scissors_btn.setFocusable(false); 
       
        lizard_btn.setBounds(530, 400, 90, 50); 
        lizard_btn.addActionListener(this); 
        lizard_btn.setText("Lizard"); 
        lizard_btn.setFocusable(false); 
        
        spock_btn.setBounds(640, 400, 90, 50); 
        spock_btn.addActionListener(this); 
        spock_btn.setText("Sprock"); 
        spock_btn.setFocusable(false); 


        //Setting Parameters for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLayout(null);
        setSize(1000,700);
        
        //Allows the JFrame to appear in the  middle of the screeen
        setLocationRelativeTo(null);

        //Allows the JFrame to be Visible
        setVisible(true); 

        //adding buttons to Frame
        this.add(rock_btn);
        this.add(paper_btn);
        this.add(scissors_btn);
        this.add(lizard_btn);
        this.add(spock_btn);

        //adding labels to Frame
        this.add(rock_lb);
        this.add(paper_lb);
        this.add(scissors_lb);
        this.add(lizard_lb);
        this.add(spock_lb);

        //Starting Background Audio once JFrame is opened
        clip.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == rock_btn){ //if button is pressed
            
            buttonsOff();//calls buttonsOff method
            rock_lb.setVisible(true); //shows label in Jframe

        }else if(e.getSource() == paper_btn){ 
            
            buttonsOff();//calls buttonsOff method
            paper_lb.setVisible(true);//shows label in Jframe

        }else if(e.getSource() == scissors_btn){ 
            
            buttonsOff();
            scissors_lb.setVisible(true);//shows label in Jframe

        }else if(e.getSource() == lizard_btn){ 
            
            buttonsOff();//calls buttonsOff method
            lizard_lb.setVisible(true);//shows label in Jframe

        }else if(e.getSource() == spock_btn){ 
            
            buttonsOff();//calls buttonsOff method
            spock_lb.setVisible(true);//shows label in Jframe
        }

    }

    //Method to Disable all Buttons
    public void buttonsOff(){
        rock_btn.setEnabled(false);
        paper_btn.setEnabled(false);
        scissors_btn.setEnabled(false);
        lizard_btn.setEnabled(false);
        spock_btn.setEnabled(false);
    }
}