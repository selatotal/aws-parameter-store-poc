package br.com.selat.awsparameterstorepoc.impl.rest.v1;

import br.com.selat.awsparameterstorepoc.contract.v1.ParameterOutput;
import br.com.selat.awsparameterstorepoc.contract.v1.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("")
public class ParameterEndpoint {

    private final ParameterService service;

    @Autowired
    public ParameterEndpoint(ParameterService service) {
        this.service = service;
    }

    @GET
    @Path("parameter/{parameterPath: .+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("parameterPath") String parameterPath){
        Optional<ParameterOutput> output = service.get("/" + parameterPath);
        if (output.isPresent()){
            return Response.ok().entity(output.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("path/{parameterPath: .+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPath(@PathParam("parameterPath") String parameterPath){
        List<ParameterOutput> output = service.getPath("/" + parameterPath);
        return Response.ok().entity(output).build();
    }

}
