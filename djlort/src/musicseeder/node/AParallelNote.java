/* This file was generated by SableCC (http://www.sablecc.org/). */

package musicseeder.node;

import musicseeder.analysis.*;

@SuppressWarnings("nls")
public final class AParallelNote extends PNote
{
    private PNote _p1_;
    private PNote _p2_;

    public AParallelNote()
    {
        // Constructor
    }

    public AParallelNote(
        @SuppressWarnings("hiding") PNote _p1_,
        @SuppressWarnings("hiding") PNote _p2_)
    {
        // Constructor
        setP1(_p1_);

        setP2(_p2_);

    }

    @Override
    public Object clone()
    {
        return new AParallelNote(
            cloneNode(this._p1_),
            cloneNode(this._p2_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAParallelNote(this);
    }

    public PNote getP1()
    {
        return this._p1_;
    }

    public void setP1(PNote node)
    {
        if(this._p1_ != null)
        {
            this._p1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._p1_ = node;
    }

    public PNote getP2()
    {
        return this._p2_;
    }

    public void setP2(PNote node)
    {
        if(this._p2_ != null)
        {
            this._p2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._p2_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._p1_)
            + toString(this._p2_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._p1_ == child)
        {
            this._p1_ = null;
            return;
        }

        if(this._p2_ == child)
        {
            this._p2_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._p1_ == oldChild)
        {
            setP1((PNote) newChild);
            return;
        }

        if(this._p2_ == oldChild)
        {
            setP2((PNote) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
