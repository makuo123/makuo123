package com.stock.util.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author makuo
 * @Date 2023/9/7 17:38
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataDemo {
    // 根据Excel中指定列名或列的索引读取
    @ExcelProperty(value = "门类", index = 0)
    private String category;
    @ExcelProperty(value = "大类", index = 1)
    private String big;
    @ExcelProperty(value = "中类", index = 2)
    private String mid;
    @ExcelProperty(value = "小类", index = 3)
    private String small;
    @ExcelProperty(value = "类别名称", index = 4)
    private String name;
}
