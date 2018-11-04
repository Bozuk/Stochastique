package stochastique;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class ProgrammeLineaire{

	IloCplex modele;
	
	ProgrammeLineaire(Circuit circuit){
		int tailleCPLEX =  circuit.getTaille() - 1;
		try {
			modele = new IloCplex();
		} catch (IloException e) {
			System.err.println("Erreur à l'initialisation du modèle IloCplex");
		}
		IloNumVar[][] boolObjectif = new IloNumVar[tailleCPLEX][tailleCPLEX];
		//IloLinearNumExpr objectifMin = 
		
		try {
			//objectif
			for(int i = 0; i < tailleCPLEX; i++)
			{
				boolObjectif[i] = modele.boolVarArray(tailleCPLEX);
			}
			
			IloLinearNumExpr objectif = modele.linearNumExpr();
			
			for(int i = 0; i < tailleCPLEX; i++)
			{
				for(int j = 0; j < tailleCPLEX; j++)
				{
					objectif.addTerm(circuit.getTableauCout()[i][j], boolObjectif[i][j]);
				}
			}
			
			modele.addMinimize(objectif);
			
			//contraintes
			
			for(int i=0; i<tailleCPLEX; i++)
			{
				IloLinearNumExpr contrainte = modele.linearNumExpr();
				for(int j = 0; j<tailleCPLEX; j++) {
					if(i!=j)
					{
						contrainte.addTerm(1.0, boolObjectif[i][j]);
					}
				}
				modele.addEq(contrainte, 1.0);
			}
			
			for(int j = 0; j<tailleCPLEX; j++) {
				IloLinearNumExpr contrainte = modele.linearNumExpr();
				for(int i = 0; i<tailleCPLEX; i++) {
					if(i!=j)
						contrainte.addTerm(1.0, boolObjectif[i][j]);		
				}
				modele.addEq(contrainte, 1.0);
			}
			modele.solve();
		/*	int soustour;
			while(soustour < tailleCPLEX) {
				for(int i =0; i<tailleCPLEX;i++)
				{
					
				}
			}*/
			
		} catch (IloException e) {
			e.printStackTrace();
		}
	}
	
	public double resultatCout() throws IloException {
			return modele.getObjValue();
	}


}
