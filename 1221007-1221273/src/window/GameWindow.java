package window;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.Controller;
import game.Chess;
import pieces.Bispo;
import pieces.Cavalo;
import pieces.Dama;
import pieces.Peao;
import pieces.Piece;
import pieces.Rei;
import pieces.Torre;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class GameWindow extends JFrame implements MouseListener{
	public static final int size = 100;
	protected final int LARG_PADRAO = size*8+50;
	protected final int ALT_PADRAO = size*8 +50;
	Controller controller;
	Board board = new Board();

	
	public GameWindow (Controller cont) {
		board.setLayout(null);
		board.setBackground(Color.GREEN);
		add(board);
		addMouseListener(this);
		controller=cont;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl/2 - LARG_PADRAO/2;
		int y= sa/2 - ALT_PADRAO/2;
		setBounds(x,y,LARG_PADRAO, ALT_PADRAO);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void mouseClicked(MouseEvent e) {

		if(SwingUtilities.isLeftMouseButton(e))
		{
			int selectedSquareX = (e.getX() - 8) / size;
			int selectedSquareY = (e.getY() - 30) / size;
			//System.out.printf("[%d]", selectedSquareX);
			//System.out.printf("[%d] ", selectedSquareY);
			controller.clickAction(selectedSquareX,selectedSquareY);
		}
		else if (SwingUtilities.isRightMouseButton(e))
		{
			System.out.println("clicou botao direito");
			
			JFileChooser save = new JFileChooser();
			
			//Cria um filtro para arquivos txt
			FileFilter filter = new FileNameExtensionFilter("Txt file (*.txt)", "txt");
			
			//forca o salvamento em txt como unica opcao
			save.addChoosableFileFilter(filter);
			save.setFileFilter(filter);
			
			int botao_clicado = save.showSaveDialog(this);
			
			if(botao_clicado == JFileChooser.APPROVE_OPTION)
			{
				try {
				FileWriter fw = new FileWriter(save.getSelectedFile()+".txt");
				
				String saveData = "";
				Piece[][] pecas = Chess.getChess().getPieces();
				

				//faz uma matriz com as posições do tabuleiro
				for(int i = 0; i < pecas.length; i++) {
					for(int j = 0; j < pecas[i].length; j++) {
						
						//Comparador Ternário para definir preto ou branco
						//A cor da peça é preta? Se sim, valor = "p", caso não, verifica se é verde. É? Então "v" se não, valor = "b"
						String cor = pecas[j][i].getCor() == Color.black ? "p" : pecas[j][i].getCor() == Color.green ? "v" : "b";
						
						saveData += cor + "_";
						
						if (pecas[j][i] instanceof Rei) {
							//Rei rei = (Rei) pecas[i][j] ;
							//System.out.println(rei.toString());
							
							saveData += "r";
							
						}
						
						else if (pecas[j][i] instanceof Dama) {
							
							saveData += "d";
						}
						
						else if (pecas[j][i] instanceof Bispo) {
							
							saveData += "b";
						}
						
						else if (pecas[j][i] instanceof Cavalo) {
							
							saveData += "c";
						}
						
						else if (pecas[j][i] instanceof Peao) {
							
							saveData += "p";
						}
						
						else if (pecas[j][i] instanceof Torre) {
							
							saveData += "t";
						}
						
						else {
							saveData += "v";
						}

						// Se não é o ultimo elemento da linha, coloca ","
						if(!(j == pecas[i].length - 1))
							saveData += ","; 
					}
					saveData += "\r\n"; // quebra de linha
				}
				
				//System.out.println(saveData.toString());				
	            fw.write(saveData);
	            fw.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				File file = save.getSelectedFile();
				String fileName = file.getAbsolutePath();
			}
			
			if(botao_clicado == JFileChooser.CANCEL_OPTION) {
				return;
			}
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
