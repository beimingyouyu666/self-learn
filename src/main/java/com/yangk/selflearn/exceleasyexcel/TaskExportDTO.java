/*
 * Copyright (c) 2019, FPX and/or its affiliates. All rights reserved. Use, Copy is subject to authorized license.
 */
package com.yangk.selflearn.exceleasyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * 任务单 实体类
 *
 * @author daniel.huang
 * @date 2019-01-12
 */
@Data
public class TaskExportDTO extends BaseRowModel {

    /**
     * 仓库编码
     */
    @ExcelProperty(index = 0, value = "仓库")
    private String warehouseCode;

    /**
     * 任务ID
     */
    private Long taskNo;

    /**
     * 任务ID
     */
    @ExcelProperty(index = 1, value = "任务单号")
    private String taskNoString;

    /**
     * 任务类型
     */
    @ExcelProperty(index = 2, value = "任务类型")
    private String taskType;

    /**
     * 状态
     */
    @ExcelProperty(index = 3, value = "任务状态")
    private String status;

    /**
     * 4px跟踪号
     */
    @ExcelProperty(index = 4, value = "4px跟踪号")
    private String fpxTrackingNo;

    /**
     * 业务类型
     */
    @ExcelProperty(index = 5, value = "业务类型")
    private String businessType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    @ExcelProperty(index = 6, value = "生成时间")
    private String createTimeString;

}
