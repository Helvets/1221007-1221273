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

import control.Controller;
import pieces.Bispo;
import pieces.Cavalo;
import pieces.Dama;
import pieces.Peao;
import pieces.Piece;
import pieces.Rei;
import pieces.Torre;
import pieces.Vago;
import window.GameWindow;

public class Save {
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
		
	

public static void initGame() {
	GameWindow w= new GameWindow(Controller.getController());
	w.setTitle("Xadrez");
	w.setVisible(true);
	w.setLayout(null);
}

// initializes a new game
public void newGameAction() {
	initGame();
}

// loads a saved game
public static void loadGameAction(File savedGame) {
	Chess.getChess().loadGame(parsePieces(savedGame));
	initGame();
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
                		break;
                	case "t": // torre
                		loadedPieces[j][i] = new Torre(cor);
                		break;
                	case "d": // dama
                		loadedPieces[j][i] = new Dama(cor);
                		break;
                	case "r": // rei
                		loadedPieces[j][i] = new Rei(cor);
                		break;
                	case "c": // cavalo
                		loadedPieces[j][i] = new Cavalo(cor);
                		break;
                	case "p": // peao
                		loadedPieces[j][i] = new Peao(cor);
                		break;
                	default:
                		loadedPieces[j][i] = new Vago();                 		//vago
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
