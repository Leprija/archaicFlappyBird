package flappyBird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Action implements KeyListener {

	private Bird bird = Bird.getInstance();
	
	@Override
	public void keyPressed(KeyEvent ke) {
		Painter painter = Painter.getInstance();
		int pressedKey = ke.getKeyCode();
		if(pressedKey == KeyEvent.VK_SPACE) {
			
			if(bird.getStatus() == STATUS.FLYING) {			
				
				if(bird.getDirection() == DIRECTION.DOWN) {
					bird.setDirection(DIRECTION.UP);
					painter.setTempHeight(bird.getY());
				}else {					
					painter.increaseTempHeight(60);
				}
				
			}else {
				painter.restartGame();
			}
		
		}
		
		if(pressedKey == KeyEvent.VK_ENTER) {
			
			if(bird.getStatus() == STATUS.FLYING) {
				
				if(!painter.isPaused()) {
					painter.setPaused(true);
				}else {
					painter.setPaused(false);
				}
				
			}
		}
		
		if(pressedKey == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
		
	@Override
	public void keyReleased(KeyEvent arg0) {}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}

 
