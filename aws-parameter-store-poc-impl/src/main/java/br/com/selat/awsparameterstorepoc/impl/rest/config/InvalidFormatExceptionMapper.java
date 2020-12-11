package br.com.selat.awsparameterstorepoc.impl.rest.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.lang.String.format;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

	@Override
	public Response toResponse(InvalidFormatException exception) {
		return Response
						.status(Response.Status.BAD_REQUEST)
						.entity(format("Invalid value [%s] for field [%s] of type [%s]", exception.getValue().toString(), exception.getPath().get(exception.getPath().size()-1).getFieldName(), exception.getTargetType().getSimpleName()))
						.type(MediaType.TEXT_PLAIN_TYPE)
						.build();
	}

}
