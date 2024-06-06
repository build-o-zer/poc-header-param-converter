package org.juandfx.header_param_poc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


/**
 * RequestHelper : this class is used to get the header values from the request from any class (Service, Controller, etc).
 * 
 */
@Slf4j
@UtilityClass
public class RequestHelper {

    public static List<String> getHeader(String headerName) {
        log.info("Calling RequestHelper");
        Optional <RequestAttributes> attributes = Optional.ofNullable(RequestContextHolder.getRequestAttributes());
        return Collections.list(attributes.map(RequestHelper::getRequest).get().getHeaders(headerName));
    }

    /**
     * Get the request from the RequestAttributes.
     * This method is used to get the HttpServletRequest from the RequestAttributes and easy the access to the headers.
     * 
     * @param attributes RequestAttributes from the Spring RequestContextHolder
     * @return an instance of HttpServletRequest
     */
    private static HttpServletRequest getRequest(RequestAttributes attributes) {
            return ((ServletRequestAttributes) attributes).getRequest(); 
    }
}
