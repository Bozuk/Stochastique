package stochastique;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class CircuitPanel extends JPanel {

	private Ville[] villes;
	private double xMax;
	private double xMin;
	private double yMax;
	private double yMin;
	private double proportion;

	public CircuitPanel() {
		villes = new Ville[0];
		proportion = 1;
	}

	public void setVilles(Ville[] villes1) {
		// TODO Auto-generated method stub
		this.villes = villes1;
		xMax = villes[0].x;
		xMin = villes[0].x;
		yMax = villes[0].y;
		yMin = villes[0].y;
		for (int i = 1; i < villes.length; i++) {
			if (villes[i].x > xMax)
				xMax = villes[i].x;
			if (villes[i].x < xMin)
				xMin = villes[i].x;
			if (villes[i].y > yMax)
				yMax = villes[i].y;
			if (villes[i].y < yMin)
				yMin = villes[i].y;
		}
		if ((xMax - xMin) > (730.0 / 480.0 * (yMax - yMin)))
			proportion = 730 / (xMax - xMin);
		else
			proportion = 480 / (yMax - yMin);
	}

	public void setProportion(double proportion1) {
		this.proportion = proportion1;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.RED);

		for (int i = 1; i < villes.length; i++) {
			g.drawOval((int) ((villes[i - 1].x - xMin) * proportion) - 1,
					(int) ((villes[i - 1].y - yMin) * proportion) - 1, 2, 2);
			g.drawLine((int) ((villes[i - 1].x - xMin) * proportion), (int) ((villes[i - 1].y - yMin) * proportion),
					(int) ((villes[i].x - xMin) * proportion), (int) ((villes[i].y - yMin) * proportion));
		}
		g.drawOval((int) ((villes[villes.length - 1].x - xMin) * proportion) - 1,
				(int) ((villes[villes.length - 1].y - yMin) * proportion) - 1, 2, 2);
		g.drawLine((int) ((villes[villes.length - 1].x - xMin) * proportion),
				(int) ((villes[villes.length - 1].y - yMin) * proportion), (int) ((villes[0].x - xMin) * proportion),
				(int) ((villes[0].y - yMin) * proportion));
		/*
		 * for (int i = 1; i < coordinateData.length; i++) { g.drawOval((int)
		 * (coordinateData[i - 1][1] * proportion) - 1, (int) (coordinateData[i - 1][2]
		 * * proportion) - 1, 2, 2); g.drawLine((int) (coordinateData[i - 1][1] *
		 * proportion), (int) (coordinateData[i - 1][2] * proportion), (int)
		 * (coordinateData[i][1] * proportion), (int) (coordinateData[i][2] *
		 * proportion)); } g.drawOval((int) (coordinateData[coordinateData.length -
		 * 1][1] * proportion) - 1, (int) (coordinateData[coordinateData.length - 1][2]
		 * * proportion) - 1, 2, 2); g.drawLine((int)
		 * (coordinateData[coordinateData.length - 1][1] * proportion), (int)
		 * (coordinateData[coordinateData.length - 1][2] * proportion), (int)
		 * (coordinateData[0][1] * proportion), (int) (coordinateData[0][2] *
		 * proportion));
		 */
	}

}
