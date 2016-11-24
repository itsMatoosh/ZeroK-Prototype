package me.matoosh.zerok.prototyping.networkemulator;

import java.util.Random;

import me.matoosh.zerok.p2p.Directory;
import me.matoosh.zerok.p2p.NetworkID;
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
			//Determining how many nodes the new node should connect to.
			int connectTo = r.nextInt(NodeRegistry.registeredNodes.size() - 1);
			if(connectTo > 7) {
				connectTo = 7; //noone has that many friends.
			}
			
			for(int i = 0; i <= 7; i++) {
				n.Connect(NodeRegistry.getNode(r.nextInt(NodeRegistry.registeredNodes.size() - 1)).id);
			}		
		}
	}

	//Resource creation logic only for prototyping purposes.
	//Normally resources are uploaded by peers within the network.
	public static void newResource(NetworkID id, String name, 
			Directory path, byte[] data) {
		//Getting the node to send the resource from.
		Node originNode = NodeRegistry.getNode(id);
		
		//Sending the resource to its owner.
		originNode.UploadResource(new Resource(path, name, data));
	}
	
	//Sends a resource to a specific Node.
	public static void routeTo(Node receiver, Resource res) {
		receiver.OnResourceRoute(res);
	}
}
