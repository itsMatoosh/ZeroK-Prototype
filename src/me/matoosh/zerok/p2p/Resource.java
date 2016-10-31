package me.matoosh.zerok.p2p;

public class Resource {

	public String name;
	public Directory path;
	public byte[] data;
	
	public Resource(Directory path, String name, byte[] data) {
		this.name = name;
		this.data = data;
	}
}