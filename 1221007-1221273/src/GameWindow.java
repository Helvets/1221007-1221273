import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameWindow extends JFrame{
	public final int LARG_PADRAO = 400*3;
	public final int ALT_PADRAO = 300*3;
	
//	public static final int size = 50;
////	public static final int BOARD_MARGIN = 20;
	Board game_board = new Board();
	public GameWindow () {
		game_board.setLayout(null);
		game_board.setBackground(Color.GREEN);
		game_board.iniChessBoard();
		add(game_board);

		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_PADRAO/2;
		int y= sa/2 - ALT_PADRAO/2;
		
		setBounds(x,y,LARG_PADRAO, ALT_PADRAO);
		
//		setSize(size*8+BOARD_MARGIN*2, size*8+BOARD_MARGIN*3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}


}
