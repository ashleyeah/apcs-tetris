import javax.swing.JFrame;

import Game.Tetris;

public class Driver {
    public static void main( String[] args ) {
        //create a JFrame (window) that will be visible on screen
        JFrame frame = new JFrame( "Game" );

        frame.setLocation( 400, 50 ); // place the frame in the upper left corner
        Tetris tetris = new Tetris( 300, 400 ); // create a Game object with width = 600, height = 800
        frame.getContentPane().add(tetris); // add game to the frame so it will be on the screen
        frame.pack();
        frame.setVisible(true);
        tetris.playTetris(); // call the playGame() method to intitiate the game
    }
}