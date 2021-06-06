package Tetris;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ZD extends Tetromino {
    public ZD (){
        cells = new Cell[1];
        cells[0] = new Cell(0,4,Tetris.WALL);
        type = 1;
    }
    private Boolean haveTime = true;
    int delay=5000;    //时间间隔，单位为毫秒
    ActionListener taskPerformer = e -> { haveTime = false; };
    Timer timer = new Timer(delay,taskPerformer);
    @Override
    public Cell[] getCells() {
        return super.getCells();
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
    }

    @Override
    public void moveRight() {
        super.moveRight();
    }

    @Override
    public void softDrop() {
        timer.start();
    }

    @Override
    public Boolean getHaveTime() {
        return haveTime;
    }

    @Override
    public void rotateRight() {
        super.rotateRight();
    }

    @Override
    public void rotateLeft() {
        super.rotateLeft();
    }
}
