package main.Board;

import main.Utility.Team;

import javax.swing.*;

public class Main extends JFrame {

    Main() {
        this.setTitle("OopsChess");
        this.add(new GameBoard());

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
