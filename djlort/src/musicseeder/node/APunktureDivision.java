/* This file was generated by SableCC (http://www.sablecc.org/). */

package musicseeder.node;

import musicseeder.analysis.*;

@SuppressWarnings("nls")
public final class APunktureDivision extends PDivision
{

    public APunktureDivision()
    {
        // Constructor
    }

    @Override
    public Object clone()
    {
        return new APunktureDivision();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPunktureDivision(this);
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