package util;

import java.io.*;

import org.apache.log4j.Logger;

/**
 * Created by jam on 17-6-1.
 */
public class FileUtil {

    private static final Logger log = Logger.getLogger(FileUtil.class);

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 19:15
     * @param  dir: new file path
     * @return
     * @description 新建文件夹
     */
    final public static void mkdirs( final String dir) {
        try {
            File dirPath = new File(dir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
        } catch (Exception e) {
            log.error("创建目录操作出错: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:11
     * @param fileName: String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @return File: new File
     * @description 新建文件
     */
    public final static File createNewFile(final String fileName) {
        try {
            String fileNameTemp = fileName;
            File filePath = new File(fileNameTemp);
            if (!filePath.exists()) {
                filePath.createNewFile();
            }
            return filePath;
        } catch (Exception e) {
            log.error("新建文件操作出错: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:01
     * @param fileName: String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content: String 文件内容
     * @return
     * @description 新建文件
     */
    public final static void createNewFile(String fileName, String content) {
        try {
            File filePath = createNewFile(fileName);
            if (filePath == null) {
               return;
            }
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            String strContent = content;
            pw.println(strContent);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            log.error("新建文件时保存内容出错: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:18
     * @param  fileName 包含路径的文件名
     * @return
     * @description  删除文件
     */
    public static void delFile(String fileName) {
        try {
            String filePath = fileName;
            java.io.File delFile = new java.io.File(filePath);
            delFile.delete();
        } catch (Exception e) {
            log.error("删除文件操作出错: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:21
     * @param  folderPath  文件夹路径
     * @return  
     * @description  删除文件夹
     */
    public static void delFolder(String folderPath) {
        try {
            // 删除文件夹里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            java.io.File myFilePath = new java.io.File(filePath);
            // 删除空文件夹
            myFilePath.delete();
        } catch (Exception e) {
            log.error("删除文件夹操作出错"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:21
     * @param  fileName 包含路径的文件名
     * @return
     * @description   删除文件或文件夹
     */
    public static void delFileOrdelFolder(String fileName) {
        try {
            String filePath = fileName;
            java.io.File delFile = new java.io.File(filePath);
            if(delFile.exists()){
                if(delFile.isFile()){
                    delFile(fileName);
                }else{
                    delFolder(fileName);
                }
            }else{
                log.error("文件不存在！ ");
            }
            delFile.delete();
        } catch (Exception e) {
            log.error("删除文件操作出错: "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:22
     * @param  path 文件夹路径
     * @return
     * @description  删除文件夹里面的所有文件
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] childFiles = file.list();
        File temp = null;
        for (int i = 0; i < childFiles.length; i++) {
            //File.separator与系统有关的默认名称分隔符
            //在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'。
            if (path.endsWith(File.separator)) {
                temp = new File(path + childFiles[i]);
            } else {
                temp = new File(path + File.separator + childFiles[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + childFiles[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + childFiles[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:23
     * @param srcFile
     *            包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest
     *            目标文件目录；若文件目录不存在则自动创建  如：E:/phsftp/dest
     * @return 新的文件地址
     * @throws IOException
     * @description  复制单个文件
     */
    public static File copyFile(String srcFile, String dirDest) {
        File newFile = null;
        try {
            FileInputStream in = new FileInputStream(srcFile);
            mkdirs(dirDest);
            newFile = new File(dirDest,new File(srcFile).getName());
            FileOutputStream out = new FileOutputStream(newFile);
            int len;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            log.error("复制文件操作出错:"+e.getMessage());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:25
     * @param oldPath
     *            String 源文件夹路径 如：E:/phsftp/src
     * @param newPath
     *            String 目标文件夹路径 如：E:/phsftp/dest
     * @return
     * @description  复制文件夹
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在 则新建文件夹
            mkdirs(newPath);
            File file = new File(oldPath);
            String[] files = file.list();
            File temp = null;
            for (int i = 0; i < files.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + files[i]);
                } else {
                    temp = new File(oldPath + File.separator + files[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/" + (temp.getName()).toString());
                    byte[] buffer = new byte[1024 * 2];
                    int len;
                    while ((len = input.read(buffer)) != -1) {
                        output.write(buffer, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
                }
            }
        } catch (Exception e) {
            log.error("复制文件夹操作出错:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:27
     * @param oldPath 包含路径的文件名 如：E:/phsftp/src/ljq.txt
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @return 新的文件对象
     * @description  移动文件到指定目录,返回新的文件对象
     */
    public static File moveFile(String oldPath, String newPath) {
        File newFile = copyFile(oldPath, newPath);
        delFile(oldPath);
        return newFile;
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:28
     * @param oldPath 源文件目录  如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @return
     * @description 移动文件到指定目录，不会删除文件夹
     */
    public static void moveAndEmptyFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delAllFile(oldPath);
    }

    /**
     * @Author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:28
     * @param oldPath 源文件目录  如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @description  移动文件到指定目录，会删除文件夹
     */
    public static void moveAndDelFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }
}
