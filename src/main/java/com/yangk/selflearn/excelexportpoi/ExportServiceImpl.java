package com.yangk.selflearn.excelexportpoi;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@Service
public class ExportServiceImpl implements ExportService{

    @Override
    public void export(HttpServletResponse response, String fileName) {

        // 待导出数据
        List<ExportContentInfo> contents = getContent();

        ExcelUtils excelUtils = new ExcelUtils(contents, getHeaderInfo(), getFormatInfo(),ExportContentInfo.class);

        excelUtils.sendHttpResponse(response,"导出测试",excelUtils.getWorkbook());
    }

    // 获取格式化信息
    private Map<String, ExcelFormat> getFormatInfo() {
        Map<String, ExcelFormat> format = new HashMap<>();
        format.put("id", ExcelFormat.FORMAT_INTEGER);
        format.put("categoryId", ExcelFormat.FORMAT_INTEGER);
        format.put("branchId", ExcelFormat.FORMAT_INTEGER);
        format.put("shopId", ExcelFormat.FORMAT_INTEGER);
        format.put("price", ExcelFormat.FORMAT_DOUBLE);
        format.put("stock", ExcelFormat.FORMAT_INTEGER);
        format.put("salesNum", ExcelFormat.FORMAT_INTEGER);
        format.put("isDel", ExcelFormat.FORMAT_INTEGER);
        return format;
    }

    private List<ExportContentInfo> getContent() {

        List list = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            ExportContentInfo exportContentInfo = new ExportContentInfo();
            exportContentInfo.setId((long) i);
            exportContentInfo.setProductName("");
            exportContentInfo.setCategoryId(0L);
            exportContentInfo.setCategoryName("");
            exportContentInfo.setBranchId(0L);
            exportContentInfo.setBranchName("");
            exportContentInfo.setShopId(0L);
            exportContentInfo.setShopName("");
            exportContentInfo.setPrice(0.0D);
            exportContentInfo.setStock(0);
            exportContentInfo.setSalesNum(0);
            exportContentInfo.setCreateTime(Calendar.getInstance().getTime().toString());
            exportContentInfo.setUpdateTime("");
            exportContentInfo.setIsDel((byte)0);

            list.add(exportContentInfo);
        }
        return list;

    }


    // 获取表头信息
    private List<ExcelHeaderInfo> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo(1, 1, 0, 0, "id"),
                new ExcelHeaderInfo(1, 1, 1, 1, "商品名称"),

                new ExcelHeaderInfo(0, 0, 2, 3, "分类"),
                new ExcelHeaderInfo(1, 1, 2, 2, "类型ID"),
                new ExcelHeaderInfo(1, 1, 3, 3, "分类名称"),

                new ExcelHeaderInfo(0, 0, 4, 5, "品牌"),
                new ExcelHeaderInfo(1, 1, 4, 4, "品牌ID"),
                new ExcelHeaderInfo(1, 1, 5, 5, "品牌名称"),

                new ExcelHeaderInfo(0, 0, 6, 7, "商店"),
                new ExcelHeaderInfo(1, 1, 6, 6, "商店ID"),
                new ExcelHeaderInfo(1, 1, 7, 7, "商店名称"),

                new ExcelHeaderInfo(1, 1, 8, 8, "价格"),
                new ExcelHeaderInfo(1, 1, 9, 9, "库存"),
                new ExcelHeaderInfo(1, 1, 10, 10, "销量"),
                new ExcelHeaderInfo(1, 1, 11, 11, "插入时间"),
                new ExcelHeaderInfo(1, 1, 12, 12, "更新时间"),
                new ExcelHeaderInfo(1, 1, 13, 13, "记录是否已经删除")
        );
    }
}
