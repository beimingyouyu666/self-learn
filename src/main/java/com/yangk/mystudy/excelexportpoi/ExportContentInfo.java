package com.yangk.mystudy.excelexportpoi;

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
public class ExportContentInfo {

        private Long id;
        private String productName;
        private Long categoryId;
        private String categoryName;
        private Long branchId;
        private String branchName;
        private Long shopId;
        private String shopName;
        private Double price;
        private Integer stock;
        private Integer salesNum;
        private String createTime;
        private String updateTime;
        private Byte isDel;

}
