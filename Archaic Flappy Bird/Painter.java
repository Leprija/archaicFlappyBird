package flappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Painter extends JComponent implements ActionListener {

	private static final Painter INSTANCE = new Painter();
		
	private Timer animation = new Timer(25, this);
	private Random rnd = new Random();

	private Background background = new Background();
	private Bird bird = Bird.getInstance();
	private Pipe[] pipes = new Pipe[2];	
	private Gateway[] gateways = new Gateway[2];
	
	private static final long serialVersionUID = 1L;
	private int tempHeight = bird.getY();
	private int space = 650;
	private int score = 0; 
	private boolean paused = false;
	private boolean gameOver = false;
	
	private Painter() {		
		animation.start();		
		addKeyListener(new Action());
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		installPipes(); 
	}
		
	public void paintComponent(Graphics g) {
		g.setColor(background.getSkyColor());
		g.fillRect(background.getSkyX(), background.getSkyY(), background.getSkyWidth(), background.getSkyHeight());
		g.setColor(background.getGroundColor());
		g.fillRect(background.getGroundX(), background.getGroundY(), background.getGroundWidth(), background.getGroundHeight());
		g.setColor(Pipe.getColor());
		for(int i = 0; i < pipes.length; i++) {
			g.fillRect(pipes[i].getX(), 0, pipes[i].getWidth(), pipes[i].getHeight());			
		}
		g.setColor(background.getSkyColor());
		for(int i = 0; i < gateways.length; i++) {
			g.fillRect(pipes[i].getX(), gateways[i].getY(), gateways[i].getWidth(), gateways[i].getHeight());			
		}
		g.setColor(Color.RED);
		g.fillRect(bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 128));
		g.drawString(String.valueOf(score), 0, 90);
		if(paused) {
			g.drawString("PAUSED", 150, 400);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 32));
			g.drawString("PRESS 'ENTER' TO UNPAUSE", 180, 450);
		}
		if(gameOver) {
			g.drawString("GAME OVER", 20, 400);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 32));
			g.drawString("PRESS 'SPACE' TO RESTART THE GAME", 110, 450);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {		
		
		if(bird.getDirection() == DIRECTION.DOWN) {
			bird.setY(bird.getY()+5);
		}else {		
			bird.setY(bird.getY()-5);
			if(bird.getY() == tempHeight-60) {
				bird.setDirection(DIRECTION.DOWN);
			}			
		}
		
		for(int i = 0; i < pipes.length; i++) {
			pipes[i].setX(pipes[i].getX()-pipes[i].getVelocity());
			if(pipes[i].getX() == -pipes[i].getWidth()) {
				
				int pos;				
				
				if(i == 0) {
					pos = 1;
				}else {
					pos = 0;
				}
				
				pipes[i].setX(pipes[pos].getX() + space);
				gateways[i].setY(rnd.nextInt(50)*10 + 50);
			}
			
			//Uslovi prekidanja igre
			if(bird.getY()+bird.getHeight() == background.getGroundY() //sudar sa zemljom
			|| bird.getX()+bird.getWidth() == pipes[i].getX() && bird.getY() < gateways[i].getY() //sudar sa gornjim delom cevi 
			|| bird.getX()+bird.getWidth() == pipes[i].getX() && bird.getY() == gateways[i].getY() && bird.getDirection() == DIRECTION.UP // sudar sa gornjom ivicom cevi
			|| bird.getY() == gateways[i].getY() && bird.getX()+bird.getWidth() > pipes[i].getX() && pipes[i].getX()+gateways[i].getWidth() > bird.getX() //sudar sa unutrasnjim gornjim delom cevi
			|| bird.getX()+bird.getWidth() == pipes[i].getX() && bird.getY()+bird.getHeight() > gateways[i].getY()+gateways[i].getHeight() //sudar sa donjim delom cevi	
			|| bird.getX()+bird.getWidth() == pipes[i].getX() && bird.getY()+bird.getHeight() == gateways[i].getY()+gateways[i].getHeight() && bird.getDirection() == DIRECTION.DOWN // sudar sa donjom ivicom cevi
			|| bird.getY()+bird.getHeight() == gateways[i].getY()+gateways[i].getHeight() && bird.getX()+bird.getWidth() > pipes[i].getX() && pipes[i].getX()+gateways[i].getWidth() > bird.getX()) { // sudar sa unutrasnjim donjim delom cevi  				
				
				bird.setStatus(STATUS.CRASHED);
				bird.setDirection(DIRECTION.DOWN);
				gameOver = true;
				pipes[0].setVelocity(0);
				pipes[1].setVelocity(0);	
				animation.setDelay(5);
				
				if(bird.getY()+bird.getHeight() == background.getGroundY() 
				|| bird.getY()+bird.getHeight() == gateways[i].getY()+gateways[i].getHeight() && bird.getX()+bird.getWidth() > pipes[i].getX() && pipes[i].getX()+gateways[i].getWidth() > bird.getX()) {									
					animation.stop();	
				}
				
			}	
			
			if(bird.getX() == pipes[i].getX()+pipes[i].getWidth()/2 && bird.getStatus() != STATUS.CRASHED) {
				score++;
			}
		}
		repaint();		
	}
	
	protected static Painter getInstance() {
		return INSTANCE;
	}
	
	protected Timer getAnimation() {
		return animation;
	}
	
	private void installPipes() {
		for(int i = 0; i < pipes.length; i++) {
			if(i == 0) {
				pipes[i] = new Pipe(1000);
			}else {
				pipes[i] = new Pipe(pipes[i-1].getX() + space);
			}
			gateways[i] = new Gateway(rnd.nextInt(50)*10 + 50);
		}	
	}
	
	protected int getTempHeight() {
		return tempHeight;
	}
	
	protected void setTempHeight(int tempHeight) {
		this.tempHeight = tempHeight;
	}
	
	protected void increaseTempHeight(int tempHeight) {
		this.tempHeight-=tempHeight;
	}
	
	protected boolean isPaused() {
		return paused;
	}

	protected void setPaused(boolean paused) {
		this.paused = paused;
		if(animation.isRunning()) {
			repaint();
			animation.stop();
		}else{
			animation.start();
		}
	}
	
	protected void restartGame() {
		score = 0;
		bird.setX(385);
		bird.setY(395);
		bird.setStatus(STATUS.FLYING);
		bird.setDirection(DIRECTION.DOWN);
		animation.setDelay(25);
		gameOver = false;
		installPipes();		
		animation.start();
	}
	
}
