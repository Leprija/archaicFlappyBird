package flappyBird;

public class Bird {
	
	private static final Bird INSTANCE = new Bird(385, 395);
	
	private int x;
	private int y;
	private int width = 20;
	private int height = 20;
	
	private STATUS status = STATUS.FLYING;
	private DIRECTION direction = DIRECTION.DOWN;
	
	private Bird(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
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

	protected STATUS getStatus() {
		return status;
	}

	protected void setStatus(STATUS status) {
		this.status = status;
	}

	protected DIRECTION getDirection() {
		return direction;
	}

	protected void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
		
	protected static Bird getInstance() {	
		return INSTANCE;
	}
	
}
