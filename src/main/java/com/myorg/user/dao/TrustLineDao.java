package com.myorg.user.dao;

import com.myorg.user.model.Balance;
import com.myorg.user.model.Transaction;

/**
 * Provides data access for Trustline operations
 *
 * @author vg
 * @since Oct 2018
 */
public interface TrustLineDao {

    Balance getBalance();

    Balance update(Transaction transaction);
}
