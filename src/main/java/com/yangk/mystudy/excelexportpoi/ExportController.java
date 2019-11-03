package com.yangk.mystudy.excelexportpoi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@RestController
@RequestMapping("/excelUtils")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        exportService.export(response, "商品信息" + new Random().nextInt(1000));
    }



}
