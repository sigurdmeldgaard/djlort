package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect RythmicRepetition extends GuiPhases {
    int timeDepth;
    PFunction currentFunction;

    public @Override String title() {
	return "Rhythmic repetition";
    }

    public @Override ASingleNote ASingleNote.myClone(){
	ASingleNote n = new ASingleNote();
	n.height = this.height;
	return n;
    }

    public @Override AParallelNote AParallelNote.myClone(){
	AParallelNote n = new AParallelNote(this.getP1().myClone(),
				  this.getP2().myClone());
	return n;
    }

    public abstract PNote PNote.myClone();
    
    public @Override ASplitNote ASplitNote.myClone(){
	ASplitNote n = new ASplitNote((PDivision)(this.getDivision().clone()),
						this.getFirst().myClone(),
						this.getSecond().myClone());
	return n;
    }
    
    public @Override void inAChord(AChord node){
	currentFunction = node.getFunction();
    }
    
    public @Override void inASong(ASong node){
	timeDepth=0;
    }


    public @Override void outASong(ASong node){
    }

    public @Override void inASplitNote(ASplitNote node){
	timeDepth++;
	if(Util.random.nextDouble()>.3){
	    PFunction function = currentFunction;
	    PNote first, second;
	    first = (PNote)node.myClone();
	    second = (PNote)node.myClone();
	    ASplitNote split = new ASplitNote(new AStraightDivision(),
					      first,
					      second);
	    node.replaceBy(split);
	}
    }

    public @Override void inASingleNote(ASingleNote node){
    } 
    
    public @Override void outASplitNote(ASplitNote node){
	timeDepth--;
    }
}
