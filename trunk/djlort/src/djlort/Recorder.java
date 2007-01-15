package djlort;
import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Recorder {
	public Recorder(Protocol protocol) {
		try {

		      MidiDevice.Info[] info = MidiSystem.getMidiDeviceInfo();
		      MidiDevice.Info inputDeviceInfo =
		          (MidiDevice.Info) JOptionPane.showInputDialog (null,
		                                                         "Select INPUT MIDI Device",
		                                                         "PlumStone Deployment Tester",
		                                                         JOptionPane.QUESTION_MESSAGE,
		                                                         /* Icon */ null,
		                                                         info,
		                                                         null);

		        MidiDevice inputPort = MidiSystem.getMidiDevice (inputDeviceInfo);
		        System.out.println (inputPort);
		        inputPort.open ();
		        List<Transmitter> listTransmitters = inputPort.getTransmitters ();
		        if (!listTransmitters.isEmpty ()) {
		          System.out.println ("getTransmitters problem- list should be empty but contains:");
		          for (Transmitter item : listTransmitters) {
		            System.out.print (" " + item);
		          }
		          System.out.println ();
		        }

		        Transmitter t = inputPort.getTransmitter ();

			
			
			// Just get the first MIDI input port
			Transmitter input = MidiSystem.getTransmitter();
			input.setReceiver(new Sender(protocol));
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{		
		JFrame frame = new JFrame("Spil mig et stykke");
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		

		String host = "10.11.20.170";
		Protocol tcp = new TcpProtocol(host,8000);
		Protocol udp = new UdpProtocol(host,3000);
		new Recorder(udp);
		
	}
}
