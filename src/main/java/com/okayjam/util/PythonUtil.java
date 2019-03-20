package com.okayjam.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/19 11:42
 **/
public class PythonUtil {
    private static final Logger logger =  LoggerFactory.getLogger(PythonUtil.class);

    /**
     * 执行本地python脚本
     * @param pythonPath
     * 	python脚本路径
     * @param args
     * 	python脚本参数列表
     */
    public static void execute(String pythonPath, String...args) {
        if (pythonPath == null || "".equals(pythonPath.trim())) {
            throw new IllegalArgumentException("[pythonPath] can not be blank");
        }

        // 参数准备
        String[] cmdArgs = new String[2 + args.length];
        cmdArgs[0] = "python";
        cmdArgs[1] = pythonPath;
        System.arraycopy(args, 0, cmdArgs, 2, args.length);

        BufferedReader info = null;
        BufferedReader error =null;
        try {
            logger.info("[python]-python {} ... begin", pythonPath);
            Process pr = Runtime.getRuntime().exec(cmdArgs);
            info = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = info.readLine()) != null) {
                logger.info(line);
            }

            error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String errLine;
            while ((errLine = error.readLine()) != null) {
                logger.error(errLine);
            }

            logger.info("[python]-python {} ... end", pythonPath);
            // 等待直到python执行结束
            pr.waitFor();
        } catch (Exception e) {
            logger.error("[python]-error occurs in executing python[" + pythonPath + "] ", e);
        } finally {
            closeQuietly(info);
            closeQuietly(error);
        }
    }

    public static void execute(String pythonFilePath, String pyFile)  {
        BufferedReader in = null;
        BufferedReader err =null;
        try{
            Process pr = Runtime.getRuntime().exec(
                    "python "+pythonFilePath + "/" +pyFile +" "+ pythonFilePath);
             in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
             err = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String errLine;
            while ((errLine = err.readLine()) != null) {
                System.err.println(errLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(in);
            closeQuietly(err);
        }

    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) { logger.error("close error:", e);
            }
        }
    }
}
