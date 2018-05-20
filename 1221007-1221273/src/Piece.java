import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public abstract class Piece {
	public boolean canJump;
	public Color cor;
	public boolean isHighlighted;
	public boolean isSelected;
	public boolean isFirstTurn;
	
	protected Image img_branco;
	protected Image img_preto;
	
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		if (this.cor == Color.black) {
			g.drawImage(img_preto, positionX, positionY, squareWidth, squareWidth, null);
		} else {
			g.drawImage(img_branco, positionX, positionY, squareWidth, squareWidth, null);
		}
		Graphics2D g2d = (Graphics2D) g;
		if (this.isHighlighted) {
			g2d.setColor(Color.green);
		    g2d.setStroke(new BasicStroke(3));
			g2d.drawOval(positionX+5, positionY+5,squareWidth-10, squareWidth-10);
		}
		if (this.isSelected) {
			g2d.setColor(Color.red);
		    g2d.setStroke(new BasicStroke(3));
			g2d.drawOval(positionX+5, positionY+5,squareWidth-10, squareWidth-10);
		}
				
	}
	public abstract boolean canMove(int x, int y);
	public abstract boolean canCapture(int x, int y);
}
