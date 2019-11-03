package com.yangk.mystudy.refactoring;

import lombok.Data;

/**
 * @Description 电影实体
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
@Data
public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public abstract double getCharge(int daysRented);

/*    {
        double result = 0;
        switch (getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                result +=(daysRented -2 ) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                result +=daysRented * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (daysRented > 3)
                    result += (daysRented -3) * 1.5;
                break;
        }
        return result;
    }*/

    public int getFrequentRenterpoints(int daysTented) {
        if ((getPriceCode() == Movie.NEW_RELEASE) &&
                daysTented > 1) {
            return 2;
        } else {
            return 1;
        }
    }

}
