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
 * 
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
public class ImageLoader extends Component {
           
    private BufferedImage img;
 
    public void paint(Graphics g) {
        g.drawImage(img, 100, 100, this);
    }
 
    public ImageLoader(String path) {
       try {
           img = ImageIO.read(new File(path));
       } catch (IOException e) {
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
}
