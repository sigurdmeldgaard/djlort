/* This file was generated by SableCC (http://www.sablecc.org/). */

package musicseeder.node;

import musicseeder.analysis.*;

@SuppressWarnings("nls")
public final class AStraightDivision extends PDivision
{

    public AStraightDivision()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new AStraightDivision();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStraightDivision(this);
    }

    @Override
    public String toString()
    {
        return "";
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        throw new RuntimeException("Not a child.");
    }
}
