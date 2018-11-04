package stochastique;

public class RecuitPVC extends Recuit {

	Circuit scenario;
	int Kopt;
	
	RecuitPVC(double temp, Circuit scen, int iter, double refroid, int k) {
		this.temperature=temp;
		this.scenario=scen;
		this.iterations=iter;
		this.refroidissement=refroid;
		this.Kopt=k;
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
		for (int j=0; j<this.Kopt-1; j++) {
			Circuit temp = new Circuit(essai);
			int a = (int) (Math.random()*this.scenario.taille); 
			int b = (int) (Math.random()*this.scenario.taille); //les futurs 2 sommets que l'on va inverser au hasard
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
			this.temperature=(double) ((this.scenario.cout)/10);
			int acceptation;
			double probaAcceptation=0;
			while (probaAcceptation<0.8) {
				//Tant qu'on a pas 80% d'acceptation sur un tour complet, on double la température
				acceptation=0;
				for (int i=0; i<this.iterations; i++) {
					Circuit essai=this.genererNouveauScenario();
					this.accepterScenario(essai);
					if (this.scenario==essai) {
						acceptation+=1;
					}
				}
				probaAcceptation=acceptation/iterations;
				this.temperature*=2;
			}
		}
	}
	
	void accepterScenario(Circuit essai) {
		//Attention, marche uniquement pour un problème de minimisation
		if (this.scenario.cout>essai.cout) {
			this.scenario=essai;
		}
		else {
			double temp = Math.exp((this.scenario.cout - essai.cout) / temperature);
			double proba = Math.random();
			if (temp<proba) {
				this.scenario=essai;
			}
		}
	}
	
	void effectuerRecuit() {
		this.setTemperatureInitiale();
		while (this.temperature>1) {
			for (int i=0; i<this.iterations; i++) {
				Circuit essai=this.genererNouveauScenario();
				this.accepterScenario(essai);
			}
			this.majTemperature();
		}
	}


	

}
