package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Polygon implements KeyListener{
	
	private static Point[] inShape = {new Point(0, 20), new Point(10, -10), new Point(0, 0), new Point(-10, -10)};
	private static Point inPosition = new Point(380, 450);
	private static double inRotation = 180;
	
	private static boolean left;
	private static boolean right;
	
	public static int speed = 3;
	
	public Player()
	{
		super(inShape, inPosition, inRotation);
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
		brush.setColor(Color.red);
		brush.fillPolygon(xPoints, yPoints, inShape.length);
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
		
		if(left == right)
		{
			super.rotation = 180;
		}
	}

	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			right = true;
	}

	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
			right = false;
	}
	
	public void keyTyped(KeyEvent e) {
		//Needed but not implemented
	}
	
}
