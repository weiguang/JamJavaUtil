package main.com.okayjam.baseTest;

import com.test.Animal;
import com.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/09/12 10:57
 **/
public class ExcelTest {
    @Test
    public void createExcelTest1() throws FileNotFoundException {
        //web对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建表头等
        HSSFSheet sheet = wb.createSheet("test1");
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);

        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("嗯哼");
        cell.setCellValue("我是写入的值");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        FileOutputStream output = new FileOutputStream("E:\\JAKARTA.xls");
        ExcelUtil.outputExcel(wb, output);
    }

    @Test
    public void createExcelTest2() {
        String[] headers = {"h1","h2"};
        String[] ds_titles = {"t1","t2"};
        List<Map<String, Object>> data = new ArrayList<>();
        Map map= new HashMap<>();
        map.put("t1", null);
        map.put("t2", true);
        data.add(map);
        map= new HashMap<>();
        map.put("t1", 789);
        map.put("t2", 66.8D);
        data.add(map);
        SXSSFWorkbook wb = ExcelUtil.createExcel("tt", headers, ds_titles, data);
        try {
            FileOutputStream output = new FileOutputStream("E:\\JAKARTA.xlsx");
            ExcelUtil.outputExcel(wb, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createExcelTest3() {
        String[] headers = {"h1","h2"};
        String[] ds_titles = {"name1","passwd"};
        List data = new ArrayList<>();
        data.add(new Animal("j1") {{setPasswd("123");}});
        data.add(new Animal("j2") {{setPasswd("456");}});
        SXSSFWorkbook wb = ExcelUtil.createExcel("tt", headers, ds_titles, data);
        try {
            FileOutputStream output = new FileOutputStream("E:\\JAKARTA.xlsx");
            ExcelUtil.outputExcel(wb, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public  void test() {
        System.out.println(null instanceof  Object);
        Object o = null;
        //System.out.println(o.toString());
        System.out.println(t());
    }

    public int t() {
        int num = 5;
        try {
            num = num/0;
        }catch (Exception e) {
            num = 10;
        }finally {
            num = 15;
        }
        return num;
    }

}
