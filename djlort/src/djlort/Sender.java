package djlort;
import javax.sound.midi.*;

public class Sender implements Receiver{

	Protocol protocol;
	
	public Sender(Protocol p){
		protocol = p;
	}
		
	public void send(MidiMessage msg, long timeStamp){
		// Send note over udp		
		MidiEvent event = new MidiEvent(msg, timeStamp);
		String s = eventToString(event);
		if ( s == null ) return; // Ignore some messages
		System.out.println("Sending "+s+" to " + protocol);
		protocol.send(s);
	}

	public static String eventToString(MidiEvent event) {
		// Convert midi event to udp packet
		ShortMessage msg = null;
		if ( event.getMessage() instanceof ShortMessage ){
			msg = (ShortMessage)event.getMessage();
		} else return null;//assert false : "We got something other than a ShortMsg";
		
		int volume = -1;
		switch(msg.getCommand()){
		case ShortMessage.NOTE_ON:
			volume = msg.getData2();
			break;
		case ShortMessage.NOTE_OFF:
			volume = 0;
			break;
		default: return null; //assert false : "We only like note on and note off";
		}
		int height = msg.getData1();
		
		String s = msg.getChannel() + " "
		 + height + " " + volume + " " + event.getTick() + "\n";
		return s;
	}

	public void close(){
	}
	
}
