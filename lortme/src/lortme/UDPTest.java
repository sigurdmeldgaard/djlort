package lortme;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.io.*;

public class UDPTest
    extends MIDlet 
    implements CommandListener {
  private static Form mMainForm;
  static final String hostname = "8kc.dk";
  static final int serverport 	= 3000;
  static final int sendport	= 3000;
  static final int receiveport 	= 3001;
 // private Form resultScreen = null;
  static boolean isOver = false;
  TextField message=null; 

  static String midimsg = "note 0 64 127 1000";
  
  public UDPTest() {
	  mMainForm = new Form("UDP Testing");
	  mMainForm.addCommand(new Command("Exit", Command.EXIT, 0));
	  mMainForm.addCommand(new Command("Play!", Command.OK, 0));
	  mMainForm.setCommandListener(this);

//    message=new TextField("note 0 64 127 1000"," ",90,TextField.ANY);
  }
  
  public void startApp() { 
    Display.getDisplay(this).setCurrent(mMainForm);
  }
  
  public void pauseApp() {}
  
  public void destroyApp(boolean unconditional) {}

  
  public static void sendNote(){
      
	  DatagramConnection dc = null;
	  String localhost = "datagram://127.0.0.1:" + sendport;
	  try {
		  dc = (DatagramConnection)Connector.open(localhost);
		  for(int i=0; i<4; i++){
			  String messageStr = "note 1 "+(i*4+64)+" "+100+" -1";
			  byte[] message = (messageStr+";\n").getBytes();//msg.getBytes();

			  Datagram datagram = dc.newDatagram(message, message.length, "datagram://" + hostname + ":" + serverport);         
			  dc.send(datagram);
			  mMainForm.append(new StringItem(null, "Send " + midimsg + " til "+hostname));
		  }
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