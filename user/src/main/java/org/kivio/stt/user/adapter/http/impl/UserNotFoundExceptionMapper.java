package org.kivio.stt.user.adapter.http.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
