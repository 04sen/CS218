import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener{
    JButton button = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();

    public static void main(String[] args){
        new GameFrame();
    }

    public GameFrame(){


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
        }else if(e.getSource() == button2){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
        }else if(e.getSource() == button3){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
        }else if(e.getSource() == button4){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
        }else if(e.getSource() == button5){ //if button is pressed
            
            //disables all the buttons so users can not press it again.
            button.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
        }

    }




}