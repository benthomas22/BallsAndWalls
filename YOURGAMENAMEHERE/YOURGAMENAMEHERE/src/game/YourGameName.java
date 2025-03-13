package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

class YourGameName extends Game {
	static int counter = 0;
	static int highScore = 0;
	static int level = 0;
	static int highestLevel = 0;
	static Player player;
	static boolean gameStarted = false;

	static {
		 player = new Player();
	}
	
  public YourGameName() {
    super("YourGameName!",800,600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(new KeyAdapter() { //anonymous class for start screen
		public void keyPressed(KeyEvent e)
		{
			if(!gameStarted)
			{
				gameStarted = true;
				removeKeyListener(this);
				addKeyListener(player);
				RestartGame();
				repaint();
			}
		}
	});
  }
  
	public void paint(Graphics brush) {
    	
		if(!gameStarted)
		{
			brush.setColor(Color.getHSBColor(250f/360f, 0.4f, 0.23f));
	    	brush.fillRect(0,0,width,height);
	    	brush.setColor(Color.white);
	    	brush.drawString("BALLS AND WALLS", 342, 400);
	    	brush.drawString("PRESS ANY KEY TO START", 320, 450);
		}
		else
		{
			brush.setColor(Color.getHSBColor(250f/360f, 0.4f, 0.23f));
	    	brush.fillRect(0,0,width,height);
	    	
	    	// sample code for printing message for debugging
	    	// counter is incremented and this message printed
	    	// each time the canvas is repainted
	    	counter++;
	    	if(counter > highScore)
	    		highScore = counter;
	    	if(level > highestLevel)
	    		highestLevel = level;
	    	
	    	brush.setColor(Color.white);
	    	brush.drawString("Score is " + counter,10,10);
	    	brush.drawString("Highscore is " + highScore,10,25);
	    	brush.drawString("Level is " + level,10,40);
	    	brush.drawString("Highest Level is " + highestLevel,10,55);
	    	if(counter % Wall.wallFreq == 0)
	    	{
	    		Wall.spawnWall();
	    	}
	    	if(counter % Balls.ballFreq == 0)
	    	{
	    		//System.out.println("balls");
	    		Balls.spawnBall();

	    	}
	    	
	    	Wall.moveWalls();
	    	Balls.moveBalls();
	    	Wall.paint(brush);
	    	Balls.paint(brush);
	    	
	    	player.move();
	    	player.paint(brush);
	    	
		}
    	
  }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
  }
	
	public static void RestartGame()
	  {
		  counter = 0;
		  level = 0;
		  player.position = Player.inPosition;
		  Player.speed = 3;
		  Wall.deleteWalls();
		  Wall.speed = 3;
		  Wall.wallFreq = 50;
		  Balls.deleteBalls();
		  Balls.ballFreq = 500;
		  Player.left = false;
		  Player.right = false;
		  Player.up = false;
		  Player.down = false;
		  Player.sprint = false;
	  }
	  
	  public static void GameOver() 
	  {
	      int choice = JOptionPane.showOptionDialog(null, "You lasted:" + YourGameName.counter+" ticks "+ "\n You lost on level : " + 
	    		  YourGameName.level , "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Restart", "Quit"}, "Restart");
		  if(choice == JOptionPane.YES_OPTION)
			  RestartGame();
		  else
			  System.exit(0);
	  }
}