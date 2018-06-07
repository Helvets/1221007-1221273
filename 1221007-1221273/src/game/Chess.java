package game;

import control.*;
import pieces.*;

import static window.GameWindow.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Chess extends MouseAdapter implements MouseListener, Observed {
	
	private static Chess chess = null;
	private Observer obs;
	private Piece[][] pieces = new Piece[8][8];
	private boolean isBlackTurn;
	private Selected selected = new Selected();

	public Chess() {
		ChessInitializer();
	}
	
	public static Chess getChess() {
		if(chess == null)
			chess = new Chess();
		addMouseListener(chess);
		return chess;
	}
	public Piece[][] getPieces() {
		return this.pieces;
	}
	
	public void add(Observer o) {
		obs = o;
		
	}
	
	public void remove(Observer o) {
		
	}

	private void ChessInitializer() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 1) { //Preenche todos peões pretos na matriz de peças
					pieces[i][j] = new Peao(Color.black);
				} else if (j == 6) { //Preenche todos peões brancos na matriz de peças
					pieces[i][j] = new Peao(Color.white);
				} else {
					pieces[i][j] = new Vago();
				}
			}
		}
		//Preenche matriz com as demais peças
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
	
	
	//Ilumina/destaca movementos validos
	//usado na implementacao do movimento
	public void moveList(int x, int y) {
		if (isBlackTurn && pieces[x][y].cor == Color.black) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.white) {
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
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.black) {
							pieces[i][j].isHighlighted= true;
						}
					}
				}
			}
		}
	}
	
	//retorna true se casa eh cavalo
	//retorna true para as diagonais, verticais e horizontais livres de pecas
	public boolean isTheWayClear(int x1, int y1, int x2, int y2) {
		if (pieces[x1][y1].canJump) {  // cavalho
			return true;
		} 
		int xDifference = x2 - x1;
		int yDifference = y2 - y1;
		if (Math.abs(xDifference) == Math.abs(yDifference)) { //diagonal
			if (x2 > x1 && y2 > y1) {			// inferior direita
				x1++;
				y1++;
				while (x2 > x1 && y2 > y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1++;
					y1++;
				}
			} else if (x2 > x1 && y2 < y1) {  //diagonal superior
				x1++;
				y1--;
				while (x2 > x1 && y2 < y1) {
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1++;
					y1--;
				}
			} else if (x2 < x1 && y2 < y1) {  // diagonal esquerda superior
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
				while (x2 < x1 && y2 > y1) {  //diagonal esquerda inferior
					if (pieces[x1][y1].toString()!="vazio") {
						return false;
					}
					x1--;
					y1++;
				}
			}
		} else {
			if (xDifference == 0) {   //vertical
				if (y2 > y1) {
					for (int i = y1+1; i < y2; i++) { // para baixo
						if (pieces[x1][i].toString()!="vazio") {
							return false;
						}
					}
				} else {
					for (int i = y1-1; i > y2; i--) { //para cima
						if (pieces[x1][i].toString()!="vazio") {
							return false;
						}
					}
				}
			} else {		// horizontal
				if (x2 > x1) {
					for (int i = x1+1; i < x2; i++) {  //direita
						if (pieces[i][y1].toString()!="vazio") {
							return false;
						}
					}
				} else {
					for (int i = x1-1; i > x2; i--) { //esquerda
						if (pieces[i][y1].toString()!="vazio") {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	
	//limpa as selecoes e destaques
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
		pieces[x][y].isSelected=false;
		pieces[x][y].isFirstMove=false;
		pieces[selected.i][selected.j]= new Vago();
		isBlackTurn= !isBlackTurn;
	}
	

	
	public void mouseClicked(MouseEvent e) {

		int selectedSquareX = (e.getX() - 8) / size;
		int selectedSquareY = (e.getY() - 30) / size;
		System.out.printf("%d ", selectedSquareX);
		System.out.printf("%d\n", selectedSquareY);
		if (selectedSquareX >= 0 && selectedSquareY >= 0 && selectedSquareX < 8 && selectedSquareY < 8) {
			if (!selected.someoneIsSelected) {
				pieces[selectedSquareX][selectedSquareY].isSelected = true;
				moveList(selectedSquareX, selectedSquareY);
				selected.SelectedUpdate(true, selectedSquareX, selectedSquareY);
			} else if (pieces[selectedSquareX][selectedSquareY].isHighlighted) {
				move(selectedSquareX, selectedSquareY);
				ClearSelecction();
				selected.someoneIsSelected = false;
			} else if ((isBlackTurn && pieces[selectedSquareX][selectedSquareY].cor == Color.black)
					|| (!this.isBlackTurn &&pieces[selectedSquareX][selectedSquareY].cor == Color.white)) {
				ClearSelecction();
				pieces[selectedSquareX][selectedSquareY].isSelected = true;
				moveList(selectedSquareX, selectedSquareY);
				selected.SelectedUpdate(true, selectedSquareX, selectedSquareY);
			}
			obs.notify(this);
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
