package stochastique;

public class RecuitPVCStochastique extends RecuitPVC {

	double variance;
	
	RecuitPVCStochastique(double temp, Circuit scen, int iter, double refroid, int k, double var) {
		super(temp, scen, iter, refroid, k);
		this.variance=var;
	}
	
	@Override
	boolean accepterScenario(Circuit essai) {
		//Attention, marche uniquement pour un problème de minimisation
		//Version stochastique
		double coutactuel=this.scenario.coutStochastique(this.variance);
		double coutessai=essai.coutStochastique(this.variance);
		if (coutactuel>coutessai) {
			this.scenario=essai;
			return true;
		}
		else {
			double temp = Math.exp((coutactuel - coutessai)/this.temperature);
			double proba = Math.random();
			if (temp>proba) {
				this.scenario=essai;
				return true;
			}
		}
		return false;
	}

}
