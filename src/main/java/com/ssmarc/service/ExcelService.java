package com.ssmarc.service;

import java.util.List;
import java.util.Map;

/**
 * @author ：Angular
 * @ProjectName: SSMARC
 * @Package: com.ssmarc.service
 * @ClassName: ExcelService
 * @date ：Created in 2021/1/17 9:57
 * @description：
 * @modified By：
 * @version: $
 */
public interface ExcelService {
    List<Map<String, Object>> getHourlyAvgContinEmissionMonit();
}
