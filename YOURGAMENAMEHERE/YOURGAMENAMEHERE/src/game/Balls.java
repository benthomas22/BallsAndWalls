package game;

import java.awt.Color;
import java.util.Iterator;
import java.awt.Graphics;
import java.util.ArrayList;

public class Balls extends Polygon{

	private static ArrayList<Balls> balls = new ArrayList<>();
	private static int width = 50;
	private static int radius = 25;
	private static int height = 50;
	private static int speed = 2;
	public static int ballFreq = 500;

	
	public Balls(int x, int y)
	{
		super(new Point[] { new Point(x + radius, y),  // Right
				//ngl the balls looking ugly revert to squares?
			    new Point((int) (x + radius * Math.cos(Math.PI / 6)), (int) (y - radius * Math.sin(Math.PI / 6))), 
			    new Point((int) (x + radius * Math.cos(Math.PI / 3)), (int) (y - radius * Math.sin(Math.PI / 3))), 
			    new Point(x, y - radius),  // Top
			    
			    new Point((int) (x - radius * Math.cos(Math.PI / 3)), (int) (y - radius * Math.sin(Math.PI / 3))), 
			    new Point((int) (x - radius * Math.cos(Math.PI / 6)), (int) (y - radius * Math.sin(Math.PI / 6))), 
			    new Point(x - radius, y),  // Left
			    new Point((int) (x - radius * Math.cos(Math.PI / 6)), (int) (y + radius * Math.sin(Math.PI / 6))), 
			    new Point((int) (x - radius * Math.cos(Math.PI / 3)), (int) (y + radius * Math.sin(Math.PI / 3))),
			    new Point(x, y + radius),  // Bottom
			    new Point((int) (x + radius * Math.cos(Math.PI / 3)), (int) (y + radius * Math.sin(Math.PI / 3))),
			    new Point((int) (x + radius * Math.cos(Math.PI / 6)), (int) (y + radius * Math.sin(Math.PI / 6))) 
			}, new Point(x, y), 0);
		//this.used = false;

	}
	
	public static void spawnBall()
	{
		//width += (Math.random() * 600) - 300;
		int randX = (int) (Math.random() * (700 - width)); //chooses a random x coordinate
		balls.add(new Balls(randX, 0));
		System.out.print(balls.size());
	}
	
	public static void moveBalls()
	{
		Iterator<Balls> iterator = balls.iterator();
		while (iterator.hasNext())
		{	
			Balls ball = iterator.next();
			ball.position.y += speed;
			ball.rotation += speed;
			if(ball.position.y > 600)
			{
				iterator.remove();
			}
			if(ball.contains(YourGameName.player.position)) {
				if(YourGameName.counter % 2 == 0) {
				Wall.wallFreq += 10;
				Wall.speed -= 1;
			}
				else{
					Wall.wallFreq -= 10;
					Wall.speed += 1;
				}
				Balls.ballFreq -= 10;
				
				if(Wall.wallFreq >= 200) {
					Wall.wallFreq -= 10;
				}
				
				YourGameName.level++;
				iterator.remove();
			}

		}		
		
	}
	
	public static void deleteBalls()
	{
		balls.clear();
	}
	
	public static void paint(Graphics brush)
	{
		brush.setColor(Color.getHSBColor(88/360f, 0.15f, 0.75f));
        for (Balls ball : balls)
        {

        	Point[] points = ball.getPoints();
    		int[] xPoints = new int[points.length];
    		int[] yPoints = new int[points.length];
    		for(int i = 0; i < points.length; i++)
    		{
    			xPoints[i] = (int) points[i].x;
    			yPoints[i] = (int) points[i].y;
    		}
    		brush.fillPolygon(xPoints, yPoints, points.length);
        }
	}
}