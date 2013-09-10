//  File : ./main.java

import java.io.*;
import java.util.*;
public class Try
{
	// Operations

	public static void main(String[] argv) throws Exception{
		System.loadLibrary("dsp");
		dsp.initDSP();
		Infotainment device = new Infotainment();
		Scanner input = new Scanner(System.in);
		System.out.println("r: Radio");
		System.out.println("<integer 0-30>: Volume");
		System.out.println("+: Increase Volume");
		System.out.println("-: Decrease Volume");
		System.out.println("s: Play sinusoid");
		System.out.println("mp3: Play MP3 file");
		System.out.println("x: Exit");
		while(!device.Exit) 
		{ 
			System.out.print("input: ");
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
