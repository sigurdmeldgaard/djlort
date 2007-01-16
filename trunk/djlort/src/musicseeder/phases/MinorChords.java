package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;
import java.util.*;

public aspect MinorChords extends GuiPhases{
    private AChord makeChord(PFunction function){
	ASingleNote note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	return new AChord(note, function);
    }
    
    public PhaseType phaseType() {
	return PhaseType.BASIS;
    }
    
    public @Override String title() {
	return "Minor chords";
    }

    public void inASong(ASong node){
	List<PChord> prog = new LinkedList<PChord>();
	PFunction function;

	prog.add(makeChord(new ATonikaParFunction()));
	prog.add(makeChord(new ASubdominantParFunction()));
	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));

	prog.add(makeChord(new ATonikaParFunction()));
	prog.add(makeChord(new ASubdominantParFunction()));
	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));

	APart part=new APart(prog);
	List<PPart> parts = new LinkedList<PPart>();
	parts.add(part);
	node.setParts(parts);
    }
}
