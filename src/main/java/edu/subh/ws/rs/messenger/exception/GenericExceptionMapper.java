package edu.subh.ws.rs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.subh.ws.rs.messenger.model.ErrorMessage;

//@Provider			//by commenting exception  has disabled
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {

		ErrorMessage em = new ErrorMessage(ex.getMessage(), 500,"http://localhost:1010/WS_Rest_SocialMedia");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(em)
				.build();
	}
}
