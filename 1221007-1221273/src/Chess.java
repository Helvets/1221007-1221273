import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;

public class Chess extends Board{
	public static final int size = 50;
	public boolean isBlackTurn;
	public Selected selected = new Selected();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	public Chess() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 1) {
					pieces[i][j] = new Peao(Color.black);
				} else if (j == 6) {
					pieces[i][j] = new Peao(Color.white);
				} else {
					pieces[i][j] = new Vago();
				}
			}
		}
		pieces[4][0] = new Rei(Color.black);
		pieces[4][7] = new Rei(Color.white);
		pieces[0][0] = new Torre(Color.black);
		pieces[7][0] = new Torre(Color.black);
		pieces[0][7] = new Torre(Color.white);
		pieces[7][7] = new Torre(Color.white);
		pieces[3][0] = new Dama(Color.black);
		pieces[3][7] = new Dama(Color.white);
		pieces[2][0] = new Bispo(Color.black);
		pieces[5][0] = new Bispo(Color.black);
		pieces[2][7] = new Bispo(Color.white);
		pieces[5][7] = new Bispo(Color.white);
		pieces[1][0] = new Cavalo(Color.black);
		pieces[6][0] = new Cavalo(Color.black);
		pieces[1][7] = new Cavalo(Color.white);
		pieces[6][7] = new Cavalo(Color.white);
		isBlackTurn = false;
	}
	
	public void moveList(int x, int y) {
		if (isBlackTurn && pieces[x][y].cor == Color.black) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.white && pieces[i][j].toString()!="vazio") {
							pieces[i][j].isHighlighted= true;
						}	
					}	
				}
			}
		}else if (!isBlackTurn && pieces[x][y].cor == Color.white) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.black && pieces[i][j].toString()!="vazio") {
							pieces[i][j].isHighlighted= true;
						}
					}
				}
			}
		}
	}
	
	public boolean isTheWayClear(int x1, int y1, int x2, int y2) {
		if (pieces[x1][y1].canJump) {
			return true;
		} 
		int xDifference = x2 - x1;
		int yDifference = y2 - y1;
		if (Math.abs(xDifference) == Math.abs(yDifference)) {
			if (x2 > x1 && y2 > y1) {
				x1++;
				y1++;
				while (x2 > x1 && y2 > y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1++;
					y1++;
				}
			} else if (x2 > x1 && y2 < y1) {
				x1++;
				y1--;
				while (x2 > x1 && y2 < y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1++;
					y1--;
				}
			} else if (x2 < x1 && y2 < y1) {
				x1--;
				y1--;
				while (x2 < x1 && y2 < y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1--;
					y1--;
				}
			} else {
				x1--;
				y1++;
				while (x2 < x1 && y2 > y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1--;
					y1++;
				}
			}
		} else {
			if (xDifference == 0) {
				if (y2 > y1) {
					for (int i = y1+1; i < y2; i++) {
						if (pieces[x1][i].toString()!="vazio") {
							return false;
						}
					}
				} else {
					for (int i = y1-1; i > y2; i--) {
						if (pieces[x1][i].toString()!="vazio") {
							return false;
						}
					}
				}
			} else {
				if (x2 > x1) {
					for (int i = x1+1; i < x2; i++) {
						if (pieces[i][y1].toString()!="vazio") {
							return false;
						}
					}
				} else {
					for (int i = x1-1; i > x2; i--) {
						if (pieces[i][y1].toString()!="vazio") {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void ClearSelecction() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j].isHighlighted=false;
				pieces[i][j].isSelected=false;
			}
		}

	}
	
	public void move(int x, int y) {
		pieces[x][y]=pieces[selected.i][selected.j];
		pieces[x][y].isSelected=false;;
		pieces[selected.i][selected.j]= new Vago();
		isBlackTurn= !isBlackTurn;
	}
}
