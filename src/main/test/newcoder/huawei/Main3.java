package newcoder.huawei;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/9 19:35.
 */
public class Main3 {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        //System.out.println("Hello world!");
        //String src = sn.nextLine().toLowerCase();
        //char c = sn.next().toLowerCase().charAt(0);
        //getCharCount(src, c);

        //
//        while (sn.hasNextInt()) {
//            xiaoMingRandom();
//        }

        //
//        splitString(sn.nextLine());
//        splitString(sn.nextLine());

        //
//        while (sn.hasNext()) {
//            changeHexToDe(sn.next());
//        }
        //System.out.println(isPrime(35));
        //System.out.println(new Main3().getResult(sn.nextLong()));
//        getFactor(sn.nextLong());

        //
//        System.out.println(getApproximateValue(sn.nextDouble()));

//        mergeMap();

//        System.out.println(getNewOrderNumber(sn.nextLong()));

 //       System.out.println(getCharNumber(sn.next()));

 //       System.out.println(reverseInteger(sn.nextLong()));

//        System.out.println(new StringBuilder(sn.next()).reverse().toString());

//        System.out.println(reverse(sn.nextLine()));

//        StringSort();

        System.out.println(calOne(sn.nextLong()));
    }

    public static int calOne(long n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n & (n-1);
        }
        return sum;
    }

    public static void StringSort() {
        int n = sn.nextInt();
        List list = new ArrayList();
        for (int i = 0; i < n; i++) {
            list.add(sn.next());
        }
        Collections.sort(list);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static String reverse(String sentence){
        String[] st = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = st.length - 1; i >= 0 ; i--) {
            sb.append(st[i]+ " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String reverseInteger (long n) {
        return new StringBuilder(String.valueOf(n)).reverse().toString();
    }

    public static void getCharCount(String src, char c) {
        int sum = 0;
        for (char cc : src.toCharArray()) {
            if(cc == c) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    public static void xiaoMingRandom() {
        TreeSet<Integer> set = new TreeSet<Integer>();
        //多组数据输入
        int n = sn.nextInt();
        for (int i = 0; i < n; i++) {
            set.add(sn.nextInt());
        }

        for (Object o : set) {
            System.out.println(o);
        }
    }

    public static void splitString(String src) {
        String tsrc = src;
        while (tsrc.length() % 8 != 0) {
            tsrc +="0";
        }
        int count = tsrc.length() / 8;
        for (int i = 0; i < count; i++) {
            System.out.println(tsrc.substring(i*8, i*8 + 8));
        }
    }

    public static void changeHexToDe(String src) {
        int a = 0;
        int sum = 0;
        for (int i = src.length() - 1; i > 1; i--) {
            char c = src.charAt(i);
            if(c >= 'A' &&  c <= 'F' ) sum += (int)Math.pow(16,a) * (c - 'A' + 10 );
            else if (c >= '0' &&  c <= '9' ) sum += 16 * a * (c - '0' );
            a++;
        }
        System.out.println(sum);
    }

    /**
     * 分解因子
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/8/10 11:08
     * @param ulDataInput
     * @return
     */
    public String getResult(long ulDataInput){
        long t = ulDataInput;
        long i = 2;
        StringBuilder sb = new StringBuilder();
        while(ulDataInput != 1 && i <= t) {
            while(ulDataInput % i == 0) {
                ulDataInput /= i;
                sb.append(i + " ");
            }
            i++;
        }
        return sb.toString();
    }


    public static void getFactor(long a) {
        long i = 1;
        while(a != 1) {
            i = getNextprime(i);
            while(a%i == 0) {
                a /=i;
                System.out.print(i + " ");
            }
        }

    }

    public static long getNextprime(long n) {
        for (long i = n + 1; i < Long.MAX_VALUE; i++) {
            if(isPrime(i)) return i;
        }
        return 0;
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }

    public static long getApproximateValue(double n) {
        return Math.round(n);
    }

    public static void mergeMap(){
        int n = sn.nextInt();
        TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
        for (int i = 0; i < n; i++) {
            int k = sn.nextInt();
            int v = sn.nextInt();
            if (map.containsKey(k) ){
                map.put(k, map.get(k) + v);
            }else {
                map.put(k, v);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static long getNewOrderNumber(long n) {
        String s = String.valueOf(n);
        LinkedHashSet set = new LinkedHashSet();
        for (char c : new StringBuilder(s).reverse().toString().toCharArray()) {
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : set) {
            sb.append(o);
        }
        return Integer.valueOf(sb.toString());
    }

    public static int getCharNumber(String src) {
        int sum = 0;
        HashSet set = new HashSet(128);
        for (char c : src.toCharArray()) {
            if( c >= 0 && c <= 127) set.add(c);
        }
        return  set.size();
    }
}
