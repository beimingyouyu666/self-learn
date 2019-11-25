package com.yangk.selflearn.ctmds;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/11
 * @Version 1.0
 */
@Data
public class ExcelResponse {

    private List<ExportContentInfo1> data;

    private boolean success;

    private String curPage;

    private int totalRows;
}
