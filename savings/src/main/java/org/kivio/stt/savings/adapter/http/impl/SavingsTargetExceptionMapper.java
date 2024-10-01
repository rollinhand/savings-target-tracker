package org.kivio.stt.savings.adapter.http.impl;

import org.kivio.stt.savings.domain.SavingsTargetException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SavingsTargetExceptionMapper implements ExceptionMapper<SavingsTargetException> {
    @Override
    public Response toResponse(SavingsTargetException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
