package stochastique;

import java.util.Random;

public class Circuit {
	protected double cout;
	protected double[][] tableauCout;
	protected int taille;
	protected Ville[] villes;
	
	public Circuit(Ville[] villes, double[][] tableauCout){
		this.villes = villes;
		this.taille = villes.length;
		this.updateCout();
		this.tableauCout = tableauCout;
	}
	
	public Circuit(Circuit cir){
		taille=cir.taille;
		villes=new Ville[taille];
		for (int i=0; i<this.taille; i++) {
			villes[i]=cir.villes[i];
		}
		this.cout=cir.cout;
		this.tableauCout=cir.tableauCout;
	}
	

	public void updateCout() {
		double newcout = 0;	
		for(int i = 0; i < taille-1; i++)
		{
			newcout += cout2Villes(this.villes[i].x,this.villes[i].y,this.villes[i+1].x,this.villes[i+1].y);
		}
		this.cout=newcout;
	}
	
	public double cout2Villes(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2 -y1)*(y2 -y1));
	}
	
	public double getCout() {
		return cout;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public double[][] getTableauCout() {
		return tableauCout;
	}
	
	public double coutStochastique(double var) {
		double cout = 0;
		for(int j=0; j<10; j++) { //On évalue 10 fois le cout stochastique pour travailler ensuite sur la moyenne
			for(int i = 0; i < taille-1; i++)
			{
				cout += cout2VillesStocha(this.villes[i].x,this.villes[i].y,this.villes[i+1].x,this.villes[i+1].y,var);
			}
		}
		cout=cout/10;
		return cout;		
	}
	
	public double cout2VillesStocha(double x1, double y1, double x2, double y2,double var) {
		double moy= Math.sqrt((x2-x1)*(x2-x1) + (y2 -y1)*(y2 -y1));
		Random r = new Random();
		double gauss = r.nextGaussian();
		double ecarttype=moy*var;
		double cout = gauss*ecarttype+moy;
		return cout;
	}

	public Ville[] getVilles() {
		// TODO Auto-generated method stub
		return villes;
	}
	
	public void afficherToutesVilles() {
		for(int i = 0; i < villes.length; i++)
		{
			villes[i].affichage();
		}
	}
	
	public void echanger2Villes(int ville1, int ville2) {
		Ville tmp;
		int indVille1 = -1, indVille2 = -1;
		for(int i = 0; i<villes.length;i++)
		{
			if(villes[i].getIndice() == ville1)
				indVille1 = i;
			else if(villes[i].getIndice() == ville2)
				indVille2 = i;
			
			if(indVille1 != -1 && indVille2 != -1)
			{
				tmp = villes[indVille1];
				villes[indVille1] = villes[indVille2];
				villes[indVille2] = tmp;
				break;
			}
		}
	}
}
