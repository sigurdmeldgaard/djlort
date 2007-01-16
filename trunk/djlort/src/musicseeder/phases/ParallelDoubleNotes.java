 package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect ParallelDoubleNotes extends GuiPhases{
    // public void Main.h(){}
    
    int timeDepth;
    int pos;
    PFunction currentFunction;

    public String doc(){
	return "Makes parallel notes";
    }

    public @Override String title() {
	return "Parallel notes";
    }
   
    public @Override void inAChord(AChord node){
	currentFunction = node.getFunction();
    }
    
    public @Override void inASong(ASong node){
	pos=0;
	timeDepth=0;
    }


    public @Override void outASong(ASong node){
    }

    public @Override void inASplitNote(ASplitNote node){
	timeDepth++;
    }

    public @Override void inASingleNote(ASingleNote node){
	if(Util.random.nextDouble()>.3){
	    PFunction function = currentFunction;
	    ASingleNote p1, p2;
	    p1 = new ASingleNote();
	    p1.height = node.height;

	    p2 = new ASingleNote();
	    p2.height = p1.height;
	    while ( p1.height == p2.height){
		p2.height = chooseRandom(function.getSteps());
	    }
	    
	    AParallelNote parallel = new AParallelNote(p1, p2);
	    node.replaceBy(parallel);
	}
    } 


    public @Override void outASplitNote(ASplitNote node){
	timeDepth--;
    }
}
