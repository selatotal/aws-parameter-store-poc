package br.com.selat.awsparameterstorepoc.impl.rest.config;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ContractStructureExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

	@Override
	public Response toResponse(UnrecognizedPropertyException exception) {
		return Response
						.status(Response.Status.BAD_REQUEST)
						.entity("Contract structure is invalid. An unsupported field was sent on the request. ")
						.type(MediaType.TEXT_PLAIN_TYPE)
						.build();
	}

}
