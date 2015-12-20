/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;quez
 * 
 * Final Project Programming assignment
 * 
 * For this project, we as a group explored the basics of computer programming, using only simple methods and ways to properly
 * find a solution for this project. In this project, however, the goal is to have the player move about a 9 X 9 grid, consisting of
 * 9 evenly spaced rooms, one of which contains a document. IF the player finds the document hidden in these rooms, then the player wins, and wil
 * advance to the next level. Ninjas patrol the grid, moving randomly and checking if the player is around. IF the player so happens to meet with 
 * one of the ninjas, the ninja will stab the player, sending them back to the starting position and losing a life. Plaer has 3 lives.
 * 
 * <The Rusty Spoons>
 * <Mario Garcia> <Anuja Joshi> <Michelle Duong> <Matthew Musquiz> <Kristin Adachi>
 */
package edu.csupomona.cs.cs141.prog_assgnmntFINAL;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * The spy class, or otherwise known as the player class, is the main protagonist of the game world, the hero 
 * that is to be controlled by the one who is going to play. The player is given certain advantages, that is 
 * to move one direction in any direction for his/her turn, to shoot and kill an enemy if he/she so desires, and to
 * check rooms for the documents to win the game overall. Of course, the player is given three lives to complete their goal, 
 * if an enemy was to kill the player, the player would lose a life and start back to square one. If they lose all lives, the game
 * is lost.
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public class Spy extends Object implements BallOfEnergy, Serializable{
	
	/**
	 * UID that is used to check the authenticity of the class when loading a game.
	 */
	private static final long serialVersionUID = -7248216978623767313L;
	/**
	 * The coordinates used to determine the location of the spy.
	 */
	private int row, col;
	/**
	 * the lives that turns that the player will be using if they reach a powerup or an enemy. Turns will detemine 
	 * how many turns left for for invincibility to wear off, if the player had activated that powerup.
	 */
	private int lives, turns;
	/**
	 * returns the boolean alive, to make sure that the player is still alive in the game.
	 */
	private boolean alive;
	/**
	 * boolean to make sure that if the player activates the invincibility power up, then no enemy can stab the player
	 * for that certain amount of turns.
	 */
	private boolean impenetrable;
	/**
	 * gives the player a bullet to use whenever they so choose to use it. This will allow the player to kill an enemy, or enemies
	 * in a certain direction. 
	 */
	private int bullets;
	/**
	 * the symbol used to represent the player in the grid display.
	 */
	private String symbol = "S";
	
	private JLabel pic = new JLabel(new ImageIcon("GameImgs/MinionPlayer.jpg"));
	/**
	 * Constructor used to create the spy. Sets all values to its default settings, determined by the specifications of the project.
	 * @param alive
	 */
	public Spy(boolean alive)
	{
		this.alive = alive;
		lives = 3;
		bullets = 1;
		impenetrable = false;
		turns = 0;
		this.alive = alive;
	}
	
	/**
	 * returns the symbol of the player to the class that requests it.
	 * @return symbol - the symbol used to represent the player.
	 */
	public String getSymbol()
	{
		return symbol;
	}
	
	/**
	 * returns the current state of lives left to the class that requests it.
	 * @return lives - the number of lives that the player has left.
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * if a player is to be stabbed by an enemy in the grid, then this method will be called to remove a life 
	 * from the player. If the player has zero lives left, then the player is presumed dead and the game is lost.
	 */
	public void takeLife()
	{
		lives--;
		if( lives <= 0 )
		{
			alive = false;
		}
	}
	
	/**
	 * returns the status of the player, whether they are alive or dead, to the class that requests it.
	 * @return alive - the status of the player, whether they are alive or dead.
	 */
	public boolean isAlive()
	{
		return alive;
	}
	
	/**
	 * Method used to make the player invincible for 5 turns, which starts immediately once the player activates it.
	 * @param check - check to make the player invincible, and turn it true.
	 * @return impenetrable - boolean used to make the player invincible.
	 */
	public boolean makeImpenetrable(boolean check)
	{
		turns = 6;
		impenetrable = check;
		return impenetrable;
	}
	
	/**
	 * Method to return the status of the player's invincibility.
	 * @return impenetrable - the boolean variable used to make the player invincible.
	 */
	public boolean isImpenetrable()
	{
		return impenetrable;
	}
	
	/**
	 * returns the number of turns left for the invincibility to wear off.
	 * @return turns - the number of turns left for invincibility.
	 */
	public int getTurns()
	{
		return turns;
	}
	
	/**
	 * method used to decrement a turn for everytime the player moves, only if the player is
	 * invincible. 
	 */
	public void turnsLeft()
	{
		if(impenetrable)
		{
			turns--;
		}
		if( turns == 0 ) 
		{
			impenetrable = false;
		}
	}
	
	/**
	 * Sets the location of the player. This is used to determine where the player moves, and which coordinates 
	 * the player moves into.
	 * @param xPlayer - the row that the player moves into.
	 * @param yPlayer - the coloumn that the player moves into.
	 */
	public void getLocation(int xPlayer , int yPlayer)
	{
		row = xPlayer;
		col = yPlayer;
	}
	
	/**
	 * Look method that is used for the player to look around in the direction they desire, two spaces ahead of their position. If an enemy, or powerup is
	 * within the player's line of sight upon looking, they will be revealed and the symbols representing the objects will be displayed for the player to 
	 * see. This will trigger the warnings that an enemy is nearby the player.
	 * @param number - the direction that the player chooses to look at.
	 * @param n - the grid used that the player stands in.
	 * @param enemys - the enemies that will be used to check if they are revealed by the player.
	 * @param rooms - the rooms that will be used to block off the sight range of the player.
	 * @param powerups - power ups that will be checked. If the player spots any power up, they too will be revealed.
	 * @return - spotted - determines whether an enemy is nearby, which will trigger a warning for the player to watch out.
	 */
	public boolean look(int number, Object[][] n, Enemy[] enemys, Room[] rooms, PowerUp[] powerups)
	{
		boolean spotted = false;
		if(number == 1)
		{
			if( row != 0)
			{

				if( n[row-1][col] != rooms[0] && n[row-1][col] != rooms[1] && n[row-1][col] != rooms[2]
						&& n[row-1][col] != rooms[3] && n[row-1][col] != rooms[4] && n[row-1][col] != rooms[5]
						&& n[row-1][col] != rooms[6] && n[row-1][col] != rooms[7] && n[row-1][col] != rooms[8])
				{
					for( int e = 0; e < enemys.length; e++)
					{
						if( n[row-1][col] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
						else if(row != 1 && n[row-2][col] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
					}
					for( int p = 0; p < powerups.length; p++)
					{
						if( n[row-1][col] == powerups[p] )
						{
							powerups[p].revealed();

						}
						if(row != 1 && n[row-2][col] == powerups[p])
						{
							powerups[p].revealed();
						}
					}
				}
			}
		}
		else if( number == 2)
		{
			if( row != 8 )
			{
				if( n[row+1][col] != rooms[0] && n[row+1][col] != rooms[1] && n[row+1][col] != rooms[2]
						&& n[row+1][col] != rooms[3] && n[row+1][col] != rooms[4] && n[row+1][col] != rooms[5]
						&& n[row+1][col] != rooms[6] && n[row+1][col] != rooms[7] && n[row+1][col] != rooms[8])
				{
					for( int e = 0; e < enemys.length; e++)
					{
						if( n[row+1][col] == enemys[e])
						{
							enemys[e].revealEnemy();                                                   //MODIFY LIKE THIS!!!
							spotted = true;
						}						
						else if(row != 7 && n[row+2][col] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
					}
					for( int p = 0; p < powerups.length; p++)
					{
						if( n[row+1][col] == powerups[p] )
						{
							powerups[p].revealed();
						}
						if( row != 7 && n[row+2][col] == powerups[p])
						{
							powerups[p].revealed();
						}
					}
				}
			}
		}
		else if( number == 3)
		{
			if( col != 0)
			{	
				if( n[row][col-1] != rooms[0] && n[row][col-1] != rooms[1] && n[row][col-1] != rooms[2]
						&& n[row][col-1] != rooms[3] && n[row][col-1] != rooms[4] && n[row][col-1] != rooms[5]
						&& n[row][col-1] != rooms[6] && n[row][col-1] != rooms[7] && n[row][col-1] != rooms[8])
				{
					for( int e = 0; e < enemys.length; e++)
					{
						if( n[row][col-1] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
						else if(col != 1 && n[row][col-2] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
					}
					for( int p = 0; p < powerups.length; p++)
					{
						if( n[row][col-1] == powerups[p])
						{
							powerups[p].revealed();
						}
						if(col != 1 && n[row][col-2] == powerups[p])
						{
							powerups[p].revealed();	
						}
					}
				}
			}
		}
		else if( number == 4)
		{
			if(col != 8)
			{
				if( n[row][col+1] != rooms[0] && n[row][col+1] != rooms[1] && n[row][col+1] != rooms[2]
						&& n[row][col+1] != rooms[3] && n[row][col+1] != rooms[4] && n[row][col+1] != rooms[5]
						&& n[row][col+1] != rooms[6] && n[row][col+1] != rooms[7] && n[row][col+1] != rooms[8])
				{
					for( int e = 0; e < enemys.length; e++)
					{
						if( n[row][col+1] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
						else if(col != 7 && n[row][col+2] == enemys[e])
						{
							enemys[e].revealEnemy();
							spotted = true;
						}
					}
					for( int p = 0; p < powerups.length; p++)
					{
						if( n[row][col+1] == powerups[p])
						{
							powerups[p].revealed();
						}
						if( col != 7 && n[row][col+2] == powerups[p])
						{
							powerups[p].revealed();
						}
					}
				}
			}
		}
		return spotted;
	}
	
	/**
	 * This method will return the row coordinates that player is set in.
	 * @return row - the current row that the player sits in.
	 */
        @Override
	public int getRow()
	{
		return row;
	}
	
	/**
	 * method that returns the coloumn coordinates that the player is set in.
	 * @return col - the coloumn that the player sits in.
	 */
        @Override
	public int getCol()
	{
		return col;
	}
	
	/**
	 * Allows for the player to recieved an additional bullet. Used by the ammo increase power up, if the player activates it.
	 * @param bullets - the number of bullets the player currently has.
	 */
	public void gainBullets(int bullets)
	{
		this.bullets += bullets;
	}
	
	/**
	 * returns the number of bullets the player currently has to the class that requests it.
	 * @return bullets - the number of bullets the player has currently to use.
	 */
	public int getBullets()
	{
		return bullets;
	}
	
	/**
	 * The shoot method is used to allow the player to fire their bullet in the direction they so choose. If the bullet crosses
	 * the path of an enemy, the enemy will be killed, and the enemy killed will no longer be used for the duration of the game. If the player 
	 * attempts to fire their gun with no bullets, the player is not allowed to fire anymore, and they will waste a turn in doing so.
	 * @param number - the direction that the player whats to shoot.
	 * @param enemys - used to check which enemies will be caught in the path of the bullet.
	 * @param n - the grid used to determine where the player stands.
	 * @return counter - determines how many enemies where killed in the crossing of the bullet.
	 */
	public int shoot(int number, Enemy[] enemys, Object[][] n)
	{
		int counter = 0;
		bullets--;
		if( number == 1 )
		{
			for(int e = 0; e < enemys.length; e++)
			{
				if( enemys[e].getCol() == col && enemys[e].getRow() <= row )
				{
					enemys[e].iskilled();
					counter++;
				}
			}
		}
		else if( number == 2 )
		{
			for(int e = 0; e < enemys.length; e++)
			{
				if( enemys[e].getCol() == col && enemys[e].getRow() >= row )
				{
					enemys[e].iskilled();
					counter++;
				}
			}
		}
		else if(number == 3)
		{
			for(int e = 0; e < enemys.length; e++)
			{
				if( enemys[e].getCol() <= col && enemys[e].getRow() == row )
				{
					enemys[e].iskilled();
					counter++;
				}
			}
		}
		else if( number == 4)
		{
			for(int e = 0; e < enemys.length; e++)
			{
				if( enemys[e].getCol() >= col && enemys[e].getRow() == row )
				{
					enemys[e].iskilled();
					counter++;
				}
			}
		}
		return counter;
	}
	
	public JLabel getPic()
	{
		return pic;
	}
}
