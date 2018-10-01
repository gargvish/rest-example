package com.myorg.user.rest;

import com.myorg.user.model.User;
import com.myorg.user.model.Users;
import com.myorg.user.dao.UserDao;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User REST resource to provide the basic CRUD operations via REST
 *
 * @author vg
 * @since Oct 2018
 */
@Path("users")
public class UserResource {

    private UserDao userDaoService;

    @Inject
    UserResource(final UserDao userService) {
        this.userDaoService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        Users hellos = new Users();
        hellos.setUserList(this.userDaoService.getAll());
        return Response.ok(hellos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        int userId = this.userDaoService.add(user);
        return Response.created(URI.create("/api/v1/user/" + userId)).build();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("helloId") int userId) {
        User user = this.userDaoService.getById(userId);
        if (user != null) {
            return Response.ok(user).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHello(@PathParam("userId") int userId, User user) {
        final User updateHello = new User();
        updateHello.setUserId(userId);
        updateHello.setUserName(user.getUserName());

        boolean success = userDaoService.update(updateHello);
        if (success) {
            return Response.ok(this.userDaoService.getById(userId)).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteHello(@PathParam("helloId") int helloId) {
        boolean success = userDaoService.delete(helloId);
        if (success) {
            return Response.noContent().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
