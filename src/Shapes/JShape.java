package Shapes;

import java.awt.Color;
import java.awt.Graphics;

import Game.Tetris;
import Game.Blocks;

public class JShape extends Shapes {
    private boolean[][] Jshapes;
    private int x, y, a;
    private Color color;

    public JShape() {
        Jshapes = new boolean[][]{{ true, false, false },{ true, true, true}};
        x = 5*Tetris.BLOCK;
        y = 0;
        a = Tetris.BLOCK*(Jshapes[0].length/2);
        color = Color.RED;
    }

    public void moveLeft() {
        if( x-a >= Tetris.BLOCK )
            x -= Tetris.BLOCK;
    }

    public void moveRight() {
        if( Tetris.BLOCK*10 - ( (x-a) + Jshapes[0].length*Tetris.BLOCK ) >= Tetris.BLOCK )
            x += Tetris.BLOCK;
    }

    public void moveDown() {
        if( Jshapes.length*Tetris.BLOCK+y <= 19*Tetris.BLOCK )
        { y += Tetris.BLOCK; }
    }

    public int getXA() {
        return x-a;
    }

    public int getY() {
        return y;
    }

    public void turn() {
        boolean[][] temp = new boolean[Jshapes[0].length][Jshapes.length];
        for ( int r = 0; r < Jshapes.length; r++ ) {
            for ( int c = 0; c < Jshapes[0].length; c++ ) {
                int i = temp.length-1-c;
                temp[i][r] = Jshapes[r][c];
            }
        }
        a = Tetris.BLOCK*( ( temp[0].length - Jshapes[0].length ) / 2 );
        y += Tetris.BLOCK*( ( Jshapes.length - temp.length ) / 2 );
        Jshapes = temp;
        if ( y < 0 )
            y = 0;
        if( x-a <= Tetris.BLOCK ) {
            x = 0;
            a = 0;
        }
        if( Tetris.BLOCK*10 - ( (x-a) + Jshapes[0].length*Tetris.BLOCK ) <= Tetris.BLOCK )
        {
            x = Tetris.BLOCK*10 - Jshapes[0].length * Tetris.BLOCK;
            a = 0;
        }
    }
    public void setColor( Color c )
    { color = c; }
    public Color getColor()
    { return color; }
    public boolean[][] getArray()
    { return Jshapes; }
    public boolean getBlock( int r, int c )
    { return Jshapes[r][c]; }
    public void draw( Graphics page )
    {
        for( int r = 0; r < Jshapes.length; r++ )
        {
            for( int c = 0; c < Jshapes[0].length; c++ )
            {
                if( Jshapes[r][c] )
                {
                    Blocks block = new Blocks( c*Tetris.BLOCK + x - a, r*Tetris.BLOCK + y );
                    block.draw( page, color );
                }
            }
        }
    }
}