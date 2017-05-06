package flappyBird;

public class Gateway {

	private int y;
	private int width = 100;
	private int height = 100;
	
	protected Gateway(int y) {
		this.y = y;
	}

	protected int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected int getWidth() {
		return width;
	}

	protected int getHeight() {
		return height;
	}
	
}
