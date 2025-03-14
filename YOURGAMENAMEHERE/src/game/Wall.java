package game;

import java.awt.Color;
import java.util.Iterator;
import java.awt.Graphics;
import java.util.ArrayList;

public class Wall extends Polygon{

	private static ArrayList<Wall> walls = new ArrayList<>();
	private static int baseWidth = 350;
	private static int baseHeight = 20;
	public static int speed = 3;
	public static int wallFreq = 100; //spawns every n updates
	
	public Wall(int x, int y, int width)
	{
		super(new Point[] {new Point(0, 0), new Point(width, 0), new Point(width, baseHeight), new Point(0, baseHeight)}, new Point(x, y), 0);
	}
	
	public static void spawnWall()
	{
		int choose = (int) (Math.random() * 2);
		if(choose == 0)
			SingleWall.spawn();
		else
			GapWall.spawn();
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
				//iterator.remove();
			}
			if(wall.contains(YourGameName.player.position)) {
				Game.GameOver();
			}
			if(wall.speed == 0) {
				if(YourGameName.counter %7 == 0) {
					wall.speed = -1;
					
				}
				else {
					wall.speed = 1;
				}		
			}
			
			if(YourGameName.level > 10 && YourGameName.counter%2 == 0 && YourGameName.level%2 == 0 ) {
				
				if(YourGameName.level< 25) {
					wall.rotation +=YourGameName.level;
				}
				wall.rotation +=1 *( YourGameName.level-10);

			
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
	
	

	public static class SingleWall
	{
		public static void spawn()
		{
			int width = (int)((Math.random() - 0.5) * baseWidth/2) + baseWidth; //chooses a random width close to baseWidth
			int randX = (int) (Math.random() * (800 - baseWidth)); //chooses a random x coordinate
			walls.add(new Wall(randX, 0, width));
		}
	}
	
	public static class GapWall
	{
		public static void spawn()
		{
			int width = baseWidth * 2;
			int randOffset = (int) ((Math.random() - 0.5) * width);
			
			walls.add(new Wall(-200, 0, width + randOffset));
			walls.add(new Wall(800, 0, width - randOffset));
		}
	}
	
	//
}
