package com.yangk.selflearn.ctmds;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description 导出内容实体
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@Data
@Accessors(chain = true)
public class ExportContentInfo1 {

    /**
     * 省份
     */
    private String areaName;

    /**
     * 备案号
     */
    private String recordNo;

    /**
     * 机构名称
     */
    private String compName;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系人号码
     */
    private String linkTel;

    /**
     * 备案状态
     */
    private String recordStatus;

    /**
     * 详情
     */
    private String detail;

    private String ROW2;

    private String companyId;


}
