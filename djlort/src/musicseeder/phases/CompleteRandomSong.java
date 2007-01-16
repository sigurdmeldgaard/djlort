package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;
import java.util.*;

public aspect CompleteRandomSong extends GuiPhases{
    private AChord makeChord(PFunction function){
	ASingleNote note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	return new AChord(note, function);
    }
    
    public @Override String title() {
	return "Complete Random Song";
    }


    public PhaseType phaseType() {
	return PhaseType.BASIS;
    }
    
    public void inASong(ASong node){
	List<PChord> prog = new LinkedList<PChord>();
	PFunction function;
	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));

	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));
	prog.add(makeChord(new AAllNoteFunction()));

	APart part=new APart(prog);
	List<PPart> parts = new LinkedList<PPart>();
	parts.add(part);
	node.setParts(parts);
    }
}
