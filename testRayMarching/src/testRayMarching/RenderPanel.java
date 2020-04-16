package testRayMarching;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2830671512572162253L;
	
	
	public Renderer rend;
	
	@Override
	public void paintComponent( Graphics g ) {
		g.drawImage(rend.getRenderImg(), 0, 0, null);
	}
	
	
	
	public RenderPanel(Renderer rend) {
		this.rend =rend;  
	}
}
