package me.matoosh.zerok.p2p;

import me.matoosh.zerok.prototyping.networkemulator.ResourceRegistry;

public class Resource extends NetworkMember {

	public String name;
	public Directory dir;
	public byte[] data;
	
	public Resource(Directory dir, String name, byte[] data) {
		//Setting the basic attributes.
		this.name = name;
		this.dir = dir;
		this.data = data;
		
		//Calculating the file id.
		this.id = new NetworkID(dir.path + "/" + name);
		
		ResourceRegistry.registerResource(this);
	}
}