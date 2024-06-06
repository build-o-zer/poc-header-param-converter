package org.juandfx.header_param_poc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Named
@Path("/endpoint")
public class ApplicationController {

    @Inject
    private BusinessService businessService;

    @GET
    @Produces("text/plain")
    public Response getHelloWord(@HeaderParam("IDFM-IVTR-HET-Max-Age") @DefaultValue("PT3H") Duration duration) 
    {
        long durationInMilliseconds = duration.toMillis();
        String result = businessService.extractData(durationInMilliseconds);
        String response = String.format("Here is the result from the businessService : %s (generated at %s)", result, LocalDateTime.now());
        return Response.ok(response).build();
    }

}
