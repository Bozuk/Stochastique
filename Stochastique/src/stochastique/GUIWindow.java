package stochastique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.image.BufferStrategy;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ilog.concert.IloException;

import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import javax.swing.JButton;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.dom4j.DocumentException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class GUIWindow {

	private JFrame frmProblemeDuVoyager;
	//private MyPanel canvas;
	private CircuitPanel canvas;
	//private DataResult data; // The result of algorithm.
	
	JLabel lblNewLabel_3;
	JLabel lblNewLabel_4;

	private static int algorithmChosenIndex;
	private static String dataAddress = "a280"; // The address of data chosen by client

	private static float temperature;
	private static int iteration;
	private static float refroid;
	private static int kopt;
	private static float variance;

	private int numbreSommets; // numbreSommets must be haven by Manager.
	
	private Circuit scenario; //circult

	private String dataResultAddress;
	private double longeurOptimal; // The result of algorithm.

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws DocumentException 
	 */
	
	public GUIWindow() throws DocumentException {

		initialize();
		//dataAddress = "a280";
		//calcul();
		//dataResultAddress = "C:\\Users\\vince\\Documents\\eclipse-workspace\\CoodinateResult\\src\\a280-result.tsp"; // The result of algorithm.
		Data test = new Data(dataAddress);
		scenario = test.CoutToXY();
		
		//numbreSommets = 280; // The result of algorithm.
		//longeurOptimal = 34671.12; // The result of algorithm.
		//showSolution(dataResultAddress, numbreSommets, longeurOptimal);
		showSolution(scenario);
	}


	private void parametresDeterministre(JPanel panel, JTextField jtfName, JTextField jtfName1, JTextField jtfName2,
			JTextField jtfName3) {
		panel.removeAll();
		panel.repaint();
		int numParametre = 4;
		int panelHeight;
		if (40 * numParametre < 310)
			panelHeight = 40 * numParametre;
		else
			panelHeight = 310;
		panel.setBounds(22, 228, 240, panelHeight);
		panel.setLayout(new GridLayout(numParametre, 2, 10, 10));

		// parameter of "recuit pour le TSP (deterministe)" as default
		panel.add(new JLabel("Temp"));
		jtfName.setSize(10, 5);
		panel.add(jtfName);
		panel.add(new JLabel("iter "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName1);
		panel.add(new JLabel("refroid "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName2);
		panel.add(new JLabel("kopt "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName3);
		panel.revalidate();
	}

	private void parametresStochastique(JPanel panel, JTextField jtfName, JTextField jtfName1, JTextField jtfName2,
			JTextField jtfName3, JTextField jtfName4) {
		panel.removeAll();
		panel.repaint();
		int numParametre = 5;
		int panelHeight;
		if (40 * numParametre < 310)
			panelHeight = 40 * numParametre;
		else
			panelHeight = 310;
		panel.setBounds(22, 228, 240, panelHeight);
		panel.setLayout(new GridLayout(numParametre, 2, 10, 10));

		panel.add(new JLabel("Temp "));
		jtfName.setSize(10, 5);
		panel.add(jtfName);
		panel.add(new JLabel("iter "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName1);
		panel.add(new JLabel("refroid "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName2);
		panel.add(new JLabel("kopt "));
		jtfName1.setSize(10, 5);
		panel.add(jtfName3);
		panel.add(new JLabel("variance "));
		jtfName4.setSize(10, 5);
		panel.add(jtfName4);
		panel.revalidate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProblemeDuVoyager = new JFrame();
		frmProblemeDuVoyager.setTitle("Probl\u00E8me du voyager de commerce");
		frmProblemeDuVoyager.setBounds(100, 100, 1080, 640);
		frmProblemeDuVoyager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProblemeDuVoyager.getContentPane().setLayout(null);

		JPanel panel = new JPanel();

		frmProblemeDuVoyager.getContentPane().add(panel);

		JTextField jtfName = new JTextField();
		jtfName.setSize(10, 5);
		JTextField jtfName1 = new JTextField();
		jtfName1.setSize(10, 5);
		JTextField jtfName2 = new JTextField();
		jtfName2.setSize(10, 5);
		JTextField jtfName3 = new JTextField();
		jtfName3.setSize(10, 5);
		JTextField jtfName4 = new JTextField();
		jtfName4.setSize(10, 5);

		parametresDeterministre(panel, jtfName, jtfName1, jtfName2, jtfName3);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "recuit pour le TSP (d\u00E9terministe)", "recuit pour le TSP (stochastique)",
						"programme lin\u00E9aire (d\u00E9terministe)", "programme lin\u00E9aire (stochastique)" }));
		comboBox.setBounds(22, 40, 240, 22);
		frmProblemeDuVoyager.getContentPane().add(comboBox);
		// splitPane.add(comboBox,1);

		// Ã¦Â·Â»Ã¥Å Â Ã¦ï¿½Â¡Ã§â€ºÂ®Ã©â‚¬â€°Ã¤Â¸Â­Ã§Å Â¶Ã¦â‚¬ï¿½Ã¦â€�Â¹Ã¥ï¿½ËœÃ§Å¡â€žÃ§â€ºâ€˜Ã¥ï¿½Â¬Ã¥â„¢Â¨
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// Ã¥ï¿½ÂªÃ¥Â¤â€žÃ§ï¿½â€ Ã©â‚¬â€°Ã¤Â¸Â­Ã§Å¡â€žÃ§Å Â¶Ã¦â‚¬ï¿½
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("algorithmChosenIndex: " + comboBox.getSelectedIndex() + " = "
							+ comboBox.getSelectedItem());
					algorithmChosenIndex = comboBox.getSelectedIndex();

					switch (comboBox.getSelectedIndex()) {
					case 0: // select recuit pour le TSP (deterministe)
					case 2:
						parametresDeterministre(panel, jtfName, jtfName1, jtfName2, jtfName3);
						break;
					case 1:
					case 3:
						parametresStochastique(panel, jtfName, jtfName1, jtfName2, jtfName3, jtfName4);
						break;
					}
				}
			}
		});

		JTextArea dataChosenText = new JTextArea();
		dataChosenText.setLineWrap(true);
		dataChosenText.setBounds(22, 118, 240, 22);
		frmProblemeDuVoyager.getContentPane().add(dataChosenText);

		JButton openBtn = new JButton("Ouvrir le fichier");
		openBtn.setBounds(22, 146, 240, 24);
		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFileOpenDialog(frmProblemeDuVoyager, dataChosenText);
			}
		});
		frmProblemeDuVoyager.getContentPane().add(openBtn);

		JLabel lblNewLabel = new JLabel("Data");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setLabelFor(dataChosenText);
		lblNewLabel.setBounds(22, 96, 240, 19);
		frmProblemeDuVoyager.getContentPane().add(lblNewLabel);

		JLabel lblAlgorithme = new JLabel("Algorithme");
		lblAlgorithme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAlgorithme.setBounds(22, 18, 240, 19);
		frmProblemeDuVoyager.getContentPane().add(lblAlgorithme);

		JLabel lblParamtre = new JLabel("Param\u00E8tre");
		lblParamtre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblParamtre.setBounds(22, 198, 240, 19);
		frmProblemeDuVoyager.getContentPane().add(lblParamtre);

		//canvas = new MyPanel();
		canvas = new CircuitPanel();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(297, 41, 750, 500);
		canvas.setVisible(true);
		canvas.setFocusable(false);
		frmProblemeDuVoyager.getContentPane().add(canvas);

		JLabel lblGraphe = new JLabel("Graphe");
		lblGraphe.setLabelFor(canvas);
		lblGraphe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblGraphe.setBounds(297, 18, 240, 19);
		frmProblemeDuVoyager.getContentPane().add(lblGraphe);

		JButton btnNewButton = new JButton("Commencer");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(22, 554, 240, 28);
		frmProblemeDuVoyager.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					temperature = Float.parseFloat(jtfName.getText());
				}
				catch(java.lang.NumberFormatException e1) {
					temperature = 0;
				}
				
				try {
					iteration = Integer.parseInt(jtfName1.getText());
				}
				catch(java.lang.NumberFormatException e1) {
					iteration = 0;
				}
				
				try {
					refroid = Float.parseFloat(jtfName2.getText());
				}
				catch(java.lang.NumberFormatException e1) {
					refroid = (float) 0.95;
				}
				
				try {
					kopt = Integer.parseInt(jtfName3.getText());
				}
				catch(java.lang.NumberFormatException e1) {
					kopt  = 2;
				}
				
				
				//refroid = Float.parseFloat(jtfName2.getText());
				
				if (algorithmChosenIndex == 1||algorithmChosenIndex == 3)
					variance = Float.parseFloat(jtfName4.getText());
				
				System.out.println("algorithmChosenIndex: " + comboBox.getSelectedIndex() + " = "
						+ comboBox.getSelectedItem());
				System.out.println("AbsolutePath: " + dataAddress);
				System.out.println("terperature:" + temperature);
				System.out.println("iteration:" + iteration);
				System.out.println("refroid:" + refroid);
				System.out.println("kopt:" + kopt);
				System.out.println("variance:" + variance);
				Data test;
				try {
					test = new Data(dataAddress);
					scenario = test.CoutToXY();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (iteration==0 && scenario.getTaille() < 250) {
					iteration=scenario.getTaille()*scenario.getTaille();
					if(algorithmChosenIndex == 1)
						iteration=iteration/10;
				}
				if (iteration==0 && scenario.getTaille() >= 250) {
					iteration=100000;
					if(algorithmChosenIndex == 1)
						iteration=iteration/10;
				}
				
				
				if(algorithmChosenIndex == 0)
				{
					RecuitPVC testRecuit = new RecuitPVC(temperature, scenario, iteration, refroid, kopt);
					testRecuit.effectuerRecuit();
					scenario = testRecuit.getScenario();
				}
				else if(algorithmChosenIndex == 1)
				{
					RecuitPVCStochastique testRecuit = new RecuitPVCStochastique(temperature, scenario, iteration, refroid, kopt, variance);
					testRecuit.effectuerRecuit();
					scenario = testRecuit.getScenario();
				}
				
				else if(algorithmChosenIndex == 2)
				{
					ProgrammeLineaire testCPLEX = new ProgrammeLineaire(scenario);
					System.out.println(scenario.getCout());
					try {
						System.out.println(testCPLEX.resultatCout());
					} catch (IloException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					testCPLEX.getCircuit().updateCout();
					scenario = testCPLEX.getCircuit();
					
				}
				else
				{
					ProgrammeLineaire testCPLEX = new ProgrammeLineaire(scenario);
					System.out.println(scenario.getCout());
					try {
						System.out.println(testCPLEX.resultatCout());
					} catch (IloException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					testCPLEX.getCircuit().updateCout();
					scenario = testCPLEX.getCircuit();
				}

				showSolution(scenario);

			}

		});

		JLabel lblNewLabel_1 = new JLabel("Nombre de sommets:");
		lblNewLabel_1.setBounds(297, 551, 141, 16);
		frmProblemeDuVoyager.getContentPane().add(lblNewLabel_1);

		JLabel lblLongeurOptimalTrouv = new JLabel("Longeur optimal trouv\u00E9:");
		lblLongeurOptimalTrouv.setBounds(297, 568, 141, 16);
		frmProblemeDuVoyager.getContentPane().add(lblLongeurOptimalTrouv);

		lblNewLabel_3 = new JLabel("" + numbreSommets);
		lblNewLabel_1.setLabelFor(lblNewLabel_3);
		lblNewLabel_3.setBounds(475, 551, 47, 16);
		frmProblemeDuVoyager.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("" + longeurOptimal);
		lblLongeurOptimalTrouv.setLabelFor(lblNewLabel_4);
		lblNewLabel_4.setBounds(475, 568, 102, 16);
		frmProblemeDuVoyager.getContentPane().add(lblNewLabel_4);

	}

	/*
	 * Ouvrir le fichier
	 */
	private static void showFileOpenDialog(Component parent, JTextArea msgTextArea) {
		// Ã¥Ë†â€ºÃ¥Â»ÂºÃ¤Â¸â‚¬Ã¤Â¸ÂªÃ©Â»ËœÃ¨Â®Â¤Ã§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã©â‚¬â€°Ã¥ï¿½â€“Ã¥â„¢Â¨
		JFileChooser fileChooser = new JFileChooser();

		// Ã¨Â®Â¾Ã§Â½Â®Ã©Â»ËœÃ¨Â®Â¤Ã¦ËœÂ¾Ã§Â¤ÂºÃ§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã¥Â¤Â¹Ã¤Â¸ÂºÃ¥Â½â€œÃ¥â€°ï¿½Ã¦â€“â€¡Ã¤Â»Â¶Ã¥Â¤Â¹
		fileChooser.setCurrentDirectory(new File("./data"));

		// Ã¨Â®Â¾Ã§Â½Â®Ã¦â€“â€¡Ã¤Â»Â¶Ã©â‚¬â€°Ã¦â€¹Â©Ã§Å¡â€žÃ¦Â¨Â¡Ã¥Â¼ï¿½Ã¯Â¼Ë†Ã¥ï¿½ÂªÃ©â‚¬â€°Ã¦â€“â€¡Ã¤Â»Â¶Ã£â‚¬ï¿½Ã¥ï¿½ÂªÃ©â‚¬â€°Ã¦â€“â€¡Ã¤Â»Â¶Ã¥Â¤Â¹Ã£â‚¬ï¿½Ã¦â€“â€¡Ã¤Â»Â¶Ã¥â€™Å’Ã¦â€“â€¡Ã¤Â»Â¶Ã¥ï¿½â€¡Ã¥ï¿½Â¯Ã©â‚¬â€°Ã¯Â¼â€°
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// Ã¨Â®Â¾Ã§Â½Â®Ã¦ËœÂ¯Ã¥ï¿½Â¦Ã¥â€¦ï¿½Ã¨Â®Â¸Ã¥Â¤Å¡Ã©â‚¬â€°
		fileChooser.setMultiSelectionEnabled(false);

		/*
		 * // Ã¦Â·Â»Ã¥Å Â Ã¥ï¿½Â¯Ã§â€�Â¨Ã§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã¨Â¿â€¡Ã¦Â»Â¤Ã¥â„¢Â¨Ã¯Â¼Ë†FileNameExtensionFilter Ã§Å¡â€žÃ§Â¬Â¬Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¥ï¿½â€šÃ¦â€¢Â°Ã¦ËœÂ¯Ã¦ï¿½ï¿½Ã¨Â¿Â°, Ã¥ï¿½Å½Ã©ï¿½Â¢Ã¦ËœÂ¯Ã©Å“â‚¬Ã¨Â¦ï¿½Ã¨Â¿â€¡Ã¦Â»Â¤Ã§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã¦â€°Â©Ã¥Â±â€¢Ã¥ï¿½ï¿½ Ã¥ï¿½Â¯Ã¥ï¿½ËœÃ¥ï¿½â€šÃ¦â€¢Â°Ã¯Â¼â€°
		 * fileChooser.addChoosableFileFilter(new
		 * FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar")); // Ã¨Â®Â¾Ã§Â½Â®Ã©Â»ËœÃ¨Â®Â¤Ã¤Â½Â¿Ã§â€�Â¨Ã§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã¨Â¿â€¡Ã¦Â»Â¤Ã¥â„¢Â¨
		 * fileChooser.setFileFilter(new
		 * FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
		 */

		// Ã¦â€°â€œÃ¥Â¼â‚¬Ã¦â€“â€¡Ã¤Â»Â¶Ã©â‚¬â€°Ã¦â€¹Â©Ã¦Â¡â€ Ã¯Â¼Ë†Ã§ÂºÂ¿Ã§Â¨â€¹Ã¥Â°â€ Ã¨Â¢Â«Ã©ËœÂ»Ã¥Â¡Å¾, Ã§â€ºÂ´Ã¥Ë†Â°Ã©â‚¬â€°Ã¦â€¹Â©Ã¦Â¡â€ Ã¨Â¢Â«Ã¥â€¦Â³Ã©â€”Â­Ã¯Â¼â€°
		int result = fileChooser.showOpenDialog(parent);

		if (result == JFileChooser.APPROVE_OPTION) {
			// Ã¥Â¦â€šÃ¦Å¾Å“Ã§â€šÂ¹Ã¥â€¡Â»Ã¤Âºâ€ "Ã§Â¡Â®Ã¥Â®Å¡", Ã¥Ë†â„¢Ã¨Å½Â·Ã¥ï¿½â€“Ã©â‚¬â€°Ã¦â€¹Â©Ã§Å¡â€žÃ¦â€“â€¡Ã¤Â»Â¶Ã¨Â·Â¯Ã¥Â¾â€ž
			File file = fileChooser.getSelectedFile();

			// Ã¥Â¦â€šÃ¦Å¾Å“Ã¥â€¦ï¿½Ã¨Â®Â¸Ã©â‚¬â€°Ã¦â€¹Â©Ã¥Â¤Å¡Ã¤Â¸ÂªÃ¦â€“â€¡Ã¤Â»Â¶, Ã¥Ë†â„¢Ã©â‚¬Å¡Ã¨Â¿â€¡Ã¤Â¸â€¹Ã©ï¿½Â¢Ã¦â€“Â¹Ã¦Â³â€¢Ã¨Å½Â·Ã¥ï¿½â€“Ã©â‚¬â€°Ã¦â€¹Â©Ã§Å¡â€žÃ¦â€°â‚¬Ã¦Å“â€°Ã¦â€“â€¡Ã¤Â»Â¶
			// File[] files = fileChooser.getSelectedFiles();

			// msgTextArea.append("Ã¦â€°â€œÃ¥Â¼â‚¬Ã¦â€“â€¡Ã¤Â»Â¶: " + file.getAbsolutePath() + "\n\n");
			msgTextArea.setText(file.getName());

			//dataAddress = file.getAbsolutePath();
			if (file.getName() != "")
				dataAddress = file.getName().replaceAll(".xml", "");
			System.out.println("AbsolutePath: " + dataAddress);
		}
	}
	
	private void showSolution(Circuit scenario) {
		// TODO Auto-generated method stub
		
		//data = new DataResult("C:\\Users\\vince\\Documents\\eclipse-workspace\\CoodinateResult\\src\\a280-result.tsp", numbreSommets); // Address of result must be haven by Algorithm.
		//data.outputInfo();

		//canvas.setCoordinate(data.getCoordinate());
		//canvas.setProportion(data.getProportion());
		canvas.removeAll();
		canvas.repaint();
		canvas.setVilles(scenario.getVilles());
		canvas.revalidate();
		
		
		lblNewLabel_3.setText("" + scenario.getTaille());
		lblNewLabel_4.setText("" + scenario.getCout());
	}
	
	public JFrame getJFrame() {
		return frmProblemeDuVoyager;
	}
}
