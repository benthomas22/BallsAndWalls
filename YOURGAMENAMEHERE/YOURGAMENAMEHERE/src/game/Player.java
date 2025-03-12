package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Polygon implements KeyListener{
	
	private static Point[] inShape = {new Point(0, 20), new Point(10, -10), new Point(0, 0), new Point(-10, -10)};
	public static Point inPosition = new Point(380, 450);
	private static double inRotation = 180;
	
	public static boolean left;
	public static boolean right;
	public static boolean up;
	public static boolean down;
	public static boolean sprint;
	
	public static int speed = 3;
	
	private static ColorUnlock playerColor;
	
	public Player()
	{
		super(inShape, inPosition, inRotation);
		playerColor = (score) -> {
			if (score >= 5000) return Color.PINK;
			if (score >= 3000) return Color.BLUE;
			if (score >= 2000) return Color.GREEN;
			if (score >= 1000) return Color.YELLOW;
			if (score >= 500) return Color.ORANGE;
			return Color.RED;
		};
	}
	
	public Color getCurrentColor()
	{
		return playerColor.getColor(YourGameName.highScore);
	}
	
	public void paint(Graphics brush)
	{
		Point[] points = this.getPoints();
		int[] xPoints = new int[inShape.length];
		int[] yPoints = new int[inShape.length];
		for(int i = 0; i < inShape.length; i++)
		{
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}
		brush.setColor(getCurrentColor());
		brush.fillPolygon(xPoints, yPoints, inShape.length);
	}
	
	public void getColor()
	{
		
	}
	
	public void move()
	{
		if(left && inPosition.x > 20) //left movement
		{
			inPosition.x -= speed;
			super.rotation = 170;
		}
		
		if(right && inPosition.x < 760) //right movement
		{
			inPosition.x += speed;
			super.rotation = 190;
		}
		
		if(up && inPosition.y > 40) //down movement
		{
			inPosition.y -= speed;
			super.rotation = 170;
		}
		
		if(down && inPosition.y < 560) //up movement
		{
			inPosition.y += speed;
			super.rotation = 190;
		}
		
		if(left == right)
		{
			super.rotation = 180;
		}
		
		if(down == up)
		{
			super.rotation = 180;
		}
		
		if( inPosition.y < 560 && inPosition.y > 40 && !up) {
			inPosition.y += speed-1;
			super.rotation = 170;
		}
		
		if(sprint == true) {
			speed = 4;
		}
	}

	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			right = true;
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
			up = true;
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
			down = true;
		//if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		//
		sprint = false;
	}

	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			right = false;
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
			up = false;
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
			down = false;
		//if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		//	sprint = true;
	}
	
	public void keyTyped(KeyEvent e) {
		//Needed but not implemented
	}
	
	public void constantShift() {
		if( inPosition.y < 560 && inPosition.y > 40) {
			inPosition.y -= speed;
			super.rotation = 170;
		}
	}
	
}