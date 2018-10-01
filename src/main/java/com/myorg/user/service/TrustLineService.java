package com.myorg.user.service;

import com.myorg.user.model.Balance;
import com.myorg.user.model.Transaction;

/**
 * Provides the Trustline Service
 *
 * @author vg
 * @since Oct 2018
 */
public interface TrustLineService {

    Balance getBalance();

    Balance process(Transaction transaction);
}
