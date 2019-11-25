package com.yangk.selflearn.refactoring;

import lombok.Data;

/**
 * @Description 表示顾客租了一部影片
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
@Data
public class Rental {

    private Movie movie;

    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }


    public int getFrequentRenterpoints() {
        return getMovie().getFrequentRenterpoints(daysRented);
    }

    public double getCharge() {
        double charge = movie.getCharge(getDaysRented());
        System.out.println("charge is :{}." + charge + "class is:{}" + movie.getClass().getName());
        return charge;
    }

}
