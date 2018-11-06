package stochastique;

public class Ville {
	protected double x;
	protected double y;
	protected int num;
	Ville(double x, double y, int num){
		this.x = x;
		this.y = y;
		this.num = num;
	}
	
	public void affichage() {
		System.out.println("X : " + x + ", Y : " + y + ", Numéro : " + num);
	}
	
	public int getIndice() {
		return num;
	}

}
