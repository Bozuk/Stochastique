package stochastique;

public class RecuitPVC extends Recuit {

	Circuit scenario;
	int Kopt;
	
	RecuitPVC(double temp, Circuit scen, int iter, double refroid, int k) {
		this.temperature=temp;
		this.scenario=scen;
		this.iterations=iter;
		this.refroidissement=refroid;
		this.Kopt=2; //On force le 2-opt faute de bonne implémentation du k-opt
	}

	@Override
	Circuit getScenario() {
		return this.scenario;
	}
	
	Circuit genererNouveauScenario() {
		//Generer un nouveau scenario
		int inf;
		int sup; //les bornes supérieures et inférieures du sous tour
		Circuit essai = new Circuit(this.scenario); //Copie du scenario actuel
		for (int j=0; j<this.Kopt-1; j++) { //Ne marche en fait qu'en 2-opt...
			Circuit temp = new Circuit(essai);
			int a = (int) (Math.random()*(this.scenario.taille-2)+1); //les futurs 2 sommets que l'on va inverser au hasard
			int b = (int) (Math.random()*(this.scenario.taille-2)+1); //le -2 et le +1 servent à ne pas avoir toucher au point de départ/arrivée
			if (a>b) {
				inf=b;
				sup=a;
			}
			else {
				inf=a;
				sup=b;
			}
			int cpt=0;
			for (int i=inf; i<=sup; i++) {
				//Inversion du sous tour
				essai.villes[i]=temp.villes[sup-cpt];
				cpt+=1;
			}
		}
		essai.updateCout();
		return essai;
	}
	
	void setTemperatureInitiale() {
		if (this.temperature==0) {
			this.temperature=(this.scenario.cout)/10;
			int acceptation;
			double probaAcceptation=0;
			Circuit initial = new Circuit(this.scenario);
			while (probaAcceptation<0.8 && this.temperature<90000) {
				//Tant qu'on a pas 80% d'acceptation sur un tour complet, on double la température
				acceptation=0;
				for (int i=0; i<this.iterations; i++) {
					this.scenario=new Circuit(initial);
					Circuit essai=this.genererNouveauScenario();
					boolean tmp = this.accepterScenario(essai);
					if (tmp) {
						acceptation+=1;
					}
				}
				probaAcceptation=acceptation/this.iterations;
				this.temperature*=2;
			}
		}
	}
	
	boolean accepterScenario(Circuit essai) {
		//Attention, marche uniquement pour un problème de minimisation
		if (this.scenario.cout>essai.cout) {
			this.scenario=essai;
			return true;
		}
		else {
			double sous=(this.scenario.cout - essai.cout);
			double temp = Math.exp( sous/this.temperature);
			double proba = Math.random();
			if (temp>proba) {
				this.scenario=essai;
				return true;
			}
		}
		return false;
	}
	
	void effectuerRecuit() {
		this.setTemperatureInitiale();
		while (this.temperature>1) {
			for (int i=0; i<this.iterations; i++) {
				Circuit essai=this.genererNouveauScenario();
				boolean tmp = this.accepterScenario(essai);
			}
			System.out.println(this.scenario.cout);
			this.majTemperature();
		}
	}

}
