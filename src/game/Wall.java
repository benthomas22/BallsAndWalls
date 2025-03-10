package game;

import java.awt.Color;
import java.util.Iterator;
import java.awt.Graphics;
import java.util.ArrayList;

public class Wall extends Polygon{

	private static ArrayList<Wall> walls = new ArrayList<>();
	private static int width = 300;
	private static int height = 20;
	// make private later
	public static int speed = 3;
	
	public static int wallFreq = 35;
	
	public Wall(int x, int y)
	{
		super(new Point[] {new Point(0, 0), new Point(width, 0), new Point(width, height), new Point(0, height)}, new Point(x, y), 0);
	}
	
	public static void spawnWall()
	{
		//width += (Math.random() * 600) - 300;
		int randX = (int) (Math.random() * (800 - width)); //chooses a random x coordinate
		walls.add(new Wall(randX, 0));
	}
	
	public static void moveWalls()
	{
		Iterator<Wall> iterator = walls.iterator();
		while (iterator.hasNext())
		{	
			Wall wall = iterator.next();
			wall.position.y += speed;
			if(wall.position.y > 600)
			{
				iterator.remove();
			}
			if(wall.contains(YourGameName.player.position)) {
				Game.GameOver();
			}

		}		
		
	}
	
	public static void paint(Graphics brush)
	{
		brush.setColor(Color.gray);
        for (Wall wall : walls)
        {
        	Point[] points = wall.getPoints();
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
	
	
	//
}
