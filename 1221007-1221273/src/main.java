
import control.Controller;
import window.GameWindow;

public class main {

		public static void main (String [] args) {
			Controller controller = Controller.getController();
			GameWindow w= new GameWindow(controller);
			w.setTitle("Xadrez");
			w.setVisible(true);
			w.setLayout(null);
			
		}
	
}
