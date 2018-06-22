package window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.Controller;

public class Menu extends JFrame {
	
	public Menu(String s) {
		super(s);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		

		JButton newGameBtn = new JButton();
		newGameBtn.setText("Novo Jogo");
		
		Controller controller = Controller.getController();
		
		newGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				controller.newGame();
			}
		});
		
		JButton loadGameBtn = new JButton();
		loadGameBtn.setText("Carregar Jogo");
		loadGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser loadChooser = new JFileChooser();
				//Cria um filtro para arquivos txt
				FileFilter filter = new FileNameExtensionFilter("Txt file (*.txt)", "txt");				
				//forca o salvamento em txt como unica opcao
				loadChooser.addChoosableFileFilter(filter);
				loadChooser.setFileFilter(filter);

			    int returnValue = loadChooser.showOpenDialog(null);
			    if (returnValue == JFileChooser.APPROVE_OPTION) {
			      File selectedFile = loadChooser.getSelectedFile();
				  controller.load(selectedFile);
				  dispose();
			    }
			}
		});

		panel.add(newGameBtn);
		panel.add(loadGameBtn);

		this.add(panel);
		this.setSize(300, 81);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void dispose() {
		this.setFocusable(false);
		this.setVisible(false);
	}
	
	public void initGame() {
		GameWindow w= new GameWindow(Controller.getController());
		w.setTitle("Xadrez");
		w.setVisible(true);
		w.setLayout(null);
	}

	// initializes a new game
	public void newGameAction() {
		initGame();
	}
	
}
