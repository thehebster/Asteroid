/* University of Central Florida 
 * COP3330 — Spring 2019 * 
 * Author: Heba Nassereddeen */


package asteroidgame;

import java.awt.Point;
import java.lang.Math;
import java.util.Random;
import blobz.BlobUtils;
import blobz.PolyBlob;

public class Asteroid extends PolyBlob
{
	private static final Random random =  new Random();
	
	public Asteroid(int x, int y, double angular_rotation)
	{
		// set the asteroid to start at the off-screen location using PolyBlob constructor
		super(-100, 100, angular_rotation);
		
		// setDelta method in Blob class sets motion velocity vector with values received
		setDelta(x, y);
		
		// generate random int between 5 and 9 to set number of vertices/sides
		int sides = random.nextInt(5) + 5;
		
		// generate random int between 10 and 30 to set the size (in pixels) of polyblob
		int size = random.nextInt(21) + 10;
		
		// Set the size of the polyblob
		setSize(size);
		
		// Set the size of each region 
		double reg_size = (2 * Math.PI) / sides;
		
		// Declare arrays containing polyblob's randomized distances and angles 
		int[] distance = new int[sides];
		double[] angle = new double[sides];
		
		// An array of Points representing a location in (x,y) coordinate space
		Point[] points = new Point[sides];
		
		
		// create a random simple polygon (no lines crossing) shape for the asteroid 
		// that has between 5 and 9 sides and is between 10 and 30 pixels in diameter
		
			for (int i = 0; i < sides; i++)
		
			{
				// generate random ints between 5 and 15 to populate distance array
				distance[i] = random.nextInt(11) + 5;
						
				// populate the angle array using formula 
				angle[i] = (i * reg_size) + (Math.random() * reg_size);
				
				// For each vertex and angle, get the relative x/y coordinates and store in Point object
				 points[i] = BlobUtils.rotatePoint(distance[i],angle[i]);		
				
			} 
			
		// Set the relative polygon for the polyblob 
		setPolygon(points);
		
		
	}

}
