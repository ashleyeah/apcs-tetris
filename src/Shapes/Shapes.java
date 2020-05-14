package Shapes;

import Game.Blocks;
import Game.Tetris;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shapes {
    private boolean[][] Shapes;
    private int x, y, a;
    private Color color;

    public Shapes(boolean[][] shapes) {
        Shapes = shapes;
        x = 5 * Tetris.BLOCK;
        y = 0;
        a = Tetris.BLOCK * (Shapes[0].length / 2);
        color = Color.RED;
    }

    public void moveLeft() {
        if (x - a >= Tetris.BLOCK)
            x -= Tetris.BLOCK;
    }

    public void moveRight() {
        if (Tetris.BLOCK * 10 - ((x - a) + Shapes[0].length * Tetris.BLOCK) >= Tetris.BLOCK)
            x += Tetris.BLOCK;
    }

    public void moveDown() {
        if (Shapes.length * Tetris.BLOCK + y <= 19 * (Tetris.BLOCK))
            y += Tetris.BLOCK;
    }

    public void turn()
    {
        boolean[][] temp = new boolean[Shapes[0].length][Shapes.length];
        for (int r = 0; r < Shapes.length; r++) {
            for (int c = 0; c < Shapes[0].length; c++) {
                int i = temp.length-1-c;
                temp[i][r] = Shapes[r][c];
            }
        }
        a = Tetris.BLOCK * ((temp[0].length - Shapes[0].length) / 2);
        y += Tetris.BLOCK * ((Shapes.length - temp.length) / 2);
        Shapes = temp;
        if (y < 0)
            y = 0;
        if (x - a <= Tetris.BLOCK) {
            x = 0;
            a = 0;
        }
        if (Tetris.BLOCK * 10 - ((x - a) + Shapes[0].length * Tetris.BLOCK) <= Tetris.BLOCK) {
            x = Tetris.BLOCK * 10 - Shapes[0].length * Tetris.BLOCK;
            a = 0;
        }
    }

    public void setColor( Color c ) {
        color = c;
    }

    public int getXA() {
        return x - a;
    }

    public int getY() {
        return y;
    }

    public boolean[][] getArray() {
        return Shapes;
    }

    public Color getColor() {
        return color;
    }

    public boolean getBlock( int r, int c ) {
        return Shapes[r][c];
    }

    public void draw( Graphics page ) {
        for (int r = 0; r < Shapes.length; r++) {
            for (int c = 0; c < Shapes[0].length; c++) {
                if(Shapes[r][c]) {
                    Blocks block = new Blocks(c * Tetris.BLOCK + x - a, r * Tetris.BLOCK + y);
                    block.draw(page, color);
                }
            }
        }
    }
}