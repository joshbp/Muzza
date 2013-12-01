package code;
import java.util.ArrayList;
import java.util.Iterator;


public class EnemyManager
{
	public ArrayList<Enemy> enemies;
	
	public EnemyManager()
	{
		enemies = new ArrayList<Enemy>();
	}
	
	public void spawn(Enemy enemy)
	{
		enemies.add(enemy);
	}

	public void movement()
	{
		for (Iterator<Enemy> i = enemies.iterator(); i.hasNext();)
		{
			Enemy enemy = i.next();
			enemy.movement();
		}
	}

	public void draw()
	{
		for (Iterator<Enemy> i = enemies.iterator(); i.hasNext();)
		{
			Enemy enemy = i.next();
			enemy.draw();
		}
	}
	
	
	public void detectCollision()
	{
		for (Iterator<Enemy> i = enemies.iterator(); i.hasNext();)
		{
			Enemy enemy = i.next();
			if (Math.abs(Game.player.x - enemy.x) <= 16 && Math.abs(Game.player.y - enemy.y) <= 16)  
			{// Player colliding with enemy
				if (Game.player.ySpeed < 0)
				{
					enemy.kill();
					i.remove();
				}
				else
				{
					Game.player.alive = false;
				}
			}
		}
	}
}