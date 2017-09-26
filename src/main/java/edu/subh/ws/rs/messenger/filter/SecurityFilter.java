package edu.subh.ws.rs.messenger.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	static final Logger logger = Logger.getLogger(LogFilter.class);
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic"; 
	private static final String SECURED_URI_PREFIX = "secured"; 
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		logger.info("Inside Security Scanning ");
		
		if(requestContext.getUriInfo().getPath().contains(SECURED_URI_PREFIX)){	//only secure single resource
			
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if(authHeader!=null && !authHeader.isEmpty()){
				logger.info("===========> "+authHeader);
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				
				String decodedString = Base64.decodeAsString(authToken);
//				String decodedString = org.glassfish.jersey.internal.util.Base64.decodeAsString(authToken);
//				String decodedString = Base64.encodeBase64String(authToken);
				
				logger.info("===========> "+decodedString);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String uname = tokenizer.nextToken();
				String pass = tokenizer.nextToken();
				
				
				if("subh".equalsIgnoreCase(uname) && "subh".equalsIgnoreCase(pass)){
					return;
				}
			}
			Response unAuthorizationStatus = Response
												.status(Response.Status.UNAUTHORIZED)
												.entity("User Cann't access the resource")
												.build();
			
			requestContext.abortWith(unAuthorizationStatus);
		}
	}
}
