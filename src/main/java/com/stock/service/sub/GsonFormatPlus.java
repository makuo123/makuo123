package com.stock.service.sub;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/13 11:01
 **/
@NoArgsConstructor
@Data
public class GsonFormatPlus {

    private String teacherName;
    private Integer age;
    private StudentDTO student;

    @NoArgsConstructor
    @Data
    public static class StudentDTO {
        private String stuName;
        private Integer score;
    }
}
