package me.matoosh.zerok.prototyping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.matoosh.zerok.ZeroK;
import me.matoosh.zerok.p2p.Node;
import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class CommandProcessor {

	public static BufferedReader br;
	public static boolean init = false;
	
	public static void Process(String readLine) {
		//Writing the command into the console.
		System.out.println("> " + readLine);
		
		switch(readLine) {
		case "stop":
			ZeroK.shouldStop = true;
			break;
		case "count":
			System.out.println(NodeRegistry.registeredNodes.size());
			break;
		case "nodes":
			//Printing all of the available nodes.
			for(Node registeredNode : NodeRegistry.registeredNodes) {
				System.out.println("Node: " + registeredNode.id + " is connected to: ");
				for(Node connected : registeredNode.connectedTo) {
					System.out.println(connected.id);
				}
			}
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
