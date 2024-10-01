package org.kivio.stt.savings.adapter.http.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SavingsTargetNotFoundExceptionMapper implements ExceptionMapper<SavingsTargetNotFoundException> {
    @Override
    public Response toResponse(SavingsTargetNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
