/* University of Central Florida 
 * COP3330 — Spring 2019 * 
 * Author: Heba Nassereddeen */

package asteroidgame;

import java.awt.Dimension;
import java.util.Random;
import blobz.BlobGUI;
import blobz.SandBox;
import blobz.SandBoxMode;


public class AsteroidGame implements BlobGUI {
	
	public static int num_asteroids; 
	SandBox sandy = new SandBox();
	private static Random random = new Random();

	public AsteroidGame(int num) 
	{
		//save the input integer in a static variable
		AsteroidGame.num_asteroids = num; 
				
		// 	configure sandbox for flow mode with 15 frames per second
		sandy.setSandBoxMode(SandBoxMode.FLOW);
		sandy.setFrameRate(15);
		
		// initialize the sandbox by passing "this" (the AsteroidField object) to the sandbox's init() method
		sandy.init(this);
	}
	
	// Required method for BlobGUI
		public void generate() 
	{		
		// Variables required to set Rocket 	
		Dimension bounds;
		int height, width;
		
		// Variables required to set Asteroids 
		int x, y, rotation_selector; 
		double rotation; 
		
		//create as many asteroids as are specified by the number that was input to the constructor for the class
		for (int a = 0; a < num_asteroids; a++)	
			{
				// Generate x and y velocity values from (-3,3), excluding zero 
				x =random.nextInt(7) - 3;
				y =random.nextInt(7) - 3;
				
				// Disallow zero values for velocity 
				if (x == 0)
				{
					x = x + 1;
				}
				
				if (y == 0)
				{
					y = y - 1;
				}
				
				//Choose a rotation	value either +0.1 or -0.1, with equal probability
				// Randomly generate an int (1 or 2) to select a value for the rotation
				
				rotation_selector = random.nextInt(2) + 1;
				
				//If rotation_selector = 1, set rotation to -.1
				if (rotation_selector == 1)
				{
					rotation = -.1;
				}
				
				//If rotation_selector = 2, set rotation to .1
				else
				{
					rotation =  .1;
				}
				
				// Create a new Asteroid object
				Asteroid aster = new Asteroid(x, y, rotation);
				
				// Add each Asteroid to the sandbox 
				sandy.addBlob(aster);
				
			}
		
		// Now, instantiate a new Rocket at the center of the sandbox
		
				//use sandbox's "getPanelBounds()" instance method, which returns an object of type Dimension
				bounds = sandy.getPanelBounds();
				
				// extract the width and height fields of this object and divide by 2 to obtain center 
				height = (int) ((bounds.getHeight()) / 2);
				width = (int) ((bounds.getWidth()) / 2);
				
				// Create a new Rocket object at center of sandbox
				Rocket rocky = new Rocket(width, height, sandy);
				
				// Add the Rocket to the sandbox
				sandy.addBlob(rocky);
		
		}

	public static void main(String[] args) 
	{
		new AsteroidGame(Integer.parseInt(args[0]));

	}

}
