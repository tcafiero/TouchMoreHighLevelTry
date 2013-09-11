//  File : ./main.java

import java.io.*;
import java.util.*;
public class ControlPanel
{
	// Operations

	public static void main(String[] argv) throws Exception{
		System.loadLibrary("dsp");
		dsp.initDSP();
		Infotainment device = new Infotainment();
		Scanner input = new Scanner(System.in);
		System.out.println("r: Radio");
		System.out.println("v: Volume");
		System.out.println("m: Toggle Mute");
		System.out.println("+: Increase Volume");
		System.out.println("-: Decrease Volume");
		System.out.println("s: Play sinusoid");
		System.out.println("mp3: Play MP3 file");
		System.out.println("TAstart: Traffic Announcement Start");
		System.out.println("TAend: Traffic Announcement End");
		System.out.println("x: Exit");
		while(!device.Exit) 
		{ 
			System.out.print("> ");
			if (input.hasNextInt())
				device.UseValue(input.nextInt());
			else device.Command(input.next());
			try 
			{ 
				Thread.sleep(50); 
			} 
			catch (InterruptedException ex) 
			{ 
				// can't do much about it can we? Ignoring  
			}
		}
		input.close();
	}
}
