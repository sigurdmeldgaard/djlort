package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;
import java.util.*;

public aspect HomeSong extends GuiPhases{
    private AChord makeChord(PFunction function){
	ASingleNote note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	return new AChord(note, function);
    }
    
    public @Override String title() {
	return "Home song";
    }

    public PhaseType phaseType() {
	return PhaseType.BASIS;
    }
    
    public void inASong(ASong node){
	List<PChord> prog = new LinkedList<PChord>();
	PFunction function;
	PFunction[] endFunctions = {new ATonikaFunction(), new ATonikaParFunction()}; 
	PFunction[] endStrofeFunctions = {
	    new ATonikaFunction(), 
	    new ATonikaParFunction(), 
	    new ADominantFunction(), 
	    new ADominantParFunction()}; 
	PFunction[] allFunctions = {
	    new ATonikaFunction(), 
	    new ATonikaParFunction(), 
	    new ADominantFunction(), 
	    new ADominantParFunction(),
	    new ASubdominantFunction(), 
	    new ASubdominantParFunction()
	}; 
	
	int t=17;
	Object last = new Object();
	PFunction f = null;
	for (int i=1;i<=t;i++) {
	    do {
		if ( i==t) {
		    // sidste akkord
		    f = (PFunction)chooseRandom(endFunctions).clone();
		} else if ( i % 4 == 0) {
		    // en 4-akkord
		    f = (PFunction)chooseRandom(endStrofeFunctions).clone();
		} else {
		    // andre tilf
		    f = (PFunction)chooseRandom(allFunctions).clone();
		}
	    } while ( f.getClass()==last.getClass());
	    prog.add(makeChord(f));
	    last = f;
	}
	for (PChord c : prog ) {
	    assert(c!=null);
	}

	APart part=new APart(prog);
	List<PPart> parts = new LinkedList<PPart>();
	parts.add(part);
	node.setParts(parts);
    }
}
