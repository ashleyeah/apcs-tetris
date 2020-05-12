package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Shapes.Shapes;
import Shapes.IShape;
import Shapes.JShape;
import Shapes.LShape;
import Shapes.OShape;
import Shapes.SShape;
import Shapes.TShape;
import Shapes.ZShape;

public class Tetris extends JPanel implements KeyListener {
    private boolean over;
    private Color[][] board;
    private boolean[][] tetris;
    private Shapes shape;
    private int time, color;
    private int points;
    public static final int BLOCK = 20;
    private JLabel label;
    private JLabel pic;

    //constructor - sets the initial conditions for this Game object
    public Tetris(int width, int height) {
        //make a panel with dimensions width by height with a black background
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(width, height));

        points = 0;

        label = new JLabel("Score = " + points);
        label.setVisible(true);
        this.add(label);
        label.setBounds(150, 50, 200, 100);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Courier", Font.BOLD, 15));

        ImageIcon icon = new ImageIcon("src/Tetris.png");
        pic = new JLabel(icon);
        pic.setVisible(true);
        this.add(pic);
        pic.setBounds(150, 5, 200, 100);
        pic.setHorizontalAlignment(SwingConstants.CENTER);

        //initialize the instance variables
        over = false;
        board = new Color[20][10];
        tetris = new boolean[20][10];
        for (int r = 0; r < board.length; r++) {
            for( int c = 0; c < board[0].length; c++ ) {
                board[r][c] = Color.BLUE;
            }
        }
        for (int r = 0; r < tetris.length; r++) {
            for( int c = 0; c < tetris[0].length; c++ ) {
                tetris[r][c] = true;
            }
        }

        time = 0;
        color = 0;
        shape = chooseShape();
        shape.setColor(chooseColor());

        this.addKeyListener(this);
        this.setFocusable(true);
    }

    //This is the method that runs the game
    public void playTetris() {
        while (!over) {
            label.setText( "Score = " + points );
            try {
                Thread.sleep(15);
            } catch(InterruptedException ignored) {}
            time += 15;
            if (time % 1200 == 0)
                shape.moveDown();
            for (int c = shape.getArray()[0].length-1; c >= 0; c--) {
                for (int r = shape.getArray().length-1; r >= 0; r--) {
                    if (shape.getBlock(r, c)) {
                        if (((r*BLOCK + shape.getY())/BLOCK + 1) > tetris.length-1
                                || !tetris[(r * BLOCK + shape.getY()) / BLOCK + 1]
                                [(c * BLOCK + shape.getXA()) / BLOCK]) {
                            for (int j = shape.getArray()[0].length-1; j >= 0; j--) {
                                for(int i = shape.getArray().length-1; i >= 0; i--) {
                                    if(shape.getArray()[i][j]) {
                                        tetris[(i*BLOCK + shape.getY())/BLOCK]
                                                [(j*BLOCK + shape.getXA())/BLOCK] = false;
                                        board[(i*BLOCK + shape.getY())/BLOCK]
                                                [(j*BLOCK + shape.getXA())/BLOCK] = shape.getColor();
                                    }
                                }
                            }
                            color++;
                            shape = chooseShape();
                            shape.setColor( chooseColor() );
                            break;
                        }
                    }
                }
            }
            if (checkLost()) {
                over = true;
            }
            checkRows();
            this.repaint(); //redraw the screen with the updated locations; calls paintComponent below
        }
//        if (over) {
//            System.exit(0);
//        }
    }

    //checks for full rows/removes them
    public void checkRows() {
        for (int r = 0; r < tetris.length; r++) {
            int count = 0;
            for (int c = 0; c < tetris[0].length; c++) {
                if (!tetris[r][c]) {
                    count++;
                }
            }
            if (count == tetris[0].length) {
                for (int i = r; i > 0; i--) {
                    for (int j = 0; j < tetris[0].length; j++) {
                        tetris[i][j] = tetris[i-1][j];
                        board[i][j] = board[i-1][j];
                    }
                }
                for (int k = 0; k < tetris[0].length; k++) {
                    tetris[0][k] = true;
                    board[0][k] = Color.BLUE;
                }
                points++;
            }
        }
    }

    public boolean checkLost() {
        for (int c = 0; c < tetris[0].length; c++) {
            if (!tetris[0][c]) {
                return true;
            }
        }
        return false;
    }

    public Color chooseColor() {
        Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.MAGENTA,
            Color.CYAN, Color.YELLOW, Color.ORANGE};
        return colors[color % 6];
    }

    public Shapes chooseShape() {
        Shapes[] shapes = new Shapes[]{ new IShape(), new JShape(), new LShape(),
                new OShape(), new SShape(), new TShape(), new ZShape() };
        int s = (int)(Math.random() * shapes.length);
        return shapes[s];
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                Blocks block = new Blocks(c*BLOCK, r*BLOCK);
                block.draw(page, board[r][c]);
            }
        }
        shape.draw(page);
    }

    //tells the program what to do when keys are pressed
    public void keyReleased(KeyEvent event) {}

    //not used but must be present
    public void keyPressed( KeyEvent event ) {
        if(event.getKeyCode() == KeyEvent.VK_RIGHT)
            shape.moveRight();
        else if(event.getKeyCode() == KeyEvent.VK_LEFT)
            shape.moveLeft();
        else if(event.getKeyCode() == KeyEvent.VK_DOWN)
            shape.moveDown();
        else if(event.getKeyCode() == KeyEvent.VK_UP)
            shape.turn();
    }

    //not used but must be present
    public void keyTyped(KeyEvent event) {}
}
