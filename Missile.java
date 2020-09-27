/* University of Central Florida 
 * COP3330 — Spring 2019 * 
 * Author: Heba Nassereddeen */

package asteroidgame;

import blobz.Blob;
import blobz.BlobProximity;
import java.awt.Color;

public class Missile extends Blob implements BlobProximity
{
	// Constructor takes 3 arguments: the x and y locations of the missile's current location 
	// (both integers) and the direction (angle) in which the missile is moving (a double)
	
	public Missile(int x, int y, double theta)
	{
		
		// invoke the Blob constructor 
		super(x, y, Color.yellow);
		
		int dx, dy, speed = 5;
		
		// Calculate the velocity components of the Missile 
		dx = (int) Math.round(speed * Math.cos(theta));
		dy = (int) Math.round(speed * Math.sin(theta));
		
		// Set location of Missile 
		setLoc(x,y);
		
		// Set the Missile in motion 
		setDelta(dx, dy);
		
		
	}

}
