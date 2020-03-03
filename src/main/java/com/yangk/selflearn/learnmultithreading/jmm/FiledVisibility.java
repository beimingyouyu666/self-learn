package com.yangk.selflearn.learnmultithreading.jmm;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2020/2/23
 * @Version 1.0
 */
public class FiledVisibility {

    int x = 1, y = 2;

    private void print() {
        // 因为在线程1调用change方法中修改了x,y的值，但是thread2中只看到了y的最新值，并没看到x的最新值
        // 就可能会打印“x=1,y=3”
        System.out.println("x=" + x + ",y=" + y);
    }

    private void change() {
        x = 3;
        y = x;
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            FiledVisibility filedVisibility = new FiledVisibility();
            Thread thread1 = new Thread(() -> {
                filedVisibility.change();
            });
            Thread thread2 = new Thread(() -> {
                filedVisibility.print();
            });
            thread1.start();
            thread2.start();
        }
    }



}
