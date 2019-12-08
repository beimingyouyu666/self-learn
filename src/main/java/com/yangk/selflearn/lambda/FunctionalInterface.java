package com.yangk.selflearn.lambda;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @Description 函数式接口学习
 * <p>
 * java.util.function中提供的常用的不同场景的函数式接口
 * <p>
 * Predicate 接收参数T对象，返回boolean类型结果
 * <p>
 * Consumer 接收参数T对象，没有返回值
 * <p>
 * Function 接收参数T 对象，返回R对象
 * <p>
 * Supplier 不接受任何参数，直接通过get()获取指定类型的对象
 * <p>
 * UnaryOperator 接受参数 T 对象，进行业务处理后，返回T对象，比如一些加强修改场景
 * <p>
 * BinaryOperator 接受两个T对象，进行业务处理后，返回一个T对象
 *
 * @Author yangkun
 * @Date 2019/12/7
 * @Version 1.0
 */
@Slf4j
public class FunctionalInterface {

    String ss = "全局变量";

    public static void main(String[] args) {
        Supplier<String> supplier = () -> UUID.randomUUID().toString();
        log.info(supplier.get());

        UnaryOperator<String> unaryOperator = (String s1) -> s1 += ":big super";
        log.info(unaryOperator.apply("flaqi"));

        BinaryOperator<Integer> binaryOperator = (Integer i1, Integer i2) -> i1 > i2 ? i1 : i2;
        log.info(String.valueOf(binaryOperator.apply(12, 11)));

        FunctionalInterface functionalInterface = new FunctionalInterface();
        functionalInterface.testLambda();
    }

    public void testLambda() {
        new Thread(()->{
            String s = "内部变量";
            System.out.println(this.ss);
        });
    }

    // lambda表达式的基本语法
    /*
    1、声明：就是和lambda表达式绑定接口类型（先要有个接口）
    2、参数：包含在一对圆括号中，和绑定的接口中的抽象方法中的参数个数及顺序一致
    3、操作符：->
    4、执行代码块：包含在一对大括号中，出现在操作符号的右侧

    [接口声明] = （参数） -> {执行代码块}；
     Function<String,String> function = (s)-> s+=":super";

     */


    /*
    1、lambda表达式必须和接口进行绑定
    2、lambda表达式的参数，可以附带0到n个参数，括号中的参数类型可以不用指定，jvm在运行时
        ，会自动根据绑定的抽象方法中的参数进行推导
    3、lambda表达式的返回值，如果代码只有一行，而且没有大括号，不用写return关键字，单行代码的执行结果
        ，会自动返回；如果添加了大括号或者有多行代码，必须通过return关键字返回执行结果

     */

    /*
    变量捕获

    lambda表达式中的this关键字与匿名内部类的this指代的不一样
    lambda表达式中的this关键字就是所属方法所在类型的对象

     */

    /*
    方法重载对lambda表达式的影响

    假如有两个重载方法，分别接受两个不同函数式接口，用lambda表达式就无法判断，会报错
     */


    // lambda表达式底层解析运行原理

    /*
    1、lambda表达式在JVM底层解析成私有静态方法和匿名内部类型

    2、通过实现接口的匿名内部类型中接口方法，调用静态实现方法。完成lambda表达式的执行
     */
}
