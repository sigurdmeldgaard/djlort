package djlort;

import static org.junit.Assert.*;

import org.junit.Test;
import javax.sound.midi.*;
public class SenderTest {

	@Test
	public void testMsgToString() {
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(ShortMessage.NOTE_ON, 0, 40, 10);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
