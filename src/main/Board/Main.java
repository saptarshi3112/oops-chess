package main.Board;

import main.Utility.Team;

import javax.swing.*;

public class Main extends JFrame {

    Main() {
        this.setTitle("OopsChess");
        this.add(new GameBoard());

//        Player p1 = new Player("Saptarshi", Team.WHITE);
//        Player p2 = new Player("Bubai", Team.BLACK);
//
//        int turn = 0;
//
//        int counter = 0;
//        while (counter != 50) {
//            System.out.println(turn);
//            counter += 1;
//            turn ^= 1;
//        }

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
