package com.yangk.selflearn.learnrefactoring;

import java.util.Vector;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setName("yk");
        Vector vector = new Vector();
        Rental rental1 = new Rental(new RegularMovie("nazha", 2), 2);
        Rental rental2 = new Rental(new ChildrensMovie("xiyangyang", 2), 3);
        vector.add(rental1);
        vector.add(rental2);
        customer.setRentals(vector);
        customer.statement();
    }

}
