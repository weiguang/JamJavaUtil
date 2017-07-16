package util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Weiguang Chen(chen2621978@gmail.com) on 2017/7/16 16:16.
 */
public class TextUtil {


    /**
     * 读取数据
     *
     * @param inStream input stream
     * @param charsetName charsetName (utf-8)
     * @return content
     * @throws  IOException
     */
    public static String readData(InputStream inStream, String charsetName) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len ;
        while( (len = inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return new String(data, charsetName);
    }

    /**
     * 一行一行读取文件，适合字符读取，若读取中文字符时会出现乱码
     *
     * @param path file path
     * @return set
     * @throws IOException
     */
    public static Set<String> readFile(String path) throws IOException {
        Set<String> datas=new HashSet<String>();
        FileReader fr=new FileReader(path);
        BufferedReader br=new BufferedReader(fr);
        String line;
        while ((line=br.readLine())!=null) {
            datas.add(line);
        }
        br.close();
        fr.close();
        return datas;
    }


    /**
     * 按照指定编码一行一行读取文件
     *
     * @param fileName file path
     * @param charsetName charset eg. UTF-8
     * @return set
     * @throws IOException
     */
    public static void readFile(String fileName, String charsetName) throws IOException {
        List<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        if (charsetName == null) { charsetName = "UTF-8"; }
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            InputStreamReader is = new InputStreamReader(new FileInputStream(fileName), charsetName);
            reader = new BufferedReader(is);
            String lineString;
            // 一次读入一行，直到读入null为文件结束
            while ((lineString = reader.readLine()) != null) {
                list.add(lineString);
                System.out.println(lineString);
            }
            //进行操作
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName file name
     * @return set
     */
    public static List<String> readFileByLines(String fileName) {
        File file = new File(fileName);
        List<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                list.add(tempString);
            }
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

}
