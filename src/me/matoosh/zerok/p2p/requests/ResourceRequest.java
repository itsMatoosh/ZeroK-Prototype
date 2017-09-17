package me.matoosh.zerok.p2p.requests;

import me.matoosh.zerok.p2p.NetworkID;

/** A resource routing request. */
public class ResourceRequest {
	/** ID of the requested resource. */
	public NetworkID resourceID;
	/** ID of the requesting node. */
	public NetworkID receiverID;
	
	public ResourceRequest(NetworkID resourceID) {
		this.resourceID = resourceID;
	}
}
