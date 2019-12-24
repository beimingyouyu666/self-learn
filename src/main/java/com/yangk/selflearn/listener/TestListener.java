package com.yangk.selflearn.listener;

import com.yangk.selflearn.base.controller.HelloController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description TODO
 * Listsner是由Tomcat管理的，Tomcat无法把SpringIOC容器的Bean注入到Listsner，而注入Bean的工作是由Spring负责的。那么解决这个问题呢？
 * 从servletContext中拿到webApplicationContext，然后调用webApplicationContext.getBean("beanName")
 *
 * spring 容器也是通过listener加载进tomcat容器的，普通listener用不了springBean
 * 不是因为springBean没加载（是否加载看listener配置顺序），而是不在一个容器级别上
 * @Author yangkun
 * @Date 2019/12/18
 * @Version 1.0
 * @blame yangkun
 */
public class TestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        HelloController controller = context.getBean(HelloController.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
