package com.yangk.mystudy.transactional;

import com.yangk.mystudy.MystudyApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TransactionalDemoTest extends MystudyApplicationTests {

    @Autowired
    TransactionalDemo transactionalDemo;

    @Test
    public void one() {
        transactionalDemo.one();
        log.info("111");
    }

    @Test
    public void two() {
    }
}