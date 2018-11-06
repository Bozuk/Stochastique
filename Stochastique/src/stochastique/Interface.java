package stochastique;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class Interface extends JFrame{
	public Interface() {
		super("Projet Stochastique");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuItemAlgo = new JMenu("Algorithmes");
		JMenu menuItemDonnees = new JMenu("Données");
		setJMenuBar(menuBar);
		menuBar.add(menuItemAlgo);
		menuBar.add(menuItemDonnees);
		
		ButtonGroup buttonAlgoGroup = new ButtonGroup();
		JRadioButtonMenuItem donnee1 = new JRadioButtonMenuItem("a280");
		
		JRadioButtonMenuItem donnee2 = new JRadioButtonMenuItem("att532");
		
		buttonAlgoGroup.add(donnee1);
		menuItemAlgo.add(donnee1);
		buttonAlgoGroup.add(donnee2);
		menuItemAlgo.add(donnee2);
			
		JLabel TitreParametre = new JLabel("Paramètre");
		TitreParametre.setFont(new Font("Arial", Font.BOLD, 15));
		TitreParametre.setBounds(50, 10, 240, 19);
		this.getContentPane().add(TitreParametre);
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(l);
		setSize(1080,640);
		setVisible(true);
			}

		}
	
		

