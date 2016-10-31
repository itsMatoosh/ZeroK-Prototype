package me.matoosh.zerok;

import java.util.Random;

import me.matoosh.zerok.p2p.Directory;
import me.matoosh.zerok.p2p.Node;
import me.matoosh.zerok.prototyping.CommandProcessor;
import me.matoosh.zerok.prototyping.networkemulator.Network;
import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class ZeroK {

	public static boolean shouldStop = false;
	public static int nodesNum = 25;
	
	public static void main(String[] args) {
		System.out.println("Starting the ZeroK prototype...");
		
		//Spawning nodes into the network.
		for(int i = 0; i <= nodesNum - 1; i++) {
			Network.newNode(i);
		}
		
		//Spawning 50 resources into the network.
		for(int x = 0; x <= 50; x++) {
			Random r = new Random();
			byte[] b = new byte[20];
			r.nextBytes(b);
			Network.newResource(r.nextInt(nodesNum - 1), "resource-" + x, new Directory("prototype"), b);
		}
		
		//Printing all of the available nodes.
		for(Node registeredNode : NodeRegistry.registeredNodes) {
			System.out.println("Node: " + registeredNode.id + " is connected to: ");
			for(Node connected : registeredNode.connectedTo) {
				System.out.println(connected.id);
			}
		}
		
		//Starting the main loop.
		mainLoop();
	}
	
	private static void mainLoop() {
		while(!shouldStop) {
			CommandProcessor.Process();
		}
	}
}
