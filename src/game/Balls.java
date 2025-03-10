package game;

import java.awt.Color;
import java.util.Iterator;
import java.awt.Graphics;
import java.util.ArrayList;

public class Balls extends Polygon{

	private static ArrayList<Balls> balls = new ArrayList<>();
	private static int width = 50;
	private static int height = 50;
	private static int speed = 2;
	public static int ballFreq = 500;

	
	public Balls(int x, int y)
	{
		super(new Point[] {new Point(0, 0), new Point(width, 0), new Point(width, height), new Point(0, height)}, new Point(x, y), 0);
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
	
	public static void paint(Graphics brush)
	{
		brush.setColor(Color.cyan);
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
