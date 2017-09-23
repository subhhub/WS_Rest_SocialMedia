package edu.subh.ws.rs.messenger.resource;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("image")
public class ImageResource {

	private static final String FILE_PATH = "C:\\Subh\\doc\\estudio\\JAXRSFiltersReqResp.JPG";

	@GET
	@Produces("image/jpg")
	public Response getFile() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
//		response.header("Content-Disposition", "attachment; filename=image_from_server.jpg");
		return response.build();
	}
}

