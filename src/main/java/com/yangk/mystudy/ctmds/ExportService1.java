package com.yangk.mystudy.ctmds;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
public interface ExportService1 {

    /**
     * @Description 导出操作
     * @Author yangkun
     * @Date 2019/10/8
     * @Param [response, fileName]
     * @Return
     **/
    void export(HttpServletResponse response, String fileName);
}
