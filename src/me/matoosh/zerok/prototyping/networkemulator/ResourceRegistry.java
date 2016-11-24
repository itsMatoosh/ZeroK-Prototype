package me.matoosh.zerok.prototyping.networkemulator;

import java.util.ArrayList;

import me.matoosh.zerok.p2p.NetworkID;
import me.matoosh.zerok.p2p.Resource;

//Prototyping class to register all of the nodes available within the network.
public class ResourceRegistry {
	public static ArrayList<Resource> registeredResources = new ArrayList<Resource>();
	
	//Registers a new resource.
	public static void registerResource(Resource r) {
		registeredResources.add(r);
	}
	
	//Gets the specified resource by id.
	public static Resource getResource(NetworkID id) {
		for(Resource registered : registeredResources) {
			if(registered.id.equals(id)) {
				return registered;
			}
		}
		return null;
	}
}
