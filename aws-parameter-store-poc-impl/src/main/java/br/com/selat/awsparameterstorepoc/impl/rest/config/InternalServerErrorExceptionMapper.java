package br.com.selat.awsparameterstorepoc.impl.rest.config;

import br.com.selat.exceptions.InternalErrorException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalErrorException> {

	@Override
	public Response toResponse(InternalErrorException exception) {
		return Response
						.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(exception.getMessage())
						.type(MediaType.TEXT_PLAIN_TYPE)
						.build();
	}

}
