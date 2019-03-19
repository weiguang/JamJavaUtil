package com.okayjam.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/19 11:42
 **/
public class PythonUtil {
    public static void execPython(String pythonFilePath, String pyFile) throws IOException {
        Process pr = Runtime.getRuntime().exec(
                "python "+pythonFilePath + "/" +pyFile +" "+ pythonFilePath);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        BufferedReader err = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        String errLine;
        while ((errLine = err.readLine()) != null) {
            System.err.println(errLine);
        }
        err.close();
        in.close();
    }
}
