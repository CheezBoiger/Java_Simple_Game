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

import java.util.Random;
import java.io.Serializable;

import javax.swing.JLabel;

/**
 * The Engine is what runs the actual statistics and calculations of the game. For this, the class also creates the grid that the game will 
 * be based on. The engine will also contain methods to manipulate the grid, based on how the user and the enemies move and if they are still
 * in the game or not. The engine specifies everything from creating the player, the enemies, the rooms, and the powerups, then placing them into the grid
 * for manipulation. This allows for the engine to do things that require the access to its grid, without the need to access them elsewhere. Engines are important 
 * to the design and implmentation of any program, as they are the classes that gather the information and manipulate them when needed.
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */

public class Engine implements Serializable{
	
	/**
	 * The UID to check authentication of the class, in which will provide useful saves that will be available to the user.
	 */
	private static final long serialVersionUID = 8452183431577643576L;
	/**
	 * the levels the player proceeds through once they find the document.
	 */
	private int level;
	/**
	 * confirms the revealing of the objects in the grid, by means of displaying all of the symbols in the grid, including the 
	 * document.
	 */
	private boolean reveal;
	/**
	 * the grid used for the game. This is where all of the object go into, so that they can be manipulated and moved.
	 */
	private Object[][] world = new Object[9][9];
	/**
	 * the player object in the grid.
	 */
	private Spy player = new Spy(true);
	/**
	 * creates the array of enemies in the grid.
	 */
	private Enemy[] ninjas = null;   
	/**
	 * creates the rooms in the grid.
	 */
	private Room[] rooms = null;     
	/**
	 * random decision maker for the enemies.
	 */
	private Random rm = new Random();
	/**
	 * creates an array of power ups.
	 */
	private PowerUp[] powerups = null;
	/**
	 * Sets whether the document has been found or not.
	 */
	private boolean docFound = false;
	/**
	 * determines if the player is checking a room for the document.
	 */
	private boolean checkRoom = false;
	/**
	 *Number of enemies killed in total.
	 */
	private int enemiesKilled = 0;
	/**
	 * number of powerups that the player has obtained.
	 */
	private int powerupsObtained = 0;
	/**
	 * Constructor to create the engine object.
	 */
	//create engine object
	public Engine()
	{
		for( int i = 0; i < world.length; ++i )
		{
			for(int j = 0; j < world[i].length; ++j )
			{
				world[i][j] = null;
			}
		}
		setPlayer();
		setRooms();
		setPowers();
		setEnemies(6);
		reveal = false;
		level = 1;
	}
	
	/**
	 * method called if the player has successful retrieved the document from the last game.
	 * Player proceeds to next level.
	 */
	public void levelUp()
	{
		docFound = false;
		checkRoom = false;
		world[player.getRow()][player.getCol()] = null;
		//record the new location for player
		player.getLocation(8, 0);
		setPowers();
		setRooms();
		setEnemies(6);
		level++;
	}
	
	/**
	 * returns the current level the player is on.
	 * @return level - the current dungeon or level.
	 */
	public int getLevels()
	{
		return level;
	}
	
	/**
	 * sets the player to their starting position, the bottom left of the grid.
	 */
	//set the player on starting position
	public void setPlayer()
	{
		player.getLocation(8, 0);
		world[player.getRow()][player.getCol()] = player;
	}
	
	/**
	 * Allows the grid to be revealed depending on the variable {@link #link reveal}. If true, the grid will display the symbols and locations of 
	 * the enemies, document and powerups.
	 * @param set - the boolean to determine whether the grid should be revealed.
	 */
	public void setReveal(boolean set)
	{
		reveal = set;
	}
	
	/**
	 * places the power ups into the grid. The power ups are placed randomly in the grid.
	 */
	public void setPowers()
	{
		//check if array is not filled
		boolean check = true;
		//create individual powerups for array
		powerups = new PowerUp[3];
		powerups[0] = new Radar();
		powerups[1] = new Invincibility();
		powerups[2] = new AmmoIncrease();
		//count the number of powerups being filled into the array
		int temp = 0;
		do
		{
			//set random coordinates
			int row, col;
			row = rm.nextInt(8);
			col = rm.nextInt(8);
			if( temp < powerups.length)
			{   
				//make sure powerups are not placed on top of rooms or player starting point.
				if(world[row][col] != rooms[0] && world[row][col] != rooms[1]
					&& world[row][col] != rooms[2] && world[row][col] != rooms[3]
					  && world[row][col] != rooms[4] && world[row][col] != rooms[5]
					  && world[row][col] != rooms[6] && world[row][col] != rooms[7]
					  && world[row][col] != rooms[8] && world[row][col] != player )	
				{
					world[row][col] = powerups[temp];
					powerups[temp].getLocation(row, col);
					temp++;
				}
			}
			else
			{
				check = false;
			}
		}while(check);
	}
	
	/**
	 * If the player is checking a room, then the value will return true, otherwise the value is false, meaning that 
	 * the player is not checking.
	 * @return checkRoom - value to determine whether the player is checking the room.
	 */
	public boolean getCheckRoom()
	{
		return checkRoom;
	}
	
	/**
	 * Overloaded method used to set the state of which the player is checking or not checking the room.
	 * @param setCheck - value given as to whether the room is being checked.
	 * @return checkRoom - value to determine whether the player is checking the room.
	 */
	public boolean getCheckRoom(boolean setCheck)
	{
		checkRoom = setCheck;
		return checkRoom;
	}
	
	/**
	 * Determines is the document was found, so that the player wins the round.
	 * @return docFound - value to determine whether the document was found.
	 */
	public boolean getDocFound()
	{
		return docFound;
	}
	
	/**
	 * If the player is attempting to debug the program, the value will confirm the debugging and thus reveal the entire grid.
	 * @return reveal - boolean value used to reveal the entire grid and the objects in it.
	 */
	//if player is requesting to debug, allow grid to be revealed
	public boolean isDebugging()
	{
		return reveal;
	}
	
	/**
	 * returns the number of bullets the player currently has.
	 * @return bullets - number of bullets player has.
	 */
	//request for the number of bullets player has
	public int getBullets()
	{
		return player.getBullets();
	}
	
	/**
	 * returns the number of turns left for the player's invincibility to wear off.
	 * @return turns - the number of turns of invincibility left over.
	 */
	//request how many turns player has left for invincibility
	public int getTurns()
	{
		return player.getTurns();
	}
	
	/**
	 * returns the status of the player, if they are dead or alive.
	 * @return alive - the boolean value used to determine the alive state of the player.
	 */ 
	//check if player is alive
	public boolean isAlive()
	{
		return player.isAlive();
	}
	
	/**
	 * returns the number of lives left that the player has.
	 * @return lives - the number of lives that the player currently has.
	 */
	//request for the number of lives player has
	public int getLife()
	{
		return player.getLives();
	}
	
	/**
	 * returns the symbol that represents the player on the grid display.
	 * @return symbol - the symbol of the player.
	 */
	//request for symbol representation of player
	public String getPlayerSymbol()
	{
		return player.getSymbol();
	}
	
	/**
	 * returns the row coordinates of the player in the grid.
	 * @return row - the row coordinate of the player
	 */
	//request for the row of the player
	public int getPlayerRow()
	{
		return player.getRow();
	}
	
	/**
	 * returns the player's coloumn coordinates in the grid.
	 * @return col - the player's coloumn coordinate.
	 */
	//request for the coloumn of the player
	public int getPlayerCol()
	{
		return player.getCol();
	}
	
	/**
	 * returns the current state of the enemy, whether they are alive or dead.
	 * @param number - the specific enemy in the array of enemies.
	 * @return alive - the current status of the enemy. 
	 */
	//check if enemy is alive
	public boolean isEnemyAlive( int number )
	{
		return ninjas[number].isAlive();
	}
	
	//request for the enemy row (location)
	public int getEnemyRow(int number)
	{
		return ninjas[number].getRow();
	}
	
	/**
	 * returns the coloumn coordinates of the enemy.
	 * @param number - the certain enemy in the array of enemies.
	 * @return col - the coloumn coordinate of the enemy.
	 */
	//request for the enemy coloumn (location)
	public int getEnemyCol(int number)
	{
		return ninjas[number].getCol();
	}
	
	/**
	 * returns the symbol of the enemy to display onto the grid.
	 * @param number - the certain enemy in the array of enemies.
	 * @return symbol - the value that represents the enemy.
	 */
	//request for the symbol representation of enemy
	public String getEnemySymbol(int number)
	{
		return ninjas[number].getSymbol();
	}
	
	/**
	 * sends the enemy back into hiding if the player is not looking.
	 * @param number - the certain enemy in the array of enemies.
	 */
	//hide the enemy if not seen by player look() method
	public void hideEnemy(int number)
	{
		ninjas[number].ishidden();
	}
	
	/**
	 * reveals the enemy if the player looks in that direction.
	 * @param number - the certain enemy in the array of enemies.
	 */
	//reveal the enemy if player finds the object
	public void revealEnemy(int number)
	{
		ninjas[number].revealEnemy();
	}
	
	/**
	 * returns the row coordinates of the room.
	 * @param number - the certain room in the array of rooms.
	 * @return row - the row coordinate of the room.
	 */
	//request for the row of the room (location)
	public int getRoomRow(int number)
	{
		return rooms[number].getRow();
	}
	
	/**
	 * returns the coloumn coordinates of the room.
	 * @param number - the certain room in the array of rooms.
	 * @return col - the coloumn coordinate of the room.
	 */
	//request for the coloumn of the room (location)
	public int getRoomCol(int number)
	{
		return rooms[number].getCol();
	}
	
	/**
	 * returns the symbol of the room. If, however, the document was revealed by the radar power up, then 
	 * the room with the document will be revealed. 
	 * @param number - the certain room in the array of rooms.
	 * @return symbol - symbol of the room.
	 */
	//request for the symbol representation of the room
	public String getRoomSymbol(int number)
	{
		if(reveal || !powerups[0].isActive())
		{
			rooms[number].cheat(true);
		}
		else
		{
			rooms[number].cheat(false);
		}
			return rooms[number].getSymbol();
	}
	
	/**
	 * hides the room from sight of the player. 
	 * @param number - the certain room from the array of rooms.
	 */
	//hide room for no reason
	public void hideRoom(int number)
	{
		rooms[number].hidden();
	}
	
	/**
	 * reveals the room with the document, if the radar power up is activated.
	 * @param number - the certain room with the document.
	 */
	//reveal the room, if radar is activated, room with document will be revealed
	public void revealRoom(int number)
	{
		rooms[number].revealed();
	}
	
	/**
	 * returns the row coordinate of the powerup.
	 * @param number - the certain powerup in the array of power ups.
	 * @return row - the row coordinate of the power up.
	 */
	//request for the row of the powerup (location)
	public int getPowerupRow(int number)
	{
		return powerups[number].getRow();
	}
	
	/**
	 * returns the coloumn coordinates of the power up.
	 * @param number - the certain power up in the array of power ups.
	 * @return col - the coloumn coordinate of the powerup.
	 */
	//request for the coloumn of the powerup (location)
	public int getPowerupCol(int number)
	{
		return powerups[number].getCol();
	}
	
	/**
	 * returns the symbol of the powerup for the player to see.
	 * @param number - the certain power up in the array of powerups/
	 * @return symbol - will return the power up symbol if the player is looking. If the player does not look in the direction of the
	 * powerup within the grid, then the grid will remain hidden.
	 */
	//request for the symbol representation of the powerup
	public String getPowerupSymbol( int number)
	{
		return powerups[number].getSymbol();
	}
	
	/**
	 * hides the power up from sight of the player.
	 * @param number - the certain power up in the array of powerups.
	 */
	public void hidePowerup(int number)
	{
		powerups[number].hidden();
	}
	
	
	/**
	 * reveals the power up if the player spots it with the look method.
	 * @param number - the certain power up in the array of power ups.
	 */
	public void revealPowerup(int number)
	{
		powerups[number].revealed();
	}
	
	/**
	 * Once the player walks on top of a power up, the power up will activate depending on what it does. 
	 * the method will then return the current state of the powerup, in which the power up is deactivated for the duration of the level.
	 * @param number - the certain power up in the array of powerups.
	 * @return active - the current state of the power up, if it is still active or not.
	 */
	public boolean isPowerupActivated( int number)
	{
		return powerups[number].isActive();
	}
	
	/**
	 * Method used to set the rooms into the grid. After setting, the document is then randomly placed 
	 * into a room, which is choosen randomly.
	 */
	//set the rooms into the grid
	public void setRooms()
	{
		//develop an array of rooms
		rooms = new Room[9];
		for(int r = 0; r < rooms.length; r++)
		{
			rooms[r] = new Room();
			
		}
		world[1][1] = rooms[0];
		rooms[0].getLocation(1, 1);
		world[1][4] = rooms[1];
		rooms[1].getLocation(1, 4);
		world[1][7] = rooms[2];
		rooms[2].getLocation(1, 7);
		world[4][1] = rooms[3];
		rooms[3].getLocation(4, 1);
		world[4][4] = rooms[4];
		rooms[4].getLocation(4, 4);
		world[4][7] = rooms[5];
		rooms[5].getLocation(4, 7);
		world[7][1] = rooms[6];
		rooms[6].getLocation(7, 1);
		world[7][4] = rooms[7];
		rooms[7].getLocation(7, 4);
		world[7][7] = rooms[8];
		rooms[8].getLocation(7, 7);
		rooms[rm.nextInt(8)].hideDoc();
	}
	
	/**
	 * Creates the enemies, organizes them into their own array, and then places each one randomly into the world, or grid.
	 * @param number - specifies how many enemies to place into the grid.
	 */
	//set the enemies into the grid
	public void setEnemies(int number)
	{	
		//created an enemy array and story some enemy object
		ninjas = new Enemy[number];
		for(int k = 0; k < ninjas.length; k++)
		{
			//add some ninjas into the array
			ninjas[k] = new Enemy(true);
		}
		//count to make sure locations are given to more than the array length
		int temp = 0;
		boolean check = true;
		do
		{
			int row = rm.nextInt(9);
			int col = rm.nextInt(9);
			if( temp < ninjas.length)
			{
				//make sure ninja is not given a location on a room or near player
				if(row != 8 && col != 7 && row != 6 && col != 0 && col != 1)
				{
					if(world[row][col] != rooms[0] && world[row][col] != rooms[1]
					  && world[row][col] != rooms[2] && world[row][col] != rooms[3]
					  && world[row][col] != rooms[4] && world[row][col] != rooms[5]
					  && world[row][col] != rooms[6] && world[row][col] != rooms[7]
					  && world[row][col] != rooms[8] )	
					{
						//set the player location
						world[row][col] =  ninjas[temp];
						ninjas[temp].getLocation(row, col);
						temp++;
					}
				}
			}
			else
			{
				check = false;
			}
		}while(check);
	}
	
	/**
	 * moves the player in the grid, depending on the direction of where the player wishes to move. 
	 * Player moves along the grid, but cannot move through rooms or outside the world.
	 * @param number - the direction where the player wishes to move.
	 */
	//move the player
	public void movePlayer(int number)
	{		
		//check for the document
		int counter = 0;
		if( number == 0)
		{
			//do nothing

		}
		//move up
		else if( number == 1)
		{
			//make sure if player is on top edge of grid, to  not go out of bounds
			if( player.getRow() != 0 )
			{
				//make sure player does not walk through the room
				for(int i = 0; i < rooms.length; i++)
				{
					if( rooms[i] != world[player.getRow()-1][player.getCol()])
					{
						//insure player that it can absolutely not walk into the any rooms on the grid
						counter++;
						//if there are no rooms there, absolutely no rooms, allow player to move to the requested position
						if(counter == 9)
						{
							//set player to new location on grid
							world[player.getRow()-1][player.getCol()] = player;
							//remove player from the original position
							world[player.getRow()][player.getCol()] = null;
							//record the new location for player
							player.getLocation(player.getRow()-1, player.getCol());
							//if player moves onto a powerup, active the powerup
							for( int p = 0; p < powerups.length; p++)
							{
								if( world[powerups[p].getRow()][powerups[p].getCol()] == world[player.getRow()][player.getCol()] && powerups[p].isActive() == true)
								{
									powerups[p].activate(player, rooms);
									powerupsObtained++;
								}
							}
						}
					}
				}

			}
		}
		else if(number == 2)
		{
			if( player.getRow() != 8 )
			{
				for(int i = 0; i < rooms.length; i++)
				{
					if( rooms[i] != world[player.getRow()+1][player.getCol()])
					{
						counter++;
						if(counter == 9)
						{
							world[player.getRow()+1][player.getCol()] = player;  //move down
							world[player.getRow()][player.getCol()] = null;
							player.getLocation(player.getRow()+1, player.getCol());
							for( int p = 0; p < powerups.length; p++)
							{
								if( world[powerups[p].getRow()][powerups[p].getCol()] == world[player.getRow()][player.getCol()] && powerups[p].isActive() == true)
								{
									powerups[p].activate(player, rooms);
									powerupsObtained++;
								}
							}
						}
					}
					if( player.getRow() != 8)
					{
						if(world[player.getRow()+1][player.getCol()]  == rooms[i])
						{
							checkRoom = true;
							if( rooms[i].hasDocument() )
							{
								docFound = true;
							}
						}
					}
				}
			}
		}
		else if(number == 3)
		{
			if( player.getCol() != 0 )
			{
				for(int i = 0; i < rooms.length; i++)
				{
					if( rooms[i] != world[player.getRow()][player.getCol()-1])
					{
						counter++;
						if(counter == 9)
						{
							world[player.getRow()][player.getCol()-1] = player;  //move left
							world[player.getRow()][player.getCol()] = null; 
							player.getLocation(player.getRow(), player.getCol()-1);
							for( int p = 0; p < powerups.length; p++)
							{
								if( world[powerups[p].getRow()][powerups[p].getCol()] == world[player.getRow()][player.getCol()] && powerups[p].isActive() == true)
								{
									powerups[p].activate(player, rooms);
									powerupsObtained++;
								}
							}
						}
					}
				}
			}
		}
		else if(number == 4)
		{
			if( player.getCol() != 8 )
			{
				for(int i = 0; i < rooms.length; i++)
				{
					if( rooms[i] != world[player.getRow()][player.getCol()+1])
					{
						counter++;
						if(counter == 9)
						{
							world[player.getRow()][player.getCol()+1] = player;  //move right
							world[player.getRow()][player.getCol()] = null;
							player.getLocation(player.getRow(), player.getCol()+1);
							for( int p = 0; p < powerups.length; p++)
							{
								if( world[powerups[p].getRow()][powerups[p].getCol()] == world[player.getRow()][player.getCol()] && powerups[p].isActive() == true)
								{
									powerups[p].activate(player, rooms);
									powerupsObtained++;
								}
							}
						}
					}
				}
			}
		}
		else if( number == 7)
		{
			System.exit(0);
		}
		player.turnsLeft();
	}
	
	public boolean checkForPlayer()
	{
		//find whether a player is successfully stabbed
		boolean success = false;
		//delete enemy from its original position
		for(int i = 0; i < world.length; i++)
		{
			for(int j = 0; j < world[i].length; j++)
			{
				for( int k = 0; k < ninjas.length; k++)
				{
					if(world[i][j] == ninjas[k])
					{
						//check if player is nearby enemy
						if( ninjas[k].isPlayerFound(world, player) )
						{
							//stab the player and send him back to starting position
							player.takeLife();
							world[player.getRow()][player.getCol()] = null;
							player.getLocation(8, 0);
							success = true;
						}
					}
				}
			}
		}
		return success;
	}

	/**
	 * moves the enemy in a random direction. Each enemy, in turn, is removed from their original positions on the grid, and placed into 
	 * their new positions, depending on which direction they moved. Before doing so however, each enemy checks to see if a player is adjacent to 
	 * their current position before moving. If so, the player will be stabbed.
	 * @return success - value to determine whether the enemy has successfully stabbed the player.
	 */
	public void moveEnemy()
	{
		//delete enemy from its original position
		for(int i = 0; i < world.length; i++)
		{
			for(int j = 0; j < world[i].length; j++)
			{
				for( int k = 0; k < ninjas.length; k++)
				{
					if(world[i][j] == ninjas[k])
					{
						world[i][j] = null;
						for( int p = 0; p < powerups.length; p++)
						{
							//return powerup back to its posisiton if enemy was originally on the spot
							if( world[i][j] == world[powerups[p].getRow()][powerups[p].getCol()])
							{
								if( powerups[p].isActive() )
								{
									world[i][j] = powerups[p];
								}
							}
						}
					}
				}
			}
		}
		//place enemy in its new position
		for( int n = 0; n < ninjas.length; n++)
		{
			int number = rm.nextInt(4) + 1;
			//check if enemy is still alive
			if( ninjas[n].isAlive() )
			{
				ninjas[n].move(number, world, rooms);
				world[ninjas[n].getRow()][ninjas[n].getCol()] = ninjas[n];
			}
			else if( !ninjas[n].isAlive() )
			{
				world[ninjas[n].getRow()][ninjas[n].getCol()] = null;
			}
		}
	}
	
	/**
	 * returns the value of whether the player has spotted the enemy or not.
	 * @param number - the direction of which the player chooses to look.
	 * @return spotted - boolean value that determines whether an enemy is spotted.
	 */
	public boolean look(int number)
	{
		return player.look(number, world, ninjas, rooms, powerups);
	}
	
	/**
	 * Allows the player to shoot the enemy, based on how many bullets the player currently possesses.
	 * @param number - the direction at which the player shoots.
	 * @return counter - the number of enemies killed by that one bullet.
	 */
	public int shoot(int number)
	{
		int temp = player.shoot(number, ninjas , world);
		enemiesKilled += temp;
		return temp;
	}
	
	/**
	 * Return the total number of power ups  the player obtained throughout the run.
	 * @return powerupsObtained - total number of power ups player obtained throughout the run, or game.
	 */
	public int getPowerupsObtained()
	{
		return powerupsObtained;
	}
	
	/**
	 * Return the total number of enemies killed throughout the game.
	 * @return enemiesKilled - the total number of enemies killed throughout the run, or game.
	 */
	public int getEnemiesKilled()
	{
		return enemiesKilled;
	}
	
	/**
	 * Returns  the image of the player onto the display.
	 * @return 
	 */
	public JLabel getSpyPicture()
	{
		return player.getPic();
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public JLabel getPowerUpPic(int number)
	{
		return powerups[number].getPic();
	}
	
	public JLabel getRoomPic(int number)
	{
		return rooms[number].getPic();
	}
	
	public JLabel getEnemyPic(int number)
	{
		return ninjas[number].getPic();
	}
}
