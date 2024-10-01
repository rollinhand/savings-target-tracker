package org.kivio.stt.savings.adapter.http;

import org.kivio.stt.savings.domain.cmd.AddSavingsTargetCmd;
import org.kivio.stt.savings.domain.payment.Deposit;
import org.kivio.stt.savings.domain.payment.Withdrawal;

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

@Path("/savings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SavingsTargetResource {
    @POST
    Response createSavings(AddSavingsTargetCmd addSavingsTargetCmd) throws URISyntaxException;

    @DELETE
    @Path("/{savingsTargetId}")
    Response deleteSavings(@PathParam("savingsTargetId") String savingsTargetId);

    @GET
    @Path("/{savingsTargetId}")
    Response getSavingsTarget(@PathParam("savingsTargetId") String savingsTargetId);

    @GET
    @Path("/findByUser/{userId}")
    Response getSavingsTargetByUser(@PathParam("userId") String userId);

    @PUT
    @Path("/{savingsTargetId}/deposit")
    Response deposit(@PathParam("savingsTargetId") String savingsTargetId, Deposit payment);

    @PUT
    @Path("/{savingsTargetId}/withdraw")
    Response withdraw(@PathParam("savingsTargetId") String savingsTargetId, Withdrawal withdrawal);
}
