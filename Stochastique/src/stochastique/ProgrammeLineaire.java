package stochastique;

import java.util.ArrayList;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class ProgrammeLineaire{

	IloCplex modele;
	Circuit circuit;
	
	ProgrammeLineaire(Circuit circuit){
		this.circuit = circuit;
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
			
			
		
			
			ArrayList<Integer> soustours;
			ArrayList<ArrayList<Integer>> listesoustours;
			
			do
			{
				boolean[] etatVilles = new boolean[tailleCPLEX];
				for(int i = 0; i<tailleCPLEX; i++) {
					etatVilles[i] = false;
				}
				
				soustours = new ArrayList<Integer>();
				listesoustours = new ArrayList<ArrayList<Integer>>();
				
				int nbVilleActuelle = 0;
				soustours.add(0);
				etatVilles[0] = true;
				
				while(!etatVille(etatVilles)) {
					for(int i = 0; i<tailleCPLEX; i++)
					{
						if(Math.abs(modele.getValue(boolObjectif[nbVilleActuelle][i])-1) < 0.01)
						{
							if(etatVilles[i])
							{
								listesoustours.add(soustours);
								soustours = new ArrayList<Integer>();
								for(int j = 0; j<tailleCPLEX;j++)
								{
									if(!etatVilles[j])
									{
										nbVilleActuelle = j;
										etatVilles[j] = true;
										soustours.add(nbVilleActuelle);
										break;
									}
								}
							}
							else
							{
								soustours.add(i);
								nbVilleActuelle = i;
								etatVilles[i] = true;
							}
							
						}
					}
					
				} 
				
				if(!listesoustours.isEmpty())
				{
					for(int i = 0; i < listesoustours.size(); i++)
					{
						ArrayList<Integer> tour = listesoustours.get(i);
						IloLinearNumExpr expr = modele.linearNumExpr();
						for(int j:tour)
						{
							for(int k:tour)
							{
								if(j!=k)
								{
									expr.addTerm(1, boolObjectif[j][k]);
								}
							}
						}
						modele.addLe(expr, tour.size()-1);
					}
					modele.solve();
				}
				
			} while(!listesoustours.isEmpty());
			circuit.afficherToutesVilles();
			
			for(int i = 1; i < tailleCPLEX-1;i++)
			{
				circuit.echanger2Villes(circuit.getVilles()[i].getIndice(), soustours.get(i));
			}
			System.out.println("----------");
			circuit.afficherToutesVilles();
			
			
		} catch (IloException e) {
			e.printStackTrace();
		}
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
	public double resultatCout() throws IloException {
			return modele.getObjValue();
	}
	
	public boolean etatVille(boolean[] listeVille) {
		for(int i = 0; i < listeVille.length; i++)
		{
			if(!listeVille[i])
				return false;	
		}
		
		return true;
	}


}
