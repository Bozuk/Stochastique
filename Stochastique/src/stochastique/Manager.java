package stochastique;

import java.awt.EventQueue;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import ilog.concert.IloException;

public class Manager {

	public static void main(String[] args) throws DocumentException {
		Data test = new Data("att48");
		/*ProgrammeLineaire testCPLEX = new ProgrammeLineaire(test.CoutToXY());
		System.out.println(test.CoutToXY().getCout());
		try {
			System.out.println(testCPLEX.resultatCout());
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*RecuitPVC testRecuit = new RecuitPVC(10000, test.CoutToXY(), 5000, 0.95, 2);
		System.out.println(testRecuit.getScenario().getCout());
		testRecuit.effectuerRecuit();
		System.out.println(testRecuit.getScenario().getCout());*/

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.getJFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
