package me.matoosh.zerok.p2p;

import java.util.ArrayList;

import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class Node {
	public int id;
	public ArrayList<Node> connectedTo = new ArrayList<Node>();
	
	//Creates a new node.
	public Node(int id) {
		this.id = id;
		
		//Registering the node within the prototyping "network".
		NodeRegistry.registerNode(this);
	}
	
	//Connects to each of the provided nodes.
	public void Connect(int[] nodes) {
		//Storing the connected nodes.
		for(int i = 0; i < nodes.length; i++) {
			//Getting info about the node.
			Node n = NodeRegistry.getNode(i);
			connectedTo.add(n);
		}
	}

	public void UploadResource(Resource resource) {
		//Sending the resource to the Node with the root catalog
		
	}
}
