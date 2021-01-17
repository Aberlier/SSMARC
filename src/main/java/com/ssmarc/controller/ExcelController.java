package com.ssmarc.controller;

import com.ssmarc.entity.DailyExcelDetailPageDTO;
import com.ssmarc.service.ExcelService;
import com.ssmarc.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author ：Angular
 * @ProjectName: SSMARC
 * @Package: com.ssmarc.controller
 * @ClassName: ExcelController
 * @date ：Created in 2021/1/17 9:34
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("ssmarc")
public class ExcelController {

    @Autowired
    ExcelService excelService;
    @RequestMapping(value = "/exportEarlyWarningToExcel", method = RequestMethod.GET)
    public void exportEarlyWarningToDCExcel(@RequestParam(value = "leixing",required = false) String leixing, HttpServletResponse response) {
        try {
   /*         //当前页数
            int pageNum = 1;
            //每页显示条数
            int pageSize = 10000;
            String orgIdStr = "";
            if (orgId.contains("--")) {
                orgIdStr = orgId.substring(0, 6);
            } else {
                orgIdStr = orgId;
            }

*/

            List<Map<String, Object>> mapList = excelService.getHourlyAvgContinEmissionMonit();
            if (mapList != null && mapList.size() > 0) {
                //excel文件名
                String[] title = new String[]{"序号", "监测点位", "含氧量","ph值","颗粒物平均值", "SO2平均值", "NOV平均值", "备注"};
                String name = null;
                switch (leixing) {
                    case "3":
                        name = "每周";
                        break;
                    case "2":
                        name = "每日";
                        break;
                    case "1":
                        name = "每时";
                        break;
                    default:
                        break;
                }
                String fileName = URLEncoder.encode( name + ".xls", "UTF-8");
                //sheet名
                String sheetName = name;
               /* JSONObject object = (JSONObject) json.get("data");
                JSONArray earlyWarningsList = (JSONArray) object.get("list");*/
                String[][] content = new String[mapList.size()][];

                for (int i = 0; i < mapList.size(); i++) {
                    content[i] = new String[title.length];
                    Map<String,Object> pojo = mapList.get(i);
                    Object pojoToObject = pojo.get("dailyExcelDetailPage");
                    String leixingName = null;
                    content[i][0] = (i + 1) + "";
                    //类型
                    if (pojo.get("monitoringPoints") != null) {
                      /*  switch (pojo.get("LEIXING").toString()) {
                            case "3":
                                leixingName = "接处警";
                                break;
                            case "2":
                                leixingName = "行政案件";
                                break;
                            case "1":
                                leixingName = "刑事案件";
                                break;
                            default:
                                break;
                        }*/

                        content[i][1] = pojo.get("monitoringPoints").toString();
                    }
                    //编号
                    if (pojo.get("oxygenDemand") != null) {
                        content[i][2] = (String) pojo.get("oxygenDemand");
                    }
                    //案件名称
                    if (pojo.get("phValue") != null) {
                        content[i][3] = (String) pojo.get("phValue");
                    }
                    //办案单位
                    if (pojo.get("dustAverage") != null) {
                        content[i][4] = (String) pojo.get("dustAverage");
                    }
                    //办案人
                    if (pojo.get("soTwoAverage") != null) {
                        content[i][5] = (String) pojo.get("soTwoAverage");
                    }
                    //推送时间
                    if (pojo.get("noxAverage") != null) {
                        content[i][6] = (String) pojo.get("noxAverage");
                    }
                    //执法问题
                    if (pojo.get("remarks") != null) {
                        content[i][7] = (String) pojo.get("remarks");
                    }
                }
                HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
                ExcelUtil.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
//            logger.error(e.getMessage(), e);
        }
    }
}
