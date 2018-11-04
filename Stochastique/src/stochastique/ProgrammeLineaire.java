package stochastique;

import ilog.concert.IloException;
import ilog.cplex.IloCplex;

public class ProgrammeLineaire{

	IloCplex modele;
	
	ProgrammeLineaire(){
		try {
			modele = new IloCplex();
		} catch (IloException e) {
			System.err.println("Erreur à l'initialisation du modèle IloCplex");
		}
	}

}
