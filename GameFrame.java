import javax.swing.*;
import java.awt.event.*;

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
    

    public static void main(String[] args){
        new GameFrame();
    }

    public GameFrame(){

        //Labels
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

        //Buttons
        rock_btn.setBounds(200, 400, 90, 50); //Sets the Bounds of the Button
        rock_btn.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        rock_btn.setText("Rock"); //Label for Button
        rock_btn.setFocusable(false); //allows Button to show above of Background
        
        paper_btn.setBounds(310, 400, 90, 50); //Sets the Bounds of the Button
        paper_btn.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        paper_btn.setText("Paper"); //Label for Button
        paper_btn.setFocusable(false); //allows Button to show above of Background
        
        scissors_btn.setBounds(420, 400, 90, 50); //Sets the Bounds of the Button
        scissors_btn.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        scissors_btn.setText("Scissors"); //Label for Button
        scissors_btn.setFocusable(false); //allows Button to show above of Background
       
        lizard_btn.setBounds(530, 400, 90, 50); //Sets the Bounds of the Button
        lizard_btn.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        lizard_btn.setText("Lizard"); //Label for Button
        lizard_btn.setFocusable(false); //allows Button to show above of Background
        
        spock_btn.setBounds(640, 400, 90, 50); //Sets the Bounds of the Button
        spock_btn.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        spock_btn.setText("Sprock"); //Label for Button
        spock_btn.setFocusable(false); //allows Button to show above of Background


        //Allows the Code to stop when the JFrame is closed
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
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == rock_btn){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            rock_btn.setEnabled(false);
            paper_btn.setEnabled(false);
            scissors_btn.setEnabled(false);
            lizard_btn.setEnabled(false);
            spock_btn.setEnabled(false);
            
            rock_lb.setVisible(true);
        }else if(e.getSource() == paper_btn){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            rock_btn.setEnabled(false);
            paper_btn.setEnabled(false);
            scissors_btn.setEnabled(false);
            lizard_btn.setEnabled(false);
            spock_btn.setEnabled(false);
            
            paper_lb.setVisible(true);
        }else if(e.getSource() == scissors_btn){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            rock_btn.setEnabled(false);
            paper_btn.setEnabled(false);
            scissors_btn.setEnabled(false);
            lizard_btn.setEnabled(false);
            spock_btn.setEnabled(false);
            
            scissors_lb.setVisible(true);
        }else if(e.getSource() == lizard_btn){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            rock_btn.setEnabled(false);
            paper_btn.setEnabled(false);
            scissors_btn.setEnabled(false);
            lizard_btn.setEnabled(false);
            spock_btn.setEnabled(false);
            
            lizard_lb.setVisible(true);
        }else if(e.getSource() == spock_btn){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            rock_btn.setEnabled(false);
            paper_btn.setEnabled(false);
            scissors_btn.setEnabled(false);
            lizard_btn.setEnabled(false);
            spock_btn.setEnabled(false);
            
            spock_lb.setVisible(true);
        }

    }
}