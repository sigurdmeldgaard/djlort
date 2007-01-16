package musicseeder.generate;
import musicseeder.*;
import musicseeder.analysis.*;
import musicseeder.node.*;
import static musicseeder.Util.*;

import java.util.*;

public interface Song{
    public ASong generate();
    public String name();
}

