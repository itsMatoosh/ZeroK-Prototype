package me.matoosh.zerok.p2p;

public abstract class NetworkMember {
	//Path of the member within the network.
	public NetworkID id;
	
	//Calculates the percent similarity to the specified NetworkMember.
	public double similarityTo(NetworkMember nm) {
		return id.similarityTo(nm.id);
	}
}
