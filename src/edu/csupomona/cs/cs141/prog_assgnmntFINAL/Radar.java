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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
/**
 * Radar is a subclass of PowerUp, and its main intention is to reveal the document that hides behind
 * one of the rooms in the world.
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public class Radar extends PowerUp implements Serializable{

	private static final long serialVersionUID = -5944204320844970345L;
	

	/** 
	 * Constructs the Radar object into the grid.
	 * Constructor method.
	 */
	public Radar()
	{
		super("RADAR","F", new JLabel(new ImageIcon("GameImgs/Radar.jpg")));
	}
	
	/**
	 * Overridden method that activates the Radar, this will access the array of rooms that were stored, and search through each one
	 * in hopes of finding the room with the document. If document is found, have the Radar set the the room's boolean to true, that the 
	 * room with the document is found and is displaying for the user to see.
	 * @returns active - the active state of the power up.
	 */
	public boolean activate(Spy spy, Room[] rooms)
	{
		System.out.println(getName() + " POWER UP HAS BEEN ACTIVATED! ( REVEALS THE DOCUMENT )");
		for( int r = 0; r < rooms.length; r++)
		{
			if( rooms[r].hasDocument() )
			{
				rooms[r].cheat(true);
			}
		}
		return super.activate(spy, rooms);
	}
}