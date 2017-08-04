package newcoder;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 21:29.
 */
import java.util.ArrayList;

/**
 * 老鼠走迷宮<br>
 *
 * 功能如下:<br>
 * find():取得可從起點至終點一個路徑，不是最短路徑。<br>
 * findMore():取得多組可從起點至終點個路徑。<br>
 *
 * 以上method的參數裡maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁
 *
 * @author ray(java吉他手)
 *
 */
public class Main3 {
    class Point {
        public int x;
        public int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    public Main3() {
    }

    int vistedFlag = 4;
    public Point createPoint(int x, int y){
        return new Point(x, y);
    }

    /**
     * 走訪迷官，回傳已取得路徑的陣列(求單一解,不為最短路徑)
     * @param maze        迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param startX    起點x
     * @param startY    起點y
     * @param endX        終點x
     * @param endY        終點y
     * @return
     */
    public String find(int[][] maze, int startX, int startY, int endX, int endY) {
        return find(maze, new Point(startX, startY), new Point(endX, endY));
    }

    /**
     * 走訪迷官，回傳已取得路徑的陣列(求單一解,不為最短路徑)
     * @param maze    迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param start
     * @param end
     * @return
     */
    public String find(int[][] maze, Point start, Point end) {
        int [][] copyMaze = arraycopy(maze);
        ArrayList ary = new ArrayList();//解題路徑
        visit(copyMaze, start, end, ary);
        return getMaxePath(ary);
    }

    /**
     * 求單一解,不為最短路徑<br>
     * 走訪迷宮，已走過的點標記為1，若走到底之後，不是終點，再將走過的點標記還原為0<br>
     * @param maze    迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param pt
     * @param end
     * @return
     */
    private boolean visit(int[][] maze, Point pt, Point end, ArrayList ary) {
        if(maze[pt.x][pt.y] == 0) {
            maze[pt.x][pt.y] = vistedFlag;
            ary.add(pt);
            if(maze[end.x][end.y] != vistedFlag) {
                if(!(visit(maze, new Point(pt.x, pt.y + 1), end, ary) ||
                        visit(maze, new Point(pt.x + 1, pt.y), end, ary) ||
                        visit(maze, new Point(pt.x, pt.y - 1), end, ary) ||
                        visit(maze, new Point(pt.x - 1, pt.y), end, ary))){
                    ary.remove(ary.size() - 1);
                    maze[pt.x][pt.y] = 0;
                }
            }
        }
        return maze[end.x][end.y] == vistedFlag;
    }

    /**
     * 走訪迷官，回傳已取得路徑的陣列(求單一解,不為最短路徑)
     * @param maze        迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param startX    起點x
     * @param startY    起點y
     * @param endX        終點x
     * @param endY        終點y
     * @return
     */
    private String [] findMore(int[][] maze, int startX, int startY, int endX, int endY) {
        return findMore(maze, new Point(startX, startY), new Point(endX, endY));
    }

    /**
     * 走訪迷官，回傳已取得路徑的陣列(求單一解,不為最短路徑)
     * @param maze    迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param start
     * @param end
     * @return
     */
    public String [] findMore(int[][] maze, Point start, Point end) {
        ArrayList pathAry = new ArrayList();//存多組路徑
        int [][] copyMaze = arraycopy(maze);//將要被尋路的迷宮copy一份
        visitMore(copyMaze, start, end, new ArrayList(), pathAry);//開始走訪迷宮

        //將多組解copy轉成String 陣列
        String [] pathStrAry = new String[pathAry.size()];
        System.arraycopy(pathAry.toArray(), 0, pathStrAry, 0, pathAry.size());
        return pathStrAry;
    }

    /**
     * 求單一解,不為最短路徑<br>
     * 走訪迷宮，已走過的點標記為1，若走到底之後，不是終點，再將走過的點標記還原為0<br>
     * @param maze        迷宮本身(maze需設定指定的內容值,0:可走的點,1:已走過的點(不可設定此數值),2:牆壁)
     * @param pt
     * @param end
     * @param ary        單一解路徑
     * @param pathAry    存多組解路徑
     * @return
     */
    private void visitMore(char[][] maze, Point pt, Point end, ArrayList ary, ArrayList pathAry) {
        maze[pt.x][pt.y] = vistedFlag;
        ary.add(pt);

        if(pt.x == end.x && pt.y == end.y){
            //走到終點，將路徑轉成字串，存入pathAry
            String path = getMaxePath(ary);
            pathAry.add(path);
        }

        if(maze[pt.x][pt.y + 1] == 0) visitMore(maze, new Point(pt.x, pt.y + 1), end, ary, pathAry);
        if(maze[pt.x + 1][pt.y] == 0) visitMore(maze, new Point(pt.x + 1, pt.y), end, ary, pathAry);
        if(maze[pt.x][pt.y - 1] == 0) visitMore(maze, new Point(pt.x, pt.y - 1), end, ary, pathAry);
        if(maze[pt.x - 1][pt.y] == 0) visitMore(maze, new Point(pt.x - 1, pt.y), end, ary, pathAry);

        ary.remove(ary.size() - 1);
        maze[pt.x][pt.y] = 0;
    }

    /**
     * 複制一份迷宮
     * @param maze
     * @return
     */
    public int [][] arraycopy(int [][] maze){
        int [][] copyMaze = new int[maze.length][];

        for(int i = 0; i < maze.length; i++){
            copyMaze[i] = new int[maze[i].length];
            System.arraycopy(maze[i], 0, copyMaze[i], 0, maze[i].length);
        }
        return copyMaze;
    }

    /**
     * 取得迷宮的路徑字串，例如:
     * @param maze
     * @return
     */
    public String getMaxePath(ArrayList ary){
        StringBuffer path = new StringBuffer();
        for(int i = 0; i < ary.size(); i++){
            Point p = (Point)ary.get(i);

            if(path.length() > 0){
                path.append(",");
            }
            path.append(p.x);
            path.append(":");
            path.append(p.y);
        }
        //System.out.println("總步數==" + ary.size());
        return path.toString();
    }

    /**
     * 印出迷宮
     * @param srcMaze    原本的迷宮
     * @param path        路徑
     */
    public void print(int [][] srcMaze, String path){
        int [][] answerMaze = arraycopy(srcMaze);

        //將解題路徑放到迷宮上
        String [] pathAry = path.split("[,]");
        for(int i = 0; i < pathAry.length; i++){
            String [] xy = pathAry[i].split("[:]");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            answerMaze[x][y] = 1;
        }
        print(answerMaze);
    }

    /**
     * 印出迷宮
     * @param maze
     */
    public void print(int [][] maze){
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                int block = maze[i][j];
                switch(block) {
                    case 0: System.out.print(" "); break;
                    case 1: System.out.print("o"); break;
                    case 2: System.out.print("█");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("老鼠走迷宮尋徑");
        demo1();//求單一解
        //demo2();//求多解
    }

    //-------以下為測試的method-----------

    //求單一解
    private static void demo1(){
        Main3 mouseVisit = new Main3();
        System.out.println("求單一解-----");
        //起點
        int startX = 1;
        int startY = 1;

        //終點
        int endX = 5;
        int endY = 5;
        //迷宮
        int[][] maze = new int[][]{
                {2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 0, 2, 0, 2},
                {2, 0, 0, 2, 0, 2, 2},
                {2, 2, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2}};

        System.out.println("原本的迷宮-------beign-----");
        mouseVisit.print(maze);//原本的迷宮
        System.out.println("原本的迷宮-------end-----");

        System.out.println("找到路徑的迷宮-------beign-----");
        String path = mouseVisit.find(maze, startX, startY, endX, endY);
        System.out.println("路徑="+path);
        mouseVisit.print(maze,path);//加上走過路徑的迷宮
        System.out.println("找到路徑的迷宮-------end-----");

    }


    //求多解
    private static void demo2(){
        Main3 Main3 = new Main3();
        System.out.println("求多解-----");
        //起點
        int startX = 1;
        int startY = 1;

        //終點
        int endX = 5;
        int endY = 5;
        //迷宮
        int[][] maze = new int[][]{
                {2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 0, 0, 0, 2},
                {2, 0, 0, 2, 0, 2, 2},
                {2, 2, 0, 2, 0, 2, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2}};
        /*
        int[][] maze = new int[][]{
                {2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2}};
         */
        String [] pathAry = Main3.findMore(maze, startX, startY, endX, endY);

        for(int i = 0; i < pathAry.length; i++){
            String path = pathAry[i];
            System.out.println("路徑["+i+"]==" + path);
            Main3.print(maze,path);
        }
    }
}
