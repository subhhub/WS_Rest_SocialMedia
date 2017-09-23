package edu.subh.ws.rs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.subh.ws.rs.messenger.model.Profile;

public class RestClientSocialMedia {

	public static void main(String[] args) {
		System.out.println("JAX RS Client ");
		
//		Client client = ClientBuilder.newClient();
//		String url = "http://localhost:1010/WS_Rest_SocialMedia/webapi/profiles";
//		Response response = client.target(url).request().get();
//		Profile profile = response.readEntity(Profile.class);
//		System.out.println(profile.getLastName());
		
//		getReqeuestClient();
//		getGoodRequestClient();
		
		postRequestClient();
		
//		Invocation invocation = prepareRequestForMessagesByYear(2017);
//		Response invoke = invocation.invoke();
//		System.out.println(invoke.getStatus());
	}

	private static Invocation prepareRequestForMessagesByYear(int year) {
		Client client = ClientBuilder.newClient();
		
		String baseUrl = "http://localhost:1010/WS_Rest_SocialMedia/webapi";
		
		//Builder Design Pattern
		return client.target(baseUrl)
				.path("messages")
				.queryParam("year", year)
				.request(MediaType.APPLICATION_JSON)
				.buildGet();
	}

	private static void postRequestClient() {
		Client client = ClientBuilder.newClient();
		
		String baseUrl = "http://localhost:1010/WS_Rest_SocialMedia/webapi";
		WebTarget target = client.target(baseUrl);
		WebTarget pathProfile = target.path("profiles");
		WebTarget singleProfileTarget = pathProfile.path("{profileName}");
		
		Profile profile = new Profile(40, "postPro0", "FirstPost0", "FirstLast0");
		
		Response post = pathProfile.request().post(Entity.json(profile));
//		System.out.println(post);
		
		Profile readEntity = post.readEntity(Profile.class);
		System.out.println(readEntity.getProfileName());
	}

	static void getGoodRequestClient(){
		Client client = ClientBuilder.newClient();

		String baseUrl = "http://localhost:1010/WS_Rest_SocialMedia/webapi";
		WebTarget target = client.target(baseUrl);
		WebTarget pathProfile = target.path("profiles");
//		WebTarget singleProfileTarget = pathProfile.path("2");				//hardcoded not recommanded
		WebTarget singleProfileTarget = pathProfile.path("{profileName}");
		
		String profile1 =singleProfileTarget.resolveTemplate("profileName", "subh")
				.request(MediaType.APPLICATION_XML)
				.get(String.class);

		String profile2 =singleProfileTarget.resolveTemplate("profileName", "subhKewat")
				.request(MediaType.APPLICATION_XML)
				.get(String.class);
		
		System.out.println(profile1);
		System.out.println(profile2);
	}

	static void getReqeuestClient(){
		Client client = ClientBuilder.newClient();
		
		String url = "http://localhost:1010/WS_Rest_SocialMedia/webapi/profiles";
//		Response response = client.target(url).request().get();
		
		/*Response response = client
				.target(url)
				.request(MediaType.APPLICATION_JSON)
				.get();
		Profile readEntity = response.readEntity(Profile.class);*/
//		System.out.println(readEntity.getProfileName());

		/*Profile profile = client				//can get only single object
				.target(url)
				.request(MediaType.APPLICATION_JSON)
				.get(Profile.class);
		System.out.println(profile.getProfileName());*/
		
		String profile = client				//By using string we can get complete collection response.
				.target(url)
				.request(MediaType.APPLICATION_XML)
				.get(String.class);
		System.out.println(profile);
	}
	
}
