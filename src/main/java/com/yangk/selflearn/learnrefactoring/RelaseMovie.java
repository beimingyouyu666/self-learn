package com.yangk.selflearn.learnrefactoring;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
public class RelaseMovie extends Movie {


    public RelaseMovie(String title, int priceCode) {
        super(title, priceCode);
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 0;
        result += daysRented * 3;
        return result;
    }
}
