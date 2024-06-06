package org.juandfx.header_param_poc;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    public String extractData(long duration)
    {
        return "Data extracted for the last " + duration + " milliseconds";
    }

}
