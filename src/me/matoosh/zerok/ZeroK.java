package me.matoosh.zerok;

import java.util.Random;

import me.matoosh.zerok.p2p.Directory;
import me.matoosh.zerok.prototyping.CommandProcessor;
import me.matoosh.zerok.prototyping.networkemulator.Network;
import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class ZeroK {

	public static boolean shouldStop = false;
	public static int nodesNum = 30000;
	private static int resourceNum = 4000;
	private static int resourceSize = 50;
	
	public static void main(String[] args) {
		System.out.println("Starting the ZeroK prototype...");
		
		//Spawning nodes into the network.
		for(int i = 0; i < nodesNum; i++) {
			Network.newNode();
		}
		
		//Spawning 50 resources into the network.
		for(int x = 0; x < resourceNum; x++) {
			Random r = new Random();
			byte[] b = new byte[resourceSize];
			r.nextBytes(b);
			
			Network.newResource(NodeRegistry.getNode(0).id, "resource-" + x, new Directory("prototype"), b);
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