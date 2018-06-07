package window;
import javax.swing.*;

import game.Chess;

import java.awt.*;




public class GameWindow extends JFrame {
	public static final int size = 100;
	protected final int LARG_PADRAO = size*8+50;
	protected final int ALT_PADRAO = size*8 +50;
	

	
	public GameWindow () {
		Board board = new Board();
		board.setLayout(null);
		board.setBackground(Color.GREEN);
		add(board);

		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_PADRAO/2;
		int y= sa/2 - ALT_PADRAO/2;
		setBounds(x,y,LARG_PADRAO, ALT_PADRAO);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
}
