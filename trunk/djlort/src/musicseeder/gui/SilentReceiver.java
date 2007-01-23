package musicseeder.gui;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class SilentReceiver implements Receiver {

	/** This class takes midi input from a Transmitter and discards it */
	
	public void close() {
		// TODO Auto-generated method stub

	}

	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub

	}

}
