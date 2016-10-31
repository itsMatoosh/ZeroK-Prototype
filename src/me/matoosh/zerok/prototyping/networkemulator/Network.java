package me.matoosh.zerok.prototyping.networkemulator;

import me.matoosh.zerok.p2p.Directory;
import me.matoosh.zerok.p2p.Node;
import me.matoosh.zerok.p2p.Resource;

//The prototyping network class.
public class Network {
	public static void newNode(int id) {
		Node n = new Node(id);
		
		if(id > 1) {
			n.Connect(new int[] {
					id - 1,
					id - 2
			});
		}
	}

	public static void newResource(int fromNode, String name, 
			Directory path, byte[] data) {
		//Getting the node to send the resource from.
		Node originNode = NodeRegistry.getNode(fromNode);
		
		//Sending the resource to its owner.
		originNode.UploadResource(new Resource(path, name, data));
	}
}
