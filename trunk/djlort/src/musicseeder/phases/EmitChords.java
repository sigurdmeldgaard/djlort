package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect EmitChords extends GuiPhases {
	public final static int barLengthInTicks = 2<<7;
	final int chordChannel = 2;
	final int chordProgram = 42;

	int pos;
	Track chordTrack;
	int velocity = 40;

	public PhaseType phaseType() {
		return PhaseType.ORCHESTER;
	}

	public @Override String title() {
		return "Chords";
	}

	public @Override void inASong(ASong node){
		pos = 0;
		chordTrack = node.sequence.createTrack();
		generateEvent(chordTrack, ShortMessage.PROGRAM_CHANGE, chordChannel, chordProgram, 0);

	}

	public @Override void inAChord(AChord node) {
		// Add all notes of function to chordTrack
		PFunction currentFunction = node.getFunction();
		for (int j: currentFunction.getSteps() ) {
			generateEvent(chordTrack, ShortMessage.NOTE_ON, chordChannel,
					scaleToMidi(j), velocity, pos);
			generateEvent(chordTrack, ShortMessage.NOTE_OFF, chordChannel, scaleToMidi(j), 0,
					pos+  (int)(barLengthInTicks*.90));
		}
		pos+=barLengthInTicks;
	}

}
