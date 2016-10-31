package me.matoosh.zerok.prototyping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.matoosh.zerok.ZeroK;

public class CommandProcessor {

	public static BufferedReader br;
	public static boolean init = false;
	
	public static void Process(String readLine) {
		//Writing the command into the console.
		System.out.println(readLine);
		
		switch(readLine) {
		case "stop":
			ZeroK.shouldStop = true;
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
