package edu.subh.ws.rs.messenger.model;

public class Link {		//no need to create annotate with root element because it will be member element of message

	private String link;
	private String rel;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
}
