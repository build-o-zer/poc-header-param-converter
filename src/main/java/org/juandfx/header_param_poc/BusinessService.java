package org.juandfx.header_param_poc;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BusinessService {

    public String extractData(long duration)
    {
        log.info("Header Params : {}", RequestHelper.getHeader("IDFM-IVTR-HET-Max-Age"));
        return "Data extracted for the last " + duration + " milliseconds";
    }

}
