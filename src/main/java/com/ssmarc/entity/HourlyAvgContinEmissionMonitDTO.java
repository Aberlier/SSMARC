package com.ssmarc.entity;

import java.io.Serializable;

/**
 * @author ：Angular
 * @ProjectName: zuihou-admin-cloud
 * @Package: com.cn.tanbao.edss.entity
 * @ClassName: DailyExcelEntity
 * @date ：Created in 2021/1/15 9:43
 * @description：废气排放连续监测小时均值日报表
 * @modified By：
 * @version: v1.0.0$
 */


public class HourlyAvgContinEmissionMonitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String MonitoringTime;

    private String DustConcentration;

    /**
     * 烟尘
     */

    private String DustConvertedConcentration;

    private String DustEmissions;

    /**
     * 二氧化硫
     */

    private String SoTwoConcentration;


    private String SoTwoAverage;


    private String SoTwoUnit;


    private String NoxAverage;


    private String NoxUnit;

    private String PhValue;


    private String PhValueUnit;

    private String OxygenDemand;


    private String OxygenDemandUnit;


    private String Remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonitoringTime() {
        return MonitoringTime;
    }

    public void setMonitoringTime(String monitoringTime) {
        MonitoringTime = monitoringTime;
    }

    public String getDustConcentration() {
        return DustConcentration;
    }

    public void setDustConcentration(String dustConcentration) {
        DustConcentration = dustConcentration;
    }

    public String getDustConvertedConcentration() {
        return DustConvertedConcentration;
    }

    public void setDustConvertedConcentration(String dustConvertedConcentration) {
        DustConvertedConcentration = dustConvertedConcentration;
    }

    public String getDustEmissions() {
        return DustEmissions;
    }

    public void setDustEmissions(String dustEmissions) {
        DustEmissions = dustEmissions;
    }

    public String getSoTwoConcentration() {
        return SoTwoConcentration;
    }

    public void setSoTwoConcentration(String soTwoConcentration) {
        SoTwoConcentration = soTwoConcentration;
    }

    public String getSoTwoAverage() {
        return SoTwoAverage;
    }

    public void setSoTwoAverage(String soTwoAverage) {
        SoTwoAverage = soTwoAverage;
    }

    public String getSoTwoUnit() {
        return SoTwoUnit;
    }

    public void setSoTwoUnit(String soTwoUnit) {
        SoTwoUnit = soTwoUnit;
    }

    public String getNoxAverage() {
        return NoxAverage;
    }

    public void setNoxAverage(String noxAverage) {
        NoxAverage = noxAverage;
    }

    public String getNoxUnit() {
        return NoxUnit;
    }

    public void setNoxUnit(String noxUnit) {
        NoxUnit = noxUnit;
    }

    public String getPhValue() {
        return PhValue;
    }

    public void setPhValue(String phValue) {
        PhValue = phValue;
    }

    public String getPhValueUnit() {
        return PhValueUnit;
    }

    public void setPhValueUnit(String phValueUnit) {
        PhValueUnit = phValueUnit;
    }

    public String getOxygenDemand() {
        return OxygenDemand;
    }

    public void setOxygenDemand(String oxygenDemand) {
        OxygenDemand = oxygenDemand;
    }

    public String getOxygenDemandUnit() {
        return OxygenDemandUnit;
    }

    public void setOxygenDemandUnit(String oxygenDemandUnit) {
        OxygenDemandUnit = oxygenDemandUnit;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
