package com.yangk.mystudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan负责扫描dao层的java文件
 */
@SpringBootApplication
@MapperScan("com.yangk.mystudy.dao")
public class MystudyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MystudyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         *  测试oom
         */
        /*List<HelloController> list = new ArrayList<>();
        while (true){
            HelloController helloController = new HelloController();

            list.add(helloController);
			System.out.println(System.currentTimeMillis());
		}*/
    }
}
