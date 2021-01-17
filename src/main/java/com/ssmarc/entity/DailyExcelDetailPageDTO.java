package com.ssmarc.entity;

import java.io.Serializable;

/**
 * @author ：Angular
 * @ProjectName: zuihou-admin-cloud
 * @Package: com.cn.tanbao.edss.entity
 * @ClassName: DailyExcelEntity
 * @date ：Created in 2021/1/15 9:43
 * @description：每日数据导出类
 * @modified By：
 * @version: v1.0.0$
 */

public class DailyExcelDetailPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String MonitoringPoints;
    private String DustAverage;
    private String DustUnit;
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

    public String getMonitoringPoints() {
        return MonitoringPoints;
    }

    public void setMonitoringPoints(String monitoringPoints) {
        MonitoringPoints = monitoringPoints;
    }

    public String getDustAverage() {
        return DustAverage;
    }

    public void setDustAverage(String dustAverage) {
        DustAverage = dustAverage;
    }

    public String getDustUnit() {
        return DustUnit;
    }

    public void setDustUnit(String dustUnit) {
        DustUnit = dustUnit;
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
