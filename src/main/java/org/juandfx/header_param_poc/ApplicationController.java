package org.juandfx.header_param_poc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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
import jakarta.xml.bind.annotation.XmlElement.DEFAULT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Named
@Path("/endpoint")
@Slf4j
public class ApplicationController {

    public static final String DEFAULT_MAX_DURATION = "PT3H";
    public static final String DEFAULT_MIN_DURATION = "PT30M";
    public static final Duration maxDuration = Duration.parse(DEFAULT_MAX_DURATION);
    public static final Duration minDuration = Duration.parse(DEFAULT_MIN_DURATION);

    @Inject
    private BusinessService businessService;

    @GET
    @Produces("text/plain")
    public Response endpoint(@HeaderParam("IDFM-IVTR-HET-Max-Age") @DefaultValue(DEFAULT_MAX_DURATION) Duration duration) 
    {
        Duration boundedDuration = getBoundedDuration(duration);
        long timeStam = getTimeMillisFromNow(boundedDuration);
        String result = businessService.extractData(timeStam);
        String response = String.format("Here is the result from the businessService : %s (generated at %s)", result, LocalDateTime.now());
        return Response.ok(response).build();
    }

    public static Duration getBoundedDuration(Duration duration) {
        Duration boundedDuration = duration.compareTo(maxDuration) < 0 ? duration : maxDuration;
        return minDuration.compareTo(boundedDuration) > 0 ? minDuration : boundedDuration;
    }

    public static long getTimeMillisFromNow(Duration duration) {
        return ZonedDateTime.now().minus(duration).toInstant().toEpochMilli();
    }

}
