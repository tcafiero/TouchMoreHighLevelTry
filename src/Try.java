//  File : ./main.java

import java.io.*;
import java.util.*;
public class Try
{
	// Operations

	public static void main(String[] argv) throws Exception{
		System.loadLibrary("dsp");
		Runtime rt = Runtime.getRuntime();
		boolean exit = false;
		Scanner s = new Scanner(System.in);
		dsp.initDSP();
		dsp.setVolume(25);
			System.out.print("r: Radio");
			System.out.print("v: Volume");
			System.out.print("+: Increase Volume");
			System.out.print("-: Decrease Volume");
			System.out.print("s: Play sinusoid");
			System.out.print("e: Exit");
		while(!exit) 
		{ 
			System.out.print("Command: ");
			String command = s.next();
			switch (command) {
			case "e" :
				exit=true;
				break;
			case "r" :
				dsp.setSource(DR_AUDIO_SOURCES.SRC_TUNER);
				dsp.doMute();
				System.out.print("Frequency: ");
				Integer frequency = s.nextInt();
				System.out.println("radio Frequency: "+frequency);
				dsp.set_frequency(frequency , TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);
				dsp.doDeMute();
				break;	    		
			case "v" :
				System.out.print("Volume: ");
				Integer volume = s.nextInt();
				System.out.println("Volume: "+volume);
				dsp.setVolume(volume);
				break;	    		
			case "+" :
				System.out.print("+");
				dsp.increase_Volume();
				break;
			case "-" :
				System.out.print("-");
				dsp.decrease_Volume();
				break;
			case "mp3" :
				dsp.setSource(DR_AUDIO_SOURCES.SRC_MP3);
				System.out.println("aplay -D TDM1_O_ENT /home/root/arribba.wav");
				Process pr = rt.exec("aplay -D TDM1_O_ENT /home/root/arribba.wav");
				dsp.setVolume(25);
			case "s" :
				System.out.println("Sinusoid");
				dsp.playSin(25, 440, true);
				break;
			}
		}
		// edit, lets not hog any cpu time 
		try 
		{ 
			Thread.sleep(50); 
		} 
		catch (InterruptedException ex) 
		{ 
			// can't do much about it can we? Ignoring  
		}
		s.close();
	}
}
