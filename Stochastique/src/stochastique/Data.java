package stochastique;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class Data {
	protected double[][] tableauCout;

	Data(String nomfichier) throws DocumentException{
		Document doc = Chargement(nomfichier);
		int tailleDonnees = 0;
		Element root = doc.getRootElement();
	    for (Iterator<Element> it = root.elementIterator("graph"); it.hasNext();) {
	        Element graph = it.next();
	        
	        for (Iterator<Element> it2 = graph.elementIterator("vertex"); it2.hasNext();) {
	        	tailleDonnees++;
	        	Element vertex = it2.next();
	        }
	        
	        
	        
	        int i = 0;
	        tableauCout = new double[tailleDonnees][tailleDonnees];

	        for (Iterator<Element> it2 = graph.elementIterator("vertex"); it2.hasNext();) {
		        Element vertex = it2.next();
		        
		       // System.out.println("Ville numéro : " + i);
		        for (Iterator<Element> it3 = vertex.elementIterator("edge"); it3.hasNext();) {
			        Element edge = it3.next();
			        tableauCout[i][Integer.parseInt(edge.getData().toString())] =  Double.parseDouble(edge.attributeValue("cost"));
			     //   System.out.println(Integer.parseInt(edge.getData().toString()));
			     //   System.out.println(Double.parseDouble(edge.attributeValue("cost")));
		        }
		        tableauCout[i][i] = 0;
		        i++;
	        }
	    }
	    
	    
		
	}
    public Document Chargement(String nomfichier) throws DocumentException {
    	File fichier =  new File("./data/"+ nomfichier +".xml") ;
        SAXReader reader = new SAXReader();
        Document document = reader.read(fichier);
        return document;
    }
    
    public Circuit CoutToXY() {
    	double[][] PrepMatrice = new double[tableauCout.length][tableauCout.length];
    	for (int i = 0; i < PrepMatrice.length; i++){
    		for (int j = 0; j < PrepMatrice.length; j++)
    			PrepMatrice[i][j] = ((tableauCout[0][j])*(tableauCout[0][j]) + (tableauCout[i][0])*(tableauCout[i][0]) - (tableauCout[i][j])*(tableauCout[i][j]))*0.5;
    	}
    	Matrix PreDecompositionMatrice = new Matrix(PrepMatrice);
    	EigenvalueDecomposition Decomposition = PreDecompositionMatrice.eig();
    	Matrix FinaleMatrice = Decomposition.getD();
    	
    	for (int i=0; i<FinaleMatrice.getColumnDimension(); ++i)
    		for (int j=0; j<FinaleMatrice.getRowDimension(); ++j)
    			FinaleMatrice.set(i, j, Math.sqrt(FinaleMatrice.get(i, j)));
    	Matrix x = Decomposition.getV().times(FinaleMatrice);
    	Ville[] villes = new Ville[tableauCout.length+1];
    	for (int i=0; i<tableauCout.length; ++i) {
    		villes[i] = new Ville(x.get(i, tableauCout.length-2), x.get(i, tableauCout.length-1), i);
    	}
    	
    	villes[tableauCout.length] = new Ville(0, 0, 0);
    	for(int i=0; i<villes.length; i++) {
    		villes[i].affichage();
    	}
    	Circuit scenario = new Circuit(villes, tableauCout);
    	
    	return scenario;
    	
    }
}