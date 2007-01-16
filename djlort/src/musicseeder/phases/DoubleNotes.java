package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect DoubleNotes extends GuiPhases{
    public void Main.h(){}
    
    int timeDepth;
    int pos;
    PFunction currentFunction;

    public String doc(){
	return "Doubles random nodes in the tree, leaving the last node at the same height, but choosing an appropriate one for the first";
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
	    ASingleNote first, second;
	    first = new ASingleNote();
	    first.height = chooseRandom(function.getSteps());
	    second = new ASingleNote();
	    second.height = node.height;
	    ASplitNote split = new ASplitNote(new AStraightDivision(),
					      first,
					      second);
	    node.replaceBy(split);
	}
    } 
    
    public @Override void outASplitNote(ASplitNote node){
	timeDepth--;
    }

    public @Override String title() {
	return "Double notes";
    }

}
