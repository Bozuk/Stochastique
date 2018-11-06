package stochastique;

public abstract class Recuit {
	//Classe abstraite générale pour effectuer un Recuit simulé. 
	//Ici avec en scénario un Circuit et en data notre objet Donnee
	//Attention, on est ici sur de la minimisation
	//On génère notre nouveau scénario en 2-opt
	protected double temperature;
	protected Object scenario;
	protected int iterations;
	protected double refroidissement;
	
	
	Object getScenario() {
		return this.scenario;
	}
	
	abstract Object genererNouveauScenario();
	
	abstract void setTemperatureInitiale();
	
	void majTemperature() {
		this.temperature*=this.refroidissement;
	}
		
	abstract void effectuerRecuit();
	

}
