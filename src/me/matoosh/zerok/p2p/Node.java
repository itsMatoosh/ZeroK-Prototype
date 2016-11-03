package me.matoosh.zerok.p2p;

import java.util.ArrayList;
import java.util.UUID;

import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class Node {
	public UUID id;
	public ArrayList<Node> connectedTo = new ArrayList<Node>();
	
	//Constructor automatically registering the node within the network.
	public Node() {
		//Assigning an id.
		id = UUID.randomUUID();
		
		//Registering to the NodeRegistry.
		NodeRegistry.registerNode(this);
		
		//Debug
		//System.out.println("New Node registered: " + id);
	}
	
	//Connects to each of the provided nodes.
	public void Connect(UUID[] ids) {
		//Storing the connected nodes.
		for(UUID id : ids) {
			//Getting info about the node.
			Node n = NodeRegistry.getNode(id);
			
			if(n == null) {
				return;
			}
			
			//Connecting TODO: Actual connecting logic
			connectedTo.add(n);
			
			//Calling the OnConnection event on the node.
			n.OnConnection(NodeRegistry.newUniqueNodeID());
		}
	}

	//Called when a node connects to this one 
	public void OnConnection(UUID assignedUUID) {
		//Caching the assigned UUID.
		this.id = assignedUUID;
	}
	
	public void UploadResource(Resource resource) {
		//Getting the destination Node id.
		
		
		//Sending the resource to the Node with the root catalog
		
	}
}
