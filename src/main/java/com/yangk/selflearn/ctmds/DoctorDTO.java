package com.yangk.selflearn.ctmds;

import lombok.Data;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/11/12
 * @Version 1.0
 * @blame yangkun
 */
@Data
public class DoctorDTO {

    /**
     * 科室 专业名称
     */
    private String department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职称
     */
    private String title;
}
