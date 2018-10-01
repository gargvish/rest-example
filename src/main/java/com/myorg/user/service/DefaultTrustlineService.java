package com.myorg.user.service;

import javax.inject.Inject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.HostAndPort;
import com.myorg.user.config.TrustLineContext;
import com.myorg.user.dao.TrustLineDao;
import com.myorg.user.model.Balance;
import com.myorg.user.model.Transaction;
import com.myorg.user.model.User;

/**
 * Default Trustline Service
 *
 * @author vg
 * @since Oct 2018
 */
public class DefaultTrustlineService
    implements TrustLineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTrustlineService.class);

    private TrustLineDao trustLineDaoService;

    @Inject
    DefaultTrustlineService(final TrustLineDao trustLineDao) {
        this.trustLineDaoService = trustLineDao;
    }

    @Override
    public Balance getBalance() {
        return this.trustLineDaoService.getBalance();
    }

    @Override
    public Balance process(Transaction transaction) {
        if (transaction.isInternal()) {
            LOGGER.info("[Trustline] Transaction: " + transaction + " Received!!");
        }
        synchronized (this) {
            if (!transaction.isInternal()) {
                send(transaction);
            }
            final Balance balance = this.trustLineDaoService.update(transaction);
            LOGGER.info("[Trustline] Balance: " + this.trustLineDaoService.getBalance());
            return balance;
        }
    }

    private void send(final Transaction transaction) {
        Client client = ClientBuilder.newClient();

        // create
        WebTarget webTarget1 = client.target(getEndpoint(transaction)).path("trustline");
        Invocation.Builder invocationBuilder1 = webTarget1.request(MediaType.APPLICATION_JSON);
        Transaction remoteTransaction = new Transaction();
        remoteTransaction.setUser(TrustLineContext.get().getUser());
        remoteTransaction.setAmount(transaction.getAmount() * -1);
        remoteTransaction.setInternal(true);
        Response response1 = invocationBuilder1.post(Entity.entity(remoteTransaction, MediaType.APPLICATION_JSON));
        if (response1.getStatus() == 200) {
            LOGGER.info("[Trustline] Transaction " + transaction + " Sent!!");
        }
    }

    String getEndpoint(final Transaction transaction) {
        final User user = transaction.getUser();
        final String endPoint = user.getEndPoint();
        final HostAndPort hostAndPort = HostAndPort.fromString(endPoint);
        return "http://localhost:" + hostAndPort.getPort() + "/api/v1";
    }
}
