package com.okayjam.file;

import com.alibaba.fastjson.JSONObject;
import com.okayjam.Thread.ThreadPoolUtil;
import com.okayjam.util.FileUtil;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Chen Weiguang <chen2621978@gmail.com>
 * @date 2019/07/08 15:57
 **/
public class CreateFile {

    static String basePath = "d:/file/opsdata";
    static int maxDeep = 3;
    static int baseDirNum = 2  ;
    public static void main(String[] args) {
        CreateFile createFile = new CreateFile();
        ThreadPoolExecutor pool = ThreadPoolUtil.getThreadPool("createFile", 10);
        for (int i = 1; i <= baseDirNum; i++) {
            String  prefix = i+"";//String.format("%02d", i);
            pool.submit(()->createFile.create(basePath+"/"+prefix, prefix,2, baseDirNum * 2));
        }
        ThreadPoolUtil.poolStopAndWait(pool);

    }

    public void create(String startPath, String  prefix, int deep, int dirNum) {
        if (!new File(startPath).exists()) {
            FileUtil.mkdirs(startPath);
        }
        if (deep <= maxDeep) {
            for (int i = 1; i <= dirNum; i++) {
                String newPrefix =  i+"";//prefix + String.format("%02d", i);
                String newPath = startPath+"/"+ newPrefix;
                //FileUtil.mkdirs(newPath);
                create(newPath, newPrefix, deep + 1, baseDirNum * (deep + 1));
            }
        } else {
            System.out.println(Thread.currentThread().getName());
            String fileName =      "1.json";
            JSONObject fileContent = new JSONObject();
            fileContent.put("name", fileName);
            fileContent.put("route", startPath);
            FileUtil.createNewFile(startPath + "/" + fileName, fileContent.toString());
        }

    }
}
