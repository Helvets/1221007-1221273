package control;
import pieces.*;

public interface Observed {

	public void remove(Observer o);
	
	public Piece[][] getPieces();

	public void add(Observer o);
}
