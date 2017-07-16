package util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * Created by Jam on 17-6-1.
 */
public class FileUtil {

    private static final Logger log = Logger.getLogger(FileUtil.class);

    /**
     * 新建文件夹
     *
     * @param dir: new file path
     * @return boolean
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 19:15
     */
    public static boolean mkdirs(final String dir) {
        try {
            File dirPath = new File(dir);
            return dirPath.mkdirs();
        } catch (Exception e) {
            log.error("创建目录操作出错: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 新建文件
     *
     * @param fileName String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @return File new File
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:11
     */
    public static File createNewFile(final String fileName) {
        try {
            File filePath = new File(fileName);
            if (!filePath.exists()) {
                filePath.createNewFile();
            }
            return filePath;
        } catch (Exception e) {
            log.error("新建文件操作出错: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新建文件
     *
     * @param fileName: String 包含路径的文件名 如:E:\phsftp\src\123.txt
     * @param content:  String 文件内容
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:01
     */
    public static void createNewFile(String fileName, String content) {
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
            log.error("新建文件时保存内容出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 包含路径的文件名
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:18
     */
    public static void delFile(String fileName) {
        try {
            String filePath = fileName;
            java.io.File delFile = new java.io.File(filePath);
            delFile.delete();
        } catch (Exception e) {
            log.error("删除文件操作出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹路径
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:21
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
            log.error("删除文件夹操作出错" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param fileName 包含路径的文件名
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:21
     */
    public static void delFileOrdelFolder(String fileName) {
        try {
            String filePath = fileName;
            java.io.File delFile = new java.io.File(filePath);
            if (delFile.exists()) {
                if (delFile.isFile()) {
                    delFile(fileName);
                } else {
                    delFolder(fileName);
                }
            } else {
                log.error("文件不存在！ ");
            }
            delFile.delete();
        } catch (Exception e) {
            log.error("删除文件操作出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path 文件夹路径
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:22
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
     * 复制单个文件
     *
     * @param srcFile 包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest 目标文件目录；若文件目录不存在则自动创建  如：E:/phsftp/dest
     * @return 新的文件地址
     * @throws IOException
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:23
     */
    public static File copyFile(String srcFile, String dirDest) {
        File newFile = null;
        try {
            FileInputStream in = new FileInputStream(srcFile);
            mkdirs(dirDest);
            newFile = new File(dirDest, new File(srcFile).getName());
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
            log.error("复制文件操作出错:" + e.getMessage());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 复制文件夹
     *
     * @param oldPath String 源文件夹路径 如：E:/phsftp/src
     * @param newPath String 目标文件夹路径 如：E:/phsftp/dest
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:25
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
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
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
            log.error("复制文件夹操作出错:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 移动文件到指定目录,返回新的文件对象
     *
     * @param oldPath 包含路径的文件名 如：E:/phsftp/src/ljq.txt
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @return 新的文件对象
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:27
     */
    public static File moveFile(String oldPath, String newPath) {
        File newFile = copyFile(oldPath, newPath);
        delFile(oldPath);
        return newFile;
    }

    /**
     * 移动文件到指定目录，不会删除文件夹
     *
     * @param oldPath 源文件目录  如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:28
     */
    public static void moveAndEmptyFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delAllFile(oldPath);
    }

    /**
     * 移动文件到指定目录，会删除文件夹
     *
     * @param oldPath 源文件目录  如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/15 21:28
     */
    public static void moveAndDelFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    /**
     * 压缩文件
     *
     * @param srcFile 要压缩的文件，不能是目录
     * @param destDir 目标目录
     * @throws Exception
     * @since 1.6+
     */
    public static void compressedFileZip(File srcFile, String destDir) throws Exception {
        String tempFileName;
        byte[] buf = new byte[1024 * 2];
        int len;
        //获取要压缩的文件
        if (srcFile == null) { return; }
        File file = srcFile;
        if (destDir == null) {
            destDir = file.getPath();
        }
        if (file != null && file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            if (destDir.endsWith(File.separator)) {
                tempFileName = destDir + file.getName() + ".zip";
            } else {
                tempFileName = destDir + "/" + file.getName() + ".zip";
            }
            FileOutputStream fos = new FileOutputStream(tempFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包

            ZipEntry ze = new ZipEntry(file.getName());// 压缩包文件名
            zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处

            while ((len = bis.read(buf)) != -1) {
                zos.write(buf, 0, len);
                zos.flush();
            }
            bis.close();
            zos.close();
        }
    }

    /**
     * 压缩文件，会把srcDir文件或者该目录下的文件分别打包
     * 例如srcDir下面有1.txt和2.txt的文件，则会在destDir目录下创建1.txt.zip和2.txt.zip
     *
     * @param srcDir  压缩前存放的目录，可以是文件或者目录
     * @param destDir 压缩后存放的目录,如果为空则默认是srcDir的目录
     * @throws Exception
     * @since 1.6+
     */
    public static void compressedFileZip(String srcDir, String destDir) throws Exception {
        if (srcDir == null) return;
        File srcfile = new File(srcDir);
        //获取要压缩的文件
        File[] files;
        if (srcfile.isDirectory()) { //srcDir is a Directory
            if (destDir == null) {
                destDir = srcfile.getPath();
            }
            files = srcfile.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    compressedFileZip(file, destDir);
                }
            }
        }else { //srcDir is a file
            if (destDir == null) {
                destDir = srcfile.getParent();
            }
            compressedFileZip(srcfile, destDir);
        }

    }

    /**
     *  将源文件/文件夹生成指定格式的压缩文件,格式zip
     * @param resourcesPath 源文件/文件夹
     * @param targetPath  目的压缩文件保存路径
     * @return void
     * @throws Exception
     */
    public static void compressedFile(String resourcesPath,String targetPath) throws Exception{
        File resourcesFile = new File(resourcesPath);     //源文件
        File targetFile = new File(targetPath);           //目的
        //如果目的路径不存在，则新建
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        String targetName = resourcesFile.getName()+".zip";   //目的压缩文件名
        FileOutputStream outputStream = new FileOutputStream(targetPath+"\\"+targetName);
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));

        createCompressedFile(out, resourcesFile, "");

        out.close();
    }

    /**
     *  生成压缩文件。
     *  如果是文件夹，则使用递归，进行文件遍历、压缩；如果是文件，直接压缩
     * @param out  输出流
     * @param file  目标文件
     * @return void
     * @throws Exception
     */
    public static void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{
        //如果当前的是文件夹，则进行进一步处理
        if(file.isDirectory()){
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir+"/"));

            dir = dir.length() == 0 ? "" : dir +"/";

            //循环将文件夹中的文件打包
            for(int i = 0 ; i < files.length ; i++){
                createCompressedFile(out, files[i], dir + files[i].getName());         //递归处理
            }
        }
        else{   //当前的是文件，打包处理
            //文件输入流
            FileInputStream fis = new FileInputStream(file);
            if (dir == null || dir.trim().length() == 0) dir += file.getName();
            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j =  0;
            byte[] buffer = new byte[1024];
            while((j = fis.read(buffer)) > 0){
                out.write(buffer,0,j);
            }
            //关闭输入流
            fis.close();
        }
    }

}
