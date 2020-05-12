package Shapes;

import java.awt.*;

import Game.Blocks;
import Game.Tetris;

public class IShape extends Shapes {
    private boolean[][] Ishapes;
    private int x, y, a;
    private Color color;

    public IShape() {
        Ishapes = new boolean[][]{{ true,  true,  true,  true}};;
        x = 5* Tetris.BLOCK;
        y = 0;
        a = Tetris.BLOCK*(Ishapes[0].length/2);
        color = Color.RED;
    }

    public void moveLeft() {
        if ( x-a >= Tetris.BLOCK )
            x -= Tetris.BLOCK;
    }

    public void moveRight() {
        if( Tetris.BLOCK*10 - ( (x-a) + Ishapes[0].length*Tetris.BLOCK ) >= Tetris.BLOCK )
        { x += Tetris.BLOCK; }
    }

    public void moveDown()
    {
        if( Ishapes.length*Tetris.BLOCK+y <= 19*(Tetris.BLOCK) )
        { y += Tetris.BLOCK; }
    }
    public void turn()
    {
        boolean[][] temp = new boolean[Ishapes[0].length][Ishapes.length];
        for( int r = 0; r < Ishapes.length; r++ )
        {
            for( int c = 0; c < Ishapes[0].length; c++ )
            {
                int i = temp.length-1-c;
                temp[i][r] = Ishapes[r][c];
            }
        }
        a = Tetris.BLOCK*( ( temp[0].length - Ishapes[0].length ) / 2 );
        y += Tetris.BLOCK*( ( Ishapes.length - temp.length ) / 2 );
        Ishapes = temp;
        if( y < 0 )
        {
            y = 0;
        }
        if( x-a <= Tetris.BLOCK )
        {
            x = 0;
            a = 0;
        }
        if( Tetris.BLOCK*10 - ( (x-a) + Ishapes[0].length*Tetris.BLOCK ) <= Tetris.BLOCK )
        {
            x = Tetris.BLOCK*10 - Ishapes[0].length * Tetris.BLOCK;
            a = 0;
        }
    }

    public int getXA() {
        return x-a;
    }
    public int getY()
    { return y; }
    public void setColor( Color c )
    { color = c; }
    public Color getColor()
    { return color; }
    public boolean[][] getArray()
    { return Ishapes; }
    public boolean getBlock( int r, int c )
    { return Ishapes[r][c]; }
    public void draw( Graphics page )
    {
        for( int r = 0; r < Ishapes.length; r++ )
        {
            for( int c = 0; c < Ishapes[0].length; c++ )
            {
                if( Ishapes[r][c] )
                {
                    Blocks block = new Blocks( c*Tetris.BLOCK + x-a, r*Tetris.BLOCK + y );
                    block.draw( page, color );
                }
            }
        }
    }
}
