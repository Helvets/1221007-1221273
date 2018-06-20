package game;

import control.*;
import pieces.*;
import sun.awt.RepaintArea;

import java.awt.*;

import javax.swing.JOptionPane;

public class Chess implements  Observed {
	
	private static Chess chess = null;
	private Observer obs;
	public Piece[][] pieces = new Piece[8][8];
	private Piece[][] piecesbackup = new Piece[8][8];
	private boolean isBlackTurn;

	private Selected selected = new Selected();
	Promotion promotionIstance;
	private Color colorcheck;
	protected int reiPretoX=4;
	protected int reiPretoY=0;
	protected int reiBrancoX=4;
	protected int reiBrancoY=7;
	protected boolean blackIsCheked=false;
	protected boolean whiteIsCheked=false;
	public Chess() { //talvez botar protected ou private pro ivan nao reclamar, pra nao ser instanciado mais de uma vez (padrão singleton)
		ChessInitializer();
	}
	
	public static Chess getChess() {
		if(chess == null)
			chess = new Chess();
		return chess;
	}
	public Piece[][] getPieces() {
		return this.pieces;
	}
	
	public boolean isBlackTurn() {
		return isBlackTurn;
	}

	public void setBlackTurn(boolean isBlackTurn) {
		this.isBlackTurn = isBlackTurn;
	}
	
	public void add(Observer o) {
		obs = o;
		
	}
	
	public void remove(Observer o) {
		
	}

	private void ChessInitializer() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (j == 1) { //Preenche todos peos pretos na matriz de peoas
					pieces[i][j] = new Peao(Color.black);
				} else if (j == 6) { //Preenche todos peÃµes brancos na matriz de peoas
					pieces[i][j] = new Peao(Color.white);
				} else {
					pieces[i][j] = new Vago();
				}
			}
		}
		//Preenche matriz com as demais peÃ§as
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
	// x e y sao as posicoes da peca a ser movida
	private void moveList(int x, int y) {
		if (isBlackTurn && pieces[x][y].cor == Color.black) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isTheWayClear(x,y,i,j)) {
						if (pieces[x][y].canMove(x-i, y-j) && pieces[i][j].toString()=="vazio" ) {
							pieces[i][j].isHighlighted= true;
							CheckRestriction(Color.black, x, y); //remove o destaque se eh uma jogada ilegal pois nao tira cheque
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.white) {
							pieces[i][j].isHighlighted= true;
							CheckRestriction(Color.black, x, y); //remove o destaque se eh uma jogada ilegal pois nao tira cheque
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
							CheckRestriction(Color.white, x, y);//remove o destaque se eh uma jogada ilegal pois nao tira cheque
						}
						if (pieces[x][y].canCapture(x-i, y-j) && pieces[i][j].cor == Color.black) {
							pieces[i][j].isHighlighted= true;
							CheckRestriction(Color.white, x, y);//remove o destaque se eh uma jogada ilegal pois nao tira cheque
						}
					}
				}
			}
		}
	}
	
	//retorna true se casa eh cavalo
	//retorna true para as diagonais, verticais e horizontais livres de pecas
	private boolean isTheWayClear(int x1, int y1, int x2, int y2) {
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
	private void ClearSelecction() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j].isHighlighted=false;
				pieces[i][j].isSelected=false;
			}
		}

	}

	private void move(int x, int y) {
		if (pieces[selected.i][selected.j].toString() == "peao-preto" && y==7) {//promocao preta
			promotionIstance=new Promotion (x, y, Color.black, obs);
			promotionIstance.popupShow();
		}
		else if (pieces[selected.i][selected.j].toString() == "peao-branco" && y==0) {//promocao branca
			promotionIstance =new Promotion (x, y, Color.white, obs);
			promotionIstance .popupShow();
		}
		if (pieces[selected.i][selected.j] instanceof Rei) {
			if (!isBlackTurn) {
				reiBrancoX=x;
				reiBrancoY=y;
				
			}else {
				reiPretoX=x;
				reiPretoY=y;
			}
		}
		pieces[x][y]=pieces[selected.i][selected.j];			//selected.i e j sao guardados na chamada da "click"
		pieces[x][y].isSelected=false;
		pieces[x][y].isFirstMove=false;
		pieces[selected.i][selected.j]= new Vago();
		isBlackTurn= !isBlackTurn;

		if (isBlackTurn) colorcheck=Color.black;
		else colorcheck=Color.white;
		
		if (isInCheck(colorcheck)){
			if(colorcheck == Color.black) {
				blackIsCheked=true;
				System.out.printf("check no rei preto \n");		
			}
			else {
				whiteIsCheked=true;
				System.out.printf("check no rei branco \n");
			}
			if (isCheckmate(colorcheck)) {
				
				String end_text;
	
				
				if (isBlackTurn) 
				{
					end_text = "Fim de Jogo, Branco Ganhou";
				}
					
					//System.out.printf("Fim de Jogo, Branco Ganhou \n");
				else {
					
					end_text = "Fim de Jogo, Preto Ganhou";
					//System.out.printf("Fim de Jogo, Preto Ganhou \n");
				}

				// mostra um painel JOptionPane usando showMessageDialog
				JOptionPane.showMessageDialog(null,
						 end_text,
						     "Mensagem de Game Over",
						     JOptionPane.INFORMATION_MESSAGE); //information_message = define o tipo do popup
				
				ChessInitializer();
			}
		} else {
			if (isBlackTurn) blackIsCheked=false;
			else whiteIsCheked=false;
		}
		obs.notify(this);

	}
	
	//executa click
	public void click(int i, int j) {
		if(Promotion.flag) { //caso o player nao tenha selecionado do popup menu a promocao
			ClearSelecction();
			promotionIstance.popupShow();
			return;
		}
		System.out.printf("[%d][%d] ", i,j);
		System.out.printf("%s\n", pieces[i][j].toString() );
		if (i >= 0 && j >= 0 && i < 8 && j < 8) {
			if (!selected.someoneIsSelected) { 					//Se ninguem esta¡ selecionado, entra aqui
				pieces[i][j].isSelected = true; 				//A peca esta¡ selecionada
				moveList(i, j); 								//movimentos que a peca pode fazer
				selected.SelectedUpdate(true, i, j); 			//Atualiza dados da peca selecionada, agora a peÃ§a selecionada esta¡ guardada em selected
			} 
			else if (pieces[i][j].isHighlighted) {				//Se clicou onde esta¡ com Highlight (movimento permitido) entra aqui
				move(i, j);										//Move a peca
				ClearSelecction();
				selected.someoneIsSelected = false;
			} else if (roque(i,j)) {
				ClearSelecction();
				selected.someoneIsSelected = false;
			}else if ((isBlackTurn && pieces[i][j].cor == Color.black) || (!isBlackTurn &&pieces[i][j].cor == Color.white)) 
			{
					ClearSelecction();
					pieces[i][j].isSelected = true;
					moveList(i, j);
					selected.SelectedUpdate(true, i, j);
			}
		}
		obs.notify(this);
	}
	
	public boolean roque(int i, int j) 
	{
		if (isBlackTurn && pieces[selected.i][selected.j].toString() == "rei-preto" && pieces[i][j].toString() == "torre-preta" &&
				pieces[selected.i][selected.j].isFirstMove && pieces[i][j].isFirstMove)
		{
			if (i==0 && pieces[i+1][j].toString() =="vazio" && pieces[i+2][j].toString() =="vazio" && pieces[i+3][j].toString() =="vazio") {
				pieces[i+3][j]=pieces[i][j];
				pieces[i][j] = new Vago();	
				move(i+2,j);
				System.out.println("roque preto longo");
				return true;
			}else if (i==7 && pieces[i-1][j].toString() =="vazio" && pieces[i-2][j].toString() =="vazio") {
				pieces[i-2][j]=pieces[i][j];
				pieces[i][j] = new Vago();	
				move(i-1,j);
				System.out.println("roque Preto curto");
				return true;
			}

		}
		else if(!isBlackTurn && pieces[selected.i][selected.j].toString() == "rei-branco" && pieces[i][j].toString() == "torre-branca"&&
				pieces[selected.i][selected.j].isFirstMove && pieces[i][j].isFirstMove) 
		{
			if (i==0 && pieces[i+1][j].toString() =="vazio" && pieces[i+2][j].toString() =="vazio" && pieces[i+3][j].toString() =="vazio") 
			{
				pieces[i+3][j]=pieces[i][j];
				pieces[i][j] = new Vago();	
				move(i+2,j);
				System.out.println("roque branco longo");
				return true;
				
			}else if (i==7 && pieces[i-1][j].toString() =="vazio" && pieces[i-2][j].toString() =="vazio")
			{
				pieces[i-2][j]=pieces[i][j];
				pieces[i][j] = new Vago();	
				move(i-1,j);
				System.out.println("roque branco curto");
				return true;
			}
		}
		return false;
	}
	
	public boolean isInCheck(Color cor) {
		boolean isChecked = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pieces[i][j].cor==Color.white && cor ==Color.black) {
					if(pieces[i][j].canCapture(i-reiPretoX ,j-reiPretoY) && isTheWayClear(i, j, reiPretoX, reiPretoY)) {
						isChecked = true;
						colorcheck=cor;
						//System.out.printf(" %s is in check \n", Rei.getReiPreto().toString());
						return isChecked;
					}
				} 
				else if (pieces[i][j].cor==Color.black && cor ==Color.white)  {
					if(pieces[i][j].canCapture(i-reiBrancoX, j-reiBrancoY) && isTheWayClear(i, j, reiBrancoX, reiBrancoY)) {
						isChecked = true;
						//System.out.printf("%s is in check \n", Rei.getReiBranco().toString());
						colorcheck=cor;
						return isChecked;
					}
				}
			}
		}
		return isChecked;
	}
	
	public boolean isCheckmate(Color cor) {
		int auxreiBrancoX=reiBrancoX;
		int auxreiBrancoY=reiBrancoY;
		int auxreiPretoX=reiPretoX;
		int auxreiPretoY=reiPretoY;
		piecesbackup= copy(pieces);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) { //escolhi a peca para tentar tirar check
				if (pieces[i][j].cor==cor) {
					ClearSelecction();
					moveList(i,j);
					for(int x = 0; x < 8; x++) {
						for(int y = 0; y < 8; y++) { //movi a peca para tentar tirar check
							if (pieces[x][y].isHighlighted) {
								if (pieces[i][j] instanceof Rei) {
									if (cor==Color.white) {
										reiBrancoX=x;
										reiBrancoY=y;
										
									}else {
										reiPretoX=x;
										reiPretoY=y;
									}
								}
								pieces[x][y] = pieces[i][j];
								pieces[i][j]= new Vago();
							}
							if (!isInCheck(cor)) {
								pieces[x][y] = piecesbackup[x][y];
								pieces[i][j]= piecesbackup[i][j];
								if (pieces[i][j] instanceof Rei) {
									if (cor==Color.white) {
										reiBrancoX=auxreiBrancoX;
										reiBrancoY=auxreiBrancoY;
										
									}else {
										reiPretoX=auxreiPretoX;
										reiPretoY=auxreiPretoY;
									}
								}
								ClearSelecction();
								return false;
							}
							pieces[x][y] = piecesbackup[x][y];
							pieces[i][j]= piecesbackup[i][j];
							if (pieces[i][j] instanceof Rei) {
								if (cor==Color.white) {
									reiBrancoX=auxreiBrancoX;
									reiBrancoY=auxreiBrancoY;
									
								}else {
									reiPretoX=auxreiPretoX;
									reiPretoY=auxreiPretoY;
								}
							}
						}
					}
				}
			}

		}
		ClearSelecction();
		return true;
	}
	
	//copia a matriz piece
	// usado para guardar a matriz antes da checagem de check-mate
	public Piece[][] copy(Piece[][] old){
		Piece[][] current= new Piece[8][8];
		for(int i=0; i<old.length; i++)
			  for(int j=0; j<old[i].length; j++)
				  current[i][j]=old[i][j];
		return current;
		
	}
	
	//tira os destaques das jogadas invalidas pois nao tiram o player de cheque
	public void CheckRestriction(Color cor, int i, int j) {
		int auxreiBrancoX=reiBrancoX;
		int auxreiBrancoY=reiBrancoY;
		int auxreiPretoX=reiPretoX;
		int auxreiPretoY=reiPretoY;
		piecesbackup= copy(pieces);
		for(int x = 0; x < 8; x++) {
			for(int  y = 0; y < 8; y++) { //movi a peca para tentar tirar check
				if (pieces[x][y].isHighlighted) {
					if (pieces[i][j] instanceof Rei) {
						if (cor==Color.white) {
							reiBrancoX=x;
							reiBrancoY=y;

						}else {
							reiPretoX=x;
							reiPretoY=y;
						}
					}
					pieces[x][y] = pieces[i][j];
					pieces[i][j]= new Vago();
					if (!isInCheck(cor)) {
						pieces[x][y] = piecesbackup[x][y];
						pieces[i][j]= piecesbackup[i][j];
						if (pieces[i][j] instanceof Rei) {
							if (cor==Color.white) {
								reiBrancoX=auxreiBrancoX;
								reiBrancoY=auxreiBrancoY;

							}else {
								reiPretoX=auxreiPretoX;
								reiPretoY=auxreiPretoY;
							}
						}
						pieces[x][y].isHighlighted=true; //eh uma jogada valida para tirar cheque
					}
					else {
						pieces[x][y] = piecesbackup[x][y];
						pieces[i][j]= piecesbackup[i][j];
						if (pieces[i][j] instanceof Rei) {
							if (cor==Color.white) {
								reiBrancoX=auxreiBrancoX;
								reiBrancoY=auxreiBrancoY;

							}else {
								reiPretoX=auxreiPretoX;
								reiPretoY=auxreiPretoY;
							}
						}
						pieces[x][y].isHighlighted=false; //nao eh uma jogada valida para tirar cheque
					}
				}	
			}
		}
	}

	// carrega jogo existente
	public void loadGame(Piece[][] loadedPieces) {
		pieces = loadedPieces;
	}
	
}
