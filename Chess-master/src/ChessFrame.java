import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessFrame extends JFrame implements MouseListener{
	public static final int SQUARE_WIDTH = 45;
	public static final int BOARD_MARGIN = 50;
	int selectedSquareX = -1;
	int selectedSquareY = -1;
	Piece pieces[][] = new Piece[8][8];
	JPanel p = new JPanel();
	JButton bt = new JButton("UNDO");
	JButton s = new JButton("SAVE");
	public boolean isBlackTurn;
	public int whiteKingX = 4;
	public int whiteKingY = 7;
	public int blackKingX = 4;
	public int blackKingY = 0;
	public Stack<String> moves = new Stack<String>();
	
	
	public ChessFrame()
	{
		p.setLayout(null);
		bt.setBounds(130, SQUARE_WIDTH*8+BOARD_MARGIN+10, 100, 50);
		p.add(bt);
		bt.addActionListener(e->{
			undo();
		});
		add(p);
		s.setBounds(250, SQUARE_WIDTH*8+BOARD_MARGIN+10, 100, 50);
		p.add(s);
		s.addActionListener(e->{
			save("game.txt");
		});
		add(p);
		initializeChessBoard();
		setTitle("Chess Game");
		//let the screen size fit the board size
		setSize(SQUARE_WIDTH*8+BOARD_MARGIN*2, SQUARE_WIDTH*8+BOARD_MARGIN*3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		isBlackTurn = false;
		
	}
	
	public void initializeChessBoard()
	{
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				if(j == 1)
				{
					pieces[i][j] = new Pawn(true);
				}
				else if(j == 6)
				{
					pieces[i][j] = new Pawn(false);
				}
				else
				{
					pieces[i][j] = null;
				}
			}
		}
		pieces[4][0] = new King(true);
		pieces[4][7] = new King(false);
		pieces[0][0] = new Rook(true);
		pieces[7][0] = new Rook(true);
		pieces[0][7] = new Rook(false);
		pieces[7][7] = new Rook(false);
		pieces[3][0] = new Queen(true);
		pieces[3][7] = new Queen(false);
		pieces[2][0] = new Bishop(true);
		pieces[5][0] = new Bishop(true);
		pieces[2][7] = new Bishop(false);
		pieces[5][7] = new Bishop(false);
		pieces[1][0] = new Knight(true);
		pieces[6][0] = new Knight(true);
		pieces[1][7] = new Knight(false);
		pieces[6][7] = new Knight(false);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//print the board 's lines to show squares
		for(int i = 0; i<=8; i++)
		{
			g.drawLine(BOARD_MARGIN, 
					BOARD_MARGIN+(i)*SQUARE_WIDTH, 
					BOARD_MARGIN+8*SQUARE_WIDTH, 
					BOARD_MARGIN+(i)*SQUARE_WIDTH);
			g.drawLine(BOARD_MARGIN+(i)*SQUARE_WIDTH, 
					BOARD_MARGIN, 
					BOARD_MARGIN+(i)*SQUARE_WIDTH, 
					BOARD_MARGIN+8*SQUARE_WIDTH);
		}
		//print the pieces, background, numbers and letters
		
		for(int i = 0; i<8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				g.setColor(Color.BLACK);
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
				g.drawString((i+1)+"", SQUARE_WIDTH-20, (9-i)*SQUARE_WIDTH-10);
				g.drawString((i+1)+"",10*SQUARE_WIDTH-20, (9-i)*SQUARE_WIDTH-10);
				g.drawString("a      b     c     d      e     f     g     h", 
						SQUARE_WIDTH+20, SQUARE_WIDTH-5);
				g.drawString("a      b     c     d      e     f     g     h", 
						SQUARE_WIDTH+20, 10*SQUARE_WIDTH-15);
				
				if((i+j)%2 == 0) {
					g.setColor(Color.GREEN);
					g.fillRect(i*SQUARE_WIDTH+BOARD_MARGIN, j*SQUARE_WIDTH+BOARD_MARGIN,
							SQUARE_WIDTH, SQUARE_WIDTH);
					
				} else {
					g.setColor(Color.PINK);
					g.fillRect(i*SQUARE_WIDTH+BOARD_MARGIN, j*SQUARE_WIDTH+BOARD_MARGIN,
							SQUARE_WIDTH, SQUARE_WIDTH);
				}
				
				if(pieces[i][j] != null)
				{
					
					pieces[i][j].drawYourself(g, i*SQUARE_WIDTH+BOARD_MARGIN, 
							j*SQUARE_WIDTH+BOARD_MARGIN, SQUARE_WIDTH);
					
				}
				
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Clicked");
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Pressed");
		//calculate which square is selected 
		selectedSquareX = (e.getX()-BOARD_MARGIN)/SQUARE_WIDTH;
		selectedSquareY = (e.getY()-BOARD_MARGIN)/SQUARE_WIDTH;
		//System.out.println(selectedSquareX+","+selectedSquareY);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Released");
		//calculate which square is targeted
		int targetSquareX = (e.getX()-BOARD_MARGIN)/SQUARE_WIDTH;
		int targetSquareY = (e.getY()-BOARD_MARGIN)/SQUARE_WIDTH;
		//System.out.println(targetSquareX+","+targetSquareY+"\n");
		
		//if these are inside the board
		if(selectedSquareX >= 0 && selectedSquareY >= 0 &&
				selectedSquareX < 8 && selectedSquareY < 8 &&
				targetSquareX >= 0 && targetSquareY >= 0 &&
						targetSquareX < 8 && targetSquareY < 8)
		{
			String pre = (char)(selectedSquareX+97) + ""+ (char)((7-selectedSquareY)+49);
			//System.out.println(pre);
			String post = (char)(targetSquareX+97) + "" + (char)((7-targetSquareY)+49);
			//System.out.println(post);
			if(pieces[selectedSquareX][selectedSquareY]!=null && 
					pieces[selectedSquareX][selectedSquareY].toString().compareTo("black-king")==0 &&
							pieces[targetSquareX][targetSquareY]!= null &&
					pieces[targetSquareX][targetSquareY].toString().compareTo("black-rook")==0 && 
					isBlackTurn==pieces[selectedSquareX][selectedSquareY].isBlack) {
				if(post.compareTo("h8")==0) {
					castling(true);
				} else {
					castling(false);
				}
			} else if(pieces[selectedSquareX][selectedSquareY]!=null && 
					pieces[selectedSquareX][selectedSquareY].toString().compareTo("white-king")==0 &&
							pieces[targetSquareX][targetSquareY]!= null &&
					pieces[targetSquareX][targetSquareY].toString().compareTo("white-rook")==0 &&
					isBlackTurn==pieces[selectedSquareX][selectedSquareY].isBlack) {
				if(post.compareTo("h1")==0) {
					castling(true);
				} else {
					castling(false);
				}
				
			} else {
				move(pre, post);
			}
			
		/*	System.out.println("inside");
			if(pieces[selectedSquareX][selectedSquareY] != null)
			{
				System.out.println("selected");
				int diffX = targetSquareX - selectedSquareX;
				int diffY = targetSquareY - selectedSquareY;
				
				if(pieces[targetSquareX][targetSquareY] != null && 
						pieces[targetSquareX][targetSquareY].isBlack!=pieces[selectedSquareX][selectedSquareY].isBlack)
				{
					System.out.println("a target");
					if((pieces[selectedSquareX][selectedSquareY].canCapture(diffX, diffY)) &&
						isTheWayClear(selectedSquareX, selectedSquareY, targetSquareX, targetSquareY) &&
						(isBlackTurn == pieces[selectedSquareX][selectedSquareY].isBlack)) 
					
					{
						System.out.println("can capture");
						pieces[targetSquareX][targetSquareY] = 
								pieces[selectedSquareX][selectedSquareY];
						pieces[selectedSquareX][selectedSquareY] = null;
						isBlackTurn = !isBlackTurn;
						if (pieces[targetSquareX][targetSquareY] instanceof Pawn) {
							Pawn temp = (Pawn)pieces[targetSquareX][targetSquareY];
							temp.isFirstTurn = false;
						}
					}
				}
				else
				{
					System.out.println("no target");
					if((pieces[selectedSquareX][selectedSquareY].canMove(diffX, diffY) &&
							isTheWayClear(selectedSquareX, selectedSquareY, targetSquareX, targetSquareY))
							&& pieces[targetSquareX][targetSquareY]==null && 
							(isBlackTurn == pieces[selectedSquareX][selectedSquareY].isBlack)) 
					{
						System.out.println("can move");
						pieces[targetSquareX][targetSquareY] = 
								pieces[selectedSquareX][selectedSquareY];
						pieces[selectedSquareX][selectedSquareY] = null;
						isBlackTurn = !isBlackTurn;
						if (pieces[targetSquareX][targetSquareY] instanceof Pawn) {
							Pawn temp = (Pawn)pieces[targetSquareX][targetSquareY];
							temp.isFirstTurn = false;
						}
					}
				}
			} */
		}
		
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Entered");
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Exited");
		
	}
	//checks if there is any piece between target and current destination
	//returns true if the way is clear or the piece can jump (such as knight)
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
					if (pieces[x1][y1] != null) {
						return false;
					}
					x1++;
					y1++;
				}
			} else if (x2 > x1 && y2 < y1) {
				x1++;
				y1--;
				while (x2 > x1 && y2 < y1) {
					if (pieces[x1][y1] != null) {
						return false;
					}
					x1++;
					y1--;
				}
			} else if (x2 < x1 && y2 < y1) {
				x1--;
				y1--;
				while (x2 < x1 && y2 < y1) {
					if (pieces[x1][y1] != null) {
						return false;
					}
					x1--;
					y1--;
				}
			} else {
				x1--;
				y1++;
				while (x2 < x1 && y2 > y1) {
					if (pieces[x1][y1] != null) {
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
						if (pieces[x1][i]!=null) {
							return false;
						}
					}
				} else {
					for (int i = y1-1; i > y2; i--) {
						if (pieces[x1][i]!=null) {
							return false;
						}
					}
				}
			} else {
				if (x2 > x1) {
					for (int i = x1+1; i < x2; i++) {
						if (pieces[i][y1]!=null) {
							return false;
						}
					}
				} else {
					for (int i = x1-1; i > x2; i--) {
						if (pieces[i][y1]!=null) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	public boolean move(String from, String to) {
		String pre = from.toLowerCase();
		String post = to.toLowerCase();
		if(pre.compareTo(post)==0) {
			return false;
		}
		int y1 = Math.abs((int)pre.charAt(1)-49-7);
		
		int x1 = Math.abs((int)pre.charAt(0)-97);
		
		int y2 = Math.abs((int)post.charAt(1)-49-7);
		
		int x2 = Math.abs((int)post.charAt(0)-97);
		
		if(pieces[x2][y2]==null && pieces[x1][y1]!=null) {
			if(isTheWayClear(x1, y1, x2, y2) && isBlackTurn==pieces[x1][y1].isBlack
					&& pieces[x1][y1].canMove(x2-x1, y2-y1) && !isCheckmate()) {
				if(pieces[x1][y1] instanceof Pawn) {
					Pawn temp = (Pawn)pieces[x1][y1];
					boolean firstTurn = temp.isFirstTurn;
					temp.isFirstTurn=false;
					
					moves.push(firstTurn+pieces[x1][y1].toString()+"-"+pre);
					
				} else {
					moves.push(pieces[x1][y1].toString()+"-"+pre);
				}
				moves.push("null"+"-"+post);
				moves.push("move");
				if(y2==0 && pieces[x1][y1] instanceof Pawn) {
					pieces[x2][y2] = new Queen(false);
				} else if (y2==7 && pieces[x1][y1] instanceof Pawn){
					pieces[x2][y2] = new Queen(true);
				} else {
					pieces[x2][y2] = pieces[x1][y1];
				}
				
				pieces[x1][y1] = null;
				
				if(pieces[x2][y2] instanceof King) {
					if(pieces[x2][y2].isBlack){
						blackKingX = x2;
						blackKingY = y2;
					} else {
						whiteKingX = x2;
						whiteKingY = y2;
					}
						
				}
				if(isInCheck()) {
					undo();
				}
				isBlackTurn=!isBlackTurn;
				
				if(isCheckmate()) {
					JFrame gg = new JFrame("GAME ENDS");
					//gg.setDefaultCloseOperation(EXIT_ON_CLOSE);
					JLabel wp = new JLabel("THE GAME IS OVER");
					gg.setSize(200, 200);
					gg.add(wp);
					gg.setVisible(true);
				}
				return true;
			}
		} else if(pieces[x2][y2]!=null && pieces[x1][y1]!=null) {
			if(isTheWayClear(x1, y1, x2, y2) && isBlackTurn==pieces[x1][y1].isBlack 
					 && pieces[x1][y1].canCapture(x2-x1, y2-y1) && 
					pieces[x1][y1].isBlack != pieces[x2][y2].isBlack && !isCheckmate()) {
				
				if(pieces[x1][y1] instanceof Pawn) {
					Pawn temp = (Pawn)pieces[x1][y1];
					boolean firstTurn = temp.isFirstTurn;
					temp.isFirstTurn=false;
					moves.push(firstTurn+pieces[x1][y1].toString()+"-"+pre);
					
				} else {
					moves.push(pieces[x1][y1].toString()+"-"+pre);
				} 
				if(pieces[x2][y2] instanceof Pawn) {
					Pawn temp2 = (Pawn)pieces[x2][y2];
					boolean firstTurn2 = temp2.isFirstTurn;
					temp2.isFirstTurn=false;
					moves.push(firstTurn2+pieces[x2][y2].toString()+"-"+post);
					
				} else {
					moves.push(pieces[x2][y2].toString()+"-"+post);
				}
				if(y2==0 && pieces[x1][y1] instanceof Pawn) {
					pieces[x2][y2] = new Queen(false);
				} else if (y2==7 && pieces[x1][y1] instanceof Pawn){
					pieces[x2][y2] = new Queen(true);
				} else {
					pieces[x2][y2] = pieces[x1][y1];
				}
				moves.push("move");
				pieces[x1][y1] = null;
				
				if(pieces[x2][y2] instanceof King) {
					if(pieces[x2][y2].isBlack){
						blackKingX = x2;
						blackKingY = y2;
					} else {
						whiteKingX = x2;
						whiteKingY = y2;
					}
						
				}
				if(isInCheck()) {
					undo();
				}
				isBlackTurn=!isBlackTurn;
				if(isCheckmate()) {
					JFrame gg = new JFrame("GAME ENDS");
					//gg.setDefaultCloseOperation(EXIT_ON_CLOSE);
					JLabel wp = new JLabel("THE GAME IS OVER");
					gg.setSize(200, 200);
					gg.add(wp);
					gg.setVisible(true);
				}
				return true;
			}
		}
		
		return false;
	}
	public void save(String fileName) {
		
		try {
			
			File f = new File(fileName);
			PrintStream p = new PrintStream(f);
			if(isBlackTurn) {
				p.println("black");
			} else {
				p.println("white");
			}
			for(int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if(pieces[i][j]!=null) {
						String pos = (char)(i+97) + ""+ (char)((7-j)+49);
						p.println(pieces[i][j] + "-"+pos);
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ChessFrame load(String fileName) {
		ChessFrame game = new ChessFrame();
		Scanner reader;
		try {
			reader = new Scanner(new File(fileName));
			String turn = reader.nextLine();
			if(turn.compareTo("black")==0) {
				game.isBlackTurn = true;
			} else {
				game.isBlackTurn = false;
			}
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					game.pieces[i][j] = null;
				}
			}
			while(reader.hasNextLine()){
				String piece = reader.nextLine();
				String type = piece.substring(0, piece.length()-3);
				String pos = piece.substring(piece.length()-2);
				int y = Math.abs((int)pos.charAt(1)-49-7);
				int x = Math.abs((int)pos.charAt(0)-97);
				if(type.compareTo("white-king")==0) {
					game.pieces[x][y] = new King(false);
					game.whiteKingX = x;
					game.whiteKingY = y;
				} else if (type.compareTo("white-bishop")==0) {
					game.pieces[x][y] = new Bishop(false);
				} else if (type.compareTo("white-knight")==0) {
					game.pieces[x][y] = new Knight(false);
				} else if(type.compareTo("white-rook")==0) {
					game.pieces[x][y] = new Rook(false);
				} else if(type.compareTo("white-queen")==0) {
					game.pieces[x][y] = new Queen(false);
				} else if(type.compareTo("white-pawn")==0) {
					game.pieces[x][y] = new Pawn(false);
					Pawn temp = (Pawn)game.pieces[x][y];
					if(y==6) {
						temp.isFirstTurn = true;
					} else {
						temp.isFirstTurn = false;
					}
				} else if(type.compareTo("black-king")==0) {
					game.pieces[x][y] = new King(true);
					game.blackKingX = x;
					game.blackKingY = y;
				} else if (type.compareTo("black-bishop")==0) {
					game.pieces[x][y] = new Bishop(true);
				} else if (type.compareTo("black-knight")==0) {
					game.pieces[x][y] = new Knight(true);
				} else if(type.compareTo("black-rook")==0) {
					game.pieces[x][y] = new Rook(true);
				} else if(type.compareTo("black-queen")==0) {
					game.pieces[x][y] = new Queen(true);
				} else if(type.compareTo("black-pawn")==0) {
					game.pieces[x][y] = new Pawn(true);
					Pawn temp = (Pawn)game.pieces[x][y];
					if(y==1) {
						temp.isFirstTurn = true;
					} else {
						temp.isFirstTurn = false;
					}
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return game;
	}
	public void undo() {
		
		if(!moves.isEmpty()) {
			int counter = 0;
			String moveType = moves.pop();
			if(moveType.compareTo("move")==0) {
				counter = 2;
			} else if (moveType.compareTo("castlingK")==0){
				counter = 4;
			} else {
				counter = 5;
			}
			for(int i = 1; i <= counter; i++) {
				String piece = moves.pop();
				
				String type = piece.substring(0, piece.length()-3);
				String pos = piece.substring(piece.length()-2);
				int y = Math.abs((int)pos.charAt(1)-49-7);
				int x = Math.abs((int)pos.charAt(0)-97);
				if(type.compareTo("white-king")==0) {
					pieces[x][y] = new King(false);
					whiteKingX = x;
					whiteKingY = y;
				} else if (type.compareTo("white-bishop")==0) {
					pieces[x][y] = new Bishop(false);
				} else if (type.compareTo("white-knight")==0) {
					pieces[x][y] = new Knight(false);
				} else if(type.compareTo("white-rook")==0) {
					pieces[x][y] = new Rook(false);
				} else if(type.compareTo("white-queen")==0) {
					pieces[x][y] = new Queen(false);
				} else if(type.compareTo("truewhite-pawn")==0) {
					pieces[x][y] = new Pawn(false);
				} else if(type.compareTo("falsewhite-pawn")==0) {
					pieces[x][y] = new Pawn(false);
					Pawn temp = (Pawn)pieces[x][y];
					temp.isFirstTurn = false;
				} else if(type.compareTo("black-king")==0) {
					pieces[x][y] = new King(true);
					blackKingX = x;
					blackKingY = y;
				} else if (type.compareTo("black-bishop")==0) {
					pieces[x][y] = new Bishop(true);
				} else if (type.compareTo("black-knight")==0) {
					pieces[x][y] = new Knight(true);
				} else if(type.compareTo("black-rook")==0) {
					pieces[x][y] = new Rook(true);
				} else if(type.compareTo("black-queen")==0) {
					pieces[x][y] = new Queen(true);
				} else if(type.compareTo("trueblack-pawn")==0) {
					pieces[x][y] = new Pawn(true);
				} else if(type.compareTo("falseblack-pawn")==0) {
					pieces[x][y] = new Pawn(true);
					Pawn temp = (Pawn)pieces[x][y];
					temp.isFirstTurn = false;
				} else if (type.compareTo("null")==0) {
					pieces[x][y] = null;
				}
			}
			isBlackTurn=!isBlackTurn;
		}
		
		repaint();
	}
	public boolean castling(boolean isKingSide) {
		if(isBlackTurn && isKingSide && pieces[5][0]==null && pieces[6][0]==null &&
				(pieces[4][0].toString().compareTo("black-king")==0) &&
				(pieces[7][0].toString().compareTo("black-rook")==0)) {
			
			moves.push(pieces[4][0].toString()+"-"+(char)(4+97) + ""+ (char)((7-0)+49));
			moves.push("null"+"-"+(char)(5+97) + ""+ (char)((7-0)+49));
			moves.push("null"+"-"+(char)(6+97) + ""+ (char)((7-0)+49));
			moves.push(pieces[7][0].toString()+"-"+(char)(7+97) + ""+ (char)((7-0)+49));
			moves.push("castlingK");
			pieces[6][0] = pieces[4][0];
			pieces[5][0] = pieces[7][0];
			pieces[4][0] = null;
			pieces[7][0] = null;
			blackKingX = 6;
			blackKingY = 0;
			isBlackTurn=!isBlackTurn;
			return true;
		} else if (isBlackTurn && !isKingSide && pieces[3][0]==null && pieces[2][0]==null &&
				pieces[1][0] == null &&
				(pieces[4][0].toString().compareTo("black-king")==0) &&
				(pieces[0][0].toString().compareTo("black-rook")==0)) {
			
			moves.push(pieces[4][0].toString()+"-"+(char)(4+97) + ""+ (char)((7-0)+49));
			moves.push("null"+"-"+(char)(3+97) + ""+ (char)((7-0)+49));
			moves.push("null"+"-"+(char)(2+97) + ""+ (char)((7-0)+49));
			moves.push("null"+"-"+(char)(1+97) + ""+ (char)((7-0)+49));
			moves.push(pieces[0][0].toString()+"-"+(char)(0+97) + ""+ (char)((7-0)+49));
			moves.push("castlingQ");
			pieces[2][0] = pieces[4][0];
			pieces[3][0] = pieces[0][0];
			pieces[4][0] = null;
			pieces[0][0] = null;
			blackKingX = 2;
			blackKingY = 0;
			isBlackTurn=!isBlackTurn;
			return true;
		} else if(!isBlackTurn && isKingSide && pieces[5][7]==null && pieces[6][7]==null &&
				(pieces[4][7].toString().compareTo("white-king")==0) &&
				(pieces[7][7].toString().compareTo("white-rook")==0)) {
			
			moves.push(pieces[4][7].toString()+"-"+(char)(4+97) + ""+ (char)((7-7)+49));
			moves.push("null"+"-"+(char)(5+97) + ""+ (char)((7-7)+49));
			moves.push("null"+"-"+(char)(6+97) + ""+ (char)((7-7)+49));
			moves.push(pieces[7][7].toString()+"-"+(char)(7+97) + ""+ (char)((7-7)+49));
			moves.push("castlingK");
			pieces[6][7] = pieces[4][7];
			pieces[5][7] = pieces[7][7];
			pieces[4][7] = null;
			pieces[7][7] = null;
			whiteKingX = 6;
			whiteKingY = 7;
			isBlackTurn=!isBlackTurn;
			return true;
		} else if (!isBlackTurn && !isKingSide && pieces[3][7]==null && pieces[2][7]==null &&
				pieces[1][7] == null &&
				(pieces[4][7].toString().compareTo("white-king")==0) &&
				(pieces[0][7].toString().compareTo("white-rook")==0)) {
			
			moves.push(pieces[4][7].toString()+"-"+(char)(4+97) + ""+ (char)((7-7)+49));
			moves.push("null"+"-"+(char)(3+97) + ""+ (char)((7-7)+49));
			moves.push("null"+"-"+(char)(2+97) + ""+ (char)((7-7)+49));
			moves.push("null"+"-"+(char)(1+97) + ""+ (char)((7-7)+49));
			moves.push(pieces[0][7].toString()+"-"+(char)(0+97) + ""+ (char)((7-7)+49));
			moves.push("castlingQ");
			pieces[2][7] = pieces[4][7];
			pieces[3][7] = pieces[0][7];
			pieces[4][7] = null;
			pieces[0][7] = null;
			whiteKingX = 2;
			whiteKingY = 7;
			isBlackTurn=!isBlackTurn;
			return true;
		}
		return false;
	}

	public boolean isInCheck() {
		boolean isChecked = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(pieces[i][j]!=null && pieces[i][j].isBlack!=isBlackTurn) {
					if(isBlackTurn) {
						boolean pawnFirstTurn = false;
						if(pieces[i][j] instanceof Pawn) {
							Pawn temp = (Pawn)pieces[i][j];
							pawnFirstTurn = temp.isFirstTurn;
						}
						if(pieces[i][j].canCapture(blackKingX-i, blackKingY-j) &&
								isTheWayClear(i, j, blackKingX, blackKingY)) {
							isChecked = true;
							if(pieces[i][j] instanceof Pawn) {
								Pawn temp = (Pawn)pieces[i][j];
								temp.isFirstTurn = pawnFirstTurn;
							}
						}
					} else {
						boolean pawnFirstTurn = false;
						if(pieces[i][j] instanceof Pawn) {
							Pawn temp = (Pawn)pieces[i][j];
							pawnFirstTurn = temp.isFirstTurn;
						}
						if(pieces[i][j].canCapture(whiteKingX-i, whiteKingY-j) &&
								isTheWayClear(i, j, whiteKingX, whiteKingY)) {
							isChecked = true;
							if(pieces[i][j] instanceof Pawn) {
								Pawn temp = (Pawn)pieces[i][j];
								temp.isFirstTurn = pawnFirstTurn;
							}
						}
					}
				}
			}
		}
		return isChecked;
	}
	
	public boolean isCheckmate() {
		boolean isKingInDanger = false;
		if(isInCheck()) {
			isKingInDanger = true;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					String pre = (char)(i+97) + ""+ (char)((7-j)+49);
					for(int k = 0; k < 8; k++) {
						for (int l = 0; l < 8; l++) {
							String post = (char)(k+97) + ""+ (char)((7-l)+49);
							if(pseudoMove(pre, post)) {
								isBlackTurn = !isBlackTurn;
								if(!isInCheck()) {
									isKingInDanger = false;
								}
								isBlackTurn = !isBlackTurn;
								undo();
							}
						}
						
					}
					
				}
			}
		}
		return isKingInDanger;
	}
	public boolean pseudoMove(String from, String to) {
		String pre = from.toLowerCase();
		String post = to.toLowerCase();
		if(pre.compareTo(post)==0) {
			return false;
		}
		int y1 = Math.abs((int)pre.charAt(1)-49-7);
		
		int x1 = Math.abs((int)pre.charAt(0)-97);
		
		int y2 = Math.abs((int)post.charAt(1)-49-7);
		
		int x2 = Math.abs((int)post.charAt(0)-97);
		if(pieces[x2][y2]==null && pieces[x1][y1]!=null) {
			if(isTheWayClear(x1, y1, x2, y2) && isBlackTurn==pieces[x1][y1].isBlack
					&& pieces[x1][y1].canMove(x2-x1, y2-y1)) {
				if(pieces[x1][y1] instanceof Pawn) {
					if(pieces[x1][y1] instanceof Pawn) {
						Pawn temp = (Pawn)pieces[x1][y1];
						moves.push(temp.isFirstTurn+pieces[x1][y1].toString()+"-"+pre);
					}
				} else {
					moves.push(pieces[x1][y1].toString()+"-"+pre);
				}
				moves.push("null"+"-"+post);
				moves.push("move");
				pieces[x2][y2] = pieces[x1][y1];
				pieces[x1][y1] = null;
				isBlackTurn=!isBlackTurn;
				if(pieces[x2][y2] instanceof King) {
					if(pieces[x2][y2].isBlack){
						blackKingX = x2;
						blackKingY = y2;
					} else {
						whiteKingX = x2;
						whiteKingY = y2;
					}
						
				}
				return true;
			}
		} else if(pieces[x2][y2]!=null && pieces[x1][y1]!=null) {
			if(isTheWayClear(x1, y1, x2, y2) && isBlackTurn==pieces[x1][y1].isBlack 
					 && pieces[x1][y1].canCapture(x2-x1, y2-y1) && 
					pieces[x1][y1].isBlack != pieces[x2][y2].isBlack ) {
				
				if(pieces[x1][y1] instanceof Pawn) {
					if(pieces[x1][y1] instanceof Pawn) {
						Pawn temp = (Pawn)pieces[x1][y1];
						moves.push(temp.isFirstTurn+pieces[x1][y1].toString()+"-"+pre);
					}
				} else {
					moves.push(pieces[x1][y1].toString()+"-"+pre);
				} 
				if(pieces[x2][y2] instanceof Pawn) {
					if(pieces[x2][y2] instanceof Pawn) {
						Pawn temp = (Pawn)pieces[x2][y2];
						moves.push(temp.isFirstTurn+pieces[x2][y2].toString()+"-"+post);
					}
				} else {
					moves.push(pieces[x2][y2].toString()+"-"+post);
				}
				moves.push("move");
				pieces[x2][y2] = pieces[x1][y1];
				pieces[x1][y1] = null;
				isBlackTurn=!isBlackTurn;
				if(pieces[x2][y2] instanceof King) {
					if(pieces[x2][y2].isBlack){
						blackKingX = x2;
						blackKingY = y2;
					} else {
						whiteKingX = x2;
						whiteKingY = y2;
					}
						
				}
				return true;
			}
		}
		
		return false;
	}
	public String at(String pos) {
		int y = Math.abs((int)pos.charAt(1)-49-7);
		
		int x = Math.abs((int)pos.charAt(0)-97);
		if(pieces[x][y]==null) {
			return "";
		} else {
			return pieces[x][y].toString();
		}
		
	}
}


