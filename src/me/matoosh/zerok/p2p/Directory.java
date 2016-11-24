package me.matoosh.zerok.p2p;

//Represents a directory within the network.
public class Directory {
	public Directory(String path) {
		this.path = path;
	}
	public String path;
	
	public String[] getHierarchy() {
		return path.split("/");
	}
}
