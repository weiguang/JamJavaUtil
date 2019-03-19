package com.okayjam.util;

import org.junit.Test;

import java.io.IOException;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/19 11:36
 **/
public class PythonUtilTest {
    String pythonFilePath = "D:\\app\\python";


    @Test
    public void testInvoke() throws IOException {
        PythonUtil.execPython(pythonFilePath, "test.py");
    }
}
