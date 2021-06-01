package Tetris;

public class U extends Tetromino {

    public U(){
        cells = new Cell[5];
        cells[0]=new Cell(0,4,Tetris.L);
        cells[1]=new Cell(0,5,Tetris.L);
        cells[2]=new Cell(0,3,Tetris.L);
        cells[3]=new Cell(1,3,Tetris.L);
        cells[4]=new Cell(1,5,Tetris.L);
    }

}
