package musicseeder;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.*;
import java.util.*;

import javax.sound.midi.*;

public aspect Util {
    public int ASingleNote.height;
    public Sequence ASong.sequence;

    public static Random random = new Random(); 

    public static int chooseRandom(int[] r){
	return r[random.nextInt(r.length)];
    }
    public static PFunction chooseRandom(PFunction[] r){
	return r[random.nextInt(r.length)];
    }

    
    /** Search up the AST for a ancestor node of a specific type.
     *  @param start the node at which to start the search
     *  @param cl the class of the node to search for
     *  @return the closest ancestor of the specified type,
     *  or <code>null</code> if no ancestor of the
     *  given start node has the specified type.
     */
    public static <T> T findNode(Node start, Class<T> cl) {
	Node n = start;
	while (!cl.isInstance(n)) {
	    n = n.parent();
	    if (n == null) return null;
	}
	return cl.cast(n);
    }

    
    public int[] PFunction.getSteps(){
	if(this instanceof AAllNoteFunction) {
	    int[] r={1,2,3,4,5,6,7,8};
	    return r;
	} else if(this instanceof ATonikaFunction) {
	    int[] r={1,3,5,8};
	    return r;
	} else if (this instanceof ATonikaParFunction) {
	    int[] r={6,3,1};
	    return r;
	} else if (this instanceof ADominantFunction) {
	    int[] r={5,2,7};
	    return r;
	} else if (this instanceof ADominantParFunction) {
	    int[] r={3,5,7};
	    return r;
	} else if (this instanceof ASubdominantFunction) {
	    int[] r={4,1,6,8};
	    return r;
	} else if (this instanceof ASubdominantParFunction) {
	    int[] r={2,4,6};
	    return r;
	} else {
	    throw new RuntimeException("Bad chord type");
	}
    }

    private static ASingleNote runDownAlongFirst(PNote x) {
	while ( true ){
	    if(x instanceof ASplitNote)
		x = ((ASplitNote)x).getFirst();
	    else if (x instanceof AParallelNote){
		x = ((AParallelNote)x).getP1();
	    } else { //A single note
		break;
	    }
	}
	return (ASingleNote)x;
    }
    
    public static ASingleNote nextNote(ASingleNote node) {
	Node x = node.parent();
	Node n = node;

	while( true ) {
	    if(x instanceof AChord){
		break;
	    } 
	    if ( x instanceof ASplitNote && n == ((ASplitNote)x).getFirst() ) {
		x = ((ASplitNote)x).getSecond();
		// run down 
		return runDownAlongFirst((PNote)x);
	    }
	    n = x;
	    x = x.parent();
	}

	assert(x instanceof AChord);

	// Find naeste akkord
	List<PChord> chords = ((APart)x.parent() ).getChordProgression();
	Iterator<PChord> i = chords.iterator();
	for ( ; i.hasNext() ; ) {
	    if ( i.next() == x && i.hasNext()) return runDownAlongFirst(((AChord)i.next()).getNote());
	}
	// Sidste tone i sidste akkord
	return null;
    }

    /** Converts scale tone to midi tone */
    public static int scaleToMidi(int step){
	int note=0;
	switch(step) {
	case 1: note = 0; break;
	case 2: note = 2; break;
	case 3: note = 4; break;
	case 4: note = 5; break;
	case 5: note = 7; break;
	case 6: note = 9; break;
	case 7: note = 11; break;
	case 8: note = 12; break;
	}
	return 60+note;
    }

    public static void generateEvent(Track track, int msgType,
			      int midiChannel, int data, int vel, long pos){
	try{
	    ShortMessage msg = new ShortMessage();
	    msg.setMessage(msgType, midiChannel, data, vel);
	    
	    MidiEvent event = new MidiEvent(msg,pos);
	    track.add(event);
	
	} catch (Exception e){
	    e.printStackTrace();
	    System.exit(-1);
	}
    }
    
    public static void generateEvent(Track track, int msgType, int midiChannel, int data, int vel){
	generateEvent(track, msgType, midiChannel, data, vel, 0);
	
    }
    

}
