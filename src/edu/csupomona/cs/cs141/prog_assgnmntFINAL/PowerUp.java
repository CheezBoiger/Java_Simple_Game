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
 * Power up class is set up to give the player the advantage of the game. Power ups allow the 
 * player to recieve one more bullet, become invincible, or to reveal the document for the player. Of course, the player
 * would need to find these power ups in the grid in order to recieve these advantages. These powerups are set randomly across 
 * the grid for the player to find. 
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public abstract class PowerUp extends Object implements BallOfEnergy, Serializable {
	
	/**
	 * the UID serial used to check the authenticity of the save that the player attempts to load in to.
	 */
	private static final long serialVersionUID = -5862677562197249173L;
	/**
	 * Active is the boolean to determine whether the powerup should still be within the game or not.
	 */
	private boolean active;
	/**
	 * the location of the power ups in the world, or grid. 
	 */
	private int row, col;
	/**
	 * Provides the name and symbol that represent the power up that will be displayed on the game world.
	 */
	private String name, symbol;
	/**
	 * determines whether the player can visibly see the powerup, if not the power up will remain hidden in the 
	 * fog of war. 
	 */
	private boolean hidden;
	
	private JLabel img;
	
	/**
	 * The constructor for the power up. This is used to create the power up that is specified.
	 * @param name - the name of the power up that will be activated.
	 * @param symbol - the symbol used to represent the power up.
	 */
	public PowerUp(String name, String symbol, JLabel img)
	{
		active = true;
		this.name = name;
		this.symbol = symbol;
		this.img = img;
		hidden = true;
	}
	
	/**
	 * reveals the symbol of the powerup to the player, if the player looks in to the direction of the power up.
	 */
	public void revealed()
	{
		hidden = false;
	}
	
	/**
	 * hides the power up after the player has seen it. 
	 */
	public void hidden()
	{
		hidden = true;
	}
	
	/**
	 * Determines whether the symbol of the power up will be revealed on the grid, or remain hidden in the fog. 
	 * This is also determined if the power up hasn't been activated. If so, the power up will no longer be visible in the 
	 * grid, nor will it be used again.
	 * @return symbol - the symbol to represent the current state of visibility of the power up.
	 */
	public String getSymbol()
	{
		String name;
		if( hidden == false && active == true)
		{
			name = this.symbol;
		}
		else
		{
			name = "X";
		}
		return name;
	}
	
	/**
	 * returns the name of the powerup.
	 * @return name - the string name of the powr up.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Activates the power up, which is based on which one was activated. Activate will be overridden depending on which 
	 * one the player walks upon. This will by default deactivate the power up after, which will overall remove the power up from the game.
	 * @param spy - the player that the power up will act on.
	 * @param rooms - the rooms that the power up will act on. This is only used by its subclass {@link Radar}.
	 * @return active - the current state of the power up, if false this power up will be removed from the game for the duration.
	 */
	public boolean activate(Spy spy, Room[] rooms)
	{
		active = false;
		return active;
	}
	
	/**
	 * Returns the current state of the power up. 
	 * @return active - the state of the power up. This is used to determine whether the player has walked over the power up.
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * sets the location of the power up, by reassigning the coordinates by row and coloumn.
	 * @param row - the row in which to set the location of the powerup.
	 * @param col - the coloumn in which to set the location of the powerup.
	 */
	public void getLocation(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Returns the current row coordinate that the power up is located in.
	 * @return row - the current row coordinate of the power up.
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Returns the current coloumn that the power up is located in.
	 * @return col - the current col coordinate of the power up.
	 */
	public int getCol()
	{
		return col;
	}
	
	public JLabel getPic()
	{
		JLabel show = null;
		if( !hidden && active )
		{
			show = this.img;
		}
		else
		{
			show = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
		}
		return show;
	}
}