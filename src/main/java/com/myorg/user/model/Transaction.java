package com.myorg.user.model;

/**
 * Models the Trustline Transaction
 * 
 * <pre>
 *     keeps the embedded user to intropect the endpoint to call
 * </pre>
 *
 * @author vg
 * @since Oct 2018
 */
public class Transaction {
    User user;
    int amount;
    boolean internal;

    public User getUser() {
        return this.user;
    }

    public int getAmount() {
        return amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isInternal() {
        return this.internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    @Override
    public String toString() {
        return "Transaction [ user: " + this.user + " amount: " + this.amount + " ]";
    }
}
