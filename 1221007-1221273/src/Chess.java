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
					pieces[i][j] = new Peao(true);
				} else if (j == 6) {
					pieces[i][j] = new Peao(false);
				} else {
					pieces[i][j] = new Vago();
				}
			}
		}
		pieces[4][0] = new Rei(true);
		pieces[4][7] = new Rei(false);
		pieces[0][0] = new Torre(true);
		pieces[7][0] = new Torre(true);
		pieces[0][7] = new Torre(false);
		pieces[7][7] = new Torre(false);
		pieces[3][0] = new Dama(true);
		pieces[3][7] = new Dama(false);
		pieces[2][0] = new Bispo(true);
		pieces[5][0] = new Bispo(true);
		pieces[2][7] = new Bispo(false);
		pieces[5][7] = new Bispo(false);
		pieces[1][0] = new Cavalo(true);
		pieces[6][0] = new Cavalo(true);
		pieces[1][7] = new Cavalo(false);
		pieces[6][7] = new Cavalo(false);
		isBlackTurn = false;
	}
	
	public void moveList(int x, int y) {
		if (isBlackTurn && pieces[x][y].isBlack) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
						}
						if (pieces[x][y].canCapture(x-i, y-j) && !pieces[i][j].isBlack && pieces[i][j].toString()!="vazio") {
							pieces[i][j].isHighlighted= true;
						}	
					}	
				}
			}
		}else if (!isBlackTurn && !pieces[x][y].isBlack) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].isBlack && pieces[i][j].toString()!="vazio") {
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
