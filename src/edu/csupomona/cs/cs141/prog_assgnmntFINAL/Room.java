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
 * Room is the class that holds the document. Within the grid, they are merely mystery doors that will hold mostly nothing.
 * There will be 9 rooms set within the grid, with the possibility of one door holding the documents that the player must get to 
 * in order to win the game. This document is set in a random room in the grid, so the game is much more unpredictable. 
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public class Room extends Object implements BallOfEnergy, Serializable{
	
	/**
	 * The serial UID that is used to authenticate the save, and allow for the user to load their saves.
	 */
	private static final long serialVersionUID = -3992713002380419098L;
	/**
	 * The current locations of the room.
	 */
	private int row, col;
	/**
	 * Boolean value to determine whether a document is in the room.
	 */
	private boolean document;
	/**
	 * Allows for the debug mode or the Radar to reveal the document in the room that contains it.
	 */
	private boolean cheat;
	/**
	 * Symbol that is used to represent the room.
	 */
	private String symbol = "R";
	/**
	 * Boolean value that determines whether the room is hidden from the player's sight.
	 */
	private boolean hidden;
	
	/**
	 * The contructor that will set the default preferences of the room object.
	 */
	private JLabel img = new JLabel(new ImageIcon("GameImgs/Room.jpg"));
	
	public Room()
	{
		document = false;
		hidden = true;
		cheat = false;

	}
	
	/**
	 * Method used by the debug mode and radar to reveal the document on the grid, which will in turn allow for the displaying 
	 * of the symbol used to represent the document.
	 * @param cheat - boolean value used to reveal the document.
	 */
	//set cheat on to allow the Radar to find the room with the document
	public void cheat(boolean cheat)
	{
		this.cheat = cheat;
	}
	
	/**
	 * Method reveals the symbol of the room, if the player looks upon it.
	 */
	//reveal the symbol of the room
	public void revealed()
	{
		hidden = false;
	}
	
	/**
	 * Hides the symbol of the room, which in turn keep it hidden from the player.
	 */
	//hide the symbol of the room
	public void hidden()
	{
		hidden = true;
	}
	
	/**
	 * Sets the location of the rooms, as specified by the parameters, which will give the rooms the definite coordinates.
	 * @param x - the row coordinate at which the room is located at.
	 * @param y - the coloumn coordinate that the room is located at.
	 */
	public void getLocation( int x, int y)
	{
		row = x;
		col = y;
	}
	
	/**
	 * Will return the row coordinate to the class that requested it.
	 * @return row - the row coordinate of the room.
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Will return the coloumn coordinate to the class that requested it.
	 * @return col - the coloumn coordinate of the room.
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * Hides the document in the room, if the {@link Engine} commands it to. This is done randomly however, 
	 * any room choosen randomly will hide the document.
	 */
	//hide the document, used for the help of Radar
	public void hideDoc()
	{
		document = true;
	}
	
	/**
	 * Allows for the visual display of the rooms on the grid. If the room is hidden, then it will be kept in the 
	 * fog, however if the document is in the room and the Radar, or debug mode, is activated, then the room will reveal the document, allowing the
	 * player to see where the goal is located at. If not, then the room will remain hidden marked with the symbol used to represent the room. 
	 * @return name - the symbol of the room that will be displayed onto the grid.
	 */
	//Sent the request of the name of the powerup.
	public String getSymbol()
	{
		String name;
		if(hidden)
		{
			name = "X";
		}
		if(cheat && document)
		{
			name = "D";
		}
		else
		{
			name = symbol;
		}
		return name;
	}
	
	/**
	 * returns the boolean of whether the document is in the room, or if it is just an empty room.
	 * @return document - the document that is hidden in the room, if not, the room is empty.
	 */
	//check if this room has a document.
	public boolean hasDocument()
	{
		return document;
	}
	
	public JLabel getPic()
	{
		JLabel imgTemp;
		if(hidden)
		{
			imgTemp = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
		}
		if(cheat && document)
		{
			imgTemp = new JLabel(new ImageIcon("GameImgs/Image-2.jpg"));
		}
		else
		{
			imgTemp = img;
		}
		return imgTemp;
	}
}
