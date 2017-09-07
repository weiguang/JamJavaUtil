package other.baicikan;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/1 9:56.
 */
public class Main {
    static int sum = 0;
    static List result = new ArrayList();
    public static void main (String[] args) {
        Scanner sn = new Scanner(System.in);
        int n = sn.nextInt();
        int k = sn.nextInt();
        int m = sn.nextInt();
        int[] data = new int[m];
        for (int i = 0; i < m; i++) {
            data[i] = sn.nextInt();
        }
        Arrays.sort(data);
        List list  = new ArrayList();
        for (int i = 0; i < m; i++) {
            list.clear();
            if (n < data[i]) break;
            list.add(i);
            order(data, k, n - data[i], list);
        }

        System.out.println(sum);
    }

    static boolean order (int[] data, int k, int remain, List list) {
        int m = data.length;
        if (list.size() == k) return true;
        for (int i = 0; i < m; i++) {
            if (remain < data[i]) return false;
            if (list.contains(i)) continue;
            list.add(i);
            if(order(data, k, remain - data[i], list) ) {
                List l = new ArrayList(list);
                Collections.sort(l);
                if ( !result.contains(l.toString())) {
                    result.add(l.toString());
                    sum++;
                    //System.out.println(list.toString());
                }
            }
            list.remove(list.size() - 1 );
        }
        return false;
    }

}
