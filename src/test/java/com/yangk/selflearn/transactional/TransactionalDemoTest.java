package com.yangk.selflearn.transactional;

import com.yangk.selflearn.SelfLearnApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TransactionalDemoTest extends SelfLearnApplicationTests {

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