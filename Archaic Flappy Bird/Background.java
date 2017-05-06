package flappyBird;

import java.awt.Color;

public class Background {

	private int skyX = 0;
	private int skyY = 0;
	private int skyWidth = 800;
	private int skyHeight = 700;
	private Color skyColor = new Color(180, 216, 231);
	
	private int groundX = 0;
	private int groundY = 700;
	private int groundWidth = 800;
	private int groundHeight = 100;
	private Color groundColor = new Color(156, 255, 0);
	
	protected int getSkyX() {
		return skyX;
	}
	
	protected int getSkyY() {
		return skyY;
	}
	
	protected int getSkyWidth() {
		return skyWidth;
	}
	
	protected int getSkyHeight() {
		return skyHeight;
	}
	
	protected Color getSkyColor() {
		return skyColor;
	}
	
	protected int getGroundX() {
		return groundX;
	}
	
	protected int getGroundY() {
		return groundY;
	}
	
	protected int getGroundWidth() {
		return groundWidth;
	}
	
	protected int getGroundHeight() {
		return groundHeight;
	}
	
	protected Color getGroundColor() {
		return groundColor;
	}
	
}
