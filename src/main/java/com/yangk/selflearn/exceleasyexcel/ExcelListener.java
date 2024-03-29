package com.yangk.selflearn.exceleasyexcel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听类，可以自定义
 *
 * @author QingDeng.Guo
 * @date 2019-06-18
 * @since v1.0.0
 */
public class ExcelListener extends AnalysisEventListener {

    // 自定义用于暂时存储data。
    // 可以通过实例获取该值
    @Setter
    @Getter
    private List<Object> datas = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(object);
        // 根据业务自行 do something
        doSomething();

        /*
        如数据过大，可以进行定量分批处理
        if(datas.size()<=100){
            datas.add(object);
        }else {
            doSomething();
            datas = new ArrayList<Object>();
        }
         */

    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        /*
            datas.clear();
            解析结束销毁不用的资源
         */
    }
}