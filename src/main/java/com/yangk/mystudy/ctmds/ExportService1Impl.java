package com.yangk.mystudy.ctmds;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.math3.analysis.function.Exp;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.javassist.expr.NewExpr;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/10/8
 * @Version 1.0
 * @blame yangkun
 */
@Service
public class ExportService1Impl implements ExportService1 {

    @Override
    public void export(HttpServletResponse response, String fileName) {

        // 待导出数据
//        List<ExportContentInfo1> contents = getContent();
//        List<ExportContentInfo1> contents = getContent1();
        List<ExportDTO> contents = getContent1();

        ExcelUtils1 excelUtils1 = new ExcelUtils1(contents, getHeaderInfo(), getFormatInfo(), ExportDTO.class);

        excelUtils1.sendHttpResponse(response,"导出测试", excelUtils1.getWorkbook());
    }

    /****
     *  获取格式化信息
     * @return
     */
    private Map<String, ExcelFormat1> getFormatInfo() {
        Map<String, ExcelFormat1> format = new HashMap<>();
        format.put("areaName", ExcelFormat1.FORMAT_STRING);
        format.put("recordNo", ExcelFormat1.FORMAT_STRING);
        format.put("compName", ExcelFormat1.FORMAT_STRING);
        format.put("address", ExcelFormat1.FORMAT_STRING);
        format.put("linkMan", ExcelFormat1.FORMAT_STRING);
        format.put("linkTel", ExcelFormat1.FORMAT_STRING);
        format.put("recordStatus", ExcelFormat1.FORMAT_STRING);
        return format;
    }

    private List<ExportDTO> getContent1() {
        String s = "{\"data\":[{\"linkTel\":\"0756-2157515\",\"recordNo\":\"械临机构备201800001\",\"areaName\":\"广东省\",\"linkMan\":\"肖静\",\"address\":\"珠海市香洲区康宁路79号\",\"compName\":\"珠海市人民医院\",\"ROW2\":1,\"companyId\":\"8FC95EF5C0A802335C4975E2C4D5D081\",\"recordStatus\":\"8\"},{\"linkTel\":\"028-61866124\",\"recordNo\":\"械临机构备201800002\",\"areaName\":\"四川省\",\"linkMan\":\"黄砚\",\"address\":\"四川省成都市青羊区日月大道1617号\",\"compName\":\"成都市妇女儿童中心医院\",\"ROW2\":2,\"companyId\":\"846EF1E8C0A802331A92B78E9E931A6F\",\"recordStatus\":\"8\"}],\"success\":true,\"curPage\":\"1\",\"totalRows\":811}";
        ExcelResponse excelResponse = JSONObject.parseObject(s, ExcelResponse.class);
        List<ExportContentInfo1> result = excelResponse.getData();
        List<ExportDTO> list = new ArrayList<>();
        for (ExportContentInfo1 exportContentInfo1 : result) {
            ExportDTO exportDTO = new ExportDTO();
            try {
                BeanUtils.copyProperties(exportContentInfo1,exportDTO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            list.add(exportDTO);
        }
        return list;
    }

    public static void main(String[] args) {
//        List<ExportContentInfo1> exportContentInfo1s = JSONObject.parseArray(s, ExportContentInfo1.class);
//        ExcelResponse excelResponse1 = JSONObject.toJavaObject(JSONObject.parseObject(s), ExcelResponse.class);
        String s = "{\"data\":[{\"linkTel\":\"0756-2157515\",\"recordNo\":\"械临机构备201800001\",\"areaName\":\"广东省\",\"linkMan\":\"肖静\",\"address\":\"珠海市香洲区康宁路79号\",\"compName\":\"珠海市人民医院\",\"ROW2\":1,\"companyId\":\"8FC95EF5C0A802335C4975E2C4D5D081\",\"recordStatus\":\"8\"},{\"linkTel\":\"028-61866124\",\"recordNo\":\"械临机构备201800002\",\"areaName\":\"四川省\",\"linkMan\":\"黄砚\",\"address\":\"四川省成都市青羊区日月大道1617号\",\"compName\":\"成都市妇女儿童中心医院\",\"ROW2\":2,\"companyId\":\"846EF1E8C0A802331A92B78E9E931A6F\",\"recordStatus\":\"8\"}],\"success\":true,\"curPage\":\"1\",\"totalRows\":811}";
        ExcelResponse excelResponse = JSONObject.parseObject(s, ExcelResponse.class);
        List<ExportContentInfo1> result = excelResponse.getData();
//        System.out.println(11);
//        getContent();
    }

    private static List<ExportContentInfo1> getContent() {
        List list = new ArrayList();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        // http://218.240.145.213:9000/CTMDS/pub/PUB010100.do?method=handle05&_dt=20191111223541
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        params.append("pageSize=1000");
        params.append("&");
        params.append("curPage=1");


        // 创建Get请求
        String url = "http://218.240.145.213:9000/CTMDS/pub/PUB010100.do?method=handle05&_dt=20191111223541&pageSize=1000&curPage=1";
//        HttpGet httpGet = new HttpGet("http://218.240.145.213:9000/CTMDS/pub/PUB010100.do?method=handle05&_dt=20191111223541" + "?" + params);
        System.out.println("url:"+url);
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(50000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(50000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(50000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                String s = EntityUtils.toString(responseEntity);
                List<ExportContentInfo1> exportContentInfo1s = JSONObject.parseArray(s, ExportContentInfo1.class);

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return list;

    }

    /**
     * 获取格式化信息
     * @return
     */
    private List<ExcelHeaderInfo1> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo1(0, 0, 0, 0, "省份"),
                new ExcelHeaderInfo1(0, 0, 1, 1, "备案号"),

                new ExcelHeaderInfo1(0, 0, 2, 2, "机构名称"),
                new ExcelHeaderInfo1(0, 0, 3, 3, "地址"),
                new ExcelHeaderInfo1(0, 0, 4, 4, "联系人"),

                new ExcelHeaderInfo1(0, 0, 5, 5, "联系方式"),
                new ExcelHeaderInfo1(0, 0, 6, 6, "备案状态")
        );
    }
}
