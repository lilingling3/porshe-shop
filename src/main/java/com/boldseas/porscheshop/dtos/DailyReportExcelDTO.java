package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.utils.ExcelColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chen Jingxuan
 * @version 2018/7/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportExcelDTO {
    @ExcelColumnName("")
    private String name;
    @ExcelColumnName("截止前一天数据")
    private String value;
}
