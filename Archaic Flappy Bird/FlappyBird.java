package flappyBird;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FlappyBird {
	
	private JFrame gameWindow;
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	private FlappyBird() {
		gameWindow = new JFrame("Flappy Bird");
		gameWindow.setSize(800, 800);
		gameWindow.setResizable(false);
		gameWindow.setLocation(screen.width/2 - gameWindow.getWidth()/2, screen.height/2 - gameWindow.getHeight()/2);
		gameWindow.add(Painter.getInstance());
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new FlappyBird();
		
	}
		
}
