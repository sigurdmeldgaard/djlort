package musicseeder.phases;

import musicseeder.node.*;
import musicseeder.analysis.*;
import musicseeder.gui.*;
import static musicseeder.Util.*;

public abstract aspect GuiPhases extends DepthFirstAdapter{

    public enum PhaseType {ORCHESTER, FILTER, BASIS}

    pointcut init(Main m) : target(m) && call(void musicseeder.gui.Main.init());

    after (Main m) returning : init(m){
	switch(phaseType()) {
	case ORCHESTER:
	    m.orchester.addElement(this);
	    break;
	case FILTER:
	    m.filters.addElement(this);
	    break;
	case BASIS:
	    m.basis.addElement(this);
	    break;
	}
    }

    public PhaseType phaseType() {
	return PhaseType.FILTER;
    }

    public String DepthFirstAdapter.title(){
	return this.getClass().toString();
    }
    public String DepthFirstAdapter.doc(){
	return title();
    }

    public String toString(){
	return title();
    }
}
