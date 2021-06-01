package Tetris;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/*
 * 四格方块
 * 属性:
 * 		--cells----四个方块
 * 行为:
 * 		moveLeft()
 * 		moveRight()
 * 		softDrop()
 */

class Tetromino {
    protected int type = 0;
    protected Cell[] cells=new Cell[4];
    private Boolean haveTime = true;
    public Cell[] getCells() {
        return cells;
    }
    /*四格方块向左移动
     * 实际上：就是每个方块向左移动
     */
    public void moveLeft() {//向左移动
        for(int i=0;i<cells.length;i++) {//for循环遍历整个"方块组"的四格方块
            Cell cell = cells[i];//四格方块都要移动
            cell.left();
        }
    }
    public void moveRight() {//向右移动
        for(Cell c:cells) {//此处使用增强for循环也可以
            c.right();
        }
    }
    public void softDrop() {//下落
        for(Cell c:cells) {
            c.drop();
        }
    }
    public Boolean getHaveTime(){
        return true;
    }
    public void rotateRight() {//向右旋转
        /**
         * 1.将cells[0]作为中心点，获取它的横纵坐标
         * 2.根据笛卡尔坐标系90度旋转公式(顺时针)：
         *   A.x = O.x - O.y + B.y
         *   A.y = O.x + O.y - B.x
         * 求出A.x和A.y，并通过遍历cells[]，再使用setCol()和setRow()方法将A.x和A.y赋值给cell[1],cell[2],cell[3]
         * 由此完成旋转方法
         *
         *   A.x:除中心点外，其他格子旋转后的横坐标   A.y:除中心点外，其他格子旋转后的纵坐标
         *   B.x:除中心点外，其他格子的当前横坐标     B.y:除中心点外，其他格子的当前纵坐标
         *   O.x:中心点的横坐标   O.y：中心点的纵坐标
         */
        Cell cell = cells[0];
        int col = cell.getCol();
        int row = cell.getRow();
        for (int i = 1;i<cells.length;i++){
            int tmpc = cells[i].getCol();
            int tmpr = cells[i].getRow();
            cells[i].setCol(col-row+tmpr);
            cells[i].setRow(col+row-tmpc);
        }
    }
    public void rotateLeft() {//向左旋转方法
        /**
         * 1.将cells[0]作为中心点，获取它的横纵坐标
         * 2.根据笛卡尔坐标系90度旋转公式(逆时针)：
         *   A.x = O.y + O.x - B.y
         *   A.y = O.y - O.x + B.x
         * 求出A.x和A.y，并通过遍历cells[]，再使用setCol()和setRow()方法将A.x和A.y赋值给cell[1],cell[2],cell[3]
         * 由此完成旋转方法
         *
         *   A.x:除中心点外，其他格子旋转后的横坐标   A.y:除中心点外，其他格子旋转后的纵坐标
         *   B.x:除中心点外，其他格子的当前横坐标     B.y:除中心点外，其他格子的当前纵坐标
         *   O.x:中心点的横坐标   O.y：中心点的纵坐标
         */
        Cell cell = cells[0];
        int col = cell.getCol();
        int row = cell.getRow();
        for (int i = 1;i<cells.length;i++){
            int tmpc = cells[i].getCol();
            int tmpr = cells[i].getRow();
            cells[i].setCol(col+row-tmpr);
            cells[i].setRow(row-col+tmpc);
        }
    }
}