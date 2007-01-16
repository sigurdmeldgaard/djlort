package musicseeder.generate;

import musicseeder.*;
import musicseeder.analysis.*;
import musicseeder.node.*;
import static musicseeder.Util.*;

import java.util.*;

public class BetterSong implements Song{
    public String name(){
	return "BetterSong!!";
    }

    private AChord makeChord(PFunction function){
	ASingleNote note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	return new AChord(note, function);
    }
    
    public ASong generate(){
	List<PChord> prog = new LinkedList<PChord>();
	PFunction function;
	/*
	prog.add(makeChord(new ATonikaFunction()));
	prog.add(makeChord(new ASubdominantFunction()));
	prog.add(makeChord(new ADominantFunction()));
	prog.add(makeChord(new ATonikaFunction()));

	prog.add(makeChord(new ATonikaFunction()));
	prog.add(makeChord(new ASubdominantFunction()));
	prog.add(makeChord(new ADominantFunction()));
	prog.add(makeChord(new ATonikaFunction()));

	prog.add(makeChord(new ATonikaParFunction()));
	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ASubdominantParFunction()));
	prog.add(makeChord(new ADominantFunction()));

	*/

	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));
	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));
	
	prog.add(makeChord(new ASubdominantParFunction()));
	prog.add(makeChord(new ADominantParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));
	prog.add(makeChord(new ATonikaParFunction()));

	APart part=new APart(prog);
	List<PPart> parts = new LinkedList<PPart>();
	parts.add(part);
	ASong song=new ASong(parts);
	return song;
    }
}
