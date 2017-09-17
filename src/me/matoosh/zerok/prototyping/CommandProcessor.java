package me.matoosh.zerok.prototyping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import me.matoosh.zerok.ZeroK;
import me.matoosh.zerok.p2p.NetworkID;
import me.matoosh.zerok.p2p.Node;
import me.matoosh.zerok.p2p.Resource;
import me.matoosh.zerok.p2p.requests.ResourceRequest;
import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;
import me.matoosh.zerok.prototyping.networkemulator.ResourceRegistry;

public class CommandProcessor {

	public static BufferedReader br;
	public static boolean init = false;
	
	public static void Process(String readLine) {
		//Writing the command into the console.
		System.out.println("> " + readLine);
		String[] command = readLine.split(" ");
		
		switch(command[0]) {
		case "stop":
			ZeroK.shouldStop = true;
			break;
		case "count":
			System.out.println(NodeRegistry.registeredNodes.size());
			break;
		case "connections":
			//Printing all of the made connections.
			for(Node registeredNode : NodeRegistry.registeredNodes) {
				System.out.println("Node: " + registeredNode.id + " is connected to: ");
				for(Node connected : registeredNode.connectedTo) {
					System.out.println(connected.id);
				}
			}
			break;
		case "nodes":
			//Printing all of the available nodes.
			for(Node registeredNode : NodeRegistry.registeredNodes) {
				System.out.println("Node: " + registeredNode.id);
			}
			break;
		case "resources":
			//Printing all of the available resources.
			for(Node registeredNode : NodeRegistry.registeredNodes) {
				for(Resource r : registeredNode.storedResources) {
					System.out.println("Resource " + r.id + " stored in: " + registeredNode.id);
				}
			}
			break;
		case "owner":
			//Calculating the owner for a resource.
			Resource r = ResourceRegistry.getResource(NetworkID.parse(command [1]));

			NodeRegistry.getNode(new Random().nextInt(NodeRegistry.registeredNodes.size() - 1)).UploadResource(r);
			break;
		case "download":
			//Requesting a resource to a node and displaying the details.
			NodeRegistry.getNode(0).RequestResource(NetworkID.parse(command[1]));
			break;
		}
	}

	public static void Process() {
		if(br == null) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		try {
			Process(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
