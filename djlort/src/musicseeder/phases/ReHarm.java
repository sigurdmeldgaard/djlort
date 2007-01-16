package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;
import java.util.*;

public aspect ReHarm extends GuiPhases{
    public abstract PFunction PFunction.getParallel();
    public @Override PFunction ATonikaFunction.getParallel(){
	return new ATonikaParFunction();
    }
    public @Override PFunction ADominantFunction.getParallel(){
	return new ADominantParFunction();
    }
    public @Override PFunction ASubdominantFunction.getParallel(){
	return new ASubdominantParFunction();
    }
    public @Override PFunction ATonikaParFunction.getParallel(){
	return new ATonikaFunction();
    }
    public @Override PFunction ADominantParFunction.getParallel(){
	return new ADominantFunction();
    }
    public @Override PFunction ASubdominantParFunction.getParallel(){
	return new ASubdominantFunction();
    }
    public @Override PFunction AAllNoteFunction.getParallel(){
	return new AAllNoteFunction();
    }

    public @Override String title() {
	return "Reharmonize";
    }

    public void inAPart(APart node){
	List<PChord> chords = node.getChordProgression();
	for(PChord i : chords){
	    if(random.nextFloat()>.7){
		AChord chord = (AChord) i;
		chord.setFunction(chord.getFunction().getParallel());
	    }
	}
    }
    
}
