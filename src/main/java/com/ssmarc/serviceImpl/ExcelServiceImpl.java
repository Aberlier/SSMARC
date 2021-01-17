package com.ssmarc.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.ssmarc.entity.DailyExcelDetailPageDTO;
import com.ssmarc.service.ExcelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Angular
 * @ProjectName: SSMARC
 * @Package: com.ssmarc.serviceImpl
 * @ClassName: ExcelServiceImpl
 * @date ：Created in 2021/1/17 9:57
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class ExcelServiceImpl implements ExcelService {


    public  List<Map<String, Object>> getHourlyAvgContinEmissionMonit() {
        Map<String,Object> map = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        DailyExcelDetailPageDTO dailyExcelDetailPageDTO = new DailyExcelDetailPageDTO();
        dailyExcelDetailPageDTO.setDustAverage("16.145");
        dailyExcelDetailPageDTO.setMonitoringPoints("1#焙烧炉烟气排放口");
        dailyExcelDetailPageDTO.setNoxAverage("293.57");
        dailyExcelDetailPageDTO.setOxygenDemand("12.45");
        dailyExcelDetailPageDTO.setPhValue("6");
        dailyExcelDetailPageDTO.setRemarks("颗粒物超标时间点：1-3，10-12…" +
                "SO2超标时间点：无" +
                "NOX超标时间点：1-3，10-12…");
        dailyExcelDetailPageDTO.setSoTwoAverage("0.665");

        DailyExcelDetailPageDTO dailyExcelDetailPage = new DailyExcelDetailPageDTO();
        dailyExcelDetailPage.setDustAverage("15.989");
        dailyExcelDetailPage.setMonitoringPoints("2#焙烧炉烟气排放口");
        dailyExcelDetailPage.setNoxAverage("293.57");
        dailyExcelDetailPage.setOxygenDemand("");
        dailyExcelDetailPage.setPhValue("");
        dailyExcelDetailPage.setRemarks("");
        dailyExcelDetailPageDTO.setSoTwoAverage("0.665");

        //数据
        map = JSON.parseObject(JSON.toJSONString(dailyExcelDetailPageDTO), Map.class);
        //map.put("dailyExcelDetailPageDTO",dailyExcelDetailPageDTO);
        map.put("dailyExcelDetailPage",dailyExcelDetailPage);

        //数据单位指标
        List<Map<String, String>> mapListUnit = new ArrayList<>();
        Map<String,String> mapUnit = new HashMap<>();
        mapUnit.put("颗粒物平均值","<=50(mg/m3)");
        mapUnit.put("So2平均含量","<=400(mg/m3)");
        mapUnit.put("NOX平均含量","<=240(mg/m3)");
        mapUnit.put("PH值","无量纲");
        mapUnit.put("化学需氧量","<=50(mg/m3)");
        mapListUnit.add(mapUnit);

        //数据单位指标
        map.put("dailyExcelUnit",mapListUnit);
        mapList.add(map);
        return mapList;
    }

    /*public static void main(String[] args) {
        System.out.println(getHourlyAvgContinEmissionMonit());
    }*/
}
