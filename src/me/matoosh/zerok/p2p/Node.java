package me.matoosh.zerok.p2p;

import java.util.ArrayList;

import me.matoosh.zerok.p2p.requests.ResourceRequest;
import me.matoosh.zerok.prototyping.networkemulator.NodeRegistry;

public class Node extends NetworkMember{
	public ArrayList<Node> connectedTo = new ArrayList<Node>();
	public ArrayList<Resource> storedResources = new ArrayList<Resource>();
	
	/** Constructor automatically registering the node within the network. */
	public Node() {
		//Assigning an id.
		id = new NetworkID();
		id.blend(2);
		
		//Registering to the NodeRegistry.
		NodeRegistry.registerNode(this);
		
		//Debug
		//System.out.println("New Node registered: " + id);
	}
	
	/** Connects to each of the provided nodes. */
	public void Connect(NetworkID id) {
		//Getting info about the node.
		Node n = NodeRegistry.getNode(id);
		
		if(n == null || connectedTo.contains(n)) {
			return;
		}
		
		//If our id is blank, generate a new one based on the connection.
		if(this.id.isBlank()) {
			this.id.copyFrom(id);
			this.id.blend(10); //Make our id 10% different from the other node.
		}
		
		//Connecting TODO: Actual connecting logic
		n.connectedTo.add(this);
		this.connectedTo.add(n);
	}
	
	/** Uploads a resource into the network. */
	public void UploadResource(Resource resource) {
		//Sending resource to the neighbor node.
		RouteTo(resource).OnResourceRoute(resource);
	}
	/** Requests a resource from the network. */
	public void RequestResource(NetworkID id) {
		ResourceRequest req = new ResourceRequest(id);
		req.receiverID = this.id;
		//Sending the request to the closest neighbor.
		RouteToReq(req).OnResourceRequestRoute(req);
	}
	
	/** Called when a Resource request is received. */
	public void OnResourceRequestRoute(ResourceRequest req) {
		//Getting the next node to pass the resource to.
		Node next = RouteToReq(req);
		
		//Checking whether the resource have already arrived at it's destination.
		if(next == this) {
			OnResourceRequestReceived(req);
		} else {
			System.out.println(this.id + " routing resource request: " + id + " to node: " + RouteToReq(req).id);
			//Passing the resource on.
			RouteToReq(req).OnResourceRequestRoute(req);
		}
	}
	/** Called when a resource request has been received. */
	public void OnResourceRequestReceived(ResourceRequest req) {
		//Debugging.
		System.out.println("Node: " + this.id + " has resource: " + req.resourceID + " requested by: " + req.receiverID);
	}
	//Called when a resource is being routed from one of the neighbors.
	public void OnResourceRoute (Resource resource) {
		//Getting the next node to pass the resource to.
		Node next = RouteTo(resource);
		
		//Checking whether the resource have already arrived at it's destination.
		if(next.equals(this)) {
			System.out.println("Owner is " + this.id + " connected to: " + this.connectedTo.size() + " with similarity: " + this.similarityTo(resource));
			OnResourceReceived(resource);
		} else {
			System.out.println(id + " routing resource: " + resource.name + ":" + resource.id + " to node: " + RouteTo(resource).id);
			//Passing the resource on.
			RouteTo(resource).OnResourceRoute(resource);
		}
	}
	
	//Called when a resource has been received from one of the neighbors.
	public void OnResourceReceived(Resource resource) {
		//Storing the received resource.
		storedResources.add(resource);
		
		//Debugging.
		System.out.println("Node: " + id + " received resource: " + resource.id);
	}
	
	/** Finds the next appropriate Node to route the resource owner. */
	private Node RouteTo(Resource res) {
		//Finding the most appropriate neighbor node to route the resource to.
		//Caching the smallest difference between NetworkIDs.
		double biggestSimilarity = this.similarityTo(res);
		Node closest = this;
		//System.out.println(this.id + " self-sim to " + res.id + " : " + biggestSimilarity);
		//System.out.println(this.id + " connected to " + connectedTo.size());
		
		for(Node node : connectedTo) {
			double sim = node.similarityTo(res);
			
			if(sim > biggestSimilarity) {
				//System.out.println(node.id + " sim to " + res.id+ " : " + sim);
				biggestSimilarity = sim;
				closest = node;
			}
		}
		
		//Checking whether the resource needs to be sent to a different node.
		return closest;
	}
	/** Finds the next appropriate Node to route the resource owner. */
	private Node RouteToReq(ResourceRequest req) {
		//Finding the most appropriate neighbor node to route the resource to.
		//Caching the smallest difference between NetworkIDs.
		double biggestSimilarity = this.id.similarityTo(req.resourceID);
		Node closest = null;
		//System.out.println(this.id + " self-sim to " + res.id + " : " + biggestSimilarity);
		//System.out.println(this.id + " connected to " + connectedTo.size());
		
		for(Node node : connectedTo) {
			double sim = node.id.similarityTo(req.resourceID);
			
			if(sim > biggestSimilarity) {
				//System.out.println(node.id + " sim to " + res.id+ " : " + sim);
				biggestSimilarity = sim;
				closest = node;
			}
		}
		
		//Checking whether the resource needs to be sent to a different node.
		if(closest != null) {
			return closest;
		} else {
			return this;
		}
	}
	/** Finds the next appropriate Node to route to the specified Node. */
	private Node RouteTo(Node node) {
		//Finding the most appropriate neighbor to route to.
		//Checking the difference between all the neighbors.
		double biggestSim = 0;
		Node closest = null;
		for(Node n : connectedTo) {
			double sim = n.similarityTo(node);
			
			if(sim > biggestSim) {
				biggestSim = sim;
				closest = n;
			}
		}
		
		return closest;
	}
}
