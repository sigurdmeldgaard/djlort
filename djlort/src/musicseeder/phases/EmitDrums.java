package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect EmitDrums extends GuiPhases {
    public final static int barLengthInTicks = 2<<7;
    final int channel = 9; // GM standard drum channel
    //    final int chordProgram = 42;

    int pos;
    Track track;
    int velocity = 60;

    public @Override void inASong(ASong node){
	pos = 0;
	track = node.sequence.createTrack();
	//generateEvent(chordTrack, ShortMessage.PROGRAM_CHANGE, chordChannel, chordProgram, 0);
	
    }

    /** Returns tick for the n'th 1/denom beat */
    private int beat(int denom, int n) {
	return (barLengthInTicks/8)*(n-1);
    }

    /** Hit a drum at tick i relative to the current bar */
    private void hit(int drum, int vel, int i) {
	int l = 10; // length of all drum notes - doesn't usually matter as long as it's > 0
	generateEvent(track, ShortMessage.NOTE_ON, channel,
		      drum, velocity, pos+i);
	generateEvent(track, ShortMessage.NOTE_OFF, channel, 
		      drum,0,pos+i+l);
    }

    public @Override String title() {
	return "Drums";
    }

    public PhaseType phaseType() {
	return PhaseType.ORCHESTER;
    }
    
    public @Override void inAChord(AChord node) {
	int bd = 35;
	int sd = 38;
	int hh = 42;
	int oh = 46;

	// skarpt mindre?
	for ( int i = 0; i<barLengthInTicks; i++){
	    if ( i==beat(8,1) || i==beat(8,5) ) {
		hit(bd,80,i);
		hit(hh,60,i);
	    }
	    if ( i==beat(8,3) || i==beat(8,7) ) {
		hit(sd,80,i);
		hit(hh,60,i);
	    }
	    if ( i==beat(8,2) || i==beat(8,4) || i==beat(8,6) || i==beat(8,8) ) {
		hit(hh,70,i);
	    }
	}

	pos+=barLengthInTicks;
    }

}
