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

/**
 * Of course every entity is simply a cute little ball of energy. This interface is implemented across all 
 * objects that will otherise be using these methods that will allow for easier understanding of how similar 
 * these objects are.
 * @author cmgarcia
 *
 */
public interface BallOfEnergy
{

	/**
	 * 
	 * @param x - the row location of the object.
	 * @param y - the col location of the object.
	 */
	public void getLocation(int x, int y);

	/**
	 * Return the row location of the object.
	 * @return row - the row coordinate of the object in a grid.
	 */
	public int getRow();
	
	/**
	 * Return the coloumn location of the object.
	 * @return col - the coloumn coordinate of the object in a grid.
	 */
	public int getCol();
	
	/**
	 * Returns the symbol of the object.
	 * @return symbol - the symbol representing the object.
	 */	
	public String getSymbol();
}
