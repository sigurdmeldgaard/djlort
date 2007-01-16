package musicseeder.generate;

import musicseeder.*;
import musicseeder.analysis.*;
import musicseeder.node.*;
import static musicseeder.Util.*;

import java.util.*;

public class SimpleSong implements Song{
    public String name(){
	return "SimpleSong!!";
    }
    
    public ASong generate(){
	List<PChord> prog = new LinkedList<PChord>();
	ASingleNote note;
	PFunction function;
	function = new ATonikaFunction();
	note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	prog.add(new AChord(note, function));
	function = new ASubdominantFunction();
	note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	prog.add(new AChord(note, function));
	function = new ADominantFunction();
	note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	prog.add(new AChord(note, function));
	function = new ATonikaFunction();
	note = new musicseeder.node.ASingleNote();
	note.height = chooseRandom(function.getSteps());
	prog.add(new AChord(note, function));
	APart part = new APart(prog);
	List<PPart> parts=new LinkedList<PPart>();
	parts.add(part);
	ASong song=new ASong(parts);
	return song;
    }
}
