package me.matoosh.zerok.prototyping.networkemulator;

import java.util.ArrayList;
import java.util.UUID;

import me.matoosh.zerok.p2p.Node;

//Prototyping class to register all of the nodes available within the network.
public class NodeRegistry {
	public static ArrayList<Node> registeredNodes = new ArrayList<Node>();
	
	public static void registerNode(Node n) {
		registeredNodes.add(n);
	}
	
	//Gets the stored node with specific id.
	//Returns null if the node was not found.
	public static Node getNode(UUID id) {
		for(Node n : registeredNodes) {
			if(n.id == id) {
				return n;
			}
		}
		return null;
	}
	public static Node getNode(int id) {
		for(int i = 0; i < registeredNodes.size(); i++) {
			if(i == id) {
				return registeredNodes.get(i);
			}
		}
		return null;
	}
	
	//Generates a new UUID unique throughout the network.
	public static UUID newUniqueNodeID() {
		return UUID.randomUUID();
	}
}
