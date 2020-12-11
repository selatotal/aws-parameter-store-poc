package br.com.selat.awsparameterstorepoc.impl.rest.v1;

import br.com.selat.awsparameterstorepoc.contract.v1.HealthOutput;
import br.com.selat.awsparameterstorepoc.contract.v1.HealthService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("health")
public class HealthEndpoint {

    private final HealthService service;

    @Autowired
    public HealthEndpoint(HealthService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response health(){
        Optional<HealthOutput> output = service.health();
        if (output.isPresent()){
            return Response.status(output.get().getStatus()).entity(output.get()).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"status\": \"500\"}").build();
        }
    }

}
