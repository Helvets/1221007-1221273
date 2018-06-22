package window;

import javax.swing.*;
import control.Controller;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JFrame implements MouseListener{
	public static final int size = 100;
	protected final int LARG_PADRAO = size*8+50;
	protected final int ALT_PADRAO = size*8 +50;
	Controller controller;
	Board board = new Board();

	
	public GameWindow (Controller cont) {
		board.setLayout(null);
		board.setBackground(Color.GREEN);
		add(board);
		addMouseListener(this);
		controller=cont;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_PADRAO/2;
		int y= sa/2 - ALT_PADRAO/2;
		setBounds(x,y,LARG_PADRAO, ALT_PADRAO);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void mouseClicked(MouseEvent e) {

		if(SwingUtilities.isLeftMouseButton(e))
		{
			int selectedSquareX = (e.getX() - 8) / size;
			int selectedSquareY = (e.getY() - 30) / size;
			controller.clickAction(selectedSquareX,selectedSquareY);
		}
		else if (SwingUtilities.isRightMouseButton(e))
		{
			controller.clickActionRight();
		}
	}
		

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent arg0) {
		
	}
}
