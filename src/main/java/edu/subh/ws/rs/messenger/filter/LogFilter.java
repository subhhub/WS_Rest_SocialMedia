package edu.subh.ws.rs.messenger.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

	static final Logger logger = Logger.getLogger(LogFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		logger.info(" Request Info Log "+requestContext.getHeaders());
		logger.fatal(" Request fatel Log "+requestContext.getHeaders());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		logger.info(" Response Log "+requestContext.getHeaders());
		logger.warn(" Request warn Log "+requestContext.getHeaders());
		
		responseContext.getHeaders().add("X-Powered-By", "SubhPoo");
	}
}
