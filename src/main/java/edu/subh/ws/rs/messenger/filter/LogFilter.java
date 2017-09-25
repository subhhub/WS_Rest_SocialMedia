package edu.subh.ws.rs.messenger.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		System.out.println(" Request Log "+requestContext.getHeaders());
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		System.out.println(" Response Log "+requestContext.getHeaders());
		
		responseContext.getHeaders().add("X-Powered-By", "SubhPoo");
	}
}
