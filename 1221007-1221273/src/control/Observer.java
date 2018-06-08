package control;

import java.awt.Color;

public interface Observer {
	public void notify(Observed o);
	public void notifyPromotion(Observed o, int i, int j, Color cor);
}
