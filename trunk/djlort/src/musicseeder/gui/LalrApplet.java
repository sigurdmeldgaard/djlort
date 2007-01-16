package musicseeder.gui;

import java.applet.*;
import java.awt.*;

public class LalrApplet extends Applet {
    public void init() {
	new musicseeder.gui.Main().run(this);
    }
}
