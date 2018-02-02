package edu.subh.ws.rs.messenger.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotation")
	public String getParamUsingAnnotation(@MatrixParam ("param") String matrixParam,
											@HeaderParam ("customHeader") String custHeader,		//custom header can be used for authentication/security
											@CookieParam ("name") String ckiName){		
		return "Hello Annotation "+matrixParam +" Header "+custHeader +" Cookie "+ ckiName;
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		
		String uri = uriInfo.getAbsolutePath().toString();
		String hdr = headers.getCookies().toString();
		
		MultivaluedMap<String, String> qryParam  = uriInfo.getQueryParameters();
		MultivaluedMap<String, String> pathParam  = uriInfo.getPathParameters();
		
		return "Context TEst "+uri+" "+hdr;
//		return "Context TEst "+qryParam;
	}
}
