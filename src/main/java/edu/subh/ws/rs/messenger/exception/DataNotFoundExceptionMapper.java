package edu.subh.ws.rs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import edu.subh.ws.rs.messenger.model.ErrorMessage;

//@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {

		ErrorMessage em = new ErrorMessage(ex.getMessage(), 404,"http://localhost:1010/WS_Rest_SocialMedia");
		return Response.status(Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
