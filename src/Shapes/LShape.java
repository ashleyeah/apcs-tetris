package Shapes;

import java.awt.Color;
import java.awt.Graphics;

import Game.Tetris;
import Game.Blocks;

public class LShape extends Shapes
{
    private boolean[][] Lshapes;
    private int x, y, a;
    private Color color;
    public LShape()
    {
        Lshapes = new boolean[][]{{ false, false, true },{ true, true, true}};
        x = 5*Tetris.BLOCK;
        y = 0;
        a = Tetris.BLOCK*(Lshapes[0].length/2);
        color = Color.RED;
    }
    public void moveLeft()
    {
        if( x-a >= Tetris.BLOCK )
        { x -= Tetris.BLOCK; }
    }
    public void moveRight()
    {
        if( Tetris.BLOCK*10 - ( (x-a) + Lshapes[0].length*Tetris.BLOCK ) >= Tetris.BLOCK )
        { x += Tetris.BLOCK; }
    }
    public void moveDown()
    {
        if( Lshapes.length*Tetris.BLOCK+y <= 19*Tetris.BLOCK )
        { y += Tetris.BLOCK; }
    }
    public int getXA()
    { return x-a; }
    public int getY()
    { return y; }
    public void turn()
    {
        boolean[][] temp = new boolean[Lshapes[0].length][Lshapes.length];
        for( int r = 0; r < Lshapes.length; r++ )
        {
            for( int c = 0; c < Lshapes[0].length; c++ )
            {
                int i = temp.length-1-c;
                temp[i][r] = Lshapes[r][c];
            }
        }
        a = Tetris.BLOCK*( ( temp[0].length - Lshapes[0].length ) / 2 );
        y += Tetris.BLOCK*( ( Lshapes.length - temp.length ) / 2 );
        Lshapes = temp;
        if( y < 0 )
        {
            y = 0;
        }
        if( x-a <= Tetris.BLOCK )
        {
            x = 0;
            a = 0;
        }
        if( Tetris.BLOCK*10 - ( (x-a) + Lshapes[0].length*Tetris.BLOCK ) <= Tetris.BLOCK )
        {
            x = Tetris.BLOCK*10 - Lshapes[0].length * Tetris.BLOCK;
            a = 0;
        }
    }
    public void setColor( Color c )
    { color = c; }
    public Color getColor()
    { return color; }
    public boolean[][] getArray()
    { return Lshapes; }
    public boolean getBlock( int r, int c )
    { return Lshapes[r][c]; }
    public void draw( Graphics page )
    {
        for( int r = 0; r < Lshapes.length; r++ )
        {
            for( int c = 0; c < Lshapes[0].length; c++ )
            {
                if( Lshapes[r][c] )
                {
                    Blocks block = new Blocks( c*Tetris.BLOCK + x - a, r*Tetris.BLOCK + y );
                    block.draw( page, color );
                }
            }
        }
    }
}
