package okayjam;

import util.EncryptionUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jam on 17-6-1.
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //System.out.println(EncryptionUtil.getSHA256("1234567890"));
      //  Test.testString();
        String[] array = new String[] {"1", "2", "3","4"};
        listAll(Arrays.asList(array),"");
    }

    /**
     *
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/17 21:25
     * @param
     * @return void
     */
    public static void listAll(List candidate, String prefix) {
        System.out.println(prefix);

        for (int i = 0; i < candidate.size(); i++) {
            List temp = new LinkedList(candidate);
            Object s = temp.remove(i);
            System.out.println("prefix :" + prefix + " temp.remove(i):"+s);
            listAll(temp, prefix + s);
        }
    }
}
