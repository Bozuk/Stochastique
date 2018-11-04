package stochastique;

public class Circuit {
	protected double cout;
	protected int taille;
	protected Ville[] villes;
	
	public Circuit(Ville[] villes){
		this.villes = villes;
		this.taille = villes.length;
		this.updateCout();
	}
	
	public Circuit(Circuit cir){
		this.villes=cir.villes;
		this.taille=cir.taille;
		this.cout = cir.cout;
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
	
	//public double coutStochastique(double var) {
	public double getCout() {
		return cout;
	}
		
	//}
}
