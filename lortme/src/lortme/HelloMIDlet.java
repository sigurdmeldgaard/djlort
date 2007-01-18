package lortme;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.io.*;

public class HelloMIDlet
    extends MIDlet 
    implements CommandListener {
  private Form mMainForm;
  static final String hostname = "127.0.0.1";
  static final int serverport 	= 3000;
  static final int sendport	= 3000;
  static final int receiveport 	= 3001;
 // private Form resultScreen = null;
  static boolean isOver = false;
  TextField message=null; 

  public HelloMIDlet() {
	  mMainForm = new Form("UDP Testing");
	  mMainForm.append(new StringItem(null, "Hello, MIDP!"));
	  mMainForm.addCommand(new Command("Exit", Command.EXIT, 0));
	  mMainForm.addCommand(new Command("Play!", Command.OK, 0));
	  mMainForm.setCommandListener(this);

//    message=new TextField("note 0 64 60 1000"," ",90,TextField.ANY);
  }
  
  public void startApp() {
    Display.getDisplay(this).setCurrent(mMainForm);
  }
  
  public void pauseApp() {}
  
  public void destroyApp(boolean unconditional) {}

  
  public static void sendNote(){
      
	  DatagramConnection dc = null;
	  byte[] message = "note 0 64 60 1000".getBytes();//msg.getBytes();
	  String localhost = "datagram://localhost:" + sendport;
	  try {
		  dc = (DatagramConnection)Connector.open(localhost);
		  Datagram datagram = dc.newDatagram(message, message.length, "datagram://" + hostname + ":" + serverport);         
		  dc.send(datagram);
	  } catch (Exception e) {
		  System.out.println("Failed to send message: " + e);
	  } finally {
		  if (dc != null) {
			  try {
				  dc.close();
			  } catch (Exception f) {
				  System.out.println("Failed to close Connector: " + f);   
			  }
		  }
	  }		
  }
  
  public void commandAction(Command c, Displayable s) {
	  switch(c.getCommandType()){
	  case Command.OK:
		  sendNote();
		  break;
	  case Command.EXIT:
		  notifyDestroyed();
		  break;
	  }
  }
}