package com.okayjam.util;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/11/01 11:45
 **/
class ExcelUtilTest {

    @Test
    void createExcel() throws FileNotFoundException {
        String headers[] =  {"", "流向类型（直发，目的地转运，始发地转运，始发+目的转运，扫尾线路）", "始发场地代码", "目的场地代码", "0D流向", "OD关系（经济圈、邻省、跨省）", "流向货量", "车型", "车次", "来源步骤0：正常发运；1：扫尾；2：正常发运和扫尾", "版本id", "上传版本号uuid", "距离", "成本", "配载城市", "", "来源步骤，整个计算流程中的步骤"};
        String ds_titles[] = {"id", "flow_type", "src_hub_code", "des_hub_code", "flow", "flow_relation", "flow_qty", "vehicle_type", "vehicle_num", "step", "process_id", "version_uuid", "distance", "cost", "cargo_city", "full_payload_total", "from_step"};
        List list = new ArrayList<>();
        SXSSFWorkbook wb = ExcelUtil.createExcel("test", headers,ds_titles, list);
        File file = new File("test.xlsx");
        ExcelUtil.outputExcel(wb, new FileOutputStream(file));
    }
}