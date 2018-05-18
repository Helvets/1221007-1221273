import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame{
	public final int LARG_PADRAO = 400*3;
	public final int ALT_PADRAO = 300*3;
	Board game_board = new Board();
	
	
	public GameWindow () {
		//super(s); //pra que serve?
		
		game_board.setBackground(Color.GREEN);
		getContentPane().add(game_board);
		
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
