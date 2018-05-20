import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Vago extends Piece {
	public Vago() {
		this.cor = Color.green;
		this.canJump = false;
		this.isHighlighted = false;
		this.isSelected = false;
	}
	//sobre-esccrita
	public void drawYourself(Graphics g, int positionX, int positionY, int squareWidth) {
		Graphics2D g2d = (Graphics2D) g;
			if (this.isHighlighted) {
				g2d.setColor(Color.green);
			    g2d.setStroke(new BasicStroke(3));
				g2d.drawOval(positionX+5, positionY+5,squareWidth-10, squareWidth-10);
			}
	}

	public boolean canMove(int x, int y) {
		return false;
	}


	public boolean canCapture(int x, int y) {
		return false;
	}
	public String toString() {
		return "vazio";
	}
}
