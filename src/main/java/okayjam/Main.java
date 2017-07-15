package okayjam;

import test.Test;
import util.EncryptionAndDecryption;

/**
 * Created by jam on 17-6-1.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(EncryptionAndDecryption.getSHA256("1234567890"));
      //  Test.testString();
    }

}
