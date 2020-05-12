package Shapes;

import java.awt.Color;
import java.awt.Graphics;

import Game.Tetris;
import Game.Blocks;

public class OShape extends Shapes
{
    private boolean[][] Oshapes;
    private int x, y, a;
    private Color color;
    public OShape()
    {
        Oshapes = new boolean[][]{{ true, true },{ true, true }};
        x = 5*Tetris.BLOCK;
        y = 0;
        a = Tetris.BLOCK*(Oshapes[0].length/2);
        color = Color.RED;
    }
    public void moveLeft()
    {
        if( x-a >= Tetris.BLOCK )
        { x -= Tetris.BLOCK; }
    }
    public void moveRight()
    {
        if( Tetris.BLOCK*10 - ( (x-a) + Oshapes[0].length*Tetris.BLOCK ) >= Tetris.BLOCK )
        { x += Tetris.BLOCK; }
    }
    public void moveDown()
    {
        if( Oshapes.length*Tetris.BLOCK+y <= 19*Tetris.BLOCK )
        { y += Tetris.BLOCK; }
    }
    public int getXA()
    { return x-a; }
    public int getY()
    { return y; }
    public void turn()
    {}
    public void setColor( Color c )
    { color = c; }
    public Color getColor()
    { return color; }
    public boolean[][] getArray()
    { return Oshapes; }
    public boolean getBlock( int r, int c )
    { return Oshapes[r][c]; }
    public void draw( Graphics page )
    {
        for( int r = 0; r < Oshapes.length; r++ )
        {
            for( int c = 0; c < Oshapes[0].length; c++ )
            {
                if( Oshapes[r][c] )
                {
                    Blocks block = new Blocks( c*Tetris.BLOCK + x - a, r*Tetris.BLOCK + y );
                    block.draw( page, color );
                }
            }
        }
    }
}
