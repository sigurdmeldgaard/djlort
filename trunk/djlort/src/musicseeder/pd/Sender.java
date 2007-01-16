package musicseeder.pd;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.sound.midi.*;

public class Sender{
    private DatagramSocket socket;
    private int port;
    private int tickLength = 5;
    
    public Sender(int port){
        try{
            socket = new DatagramSocket();
            this.port = port;
            // socket.connect();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void send(String msg){
        try{
            byte[] data = (msg+'\n').getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), port);
            socket.send(packet);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void play(Track track){
        Timer timer = new Timer("PlayerThread");
        for(int i = 0; i<track.size(); i++){
            MidiEvent event = track.get(i);
            long now = new Date().getTime();
            final Long tick = event.getTick();
            final ShortMessage msg = (ShortMessage)event.getMessage();
            timer.schedule(new TimerTask(){
                    public void run(){
                        if(msg.getCommand() == ShortMessage.NOTE_OFF){
                            send("off");
                        } else if (msg.getCommand() == ShortMessage.NOTE_ON){
                            send("on "+msg.getData1());
                        }
                    }
                }, new Date(tickLength*tick + now));
        }
    }
    
    public static void main(String args[]) throws Exception{
        Sender a = new Sender(3000);
        System.out.println("connected");
        a.send("on 60");
        Thread.sleep(300);
        a.send("off"); 
        Thread.sleep(300);
        a.send("on 65"); 
        Thread.sleep(300);
        a.send("off"); 
       
    }
}
