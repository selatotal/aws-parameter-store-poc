package br.com.selat.awsparameterstorepoc.impl.rest.config;

import br.com.selat.exceptions.ServiceValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationErrorExceptionMapper implements ExceptionMapper<ServiceValidationException> {
	private static final Logger logger = LoggerFactory.getLogger(ValidationErrorExceptionMapper.class);

	public Response toResponse(ServiceValidationException exception) {
		logger.debug("Validation Error : {}", exception.getMessage());
		return Response
						.status(Response.Status.BAD_REQUEST)
						.entity(exception.getMessage())
						.type(MediaType.TEXT_PLAIN_TYPE)
						.build();
	}

}
