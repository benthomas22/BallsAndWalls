package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class YourGameName extends Game {
	static int counter = 0;
	static Player player;

	static {
		 player = new Player();
	}
	
  public YourGameName() {
    super("YourGameName!",800,600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(player);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	if(counter % Wall.wallFreq == 0)
    	{
    		Wall.spawnWall();
    	}
    	
    	Wall.moveWalls();
    	Wall.paint(brush);
    	
    	player.move();
    	player.paint(brush);
    	
  }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
  }
}