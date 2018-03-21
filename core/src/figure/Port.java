package figure;

import playing_field.RESOURCE;

public class Port {

	private RESOURCE resource;
	
	public Port() {
		this.resource = RESOURCE.SAND;
	}
	
	public Port(RESOURCE resource) {
		this.resource = resource;
	}
	
	public RESOURCE getResource() {
		return this.resource;
	}
	
	public String toString() {
		return this.getResource().toString();
	}
}
