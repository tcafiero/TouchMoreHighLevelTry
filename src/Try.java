//  File : ./main.java

import java.io.*;
public class Try
{
	// Operations

	public static void main(String[] argv) throws Exception{
		System.loadLibrary("dsp");
		Runtime rt = Runtime.getRuntime();
		dsp.initDSP();
		dsp.setSource(DR_AUDIO_SOURCES.SRC_TUNER);
		dsp.setVolume(30);
		dsp.set_frequency(98100 , TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);		     
		Thread.sleep(10000);
		dsp.setSource(DR_AUDIO_SOURCES.SRC_MP3);
		System.out.println("aplay -D TDM1_O_ENT /home/root/arribba.wav");
		Process pr = rt.exec("aplay -D TDM1_O_ENT /home/root/arribba.wav");
		dsp.setVolume(30);
		BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

		String line=null;
		System.out.println("DR_AUDIO_SOURCES.SRC_MP3");

		while((line=input.readLine()) != null) {
			System.out.println(line);
		}
		int exitVal = pr.waitFor();
		System.out.println("Exited with error code "+exitVal);
		dsp.setSource(DR_AUDIO_SOURCES.SRC_TUNER);
		dsp.setVolume(30);
		dsp.set_frequency(98100 , TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);		     
	}
}
