package com.okayjam.file;

import java.io.File;
import java.util.Arrays;

public class RenameFile {
    public static void main(String[] args) {
        t();
    }

    public static void t() {
        String path = "D:\\迅雷下载";
//        String path = "C:\\Users\\chen2\\Desktop\\1";
        File file = new File(path);
        Arrays.stream(file.listFiles()).filter(file1 -> !file1.isDirectory()).forEach(
                file1 ->  {
                    renameReplace(file1, "BD影视分享bd-film.cc", "");
                }
        );
    }

    public static  void renameReplace(File file , String from, String to) {
        String name = file.getPath();
        name = name.replaceAll(from, to);
        String newPath = name;//file.getPath()+"/"+name;
        file.renameTo(new File(newPath));
    }
}
