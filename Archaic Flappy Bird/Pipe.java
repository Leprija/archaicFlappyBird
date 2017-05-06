package flappyBird;

import java.awt.Color;

public class Pipe {
	
	private int x;
	private int width = 100;
	private int height = 700;
	private int velocity = 5;
	
	private static Color color = new Color(0, 51, 153);
	
	protected Pipe(int x) {
		this.x = x;
	}

	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected int getWidth() {
		return width;
	}

	protected int getHeight() {
		return height;
	}

	protected int getVelocity() {
		return velocity;
	}

	protected void setVelocity(int velocity) {
		this.velocity = velocity;
	}
		
	protected static Color getColor() {
		return color;
	}
	
}