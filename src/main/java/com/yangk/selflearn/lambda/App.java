package com.yangk.selflearn.lambda;

/**
 * @Description
 *
 *
 *  1、手动编译："javac App.java"
 *
 *  2、查看app.class源代码  "javap -p App.class"
 *
 *  3、查看详细编译过程 "java -Djdk.internal.lambda.dumpProxyClasses App "
 *  *
 * @Author yangkun
 * @Date 2019/12/7
 * @Version 1.0
 */
public class App {

	public static void main(String [] args) {
		IMarkUp mu = (message) -> System.out.println(message);

		mu.markUp("lambda!");
	}
}

interface IMarkUp {
	void markUp(String msg);
}