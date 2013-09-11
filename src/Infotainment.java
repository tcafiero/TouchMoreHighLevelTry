// File : ./Infotainment.java

import java.util.concurrent.Semaphore;

public class Infotainment
{
  // \ART_SMG :: Created for state : Infotainment
  public enum RtsInfotainment_States
  {
    RtsInfotainment_States_Entertainment,
    RtsInfotainment_States_NotIn_Infotainment
  }
  
  // \ART_SMG :: Created for state : Entertainment
  public enum RtsInfotainmentEntertainment_States
  {
    RtsInfotainmentEntertainment_States_Idle,
    RtsInfotainmentEntertainment_States_Mp3Player,
    RtsInfotainmentEntertainment_States_SetFrequency,
    RtsInfotainmentEntertainment_States_SinePlayer,
    RtsInfotainmentEntertainment_States_Tuner,
    RtsInfotainmentEntertainment_States_Volume,
    RtsInfotainmentEntertainment_States_NotIn_Entertainment
  }
  
  // Attributes
  
  private boolean MuteState = false;
  
  public boolean Exit = false;
  
  private int Volume = 27;
  
  private int Frequency = 98100;
  
  private boolean TAstart = false;
  
  private boolean TAend = false;
  
  private int TAfrequency = 98100;
  
  // \ART_SMG :: Created for state : Infotainment
  private RtsInfotainment_States RtsCurrent_Infotainment;
  
  // \ART_SMG :: Created for state : Entertainment
  private RtsInfotainmentEntertainment_States RtsCurrent_Entertainment;
  
  // \ART_SMG :: Created for state : Entertainment
  private RtsInfotainmentEntertainment_States RtsAttHistory_Entertainment;
  
  // \ART_SMG :: Always Created
  private Semaphore RtsBusy;
  
  // Operations
  
  public void Command(final String Code)
  {
  // ## OperationBody [4ac09bf3-db6b-4844-ba74-d0beacf60e50] 
  /* START_SMG_BODY */
  
  try
  {
    RtsBusy.acquire();
  }
  catch (InterruptedException e)
  {}
  Command_In_Infotainment(Code);
  while(RtsRunToCompletion());
  RtsBusy.release();
  /* END_SMG_BODY */
  // ## OperationBody End 
  }
  
  public void UseValue(final int Value)
  {
  // ## OperationBody [f4542cc2-06b9-4080-9df5-30d49048bdbd] 
  /* START_SMG_BODY */
  
  try
  {
    RtsBusy.acquire();
  }
  catch (InterruptedException e)
  {}
  UseValue_In_Infotainment(Value);
  while(RtsRunToCompletion());
  RtsBusy.release();
  /* END_SMG_BODY */
  // ## OperationBody End 
  }
  
  public static int TimerSet(boolean bPaused,
    Object TimerInstance,
    long lTimeToFire,
    Object TimerDetails,
    long lMultiplier)
  {
  int Ret = 1;
  if(bPaused == true)
  {
  	((RtsTimer)TimerInstance).RtsStopTimer();
  }
  else
  {
  	// Restart it
  	((RtsTimer)TimerInstance).RtsStartTimer(lTimeToFire);
  }
  return Ret;
  }
  
  private boolean RtsRunToCompletion()
  {
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_Entertainment && RtsCurrent_Entertainment == RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_NotIn_Entertainment)
  {
  	return true;
  }
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_Entertainment && RtsCurrent_Entertainment == RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_NotIn_Entertainment)
  {
  	RtsExit_Entertainment();
  	RtsCurrent_Infotainment = RtsInfotainment_States.RtsInfotainment_States_NotIn_Infotainment;
  	return false;
  }
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_NotIn_Infotainment)
  {
  	RtsDefault_Entertainment();
  	return true;
  }
  return false;
  }
  
  // \ART_SMG :: Constructor
  public Infotainment()
  {
  RtsBusy = new Semaphore(1);
  try
  {
    RtsBusy.acquire();
  }
  catch (InterruptedException e)
  {}
  
  
  /* Initialising State Vectors to NotIn */
  RtsCurrent_Infotainment=RtsInfotainment_States.RtsInfotainment_States_NotIn_Infotainment;
  RtsCurrent_Entertainment=RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_NotIn_Entertainment;
  
  /* Initialising History attributes to NotIn */
  RtsAttHistory_Entertainment=RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_NotIn_Entertainment;
  
  while(RtsRunToCompletion());
  RtsBusy.release();
  }
  
  // \ART_SMG :: Created for state : Entertainment
  private void RtsDefault_Entertainment()
  {
  RtsEnter_Entertainment();
  
  RtsEnter_Idle();
  
  }
  
  // \ART_SMG :: Destructor
  public void SMGDestroy()
  {
  RtsBusy = null;
  
  }
  
  // \ART_SMG :: Created for state : Entertainment
  private void RtsEnter_Entertainment()
  {
  RtsCurrent_Infotainment = RtsInfotainment_States.RtsInfotainment_States_Entertainment;
  
  }
  
  // \ART_SMG :: Created for state : Idle
  private void RtsEnter_Idle()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Idle;
  
  }
  
  // \ART_SMG :: Created for state : Mp3Player
  private void RtsEnter_Mp3Player()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Mp3Player;
  
  }
  
  // \ART_SMG :: Created for state : SetFrequency
  private void RtsEnter_SetFrequency()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_SetFrequency;
  
  }
  
  // \ART_SMG :: Created for state : SinePlayer
  private void RtsEnter_SinePlayer()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_SinePlayer;
  
  }
  
  // \ART_SMG :: Created for state : Tuner
  private void RtsEnter_Tuner()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Tuner;
  
  /* Entry Actions */
  
  // ## Action [4896eb64-2c87-4d53-a7ba-1391a10e4d64] 
  if(TAstart)
  {
  dsp.set_frequency(TAfrequency, TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);
  dsp.setVolume(Volume+3);
  TAstart=false;
  }
  if(TAend)
  {
  dsp.set_frequency(Frequency, TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);
  dsp.setVolume(Volume);
  TAend=false;
  }
  // ## Action End 
  
  }
  
  // \ART_SMG :: Created for state : Volume
  private void RtsEnter_Volume()
  {
  RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Volume;
  
  }
  
  // \ART_SMG :: Created for state : Entertainment
  private void RtsExit_Entertainment()
  {
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_Entertainment)
  {
  	switch(RtsCurrent_Entertainment)
  	{
  		case RtsInfotainmentEntertainment_States_Idle:
  			RtsExit_Idle();
  			break;
  		case RtsInfotainmentEntertainment_States_Mp3Player:
  			RtsExit_Mp3Player();
  			break;
  		case RtsInfotainmentEntertainment_States_SetFrequency:
  			RtsExit_SetFrequency();
  			break;
  		case RtsInfotainmentEntertainment_States_SinePlayer:
  			RtsExit_SinePlayer();
  			break;
  		case RtsInfotainmentEntertainment_States_Tuner:
  			RtsExit_Tuner();
  			break;
  		case RtsInfotainmentEntertainment_States_Volume:
  			RtsExit_Volume();
  			break;
  		default:
  			break;
  	}
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	RtsCurrent_Entertainment = RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_NotIn_Entertainment;
  	
  }
  }
  
  // \ART_SMG :: Created for state : Idle
  private void RtsExit_Idle()
  {
  	
  	}
  
  // \ART_SMG :: Created for state : Mp3Player
  private void RtsExit_Mp3Player()
  {
  		
  		}
  
  // \ART_SMG :: Created for state : SetFrequency
  private void RtsExit_SetFrequency()
  {
  			
  			}
  
  // \ART_SMG :: Created for state : SinePlayer
  private void RtsExit_SinePlayer()
  {
  				
  				}
  
  // \ART_SMG :: Created for state : Tuner
  private void RtsExit_Tuner()
  {
  					
  					}
  
  // \ART_SMG :: Created for state : Volume
  private void RtsExit_Volume()
  {
  						
  						}
  
  private boolean Command_In_Infotainment(String Code)
  {
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_Entertainment)
  {
  	if (Command_In_Entertainment(Code))
  	{
  		return true;
  	}
  }
  return false;
  }
  
  private boolean Command_In_Entertainment(String Code)
  {
  if (
  // ## Guard [61744307-935c-4bb1-ace3-3fc7fdfdefdf] 
  Code.equalsIgnoreCase("taend")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [19f09b46-8593-4df2-9e60-8ac211fc8c7a] 
  TAend=true;
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [faf0e7d8-d9db-4ddc-9c8b-2ab03e84a03f] 
  Code.equalsIgnoreCase("m")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [0d98c54b-6a1e-49a9-b9f2-5b0f9af7a3e6] 
  if (MuteState) dsp.doDeMute();
  else dsp.doMute();
  MuteState=!MuteState;
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [17d79705-d98d-4cc0-9dc5-b8a9f8fe4bbc] 
  Code.equalsIgnoreCase("tastart")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [6d834115-f4aa-4a60-914b-16532e8bcf62] 
  TAstart=true;
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [5c2ba860-96ff-4901-b67b-8e96e680e783] 
  Code.equalsIgnoreCase("+")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [dd57a6ae-11a2-4b64-98ea-e3b63ccdf793] 
  if(Volume < 30)
  {
  Volume++;
  dsp.setVolume(Volume);
  }
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [b86a9e32-3388-4e87-b492-c3c9b014c226] 
  Code.equalsIgnoreCase("-")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [cbddf3b0-2530-412b-9fee-603efeb02534] 
  if(Volume > 0)
  {
  Volume--;
  dsp.setVolume(Volume);
  }
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [038d34ac-b85a-4fe9-a388-475540776274] 
  Code.equalsIgnoreCase("x")
  // ## Guard End 
  )
  {
  	
  	/* Store the state */
  	RtsAttHistory_Entertainment = RtsCurrent_Entertainment;
  	
  // ## Action [b953854a-3896-4fde-8880-fb07f652f5b3] 
  System.out.println("Exit");
  Exit=true;
  // ## Action End 
  
  	RtsHistory_Entertainment(true);
  	
  	return true;
  }
  if (
  // ## Guard [4be4b5c5-b76f-4e90-870c-5eef90f0f0ef] 
  Code.equalsIgnoreCase("mp3")
  // ## Guard End 
  )
  {
  	
  // ## Action [d3a8c7db-a109-4965-b59a-ea15145e5178] 
  try{
  dsp.setSource(DR_AUDIO_SOURCES.SRC_MP3);
  Runtime rt = Runtime.getRuntime();
  Process pr = rt.exec("aplay -D TDM1_O_ENT /home/root/arribba.wav");
  dsp.setVolume(Volume);
  } 
  catch (Exception e){
  System.out.println("Error running: aplay -D TDM1_O_ENT /home/root/arribba.wav");
  }
  // ## Action End 
  
  	RtsEnter_Mp3Player();
  	
  	return true;
  }
  if (
  // ## Guard [2222943f-752d-4778-be68-996b7b8d772e] 
  Code.equalsIgnoreCase("s")
  // ## Guard End 
  )
  {
  	
  // ## Action [888b0edb-7e08-443c-a223-1a38b95d84c8] 
  dsp.playSin(25, 440, true);
  dsp.setVolume(Volume);
  // ## Action End 
  
  	RtsEnter_SinePlayer();
  	
  	return true;
  }
  if (
  // ## Guard [73023336-6721-406a-94b9-d44d094b3f3d] 
  Code.equalsIgnoreCase("r")
  // ## Guard End 
  )
  {
  	
  // ## Action [dc8f0595-f385-4a0e-a05e-14fa8334b6ff] 
  dsp.setSource(DR_AUDIO_SOURCES.SRC_TUNER);
  dsp.set_frequency(Frequency, TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);
  dsp.setVolume(Volume);
  // ## Action End 
  
  	RtsEnter_Tuner();
  	
  	return true;
  }
  if (
  // ## Guard [f072d0c6-34ce-47ea-bdb9-ca27454f7f98] 
  Code.equalsIgnoreCase("v")
  // ## Guard End 
  )
  {
  	RtsEnter_Volume();
  	
  	return true;
  }
  return false;
  }
  
  private boolean UseValue_In_Infotainment(int Value)
  {
  if (RtsCurrent_Infotainment == RtsInfotainment_States.RtsInfotainment_States_Entertainment)
  {
  	if (UseValue_In_Entertainment(Value))
  	{
  		return true;
  	}
  }
  return false;
  }
  
  private boolean UseValue_In_Entertainment(int Value)
  {
  if (RtsCurrent_Entertainment == RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_SinePlayer)
  {
  	RtsExit_SinePlayer();
  	
  // ## Action [c2a2bb57-aff6-4045-b7f2-888693cf3e60] 
  dsp.playSin(25, Value, true);
  dsp.setVolume(Volume);
  // ## Action End 
  
  	RtsEnter_SinePlayer();
  	
  	return true;
  }
  if (RtsCurrent_Entertainment == RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Tuner)
  {
  	RtsExit_Tuner();
  	
  // ## Action [a3a57ef7-f287-4ba0-91b9-842fb9d2b5a8] 
  Frequency=Value;
  dsp.set_frequency(Frequency, TYPE_BAND.FM_BAND, TYPE_SUB_BAND.AM_KW_BAND);
  dsp.setVolume(Volume);
  // ## Action End 
  
  	RtsEnter_Tuner();
  	
  	return true;
  }
  if (RtsCurrent_Entertainment == RtsInfotainmentEntertainment_States.RtsInfotainmentEntertainment_States_Volume)
  {
  	RtsExit_Volume();
  	
  // ## Action [bfa6d439-ca82-4509-9402-29cc54e5722e] 
  Volume=Value;
  dsp.setVolume(Volume);
  // ## Action End 
  
  	RtsEnter_Volume();
  	
  	return true;
  }
  return false;
  }
  
  // \ART_SMG :: Created for state : Entertainment
  private void RtsHistory_Entertainment(final boolean IsDeep)
  {switch(RtsAttHistory_Entertainment)
  {
  	case RtsInfotainmentEntertainment_States_Idle:
  		RtsEnter_Entertainment();
  		RtsEnter_Idle();
  		break;
  	case RtsInfotainmentEntertainment_States_Mp3Player:
  		RtsEnter_Entertainment();
  		RtsEnter_Mp3Player();
  		break;
  	case RtsInfotainmentEntertainment_States_SetFrequency:
  		RtsEnter_Entertainment();
  		RtsEnter_SetFrequency();
  		break;
  	case RtsInfotainmentEntertainment_States_SinePlayer:
  		RtsEnter_Entertainment();
  		RtsEnter_SinePlayer();
  		break;
  	case RtsInfotainmentEntertainment_States_Tuner:
  		RtsEnter_Entertainment();
  		RtsEnter_Tuner();
  		break;
  	case RtsInfotainmentEntertainment_States_Volume:
  		RtsEnter_Entertainment();
  		RtsEnter_Volume();
  		break;
  	default:
  		RtsEnter_Entertainment();RtsEnter_Idle();
  		
  		break;
  }}

}
