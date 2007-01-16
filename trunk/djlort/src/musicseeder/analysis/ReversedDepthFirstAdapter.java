/* This file was generated by SableCC (http://www.sablecc.org/). */

package musicseeder.analysis;

import java.util.*;

import musicseeder.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPSong().apply(this);
        outStart(node);
    }

    public void inASong(ASong node)
    {
        defaultIn(node);
    }

    public void outASong(ASong node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASong(ASong node)
    {
        inASong(node);
        {
            List<PPart> copy = new ArrayList<PPart>(node.getParts());
            Collections.reverse(copy);
            for(PPart e : copy)
            {
                e.apply(this);
            }
        }
        outASong(node);
    }

    public void inAPart(APart node)
    {
        defaultIn(node);
    }

    public void outAPart(APart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPart(APart node)
    {
        inAPart(node);
        {
            List<PChord> copy = new ArrayList<PChord>(node.getChordProgression());
            Collections.reverse(copy);
            for(PChord e : copy)
            {
                e.apply(this);
            }
        }
        outAPart(node);
    }

    public void inAChord(AChord node)
    {
        defaultIn(node);
    }

    public void outAChord(AChord node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAChord(AChord node)
    {
        inAChord(node);
        if(node.getFunction() != null)
        {
            node.getFunction().apply(this);
        }
        if(node.getNote() != null)
        {
            node.getNote().apply(this);
        }
        outAChord(node);
    }

    public void inAAllNoteFunction(AAllNoteFunction node)
    {
        defaultIn(node);
    }

    public void outAAllNoteFunction(AAllNoteFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAllNoteFunction(AAllNoteFunction node)
    {
        inAAllNoteFunction(node);
        outAAllNoteFunction(node);
    }

    public void inATonikaFunction(ATonikaFunction node)
    {
        defaultIn(node);
    }

    public void outATonikaFunction(ATonikaFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATonikaFunction(ATonikaFunction node)
    {
        inATonikaFunction(node);
        outATonikaFunction(node);
    }

    public void inASubdominantFunction(ASubdominantFunction node)
    {
        defaultIn(node);
    }

    public void outASubdominantFunction(ASubdominantFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubdominantFunction(ASubdominantFunction node)
    {
        inASubdominantFunction(node);
        outASubdominantFunction(node);
    }

    public void inADominantFunction(ADominantFunction node)
    {
        defaultIn(node);
    }

    public void outADominantFunction(ADominantFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADominantFunction(ADominantFunction node)
    {
        inADominantFunction(node);
        outADominantFunction(node);
    }

    public void inATonikaParFunction(ATonikaParFunction node)
    {
        defaultIn(node);
    }

    public void outATonikaParFunction(ATonikaParFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATonikaParFunction(ATonikaParFunction node)
    {
        inATonikaParFunction(node);
        outATonikaParFunction(node);
    }

    public void inASubdominantParFunction(ASubdominantParFunction node)
    {
        defaultIn(node);
    }

    public void outASubdominantParFunction(ASubdominantParFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubdominantParFunction(ASubdominantParFunction node)
    {
        inASubdominantParFunction(node);
        outASubdominantParFunction(node);
    }

    public void inADominantParFunction(ADominantParFunction node)
    {
        defaultIn(node);
    }

    public void outADominantParFunction(ADominantParFunction node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADominantParFunction(ADominantParFunction node)
    {
        inADominantParFunction(node);
        outADominantParFunction(node);
    }

    public void inAMelody(AMelody node)
    {
        defaultIn(node);
    }

    public void outAMelody(AMelody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMelody(AMelody node)
    {
        inAMelody(node);
        if(node.getNote() != null)
        {
            node.getNote().apply(this);
        }
        outAMelody(node);
    }

    public void inASingleNote(ASingleNote node)
    {
        defaultIn(node);
    }

    public void outASingleNote(ASingleNote node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleNote(ASingleNote node)
    {
        inASingleNote(node);
        outASingleNote(node);
    }

    public void inASplitNote(ASplitNote node)
    {
        defaultIn(node);
    }

    public void outASplitNote(ASplitNote node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASplitNote(ASplitNote node)
    {
        inASplitNote(node);
        if(node.getSecond() != null)
        {
            node.getSecond().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getDivision() != null)
        {
            node.getDivision().apply(this);
        }
        outASplitNote(node);
    }

    public void inAParallelNote(AParallelNote node)
    {
        defaultIn(node);
    }

    public void outAParallelNote(AParallelNote node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParallelNote(AParallelNote node)
    {
        inAParallelNote(node);
        if(node.getP2() != null)
        {
            node.getP2().apply(this);
        }
        if(node.getP1() != null)
        {
            node.getP1().apply(this);
        }
        outAParallelNote(node);
    }

    public void inAStraightDivision(AStraightDivision node)
    {
        defaultIn(node);
    }

    public void outAStraightDivision(AStraightDivision node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStraightDivision(AStraightDivision node)
    {
        inAStraightDivision(node);
        outAStraightDivision(node);
    }

    public void inAPunktureDivision(APunktureDivision node)
    {
        defaultIn(node);
    }

    public void outAPunktureDivision(APunktureDivision node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPunktureDivision(APunktureDivision node)
    {
        inAPunktureDivision(node);
        outAPunktureDivision(node);
    }
}
