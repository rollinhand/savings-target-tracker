package org.kivio.stt.user.adapter.http;

import org.kivio.stt.user.domain.cmd.AddUserCmd;
import org.kivio.stt.user.domain.cmd.UpdateUserCmd;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserResource {
    @GET
    @Path("/{userId}")
    Response getUser(@PathParam("userId") String userId);

    @POST
    Response addUser(AddUserCmd addUserCmd) throws URISyntaxException;

    @DELETE
    @Path("/{userId}")
    Response deleteUser(@PathParam("userId") String userId);

    @PUT
    @Path("/{userId}")
    Response updateUser(@PathParam("userId") String userId, UpdateUserCmd updateUserCmd);
}
