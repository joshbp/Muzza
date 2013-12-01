package code;

import static org.lwjgl.opengl.GL11.*;

public class Enemy
{
	public boolean alive;
	public double x, y, xVelocity;

	public Enemy(int xStart, int yStart, int xStartVelocity)
	{
		x = xStart;
		y = yStart;
		xVelocity = xStartVelocity;
		alive = true;
	}

	public void movement()
	{
		if (y > 32)
		{// falling
			y -= 4;
		}
		else
		{// walking
			x += xVelocity;
			// Swap direction at edge of map;
			if (x <= 0) xVelocity = xVelocity * -1;
			if (x > 1200) xVelocity = xVelocity * -1;
		}

	}

	public void kill()
	{
		alive = false;
	}

	public void draw()
	{
		glPushMatrix();
		glTranslated(x, y, 0);
				
		glBegin(GL_QUADS);
		
		glColor3d(0.85, 0, 0);
		glVertex2d(-8, 0);
		glVertex2d(8, 0);
		glVertex2d(8, 16);
		glVertex2d(-8, 16);

		glEnd();
		glPopMatrix();		
	}
}