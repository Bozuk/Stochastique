package stochastique;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Manager {

	public static void main(String[] args) throws DocumentException {
		Data test = new Data("brazil58");
		RecuitPVC testRecuit = new RecuitPVC(30, test.CoutToXY(), 5000, 0.95, 2);
		System.out.println(testRecuit.getScenario().getCout());
		testRecuit.effectuerRecuit();
		System.out.println(testRecuit.getScenario().getCout());
	}
}
