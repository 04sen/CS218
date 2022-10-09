/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpslk_alpha_1;

/**
 *
 * @author MAANVIK
 */

import java.util.Scanner;

public class Rpsls {

       
    static int name_to_number(String name) {
        
        switch(name) {
            case "rock":
                return 0;
            case "paper":
                return 1;
            case "scissors":
                return 2;
            case "lizard":
                return 3;
            case "spock":
                return 4;
            default:
                return 5;
        }
    }
    
    public static void main(String[] args){
        
        String playerMove;
        String player2Move;
        String player3Move;
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your move Player 1 (rock, paper, scissors, lizard, or spock)");
        playerMove = scanner.nextLine();
        System.out.println("Please enter your move PLAYER 2 (rock, paper, scissors, lizard, or spock)");
        player2Move = scanner.nextLine();
        System.out.println("Please enter your move PLAYER 3 (rock, paper, scissors, lizard, or spock)");
        player3Move = scanner.nextLine();
        
      
      //  int player_number = name_to_number(player_guess);

        int playerMove1 = name_to_number(playerMove);
        int playerMove2 = name_to_number(player2Move);
        int playerMove3 = name_to_number(player3Move);
        
        int winner;
        
        System.out.println("Player 1 chooses " + playerMove);
        System.out.println("Player 2 chooses " + player2Move);
        System.out.println("Player 3 chooses " + player3Move);
        
        if (playerMove1 == playerMove2 && playerMove2 == playerMove3 ) {
            System.out.println("It is a tie\n");
        }
        if ((playerMove2 - playerMove1) % 5 < 3) {
            winner = playerMove2;
            System.out.println("Player 2 wins!\n");
        } else {
            winner = playerMove1;
            System.out.println("Player 1 wins!\n");
        }
        if ((winner - playerMove2) % 5 < 3) {
            System.out.println("Player 3 wins!\n");
        } else {
            System.out.println("Player 2 wins!\n");
        }
}
}
