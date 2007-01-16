package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import static musicseeder.Util.*;

import javax.sound.midi.*;

public aspect PrettyPrint extends DepthFirstAdapter {

    String chords = "";
    String notes = "";
    int timeDepth=0;

    public @Override void inATonikaFunction(ATonikaFunction node) {
	chords += "T               ";
    }

    public @Override void inADominantFunction(ADominantFunction node) {
	chords += "D               ";
    }

    public @Override void inASubdominantFunction(ASubdominantFunction node) {
	chords += "S               ";
    }

    public @Override void inASingleNote(ASingleNote node) {
	notes += node.height + makeSpaces((16>>timeDepth)-1);
    }

    public @Override void inASplitNote(ASplitNote node){
	timeDepth++;
    }

    public @Override void outASplitNote(ASplitNote node){
	timeDepth--;
    }

    public @Override void outASong(ASong node) {
	System.out.println(chords);
	System.out.println(notes);
	chords = "";
	notes = "";
    }

    private String makeSpaces(int n) {
	String r = "";
	for(int i=0; i<n;i++) {
	    r += " ";
	}
	return r;
    }

}
