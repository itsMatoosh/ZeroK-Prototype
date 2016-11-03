package me.matoosh.zerok.prototyping.networkemulator;

import java.util.Random;
import java.util.UUID;

import me.matoosh.zerok.p2p.Directory;
import me.matoosh.zerok.p2p.Node;
import me.matoosh.zerok.p2p.Resource;

//The prototyping network class.
public class Network {
	
	//Node creation logic only for prototyping purposes.
	//Normally nodes are created by peers joining the network.
	public static void newNode() {
		Node n = new Node();
		Random r = new Random();
		
		if(NodeRegistry.registeredNodes.size() > 1) {
			n.Connect(new UUID[] {
					NodeRegistry.getNode(r.nextInt(NodeRegistry.registeredNodes.size() - 1)).id,
					NodeRegistry.getNode(r.nextInt(NodeRegistry.registeredNodes.size() - 1)).id
			});
		}
	}

	//Resource creation logic only for prototyping purposes.
	//Normally resources are uploaded by peers within the network.
	public static void newResource(int fromNode, String name, 
			Directory path, byte[] data) {
		//Getting the node to send the resource from.
		Node originNode = NodeRegistry.getNode(fromNode);
		
		//Sending the resource to its owner.
		originNode.UploadResource(new Resource(path, name, data));
	}
}
