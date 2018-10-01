package com.myorg.user.model;

/**
 * Models the Balance
 *
 * @author vg
 * @since Oct 2018
 */
public class Balance {
    int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void adjust(int balance) {
        this.balance += -balance;
    }

    @Override
    public String toString() {
        return "Balance [ " + this.balance + " ]";
    }

}
