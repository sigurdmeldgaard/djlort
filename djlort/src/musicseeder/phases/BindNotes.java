package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect BindNotes extends GuiPhases {
    int timeDepth;
    int pos;
    PFunction currentFunction;

    public @Override void inAChord(AChord node){
	currentFunction = node.getFunction();
    }

    public @Override String title() {
	return "Bind notes together";
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

    public @Override void caseAParallelNote(AParallelNote node)
    {
        inAParallelNote(node);
        if(node.getP1() != null)
        {
            node.getP1().apply(this);
        }
	//Skip P2
        outAParallelNote(node);
    }


    
    public @Override void outASingleNote(ASingleNote node){
	if(Util.random.nextDouble()>.3){
	    PFunction function = currentFunction;
	    ASingleNote next = nextNote(node);
	    if ( next == null ) return;

	    int a = node.height;
	    int b = next.height;
	    if ( Math.abs(a-b) < 2 ) return;

	    // set newNoteHeight;
	    int newNoteHeight = findHeight(a,b,currentFunction);
		
	    ASingleNote first, second;
	    first = new ASingleNote();
	    first.height = a;
	    second = new ASingleNote();
	    second.height = newNoteHeight;
	    ASplitNote split = new ASplitNote(new AStraightDivision(),
					      first,
					      second);
	    node.replaceBy(split);
	}
    }

    private int findHeight(int a, int b, PFunction currentFunction) {
	    int c = Math.min(a,b);
	    int d = Math.max(a,b);

	    // pr�ver f�rst at finde en height i currentFunction
	    for (int i = d-1 ; i>c ; i--) {
		for (int j: currentFunction.getSteps() ) {
		    if (j == i) {
			return i;
		    }
		}
	    }

	    // nu m� man bare finde en eller anden height
	    if ( Math.abs(a-b) % 2 == 0 ){
		return (a + b) / 2;
	    } else {//ulige
		if(a>b){
		    return (a + b - 1) / 2;
		} else {
		    return (a + b + 1) / 2;
		}
	    }
    }
    
    public @Override void outASplitNote(ASplitNote node){
	timeDepth--;
    }
}
