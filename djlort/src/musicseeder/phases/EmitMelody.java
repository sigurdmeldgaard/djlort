package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect EmitMelody extends GuiPhases {
	public final static int barLengthInTicks = 2<<7;
	final int channel = 1;
	final int melodyProgram = 5;

	int timeDepth=0;
	int velDepth=0;
	int pos;
	Track currentTrack;
	int velocity = 127;
	double groove = .7;

	public @Override String title() {
		return "Melody";
	}

	public PhaseType phaseType() {
		return PhaseType.ORCHESTER;
	}

	public @Override void inASong(ASong node){
		pos = 0;
		currentTrack = node.sequence.createTrack();
		generateEvent(currentTrack, ShortMessage.PROGRAM_CHANGE, channel, melodyProgram, 0);

	}

	public @Override void caseASplitNote(ASplitNote node){
		timeDepth++;
		node.getFirst().apply(this);
		velDepth++;
		node.getSecond().apply(this);
		velDepth--;
		timeDepth--;
	}
	
//	public @Override void inASplitNote(ASplitNote node){
//	timeDepth++;
//	}
//
	public @Override void caseAParallelNote(AParallelNote node)
	{
		int tmpVelocity = velocity;
		int tmpPos = pos;
		velocity *= 0.85;
		inAParallelNote(node);
		if(node.getP1() != null)
		{
			node.getP1().apply(this);
		}
		pos=tmpPos;
		if(node.getP2() != null)
		{
			node.getP2().apply(this);
		}
		outAParallelNote(node);
		velocity = tmpVelocity;
	}

//	public @Override void outASplitNote(ASplitNote node){
//		timeDepth--;
//	}

	public @Override void inASingleNote(ASingleNote node){
		generateEvent(currentTrack, ShortMessage.NOTE_ON, channel,
				scaleToMidi(node.height)+12, (int)(velocity*Math.pow(groove,velDepth)), pos);
		pos += barLengthInTicks / (1<<timeDepth);
		generateEvent(currentTrack, ShortMessage.NOTE_OFF, channel,
				scaleToMidi(node.height)+12, 0, pos);		
	} 

}
