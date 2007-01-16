/* This file was generated by SableCC (http://www.sablecc.org/). */

package musicseeder.node;

import musicseeder.analysis.*;

@SuppressWarnings("nls")
public final class AMelody extends PMelody
{
    private PNote _note_;

    public AMelody()
    {
        // Constructor
    }

    public AMelody(
        @SuppressWarnings("hiding") PNote _note_)
    {
        // Constructor
        setNote(_note_);

    }

    @Override
    public Object clone()
    {
        return new AMelody(
            cloneNode(this._note_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMelody(this);
    }

    public PNote getNote()
    {
        return this._note_;
    }

    public void setNote(PNote node)
    {
        if(this._note_ != null)
        {
            this._note_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._note_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._note_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._note_ == child)
        {
            this._note_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._note_ == oldChild)
        {
            setNote((PNote) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}