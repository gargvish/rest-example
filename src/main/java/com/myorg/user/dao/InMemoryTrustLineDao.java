package com.myorg.user.dao;

import com.myorg.user.model.Balance;
import com.myorg.user.model.Transaction;

/**
 * In Memory data access from trustline operations
 *
 * @author vg
 * @since Oct 2018
 */
public class InMemoryTrustLineDao
    implements TrustLineDao {

    private final Balance balance = new Balance();

    @Override
    public Balance update(Transaction transaction) {
        this.balance.adjust(transaction.getAmount());
        return this.balance;
    }

    @Override
    public Balance getBalance() {
        return this.balance;
    }
}
