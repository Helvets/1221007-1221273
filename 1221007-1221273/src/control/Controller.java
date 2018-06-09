package control;

import game.Chess;

public class Controller {
	private static Controller controller = null;
	private static Observed observed = null;
	private static Observer observer = null;
	
	private Controller() {
		observed = Chess.getChess();
	}
	
	public static Controller getController() {
		if(controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public void register(Observer o) {
		observer = o;
		observed.add(observer);
	}
	
	public static Observed getObserved() {
		return observed;
	}
	
	public static Observer getObserver() {
		return observer;
	}
	public void clickAction(int i, int j) {
		Chess.getChess().click(i, j);
	}
}
