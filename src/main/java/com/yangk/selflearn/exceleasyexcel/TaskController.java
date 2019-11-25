/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.yangk.selflearn.exceleasyexcel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务 controller
 *
 * @Author: luoxingx
 * @Date: 2019/1/18
 * @version: v1.0.0
 */
@Controller
@RequestMapping("/page/task")
public class TaskController{

    @RequestMapping(value = "/exportTaskList", method = RequestMethod.POST)
    @ResponseBody
    public void exportTaskList(HttpServletResponse response, HttpServletRequest request) {

        List<TaskExportDTO> taskExportDTOList = new ArrayList<>();
        ExcelUtil.writeExcel(response, request.getHeader("User-Agent"), taskExportDTOList, "任务数据", "sheeet1",
                new TaskExportDTO());

    }


}
