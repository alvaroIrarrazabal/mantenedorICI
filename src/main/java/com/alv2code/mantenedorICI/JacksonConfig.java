package com.alv2code.mantenedorICI;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;

@Configuration
public class JacksonConfig {
	
	  @Bean
	    public Module hibernateModule() {
	        return new Hibernate5Module();
	    }
}
