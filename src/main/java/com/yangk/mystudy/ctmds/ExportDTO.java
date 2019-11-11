package com.yangk.mystudy.ctmds;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/12
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ExportDTO {

    /**
     省份
     */
    private String areaName;

    /**
     备案号
     */
    private String recordNo;

    /**
     机构名称
     */
    private String compName;

    /**
     地址
     */
    private String address;

    /**
     联系人
     */
    private String linkMan;

    /**
     联系人号码
     */
    private String linkTel;

    /**
     备案状态
     */
    private String recordStatus;


}
