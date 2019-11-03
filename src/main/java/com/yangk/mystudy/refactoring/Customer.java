package com.yangk.mystudy.refactoring;

import lombok.Data;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Description 消费者信息
 * @Author yangkun
 * @Date 2019/8/7
 * @Version 1.0
 */
@Data
public class Customer {

    private String name;

    private Vector<Rental> rentals = new Vector();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String statement() {
        Enumeration rentalVector = rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentalVector.hasMoreElements()) {
            Rental each = (Rental) rentalVector.nextElement();

            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }

        double totalAmount = getTotalAmount(rentalVector);
        int frequentRenterpoints = getTotalFrequentRenterpoints(rentalVector);

        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterpoints) + "frequent renter points";
        System.out.println(result);
        return result;

    }

    private double getTotalAmount(Enumeration rentalVector) {
        double totalAmount = 0;
        while (rentalVector.hasMoreElements()) {
            Rental each = (Rental) rentalVector.nextElement();
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }

    private int getTotalFrequentRenterpoints(Enumeration rentalVector) {
        int frequentRenterpoints = 0;
        while (rentalVector.hasMoreElements()) {
            Rental each = (Rental) rentalVector.nextElement();
            frequentRenterpoints += each.getFrequentRenterpoints();
        }
        return frequentRenterpoints;
    }


}
