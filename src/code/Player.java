package code;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.Timer;
import org.lwjgl.input.Keyboard;

public class Player
{
	public double x, y, xSpeed, ySpeed;
	public boolean jumpPressed, jumpWasPressed, alive, airborn;
	public Timer deathTimer;

	public Player()
	{
		spawn();
	}
	
	public void spawn()
	{
		x = 100;
		y = 50;
		alive = true;
		airborn = true;
		deathTimer = new Timer();
	}
	
	public void physics()
	{
		// Momentum
		x += xSpeed;
		y += ySpeed;

		if (y>32)  // On ground?
		{
			airborn = true;
			ySpeed -= 0.3; // Gravity
			//xSpeed = 0.98 * xSpeed; // Air friction
		}
		else
		{
			airborn = false;
			y = 32;
			ySpeed = 0;
			xSpeed = 0.7 * xSpeed; // Ground friction
		}
		
		// Map edge
		if (x <= 10)
		{
			xSpeed = 0;
			x = 10;
		}
		
		if (x >= 630)
		{
			xSpeed = 0;
			x = 630;
		}
	}
	
	public void movement()
	{
		jumpWasPressed = jumpPressed;
		jumpPressed = Keyboard.isKeyDown(Keyboard.KEY_UP); 
		
		if (jumpPressed && !jumpWasPressed && y == 32) ySpeed = 8;
		
		if (!airborn && Keyboard.isKeyDown(Keyboard.KEY_LEFT) && xSpeed > -5) xSpeed -= 1.5;
		if (!airborn && Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && xSpeed < 5) xSpeed += 1.5;
	}

	public void ascend()
	{
		ySpeed = 2;
		xSpeed = 0;
		
		x += xSpeed;
		y += ySpeed;

		glPushMatrix();
		glTranslated(x, y, 0);
				
		glBegin(GL_QUADS);

		glColor3d(1, 1, 1);
		
		glVertex2d(-8, 0);
		glVertex2d(8, 0);
		glVertex2d(8, 16);
		glVertex2d(-8, 16);

		glEnd();
		glPopMatrix();
		
		if (y > 600)
		{
			spawn();
		}
	}
	
	public void draw()
	{
		physics();
		
		glPushMatrix();
		glTranslated(x, y, 0);
				
		glBegin(GL_QUADS);

		glColor3d(0.2, 1, 0.2);
		
		glVertex2d(-8, 0);
		glVertex2d(8, 0);
		glVertex2d(8, 16);
		glVertex2d(-8, 16);

		glEnd();
		glPopMatrix();
		
	}
}