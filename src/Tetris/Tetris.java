package Tetris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

/*
 * 俄罗斯方块的主类:
 * 前提：必须是一块面板Jpanel,可以嵌入窗口
 * 面板上自带一个画笔，有一个功能：自动绘制
 * 其实是调用了JPanel里的paint()方法
 *
 *
 * (1)加载静态资源
 */

public class Tetris extends JPanel{
    private static final int ZDType = 1;
    //private static final int TenType = 2;
    private  Boolean ModelU = true;
    private  Boolean ModelTen = true;
    private  Boolean ModelZD = true;


    public  Tetromino randomOne() {
//随机生成方块,七种方块形状分别为O,T,I,J,L,S,Z
        Tetromino  t = null;
        if (ModelU){
            if(ModelTen){
                int num = (int)(Math.random()*10);
                switch(num) {
                    case 0:t = new O();break;
                    case 1:t = new T();break;
                    case 2:t = new I();break;
                    case 3:t = new J();break;
                    case 4:t = new L();break;
                    case 5:t = new S();break;
                    case 6:t = new Z();break;
                    case 7:t = new ZD();break;
                    case 8:t = new U();break;
                    case 9:t = new Ten();break;
                }
            }else {
                if(ModelZD){
                    int num = (int)(Math.random()*9);
                    switch(num) {
                        case 0:t = new O();break;
                        case 1:t = new T();break;
                        case 2:t = new I();break;
                        case 3:t = new J();break;
                        case 4:t = new L();break;
                        case 5:t = new S();break;
                        case 6:t = new Z();break;
                        case 7:t = new ZD();break;
                        case 8:t = new U();break;
                        //case 9:t = new Ten();break;
                    }
                }
                else {
                    int num = (int)(Math.random()*8);
                    switch(num) {
                        case 0:t = new O();break;
                        case 1:t = new T();break;
                        case 2:t = new I();break;
                        case 3:t = new J();break;
                        case 4:t = new L();break;
                        case 5:t = new S();break;
                        case 6:t = new Z();break;
                        //case 7:t = new ZD();break;
                        case 7:t = new U();break;
                        //case 9:t = new Ten();break;
                    }
                }
            }
        }else {
            if(ModelTen){
                int num = (int)(Math.random()*9);
                switch(num) {
                    case 0:t = new O();break;
                    case 1:t = new T();break;
                    case 2:t = new I();break;
                    case 3:t = new J();break;
                    case 4:t = new L();break;
                    case 5:t = new S();break;
                    case 6:t = new Z();break;
                    case 7:t = new ZD();break;
                    //case 8:t = new U();break;
                    case 8:t = new Ten();break;
                }
            }else {
                if(ModelZD){
                    int num = (int)(Math.random()*8);
                    switch(num) {
                        case 0:t = new O();break;
                        case 1:t = new T();break;
                        case 2:t = new I();break;
                        case 3:t = new J();break;
                        case 4:t = new L();break;
                        case 5:t = new S();break;
                        case 6:t = new Z();break;
                        case 7:t = new ZD();break;
                        //case 8:t = new U();break;
                        //case 9:t = new Ten();break;
                    }
                }
                else {
                    int num = (int)(Math.random()*7);
                    switch(num) {
                        case 0:t = new O();break;
                        case 1:t = new T();break;
                        case 2:t = new I();break;
                        case 3:t = new J();break;
                        case 4:t = new L();break;
                        case 5:t = new S();break;
                        case 6:t = new Z();break;
                        //case 7:t = new ZD();break;
                        //case 8:t = new U();break;
                        //case 9:t = new Ten();break;
                    }
                }
            }
        }

        if(ModelU&&ModelTen&&ModelZD){

        }
        else if(ModelTen&&ModelZD){
            int num = (int)(Math.random()*9);
            switch(num) {
                case 0:t = new O();break;
                case 1:t = new T();break;
                case 2:t = new I();break;
                case 3:t = new J();break;
                case 4:t = new L();break;
                case 5:t = new S();break;
                case 6:t = new Z();break;
                case 7:t = new ZD();break;
                //case 8:t = new U();break;
                case 8:t = new Ten();break;
            }
        }

        return t;
    }
    int flag = 0;
    /*属性：正在下落的四格方块*/
    private Tetromino currentOne = randomOne();
    /*属性：将要下落的四格方块*/
    private Tetromino nextOne = randomOne();
    /*属性：墙,20行 10列的 表格  宽度为26*/
    private Cell[][] wall=new Cell[20][10];
    private static final int CELL_SIZE=26;

    int[] scores_pool = { 0, 1, 2, 5, 10 };

    private int totalScore = 0;//总分
    private int totalLine = 0;//总行数

    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;

    private int game_state;

    String[] showState = { "P[pause]", "C[continue]", "Enter[replay]" };

    public static BufferedImage T;//各种形状的方块
    public static BufferedImage I;
    public static BufferedImage O;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage S;
    public static BufferedImage Z;
    public static BufferedImage WALL;
    public static BufferedImage background;//游戏背景
    public static BufferedImage game_over;//游戏结束

    static {
        try {
            //getResource(String url) url:加载图片的路径 相对位置是同包下
            WALL = ImageIO.read(Tetris.class.getResource("images/Add.png"));
            T = ImageIO.read(Tetris.class.getResource("images/T.png"));
            O = ImageIO.read(Tetris.class.getResource("images/O.png"));
            I = ImageIO.read(Tetris.class.getResource("images/I.png"));
            J = ImageIO.read(Tetris.class.getResource("images/J.png"));
            L = ImageIO.read(Tetris.class.getResource("images/L.png"));
            S = ImageIO.read(Tetris.class.getResource("images/S.png"));
            Z = ImageIO.read(Tetris.class.getResource("images/Z.png"));
            background = ImageIO.read(Tetris.class.getResource("images/tetris.png"));
            game_over = ImageIO.read(Tetris.class.getResource("images/game-over.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        // 绘制背景,在区域1
        //g：画笔 g.drawImage(image,x,y,null) image:绘制的图片 x:开始绘制的横坐标 y:开始绘制的纵坐标
        g.drawImage(background, 0, 0, null);
        // 平移坐标轴
        g.translate(15, 15);
        // 绘制墙
        paintWall(g);
        // 绘制正在下落的四格方块,在区域5
        paintCurrentOne(g);
        // 绘制下一个将要下落的四格方块,在区域2
        paintNextOne(g);
        paintScore(g);//绘制游戏分数和列数,分数在区域3,列数在区域4
        paintState(g);//绘制游戏状态,在区域6
    }

    private void paintState(Graphics g) {//在右侧绘制游戏状态
        if (game_state == GAMEOVER) {//游戏结束
            g.drawImage(game_over, 0, 0, null);
            g.drawString(showState[GAMEOVER], 285, 265);
        }
        if (game_state == PLAYING) {//正在游戏
            g.drawString(showState[PLAYING], 285, 265);
        }
        if (game_state == PAUSE) {//暂停游戏
            g.drawString(showState[PAUSE], 285, 265);
        }

    }

    public void paintScore(Graphics g) {//在右侧位置绘制游戏分数
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 26));
        g.drawString("SCORES:" + totalScore, 285, 165);
        g.drawString("LINES:" + totalLine, 285, 215);
    }

    /**
     * 绘制下一个将要下落的四格方块 绘制到面板的右上角的相应区域
     */
    public void paintNextOne(Graphics g) {
        // 获取nextOne对象的四个元素
        Cell[] cells = nextOne.getCells();
        for (Cell c : cells) {
            // 获取每一个元素的行号和列号
            int row = c.getRow();
            int col = c.getCol();
            // 横坐标和纵坐标
            int x = col * CELL_SIZE + 260;
            int y = row * CELL_SIZE + 26;
            g.drawImage(c.getImage(), x, y, null);
        }
    }

    /**
     * 绘制正在下落的四格方块 取出数组的元素 绘制元素的图片， 横坐标x: 纵坐标y:
     */
    public void paintCurrentOne(Graphics g) {
        Cell[] cells = currentOne.getCells();
        for (Cell c : cells) {
            int x = c.getCol() * CELL_SIZE;
            int y = c.getRow() * CELL_SIZE;
            g.drawImage(c.getImage(), x, y, null);
        }
    }

    /**
     * 墙是20行，10列的表格 是一个二维数组， 应该使用双层循环 绘制正方形。
     */
    public void paintWall(Graphics a) {
        // 外层循环控制行数
        for (int i = 0; i < 20; i++) {
            // 内层循环控制列数
            for (int j = 0; j < 10; j++) {

                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;

                Cell cell = wall[i][j];

                if (cell == null) {
                    a.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    a.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    public boolean isGameOver() {
        Cell[] cells = nextOne.getCells();
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row][col] != null) {//若方块已经达到第20行,则游戏结束
                return true;
            }
        }
        return false;
    }

    public boolean isFullLine(int row) {

        Cell[] line = wall[row];
        for (Cell c : line) {
            if (c == null) {//遍历到为空的方块即返回false,表明这一行没有满.
                return false;
            }
        }
        return true;
    }

    public void destroyLine(int row){
        if (isFullLine(row)){
            reFresh(row,0);
            timer.restart();
        }

//        int flag = 0;
//        for(int c = 0;c<10;c++){
//            if(wall[row][c]==null){
//
//            }else {
//                if(wall[row][c].getImage() == T)
//                    flag = 1;
//            }
//
//        }
//        if (isFullLine(row)){
//            reFresh(row,flag);
//            if(flag == 1){
//                for(int r = row-1;r>=0;r--){
//                    reFreshLine(r);
//                }
//
//            }
//            timer.restart();
//        }
    }
    public void reFreshLine(int r) {

//        if(flag == 0){
//            for (int c = 0;c<10;c++){
//                wall[r+1][c] = wall[r][c];
//            }
//            return;
//        }
        for(int c = 0;c<10;c++){
            if(wall[r][c] == null){
                if(wall[r+1][c]==null)
                    wall[r+1][c]=null;
                else {
                    if(wall[r+1][c].getImage()!=T)
                        wall[r+1][c]=null;
                }
            }
            else{
                if(wall[r][c].getImage()==T){
                    if(wall[r+1][c]==null){
                    }
                    else {
                        if(wall[r+1][c].getImage()!=T){
                            wall[r+1][c] = null;
                        }
                    }
                }else {
                    if(wall[r+1][c]==null)
                        wall[r+1][c]=wall[r][c];
                    else {
                        if(wall[r+1][c].getImage()!=T)
                            wall[r+1][c]=wall[r][c];
                    }
                }
            }
        }
    }

    public void reFresh(int r,int flag){
        if (flag == 0){
            for (int row = r-1;row>=0;row--){
                for (int c= 0;c<10;c++){
                    if(wall[row][c] == null){
                        wall[row+1][c] = null;
                    }else {
                        wall[row+1][c] = wall[row][c];
                    }
                }
            }
        }
//        if(flag == 0){
//            Cell[] tmp = new Cell[10];
//            for (int i =r;i>0;i--){
//                wall[i] = wall[i-1];
//            }
//            for(int i = 0;i<10;i++){
//                tmp[i] = null;
//            }
//            wall[0] = tmp;
//        }
    }

    public void destroyLine(){
        int lines = 0;
        //获取当前正在下落的形状方块
        Cell[] cells = currentOne.getCells();
        /**
         * 请在下方补全代码
         */

        for(int i = 0;i<cells.length;i++){
            while (isFullLine(cells[i].getRow())) {
                int flag = 0;
                for(int c = 0;c<10;c++){
                    if(wall[cells[i].getRow()][c]==null){
                    }else {
                        if(wall[cells[i].getRow()][c].getImage() == T)
                            flag = 1;
                    }
                }
                reFresh(cells[i].getRow(),flag);
               for(int r = cells[i].getRow()-1;r>=0;r--){
                   if (flag == 1){
                       reFreshLine(r);
                   }
               }
               lines++;
               timer.restart();
            }
        }




        //获取分数
        totalScore += scores_pool[lines];
        totalLine += lines;

    }

    public boolean canDrop() {
        Cell[] cells = currentOne.getCells();//当前方块数组
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (row == 19) {//落到底了
                return false;
            }
            if (wall[row + 1][col] != null) {//某一元素下面不为空
                return false;
            }
        }
        return true;
    }

    public void landToWall() {
        Cell[] cells = currentOne.getCells();
        if(currentOne.type == ZDType){
            for (Cell c :cells){
                int row = c.getRow();
                int col = c.getCol();
                wall[row][col] = null;
            }
        }
        else {
            for (Cell c : cells) {
                // 获取最终的行号和列号
                int row = c.getRow();
                int col = c.getCol();
                Cell cell = new Cell(row,col,WALL);
                if(c.getImage()==T)
                    wall[row][col] = c;
                else
                    wall[row][col] = cell;
            }
        }
    }

    public boolean outOfBounds() {//越界异常
        Cell[] cells = currentOne.getCells();
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (col < 0 || col > 9 || row > 19 || row < 0) {//不能越过wall[][]
                return true;
            }
        }
        return false;
    }

    public boolean coincide() {//两个方块重合
        Cell[] cells = currentOne.getCells();
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    protected void moveLeftAction() {
        currentOne.moveLeft();
        if (outOfBounds() || coincide()) {//如果左移出了边界,执行右移的方法防止游戏错误
            currentOne.moveRight();
        }
    }

    protected void moveRightAction() {
        currentOne.moveRight();
        if (outOfBounds() || coincide()) {//如果右移出了边界,执行左移的方法防止错误.
            currentOne.moveLeft();
        }

    }
    public void softDropAction() {
        if (canDrop()) {
            currentOne.softDrop();
        } else {
            landToWall();
            destroyLine();
            currentOne = nextOne;//把这一个方块"变成"下一个方块
            nextOne = randomOne();//再随机生成一个"下一个方块"
        }
    }
    public void handDropAction() {
        for (;;) {
            if (canDrop()) {
                currentOne.softDrop();
            } else {
                break;
            }
        }
        landToWall();
        destroyLine();
        if (!isGameOver()) {
            currentOne = nextOne;
            nextOne = randomOne();
        } else {
            game_state = GAMEOVER;
        }
    }
    public void rotateRightAction() {
        currentOne.rotateRight();
        if (outOfBounds() || coincide()) {//转过头了怎么办?这就是rotateLeft()方法的用处了
            currentOne.rotateLeft();
        }
    }
    public void destroyOne(){
        int col = currentOne.getCells()[0].getCol();
        for(int i = 1;i<20;i++){
            if (wall[i][col]!=null){
                wall[i][col]=null;
                break;
            }
        }
    }
    //int delay=10000;    //时间间隔，单位为毫秒
    ActionListener taskPerformer = e -> {
            Cell[] tmp = wall[0];
        System.arraycopy(wall, 1, wall, 0, 19);
            wall[19] = tmp;
            Random random = new Random();
        creatLine(random);
        while (isFullLine(19)){
            creatLine(random);
        }
    };
    ActionListener taskPerformer2 = e -> {

    };
    Timer timer = new Timer(10000,taskPerformer);
    public void creatLine(Random random) {
        for (int i=0;i<10;i++){
            int r = random.nextInt()%2;
            if(r == 0){
                wall[19][i]=null;
            }else {
                wall[19][i]=new Cell(19,i,J);
            }
        }
    }

    public void restart(){
        game_state = PLAYING;
        wall = new Cell[20][10];//画一个新的"墙"
        currentOne = randomOne();
        nextOne = randomOne();
        totalScore = 0;//分数置为0
        totalLine = 0;//列数置为0
        timer.restart();
    }
    public void start() {//封装了游戏逻辑
        timer.start();
        game_state = PLAYING;
        KeyListener l = new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_P) {//VK_P即表示键盘P键
                    if (game_state == PLAYING) {//状态为PLAYING才能暂停
                        game_state = PAUSE;
                        timer.stop();
                    }
                }
                if (code == KeyEvent.VK_C) {
                    if (game_state == PAUSE) {
                        game_state = PLAYING;
                        timer.restart();
                    }
                }
                if (code == KeyEvent.VK_ENTER||code == KeyEvent.VK_S) {
                    restart();
                }
                if(game_state == PLAYING){
                    switch (code) {
                        case KeyEvent.VK_DOWN://按下缓慢下降
                            if(currentOne.type==ZDType){
                                destroyOne();
                            }else {
                                softDropAction();
                            }
                            break;
                        case KeyEvent.VK_LEFT://按左左移
                            moveLeftAction();
                            break;
                        case KeyEvent.VK_RIGHT://按右右移
                            moveRightAction();
                            break;
                        case KeyEvent.VK_UP://按上变形
                            if(currentOne.type ==ZDType){//金手指外挂
                                fillOne();
                            }else {
                                rotateRightAction();
                            }
                            break;
                        case KeyEvent.VK_SPACE://按空格直接到底
                            if (currentOne.type!=ZDType){
                                handDropAction();
                            }
                            break;
                    }
                }
                repaint();//每操作一次都要重新绘制方块
            }
        };//内部类

        this.addKeyListener(l);
        this.requestFocus();

        while (true) {
/**
 * 当程序运行到此，会进入睡眠状态， 睡眠时间为800毫秒，单位为毫秒 800毫秒后，会自动执行后续代码
 */
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (game_state == PLAYING) {
                if (canDrop()&&currentOne.getHaveTime()) {
                    currentOne.softDrop();
                } else {
                    landToWall();//更新墙
                    destroyLine();

                    // 将下一个下落的四格方块赋值给正在下落的变量
                    if (!isGameOver()) {
                        currentOne = nextOne;
                        nextOne = randomOne();
                    } else {
                        timer.stop();
                        game_state = GAMEOVER;
                    }
                }
                repaint();
                /*
                 * 下落之后，要重新进行绘制，才会看到下落后的 位置 repaint方法 也是JPanel类中提供的 此方法中调用了paint方法
                 */
            }
        }
    }

    private void fillOne() {
        int col = currentOne.getCells()[0].getCol();
        int flag = 1;
        for(int i = 1;i<20;i++){
            if (wall[i][col]!=null){
                wall[i-1][col] = currentOne.getCells()[0];
                //wall[i-1][col].setImage(WALL);
                destroyLine(i-1);
                flag = 0;
                break;
            }
        }
        if (flag == 1){
            wall[19][col] = currentOne.getCells()[0];
            //wall[19][col].setImage(WALL);
            destroyLine(19);
        }
    }


    public static void main(String[] args) {


        // 1:创建一个窗口对象
        JFrame frame = new JFrame("俄罗斯方块");
        // 创建一个菜单栏
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("难度");
        JMenu menu1 = new JMenu("游戏扩展");
        JMenu menu2 = new JMenu("设置2");
        menuBar.add(menu);
        menuBar.add(menu1);
        //menuBar.add(menu2);

        ButtonGroup level = new ButtonGroup();
        JRadioButtonMenuItem level0 = new JRadioButtonMenuItem("无难度");
        JRadioButtonMenuItem level1 = new JRadioButtonMenuItem("每十秒产生一行多余");
        JRadioButtonMenuItem level2 = new JRadioButtonMenuItem("每五秒产生一行多余");
        level.add(level0);
        level.add(level1);
        level.add(level2);
        menu.add(level0);
        menu.add(level1);
        menu.add(level2);
        JCheckBoxMenuItem tetrisU = new JCheckBoxMenuItem("U型方块");
        JRadioButtonMenuItem tetris = new JRadioButtonMenuItem("关闭子弹");
        JRadioButtonMenuItem tetrisTen = new JRadioButtonMenuItem("子弹和'十'型方块");
        JRadioButtonMenuItem tetrisZD= new JRadioButtonMenuItem("子弹");
        ButtonGroup model = new ButtonGroup();
        model.add(tetris);
        model.add(tetrisZD);
        model.add(tetrisTen);
        menu1.add(tetrisU);
        menu1.addSeparator();
        menu1.add(tetrisZD);
        menu1.add(tetrisTen);
        menu1.add(tetris);

        // 创建游戏界面，即面板
        Tetris panel = new Tetris();
        // 将面板嵌入窗口
        //menuBar.setSize(35,100);
        frame.setJMenuBar(menuBar);
        frame.add(panel);

        // 2:设置为可见
        frame.setVisible(true);
        // 3:设置窗口的尺寸
        frame.setSize(535, 600);
        // 4:设置窗口居中
        frame.setLocationRelativeTo(null);
        // 5:设置窗口关闭，即程序终止
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 游戏的主要逻辑封装在start方法中
        level1.setSelected(true);
        level0.addChangeListener(e -> {
            if(level0.isSelected()){
                panel.timer.stop();
                panel.timer = new Timer(50000000,panel.taskPerformer2);
                panel.timer.start();
            }
        });
        level1.addChangeListener(e -> {
            if(level1.isSelected()){
                panel.timer.stop();
                panel.timer = new Timer(10000,panel.taskPerformer);
                panel.timer.start();
            }
        });
        level2.addChangeListener(e -> {
            if(level2.isSelected()){
                panel.timer.stop();
                panel.timer = new Timer(5000,panel.taskPerformer);
                panel.timer.start();
            }
        });
        tetrisU.setSelected(true);
        tetrisTen.setSelected(true);
        tetrisU.addChangeListener(e -> {
            if(tetrisU.isSelected()){
                panel.ModelU = true;
            }else {
                panel.ModelU = false;
            }
        });
        tetrisTen.addChangeListener(e -> {
            if(tetrisU.isSelected()){
                panel.ModelZD = true;
                panel.ModelTen = true;
            }else {
                panel.ModelTen = false;
                panel.ModelZD = false;
            }
        });
        tetrisZD.addChangeListener(e -> {
            if(tetrisZD.isSelected()){
                panel.ModelZD = true;
                panel.ModelTen = false;
                //panel.restart();
            }else {
                panel.ModelZD= false;
                //panel.ModelTen = false;
            }
        });
        tetris.addChangeListener(e -> {
            if(tetris.isSelected()){
                panel.ModelZD = false;
                panel.ModelTen = false;
                panel.restart();
            }else {
                panel.ModelZD= false;
                //panel.ModelTen = false;
            }
        });
        panel.start();
    }
}
