import javax.swing.*;

import Game.Tetris;

public class GameDriver {
    public static void main( String[] args ) {
        //create a JFrame (window) that will be visible on screen
        JFrame frame = new JFrame( "Tetris" );

        frame.setLocation( 400, 50 ); // place the frame in the upper left corner
        Tetris tetris = new Tetris( 300, 400 ); // create a Game object with width = 300, height = 400
        frame.getContentPane().add(tetris); // add game to the frame so it will be on the screen
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tetris.playTetris(); // call the playGame() method to initiate the game
    }
}