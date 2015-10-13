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
 * Enemy is the threat of the game. A player will mover throughout a grid like enviroment, in hopes of searching for
 * a document, however, the enemy is intended to prevent the player from reach his/her goals. The enemy is to move 
 * randomly in the grid, and search each adjacent tile for the player. If the player is next to the enemy, the enemy 
 * will stab the player, killing him/her, and sending him/her back to the starting point. 
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public class Enemy implements BallOfEnergy,Serializable{
	
	/**
	 * the serial version UID that will be used in order to properly load a saved game.
	 */
	private static final long serialVersionUID = 2180894655636700671L;
	/**
	 * the coordinates of the enemy object within the grid. These coordinates are intended to be used for 
	 * movement along the grid, as well as to assist the enemy in looking for the spy.
	 */
	private int row, col;
	/**
	 * boolean value that determines whether the enemy is alive or dead. If alive, then the enemy will still
	 * be displayed onto the grid. If not, the enemy object is no longer used in the grid.
	 */
	private boolean alive;
	/**
	 * variable that is used to determine whether the enemy object is hidden from sight of the player.
	 */
	private boolean hidden;
	/**
	 * Symbol used to represent the enemy within the grid. This is only seen if the enemy is visible to the 
	 * player or the debug method is active.
	 */
	private String symbol = "E";
	
	private JLabel img = new JLabel(new ImageIcon("GameImgs/Ninja.jpg"));
	/**
	 * Constructor of the enemy that is to set the object boolean values to true.
	 * @param alive - boolean value that sets the enemy alive to true. 
	 */
	public Enemy(boolean alive)
	{
		hidden = true;
		this.alive = alive;
	}
	
	/**
	 * sets the alive boolean false for the enemy object.
	 * @return alive - returns the state of the enemy. By this, the enemy is killed, and the method is to 
	 * make the enemy alive boolean false.
	 */
	//kill the enemy, where requested
	public boolean iskilled()
	{
		alive = false;
		return alive;
	}
	
	/**
	 * Gives the current state of the enemy whether the object is alive or dead. 
	 * @return alive - the boolean value that determines whether the enemy object should still be displayed or not.
	 */
	//send the word that enemy is either alive or dead
	public boolean isAlive()
	{
		return alive;
	}
	
	/**
	 * method used to hide the enemy from visible sight.
	 */
	//hide the enemy
	public void ishidden()
	{
		hidden = true;
	}
	
	/**
	 * reveals the enemy to the player, or world if in debug mode. Value assists with revealing the 
	 * symbol of that enemy.
	 */
	//reveal the enemy
	public void revealEnemy()
	{
		hidden = false;
	}
	
	/**
	 * Show the symbol of the enemy in the grid, but only if the enemy is not hidding from sight of the 
	 * player. If so, then return the default fog of war symbol. 
	 * @return symbol - the symbol that will represent the current state of the enemy in the grid.
	 */
	//determine whether enemy will show the symbol
	public String getSymbol()
	{
		if(hidden)
		{
			symbol = "X";
			return symbol;
		}
		else
		{
			symbol = "E";
			return symbol;
		}
	}
	
	/**
	 * Sets the coordinates of the enemy object within the grid. This method is useful for moving the enemy 
	 * and manipulating the grid to match the specified coordinates to move the enemy in.
	 * @param xEnemy - the row coordinate that the enemy will move into.
	 * @param yEnemy - the coloumn coordinate that the enemy will move into.
	 */
	//set the enemy location
	public void getLocation(int xEnemy , int yEnemy)
	{
		row = xEnemy;
		col = yEnemy;
	}
	
	/**
	 * This method will return the row coordinate of the enemy back to the class that requested it. 
	 * @return row - the row coordinate that is used for the movement of the enemy.
	 */
	//send the row of the enemy
	public int getRow()
	{
		return row;
	}
	
	/**
	 * This method will return the coloumn coordinate of the enemy back to the class that requested it.
	 * @return col - the coloumn coordinate that is used for the movement of the enemy.
	 */
	//send the coloumn of the enemy
	public int getCol()
	{
		return col;
	}
	
	/**
	 * The look method for the enemy. This is intended to search for the player, and determine to stab the player if he/she is
	 * on an adjacent tile to the enemy that is searching around. The enemy is to check above, down, left and right for the player.
	 * If player is found, the enemy will stab the player.
	 * @param n - the grid that the enemy is to use to check its surroundings.
	 * @param player - uses he identification of the player to determine the 
	 * @return stab - determines whether the enemy has successfully stabbed the player.
	 */
	//check if player is near the enemy
	public boolean isPlayerFound(Object[][] n, Spy player)
	{
		//check whether the enemy stabbed the player
		boolean stab = false;

		if(isAlive())
		{
			//check top of the grid
			if( row == 0 )
			{
				//if in top left corner, check down and to the right
				if( col == 0 )
				{
					if( n[row][col+1] == player || n[row][col] == player || n[row+1][col] == player)
					{
						stab = true;
					}
				}
				//if in top right corner, check down and to the left.
				else if( col == 8)
				{
					if( n[row][col-1] == player || n[row][col] == player || n[row+1][col] == player)
					{
						stab = true;
					}
				}
				//if not on either edge of the grid, check all around
				else if( col > 0 && col < 8)
				{
					if( n[row][col-1] == player ||  n[row][col+1] == player || n[row][col] == player || n[row+1][col] == player)
					{
						stab = true;
					}
				}
			}
			//if enemy is positioned on the bottom of the grid, check positions
			else if( row == 8 )
			{
				//if enemy is on the bottom left of the grid, check positions
				if( col == 0 )
				{
					if( n[row][col+1] == player || n[row][col] == player || n[row-1][col] == player)
					{
						stab = true;
					}
				}
				//of enemy is on bottom right of the grid, check positions
				else if( col == 8)
				{
					if( n[row][col-1] == player || n[row][col] == player || n[row-1][col] == player)
					{
						stab = true;
					}
				}
				//if enemy is not on either corner, check all around.
				else if( col > 0 && col < 8 )
				{
					if( n[row][col-1] == player || n[row][col] == player || n[row][col+1] == player || n[row-1][col] == player)
					{
						stab = true;
					}
				}
			}
			//if enemy is in center, and not anywhere on the corners of the grid, check these positions.
			else if( row < 8 && row > 0)
			{
				//if enemy is on the left side of the grid, check these positions
				if( col == 0 )
				{
					if(  n[row][col+1] == player || n[row][col] == player || n[row-1][col] == player || n[row+1][col] == player)
					{
						stab = true;
					}
				}
				//if enemy is on the right side of the grid, check these positions
				else if( col == 8)
				{
					if( n[row][col-1] == player || n[row][col] == player || n[row-1][col] == player || n[row+1][col] == player)
					{
						stab = true;
					}
				}
				//if enemy is in center and not on the edge of the grid, check all around
				else if( col > 0 && col < 8)
				{
					if( n[row][col+1] == player || n[row][col-1] == player || n[row][col] == player || n[row+1][col] == player || n[row-1][col] == player)
					{
						stab = true;
					}
				}
			}
		}
		//check if player is invincible
		if (player.isImpenetrable())
		{
			stab = false;
		}

		return stab;
	}
	
	/**
	 * Helper method used to move the enemy one tile within the grid from any direction specified. Two methods are used for the assisting of the
	 * movement of the enemy, this method is used to shorten the length of code that is required to move the enemy.
	 * @param number - the number used in determining the direction that the enemy is to move about.
	 * @param n - the grid used to determine where the enemy should move within.
	 * @param rooms - room objects to make sure that the enemy object will not collide and move through.
	 * @param r - the row in which the enemy is to move into.
	 * @param c - the coloumn in which the enemy is to move into.
	 * @param cnt - counter that checks to make sure all rooms will not be consumed by the enemy object that is to move.
	 */
	//move the enemy to the given location
    public void helperForMove(int number, Object[][] n, Room[] rooms, int r, int c, int cnt)
    {
        for(int j = 0; j < rooms.length; j++)
        {
            if( rooms[j] != n[r][c] )
            {
                cnt++;
                if(cnt >= 9 )
                {
                    getLocation(r, c);   
                }
            }
        }
    }

    /**
     * The move method for the enemy object. This method checks the grid to make sure no rooms will be in the way of the enemy.
     * After checking, the enemy is assigned new coordinates within the grid, which will be used by the grid to manipulate and 
     * reassign the enemy to its new location within.
     * @param number - the decision number used to determine the direction of which to move.
     * @param n - the grid that is used to move the enemy into.
     * @param rooms - the rooms that are specified within the game world. These are used to make sure that the enemy will not move into them.
     */
    //determine where the enemy wants to move
    public void move(int number, Object[][] n, Room[] rooms)
    {
        int counter = 0;
        if( number == 1) // move up
        {
            if( getRow() != 0 )
            {
                helperForMove(number, n, rooms, getRow()-1, getCol(), counter);
            }
        }
        else if(number == 2) // move down
        {
            if(getRow() != 8 )
            {
                helperForMove(number, n, rooms, getRow()+1, getCol(), counter);
            }
        }
        else if(number == 3) // move left
        {
            if(getCol() != 0 )
            {
                 helperForMove(number, n, rooms, getRow(),getCol()-1, counter);
            }
        }
        else if(number == 4) // move right
        {
            if( getCol() != 8 )
            {
                helperForMove(number, n, rooms, getRow(),getCol()+1, counter);
            }
        }
    }
    
    public JLabel getPic()
    {
		if(hidden)
		{
			img = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
			return img;
		}
		else
		{
			img = new JLabel(new ImageIcon("GameImgs/Ninja.jpg"));
			return img;
		}
    }
}
