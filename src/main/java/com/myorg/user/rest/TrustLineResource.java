package com.myorg.user.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.myorg.user.model.Balance;
import com.myorg.user.model.Transaction;
import com.myorg.user.service.TrustLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides REST operations for Trustline
 *
 * @author vg
 * @since Oct 2018
 */
@Path("trustline")
public class TrustLineResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrustLineResource.class);

    private TrustLineService trustLineService;

    @Inject
    TrustLineResource(final TrustLineService trustLineService) {
        this.trustLineService = trustLineService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendTransaction(Transaction transaction) {
        final Balance balance = this.trustLineService.process(transaction);
        return Response.ok(balance).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance() {
        return Response.ok(this.trustLineService.getBalance()).build();
    }
}
