package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import pieces.Bispo;
import pieces.Cavalo;
import pieces.Dama;
import pieces.Peao;
import pieces.Piece;
import pieces.Rei;
import pieces.Torre;
import pieces.Vago;
import game.Chess;

public class Save {
	private static int reiPretoX;
	private static int reiPretoY;
	private static int reiBrancoX;
	private static int reiBrancoY;
	public Save() {
		JFileChooser save = new JFileChooser();
		
		//Cria um filtro para arquivos txt
		FileFilter filter = new FileNameExtensionFilter("Txt file (*.txt)", "txt");
		
		//forca o salvamento em txt como unica opcao
		save.addChoosableFileFilter(filter);
		save.setFileFilter(filter);
		
		int botao_clicado = save.showSaveDialog(null);
		
		if(botao_clicado == JFileChooser.APPROVE_OPTION)
		{
			try {
			FileWriter fw = new FileWriter(save.getSelectedFile()+".txt");
			Chess istanceOfChess=Chess.getChess();
			String saveData = "";
			Piece[][] pecas = istanceOfChess.getPieces();
			

			//faz uma matriz com as posições do tabuleiro
			for(int i = 0; i < pecas.length; i++) {
				for(int j = 0; j < pecas[i].length; j++) {
					
					//Comparador Ternário para definir preto ou branco
					//A cor da peça é preta? Se sim, valor = "p", caso não, verifica se é verde. É? Então "v" se não, valor = "b"
					String cor = pecas[j][i].getCor() == Color.black ? "p" : pecas[j][i].getCor() == Color.green ? "v" : "b";
					
					saveData += cor + "_";
					
					if (pecas[j][i] instanceof Rei) {
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
					if (pecas[j][i].isFirstMove) {
						saveData+="_" + "y";
					}else saveData+="_" + "n";

					// Se não é o ultimo elemento da linha, coloca ","
					if(!(j == pecas[i].length - 1))
						saveData += ","; 
				}
				saveData += "\r\n"; // quebra de linha
			}
			
			//salva de quem e a vez
			saveData += Chess.getChess().isBlackTurn() ? "p" : "b";	
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
	
	
	// loads a saved game
	public static void loadGameAction(File savedGame) {
		Chess.getChess().pieces =parsePieces(savedGame);
		Chess.getChess().reiPretoX=reiPretoX;
		Chess.getChess().reiPretoY=reiPretoY;
		Chess.getChess().reiBrancoX=reiBrancoX;
		Chess.getChess().reiBrancoY=reiBrancoY;
	}

// le arquivo texto com jogo existente
private static Piece[][] parsePieces(File savedGame) {
    InputStream file;
	try {
		file = new FileInputStream(savedGame);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		return null;
	}
    
	// matriz onde as pecas carregadas serao armazenadas
	Piece[][] loadedPieces = new Piece[8][8];
	
    // buffered reader to read collider file
    BufferedReader reader = new BufferedReader(new InputStreamReader(file));
    String line = null;
    
    // percorre linhas do arquivo
    int i = 0;
    try {
        while ((line = reader.readLine()) != null && i <= 7) {
        	// separa info das pecas de acordo com as virgulas
            String[] pcs = line.split(",");
            for (int j = 0; j < pcs.length; j++) {
            	// pega indo da cor e do tipo de cada peca
            	String[] pData = pcs[j].split("_");
                Color cor = pData[0].equals("b") ? Color.white : pData[0].equals("p") ? Color.black : Color.green;
                String tipo = pData[1];
                
                // cria peca de acordo com seu tipo e sua cor
                switch (tipo) {
                	case "b": // bispo
                		loadedPieces[j][i] = new Bispo(cor);
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	case "t": // torre
                		loadedPieces[j][i] = new Torre(cor);
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	case "d": // dama
                		loadedPieces[j][i] = new Dama(cor);
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	case "r": // rei
                		loadedPieces[j][i] = new Rei(cor);
                		if (cor==Color.black) {
                    		reiPretoX=j;
                    		reiPretoY=i;
                		}else {
                    		reiBrancoX=j;
                    		reiBrancoY=i;
                		}
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	case "c": // cavalo
                		loadedPieces[j][i] = new Cavalo(cor);
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	case "p": // peao
                		loadedPieces[j][i] = new Peao(cor);
                		if (pData[2].equals("n"))
                			loadedPieces[j][i].isFirstMove = false;
                		break;
                	default:
                		loadedPieces[j][i] = new Vago(); //vago
                		break;
                }
            }
            i++;
        }
    } catch (IOException e) {
        System.err.println("Could not read file");
        e.printStackTrace();
    }

    // seta a vez
    if (line.equals("p"))
    	Chess.getChess().setBlackTurn(true);
    else
    	Chess.getChess().setBlackTurn(false);
    
	return loadedPieces;
}

}
