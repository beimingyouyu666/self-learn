///**
// * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
// * Use,Copy is subject to authorized license.
// */
//package com.yangk.selflearn.exceleasyexcel;
//
//import com.yangk.selflearn.excelexportpoi.ExcelUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * 任务 controller
// *
// * @Date: 2019/1/18
// * @version: v1.0.0
// */
//@Controller
//@RequestMapping("/page/task")
//public class TaskController {
//
//    @RequestMapping(value = "/exportTaskList", method = RequestMethod.POST)
//    @ResponseBody
//    public void exportTaskList(HttpServletResponse response, HttpServletRequest request) {
//
//        List<TaskExportDTO> taskExportDTOList = new ArrayList<>();
//        ExcelUtil.writeExcel(response, request.getHeader("User-Agent"), taskExportDTOList, "任务数据", "sheeet1",
//                new TaskExportDTO());
//
//    }
//
//    /**
//     * @Description 导出
//     * @Author yangkun
//     * @Date 2019/10/10
//     * @Param [query]
//     * @Return
//     **/
//    @RequestMapping(value = "/exportCheckJobList", method = RequestMethod.POST)
//    @ResponseBody
//    public void ExportCheckJobList(CheckJobVO query, HttpServletResponse response) {
//        query.setSort("c.create_time desc");
//        PageInfo<CheckJobDTO> page = checkService.pageCheckJob(query);
//        List<CheckJobDTO> checkJobDTOList = page.getList();
//        List<CheckJobExportDTO> checkJobExportDTOs = new ArrayList<>();
//
//        List<GdsDicDTO> warehousePkgTypeList = Optional.ofNullable(gdsDicService.searchGroupCode(EnumDicGroupCode.WAREHOUSE_PKG_TYPE.name())).orElse(new ArrayList<>());
//        List<GdsDicDTO> jobStatusList = Optional.ofNullable(gdsDicService.searchGroupCode(EnumDicGroupCode.JOB_STATUS.name())).orElse(new ArrayList<>());
//        for (CheckJobDTO checkJobDTO : checkJobDTOList) {
//            CheckJobExportDTO checkJobExportDTO = new CheckJobExportDTO();
//            BeanUtils.copyProperties(checkJobDTO,checkJobExportDTO);
//            checkJobExportDTO.setInstockParcelType(DicUtil.convertCodeToName(warehousePkgTypeList,checkJobDTO.getInstockParcelType()));
//            checkJobExportDTO.setStatus(DicUtil.convertCodeToName(jobStatusList,checkJobDTO.getStatus()));
//            checkJobExportDTOs.add(checkJobExportDTO);
//        }
//
//        ExcelUtils excelUtils = new ExcelUtils(checkJobExportDTOs, getHeaderInfo(), getFormatInfo(),CheckJobExportDTO.class);
//        excelUtils.sendHttpResponse(response,"查验作业单数据",excelUtils.getWorkbook());
//
//
//}
