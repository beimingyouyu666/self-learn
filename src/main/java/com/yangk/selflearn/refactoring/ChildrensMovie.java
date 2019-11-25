package com.yangk.selflearn.refactoring;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
public class ChildrensMovie extends Movie {

    public ChildrensMovie(String title, int priceCode) {
        super(title, priceCode);
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 0;
//        result += 1.5;
        result += 10000;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
