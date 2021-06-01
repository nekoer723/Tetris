package Tetris;

public class Ten extends Tetromino {
    public Ten(){
        type = 2;
        cells = new Cell[5];
        cells[0]=new Cell(1,4,Tetris.T);
        cells[1]=new Cell(1,3,Tetris.T);
        cells[2]=new Cell(0,4,Tetris.T);
        cells[3]=new Cell(1,5,Tetris.T);
        cells[4]=new Cell(2,4,Tetris.T);
    }

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
        super.softDrop();
    }

    @Override
    public Boolean getHaveTime() {
        return super.getHaveTime();
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
