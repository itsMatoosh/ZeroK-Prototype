package me.matoosh.zerok.p2p;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import me.matoosh.zerok.crypto.Hasher;
import net.ricecode.similarity.DescendingSimilarityScoreComparator;
import net.ricecode.similarity.DiceCoefficientStrategy;
import net.ricecode.similarity.JaroStrategy;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

public class NetworkID {
	/** The network id characters. */
	private char[] id;
	
	/** Creates a blank NetworkID. */
	public NetworkID() {
		//Initializing the table.
		id = new char[32];
		
		//Setting all the id fields blank (0).
		for(int i = 0; i < id.length; i++) {
			id[i] = '0';
		}
	}
	/** Creates a NetworkID based on a string source. */
	public NetworkID(String source) {
		//Initializing the table.
		id = new char[32];
		
		//Hashing the source.
		String hash = "00000000000000000000000000000000";
		try {
			hash = Hasher.stringToMd5(source);
		} catch (NoSuchAlgorithmException e) {
			//The hash could not be generated.
			//Making the table empty.
		}
		
		for (int i = 0; i < hash.length(); i++){
		    id[i] = hash.charAt(i);        
		}
	}
	/** Blends the id keeping the specified similarity. */
	public void blend(int difference) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		boolean changesMade = false;
		
		Random r = new Random();
		for (int i = 0; i < id.length; i++){
		    if(r.nextInt(difference) == 1) {
		    	id[i] = alphabet.charAt(r.nextInt(alphabet.length()));	
		    	changesMade = true;
		    }
		}
		
		if(!changesMade) {
			blend(difference);
		}
	}
	/** Calculates similarity between this and the specified NetworkID. */
	public double similarityTo(NetworkID other) {		
		SimilarityStrategy strategy = new JaroStrategy();
		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
		return service.score(this.toString(), other.toString());
	}
	/** Copies value from the specified NetworkID. */
	public void copyFrom(NetworkID id) {
		this.id = id.id.clone();
	}
	/** Checks whether the id is blank. */
	public boolean isBlank() {
		for(char c : id) {
			if(c != '0') {
				return false;
			}
		}
		
		return true;
	}
	/** Returns a string representation of this NetworkID. */
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NetworkID) {
			for(int i = 0; i < 32; i++) {
				if(((NetworkID)obj).id[i] != this.id[i]) {
					return false;
				}
			}
			return true;
		}
		
		return super.equals(obj);
	}
	/** Parses a NetworkID from string. */
	public static NetworkID parse(String networkID) {
		NetworkID id = new NetworkID();
		id.id = networkID.toCharArray();
		return id;
	}
}
