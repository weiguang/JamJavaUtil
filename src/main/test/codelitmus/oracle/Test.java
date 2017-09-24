package codelitmus.oracle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/21 17:06.
 */
public class Test {
    static Scanner sn = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws Exception {


//        String line = br.readLine();
//        int N = Integer.parseInt(line);
//        for (int i = 0; i < N; i++) {
//            System.out.println("hello world");
//        }
//        System.out.println();

        //resolve(sn.nextLine());
        //wordPlay();
      //  cp();
        int  num =0;
        num = num+++num+++num++;
        System.out.println(num);
        double i = 0.0/0.0;
        System.out.println(i==i);
        double i2=1.0/0.0;
        System.out.println(i2 == i2);
        Double di = 0.0/0.0;
        System.out.println(di ==di);
    }

    public static void resolve(String expr) {
         //Stack<String> st = new Stack();
        LinkedList<String> st = new LinkedList();
        for (int i = 0; i < expr.length(); i++) {
            switch (expr.charAt(i)){
                case '+':
                case '-':
                case '*':
                case '/':
                    String s1 = (String)st.pop();
                    s1  = "(" + (String)st.pop() + expr.charAt(i) + s1 + ")";
                    st.push(s1);
                    break;
                default:
                    st.push(expr.charAt(i) + "");
            }
        }
        if(!st.isEmpty())
            System.out.println(st.peek());
    }

    public static void wordPlay() throws Exception{
        int D =  Integer.parseInt(br.readLine()); //sn.nextInt();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < D; i++) {
            list.add(br.readLine());
        }
        int r = Integer.parseInt(br.readLine().split(" ")[0]);
        //int c = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] grid = new String[r];
            char[] st = new char['Z'];
            for (int i = 0; i < r; i++) {
                grid[i] = br.readLine();
                for (int j = 0; j < grid[i].length(); j++) {
                    st[grid[i].charAt(j)]++;
                }
            }

            int count = 0;
            for (int i = 0; i < D; i++) {
                String s = list.get(i);
                int j = 0;
                for (j = 0; j < s.length(); j++) {
                    if(st[s.charAt(j)]  < 1 ) break;
                }
                if(j == s.length()) {
                    count++;
                }
            }
            if( (count & 1) == 1 ) System.out.println("Ramesh");
            else System.out.println("Suresh");
            if(T>1) br.readLine();
        }
    }

    static void cp() throws Exception {
        String line = br.readLine();
        int n = Integer.valueOf(line.split(" ")[0]);
        int m = Integer.valueOf(line.split(" ")[1]);
        int[][] a = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            line = br.readLine();
            int x = Integer.valueOf(line.split(" ")[0]) - 1;
            int y = Integer.valueOf(line.split(" ")[1]) - 1;
            a[x][y] = a[y][x] = 1;
        }
        int[][] b = new int[n+1][n+1];
        int[][] c = new int[n+1][n+1];
        matrixMultiplication(a,a,b);
        matrixMultiplication(a,b,c);
        int i = 0;
        for (i = 0; i < n; i++) {
            if(c[i][i] != 0) break;
        }
        if(i == n) System.out.println("yes");
        else System.out.println("no");
    }

    public static void matrixMultiplication(int[][] matrix1, int[][] matrix2,int[][] matrix3) {
        for(int i=0;i<matrix1.length;i++) {
                         for(int j=0;j<matrix2[0].length;j++) {
                                 int sum = 0;
                                for(int k=0;k<matrix2.length;k++) {
                                        sum += matrix1[i][k] * matrix2[k][j];
                                     }
                                //System.out.print("\t" + sum);
                                matrix3[i][j] =sum;
                            }
                        System.out.println();
                   }
    }

}
