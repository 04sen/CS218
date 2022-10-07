import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener{
    JButton button = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();

    ImageIcon rock_img = new ImageIcon("RPSLS\\Imgs\\Rock.png");
    JLabel label = new JLabel();
    ImageIcon paper_img = new ImageIcon("RPSLS\\Imgs\\paper.png");
    JLabel label2 = new JLabel();
    ImageIcon scissors_img = new ImageIcon("RPSLS\\Imgs\\scissors.png");
    JLabel label3 = new JLabel();
    ImageIcon lizard_img = new ImageIcon("RPSLS\\Imgs\\lizard.png");
    JLabel label4 = new JLabel();
    ImageIcon spock_img = new ImageIcon("RPSLS\\Imgs\\spock.png");
    JLabel label5 = new JLabel();
    

    public static void main(String[] args){
        new GameFrame();
    }

    public GameFrame(){

        //Labels
        label.setText("You have chosen Rock!");
        label.setIcon(rock_img);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setBounds(350, 500, 500,130);
        label.setVisible(false);

        label2.setText("You have chosen Paper!");
        label2.setIcon(paper_img);
        label2.setHorizontalTextPosition(JLabel.LEFT);
        label2.setBounds(350, 500, 500,130);
        label2.setVisible(false);

        label3.setText("You have chosen Scissors!");
        label3.setIcon(scissors_img);
        label3.setHorizontalTextPosition(JLabel.LEFT);
        label3.setBounds(350, 500, 500,130);
        label3.setVisible(false);

        label4.setText("You have chosen Lizard!");
        label4.setIcon(lizard_img);
        label4.setHorizontalTextPosition(JLabel.LEFT);
        label4.setBounds(350, 500, 500,130);
        label4.setVisible(false);

        label5.setText("You have chosen Spock!");
        label5.setIcon(spock_img);
        label5.setHorizontalTextPosition(JLabel.LEFT);
        label5.setBounds(350, 500, 500,150);
        label5.setVisible(false);

        //Buttons
        button.setBounds(200, 400, 90, 50); //Sets the Bounds of the Button
        button.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        button.setText("Rock"); //Label for Button
        button.setFocusable(false); //allows Button to show above of Background
        
        button2.setBounds(310, 400, 90, 50); //Sets the Bounds of the Button
        button2.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        button2.setText("Paper"); //Label for Button
        button2.setFocusable(false); //allows Button to show above of Background
        
        button3.setBounds(420, 400, 90, 50); //Sets the Bounds of the Button
        button3.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        button3.setText("Scissors"); //Label for Button
        button3.setFocusable(false); //allows Button to show above of Background
       
        button4.setBounds(530, 400, 90, 50); //Sets the Bounds of the Button
        button4.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        button4.setText("Lizard"); //Label for Button
        button4.setFocusable(false); //allows Button to show above of Background
        
        button5.setBounds(640, 400, 90, 50); //Sets the Bounds of the Button
        button5.addActionListener(this); //Allows Button to be clicked using ActionListener interface
        button5.setText("Sprock"); //Label for Button
        button5.setFocusable(false); //allows Button to show above of Background


        //Allows the Code to stop when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setLayout(null);
        setSize(1000,700);
        
        //Allows the JFrame to appear in the  middle of the screeen
        setLocationRelativeTo(null);

        //Allows the JFrame to be Visible
        setVisible(true); 


        this.add(button);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            
            label.setVisible(true);
        }else if(e.getSource() == button2){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            
            label2.setVisible(true);
        }else if(e.getSource() == button3){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            
            label3.setVisible(true);
        }else if(e.getSource() == button4){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            
            label4.setVisible(true);
        }else if(e.getSource() == button5){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            
            label5.setVisible(true);
        }

    }




}