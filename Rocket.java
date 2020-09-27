/* University of Central Florida 
 * COP3330 — Spring 2019 * 
 * Author: Heba Nassereddeen */


package asteroidgame;

import blobz.PolyBlob;
import blobz.SandBox;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import blobz.BlobAction;
import blobz.BlobProximity;
import blobz.BlobUtils;

public class Rocket extends PolyBlob implements BlobAction, BlobProximity
{
	 // direction of motion 
	 private double angle = 0.0;
	 // angular change per key press
	 private final double delta = 0.15;
	 // speed in direction of motion 
	 private final double speed = 5.0; 
	 
	 private static SandBox s;
	 
	// Constructor creates stationary PolyBlob in (x,y) location 
	public Rocket(int x, int y, SandBox sandy)
	{
		// invoke the PolyBlob constructor 
		super(x, y, 0);
		
		// An array of Points representing a location in (x,y) coordinate space
		Point[]	points	=	
			{	
				new	Point(8,0),	
				new Point (-8, -7),
				new	Point(-5,0),				
				new	Point(-8,7)							
			};
		
		// Call the setPolygon instance method from PolyBlob superclass
		setPolygon(points);
		
		// Set the Rocket's fill color to Red 
		setColor(Color.red);
		
		// save	this input value as	a class	variable for use by	the	launch method
		s = sandy;
	}
	
	// Method to launch a Missile
	public void launch(SandBox sandy)
	{
		int bounding_radius, current_x, current_y;
		Point rotated_point = new Point(), launch_point = new Point();
		
	// Calculate the launch point of the Missile to be 5 pixels from bounding radius, from rocket's current position/velocity 
		
		// bounding radius is half of what Blob's getSize() method returns
		bounding_radius = (int) (.5 * getSize());
		
		// Get current x and y location of the rocket 
		current_x = getLoc().x;
		current_y = getLoc().y;
		
		// get (delta x and delta y) using desired distance and angle
		rotated_point = BlobUtils.rotatePoint(bounding_radius + 5, angle);
		
		// launch point	is at location (getLoc().x	+ delta x, getLoc().y + delta y)
		launch_point.x =  current_x + rotated_point.x;
		launch_point.y =  current_y + rotated_point.y;
		
		// Instantiate Missile and add to SandBox 
		Missile missy = new Missile(launch_point.x, launch_point.y, angle);
		
		// Add Missile to Sandbox 
		sandy.addBlob(missy);
	}

	@Override
	
	// Processing of up, right, and left arrow keys 
	public void keyAction(KeyEvent e) 
	{	
		
		// handling of key code 37 (left arrow)... rotate counterclockwise
		 if (e.getKeyCode() == 37)
		 {
			 // Subtract delta to current angle
			 angle -= delta;
			 
			 // If resulting angle is less than	0, add	2 pi
			 if ( angle < 0)
			 {
				 angle += (2 * Math.PI);
			 }
			 
			 // Set the new angle 
			 setAngle(angle);
		 }
		 
		 // handling key code 39 (right arrow)... rotate clockwise
		 else if (e.getKeyCode() == 39)
		 {
			
			 // Add delta to current angle
			 angle += delta;
			 
			 // If angle is greater than 2 pi, subtract 2 pi from angle 
			 if ( angle > (2 * Math.PI))
			 {
				 angle -= (2 * Math.PI);
			 }
			 
			 // Set the new angle 
			 setAngle(angle);
			 
		 }
		 
		// handling key code 38 (up arrow)... move forward
		 else if (e.getKeyCode() == 38)
		 {
			 int x, y, new_x, new_y; 
			 
			 // Get	current	x and y locations from Point class
			 x = getLoc().x;
			 y = getLoc().y;
			 
			 // Compute	new	location
			 new_x = x + ((int) Math.round(speed * Math.cos(angle)));
			 new_y = y + ((int) Math.round(speed * Math.sin(angle)));
			 
			 // Set	the new location
			 setLoc(new_x, new_y);
		 }
		 
		// handling key code 32 (spacebar) ... launching of missile  
		 else if (e.getKeyCode() == 32)
		 {
			 launch(s);
		 }
		 
	}

}
