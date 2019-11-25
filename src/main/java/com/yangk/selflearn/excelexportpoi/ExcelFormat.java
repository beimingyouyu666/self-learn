package com.yangk.selflearn.excelexportpoi;

import lombok.Getter;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@Getter
public enum ExcelFormat {

    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE"),
    FORMAT_STRING("STRING");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

}
