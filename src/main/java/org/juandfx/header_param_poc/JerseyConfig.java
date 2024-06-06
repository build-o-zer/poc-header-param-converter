package org.juandfx.header_param_poc;

import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
        log.info("JerseyConfig constructor called");
		register(ApplicationController.class);
        register(DurationConverterProvider.class);
	}

}