package code;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.Timer;



public class Game
{
	public static Player player;
	public static EnemyManager enemies;

	public static void main(String[] args) throws Exception
	{
		Display.setDisplayMode(new DisplayMode(640, 480));
		Display.create();

		player = new Player();
		
		enemies = new EnemyManager();
		enemies.spawn(new Enemy(800, 32, -2));
		enemies.spawn(new Enemy(1100, 32, -2));

		while (!Display.isCloseRequested())
		{
			Timer.tick();

			setCamera();
			Display.sync(60);

			update();
			draw();

			Display.update();
		}

		Display.destroy();
	}

	public static void update()
	{
		enemies.movement();
		enemies.draw();
		
		if (player.alive)
		{
			player.physics();
			player.movement();
			enemies.detectCollision();
		}
	}
	
	public static void draw()
	{
		drawBackground();
		if (player.alive)
		{
			player.draw();			
		}
		else
		{
			player.ascend();
		}
		
		enemies.draw();
	}
	
	public static void setCamera()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 0, 480, -1, 1);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	public static void drawBackground()
	{
		// Sky
		glBegin(GL_QUADS);
		
		glColor3d(0.7, 0.8, 0.9);
		glVertex2d(0,0);
		glVertex2d(640, 0);
		
		glColor3d(0.1, 0.1, 0.8);
		glVertex2d(640,480);
		glVertex2d(0, 480);
		
		// Ground
		glColor3d(0.6, 0.3, 0);
		glVertex2d(0,0);
		glVertex2d(640, 0);
		
		glVertex2d(640,32);
		glVertex2d(0, 32);

		//Grass
		glColor3d(0.1, 0.7, 0.1);
		glVertex2d(0,26);
		glVertex2d(640, 26);
		
		glVertex2d(640,32);
		glVertex2d(0, 32);

		glEnd();
	}

}
