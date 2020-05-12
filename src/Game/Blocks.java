package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Blocks {
    int x;
    int y;

    public Blocks( int xPos, int yPos ) {
        x = xPos;
        y = yPos;
    }

    public void draw( Graphics page, Color color ) {
        page.setColor( color );
        page.fillRect( x, y, Tetris.BLOCK, Tetris.BLOCK );
        Graphics2D g2 = (Graphics2D) page;
        g2.setStroke(new BasicStroke(3));
        page.setColor( Color.BLACK );
        page.drawRect( x, y, Tetris.BLOCK, Tetris.BLOCK );
    }
}