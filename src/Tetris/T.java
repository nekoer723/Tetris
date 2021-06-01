package Tetris;

public class T extends Tetromino{
    /**
     * 提供构造器，进行初始化
     * T型的四格方块的位置
     * */
    public T() {
        cells[0]=new Cell(0,4,Tetris.S);
        cells[1]=new Cell(0,3,Tetris.S);
        cells[2]=new Cell(0,5,Tetris.S);
        cells[3]=new Cell(1,4,Tetris.S);

    }
}