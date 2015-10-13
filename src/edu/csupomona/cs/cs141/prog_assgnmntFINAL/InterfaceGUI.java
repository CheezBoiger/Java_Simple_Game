/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;quez
 * 
 * Final Project Programming assignment
 * 
 * For this project, we as a group explored the basics of computer programming, using only simple methods and ways to properly
 * find a solution for this project. In this project, however, the goal is to have the player move about a 9 X 9 grid, consisting of
 * 9 evenly spaced rooms, one of which contains a document. IF the player finds the document hidden in these rooms, then the player wins, and will
 * advance to the next level. Ninjas patrol the grid, moving randomly and checking if the player is around. IF the player so happens to meet with 
 * one of the ninjas, the ninja will stab the player, sending them back to the starting position and losing a life. Player has 3 lives.
 * 
 * <The Rusty Spoons>
 * <Mario Garcia> <Anuja Joshi> <Michelle Duong> <Matthew Musquiz> <Kristin Adachi>
 */
package edu.csupomona.cs.cs141.prog_assgnmntFINAL;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The Graphical user interface will promptly load up the graphics in this game, or what was done. Game will loas a JFrame, and provide a 
 * JPanel that is used to develop the 9 X 9 grid. the Interface will then act as a key listener, which will allow the player to use keys 
 * to move and attack and look around for the document.
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duong, Matthew Musquiz, Kristin Adachi
 *
 */

public class InterfaceGUI implements KeyListener {
	/**
	 * the engine is what starts the game off. This will be used in order to obtain information that the game will manipulate 
	 * and send back to the interface for displaying.
	 */
	private Engine engine = new Engine();
	/**
	 * The save and load object that will be used to save and load the files that are in progress by the user.
	 */
	private SaveLoad saveLoad = new SaveLoad();
	
	/**
	 * The panel that will house the JLabels from the game engine. This will also set all of the images into a 9 X 9 grid.
	 */
	private JPanel panel = new JPanel(new GridLayout(9,9));
	/**
	 * The heads up display that will be displayed on the bottom of the window of the game.
	 */
	private JPanel HUD = new JPanel(new BorderLayout());
	/**
	 * The main frame that will pop up as a window. This frame is to be displaying everything that the user interface throws at it.
	 */
    private JFrame f = new JFrame("The Newb Game Menu");
    
    /**
     * Paints the display of the world, the 9 X 9 grid, which is determined by the locations of each enemy, room, power up, and player.
     * @param number - the input that the user specifies. Used for the look method.
     */
    public void paint(int number)
    {
    	panel.removeAll();
    	for(int i = 0; i < 9; i++)
    	{
    		for(int j = 0; j < 9; j++)
    		{
				int counter = 0;
				if( i == engine.getPlayerRow() && j == engine.getPlayerCol())
				{
					counter++;
				}
				
				for( int r = 0; r < 9; r++)
				{
					if( i == engine.getRoomRow(r) && j == engine.getRoomCol(r))
					{
						counter++;
					}
				}
				
				for( int p = 0; p < 3; p++)
				{
					if( engine.isPowerupActivated(p) == true)
					{
						if( i == engine.getPowerupRow(p) && j == engine.getPowerupCol(p))
						{
							counter++;
						}
					}
				}
				
				for(int e = 0; e < 6; e++)
				{
					if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e)  && engine.isEnemyAlive(e) == true)
					{
						counter++;
					}
				}
				if( counter > 0)
				{
					int limit = 0;
					for( int r = 0; r < 9; r++)
					{
						if( i == engine.getRoomRow(r) && j == engine.getRoomCol(r))
						{
							panel.add(engine.getRoomPic(r));	
						}
					}
					if( i == engine.getPlayerRow() && j == engine.getPlayerCol() && limit < 1)
					{
						panel.add(engine.getSpyPicture());
						limit++;
					}
					for(int e = 0; e < 6; e++)
					{
						if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e) && limit < 1 && engine.isEnemyAlive(e) == true)
						{
							JLabel imgEnemy = engine.getEnemyPic(e);
							panel.add(imgEnemy);
							engine.hideEnemy(e);
							limit++;
						}
					}

					for( int p = 0; p < 3; p++)
					{
						if( engine.isPowerupActivated(p) == true)
						{
							if( i == engine.getPowerupRow(p) && j == engine.getPowerupCol(p) && limit < 1)
							{
								panel.add(engine.getPowerUpPic(p));
								engine.hidePowerup(p);
								limit++;
							}
						}
					}
				}
				else
				{
					if( number == 1 )
					{
						if( i == engine.getPlayerRow() - 1 && j == engine.getPlayerCol())
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else if( i == engine.getPlayerRow() - 2 && j == engine.getPlayerCol())
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else  //fog
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
							panel.add(imgEmpty);
						}
					}
					else if(number == 2)
					{
						if( i == engine.getPlayerRow() + 1 && j == engine.getPlayerCol())
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else if( i == engine.getPlayerRow() + 2 && j == engine.getPlayerCol())
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else //fog
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
							panel.add(imgEmpty);
						}
					}
					else if(number == 3)
					{
						if( i == engine.getPlayerRow() && j == engine.getPlayerCol() - 1)
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else if( i == engine.getPlayerRow() && j == engine.getPlayerCol() - 2)
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else //fog
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
							panel.add(imgEmpty);
						}
					}
					else if(number == 4)
					{
						if( i == engine.getPlayerRow() && j == engine.getPlayerCol() + 1)
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else if( i == engine.getPlayerRow() && j == engine.getPlayerCol() + 2)
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
							panel.add(imgEmpty);
						}
						else //fog
						{
							JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
							panel.add(imgEmpty);
						}
					}
					else //fog
					{
						JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/FogAlpha.jpg"));
						panel.add(imgEmpty);
					}
				}
				panel.validate();
    		}
    	}
    }
    
    
    
    /**
     * Prints the debug version of the display, which so to speak is the mode used to debug the system. This will reveal 
     * all enemies and power ups.
     * @param number - the number that the player inputs in order to look around.
     */
    public void debugPaint(int number)
    {
    	panel.removeAll();
    	for(int i = 0; i < 9; i++)
    	{
    		for(int j = 0; j < 9; j++)
    		{
				int counter = 0;
				if( i == engine.getPlayerRow() && j == engine.getPlayerCol())
				{
					counter++;
				}
				
				for( int r = 0; r < 9; r++)
				{
					if( i == engine.getRoomRow(r) && j == engine.getRoomCol(r))
					{
						counter++;
					}
				}
				
				for( int p = 0; p < 3; p++)
				{
					if( engine.isPowerupActivated(p) == true)
					{
						if( i == engine.getPowerupRow(p) && j == engine.getPowerupCol(p))
						{
							counter++;
						}
					}
				}
				
				for(int e = 0; e < 6; e++)
				{
					if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e)  && engine.isEnemyAlive(e) == true)
					{
						counter++;
					}
				}
				if( counter > 0)
				{
					int limit = 0;
					for( int r = 0; r < 9; r++)
					{
						if( i == engine.getRoomRow(r) && j == engine.getRoomCol(r))
						{
							engine.revealRoom(r);
							panel.add(engine.getRoomPic(r));	
						}
					}
					if( i == engine.getPlayerRow() && j == engine.getPlayerCol() && limit < 1)
					{
						panel.add(engine.getSpyPicture());
						limit++;
					}
					for(int e = 0; e < 6; e++)
					{
						if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e) && limit < 1 && engine.isEnemyAlive(e) == true)
						{
							engine.revealEnemy(e);
							JLabel imgEnemy = engine.getEnemyPic(e);
							panel.add(imgEnemy);
							limit++;
						}
					}
					for( int p = 0; p < 3; p++)
					{
						if( engine.isPowerupActivated(p) == true)
						{
							if( i == engine.getPowerupRow(p) && j == engine.getPowerupCol(p) && limit < 1)
							{
								engine.revealPowerup(p);
								panel.add(engine.getPowerUpPic(p));
								limit++;
							}
						}
					}
				}
				else
				{
					JLabel imgEmpty = new JLabel(new ImageIcon("GameImgs/Image-1.jpg"));
					panel.add(imgEmpty);
				}
				
				panel.revalidate();
    		}
    	}
    }
    
    /**
     * Will start the game up, creates the JFrame and what is inside of it; the grid, the Heads Up Display, the 
     * panel, the menu bar and so on.
     */
    public void play()
    {
    	JMenuBar menuBar = new JMenuBar();
    	JMenu options = new JMenu("Options");
    	JMenu help = new JMenu("Help");
    	
    	menuBar.add(options);
    	menuBar.add(help);
    	
    	JMenuItem game = new JMenuItem("New Game");
    	game.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			engine = new Engine();
    			JOptionPane.showMessageDialog(f, "NEW GAME!!");
    			paint(0);
    		}
    	
    	});
    	JMenuItem save = new JMenuItem("Save");
    	save.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			String filename;
    			filename = JOptionPane.showInputDialog("NAME OF YOUR SAVE.");
    			saveLoad.save(filename, engine);
    			JOptionPane.showMessageDialog(f, "GAME SAVED!!");
    		}
    	
    	});
    	JMenuItem load = new JMenuItem("Load");
    	load.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			boolean success = false;
    			do
    			{
    				String filename;
    				filename = JOptionPane.showInputDialog("NAME OF WHAT YOU WANT TO LOAD.");
    				if( filename == null)
    				{
    					success = true;
    				}
    				else if( saveLoad.load(filename) == null)
    				{
    					JOptionPane.showMessageDialog(f, filename + " NOT FOUND");
    				}
    				else
    				{
    					JOptionPane.showMessageDialog(f, "GAME LOADED!!");
    	 				engine = saveLoad.load(filename);
    	 				if(engine.isDebugging() == true)
    	 				{
    	 					debugPaint(0);
    	 				}
    	 				else
    	 				{
    	 					paint(0);
    	 				}
    					success = true;
    				}
    			}while(success == false);
    		}
    	
    	});
    	
    	JMenuItem quit = new JMenuItem("Quit");
    	quit.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			JOptionPane.showMessageDialog(f,"GAME HAS QUIT!!");
    			System.exit(0);
    		}
    	
    	});
    	
    	JMenuItem about = new JMenuItem("About");
    	about.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			JOptionPane.showMessageDialog(f, "Development Team:\n\nMario Garcia\nAnuja Joshi\nMichelle Duong\nKristin Adachi\nMatthew Musquiz");
    		}
    	
    	});
    	
    	JMenuItem howtoplay = new JMenuItem("How To Play");
    	howtoplay.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e)
    		{
    			JOptionPane.showMessageDialog(f, "Move with the following keys:\nUP_ARROW - move up\nDOWN_ARROW - move down\nLEFT_ARROW - move left\nRIGHT_ARROW - move right"
    					+ "\n\nPowerups:\nInvincibility - Invincible for 5 turns\nRadar - Reveal the location of the document\nAmmo Increase - Increase your ammo count by 1"
    					+ "\n\nShooting: " + "\nW - shoot up\nS - shoot down\nA - shoot left\nD - shoot right\n\nSearching Tile Spaces:\n\n1 - Look Up\n2 - Look Down\n3 - Look Left \n4 - Look Right"
    					+"\n\nLook for the document hiding in one of these rooms\nWatch for ninjas, they want to rek you.");
    		}
    	
    	});

    	options.add(game);
    	options.add(save);
    	options.add(load);
    	options.add(quit);
    	help.add(about);
    	help.add(howtoplay);
    	
    	f.setJMenuBar(menuBar);
		paint(0);
		f.addKeyListener(this);
		f.setFocusable(true);
		f.setFocusTraversalKeysEnabled(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setHUD();
		f.add(HUD, BorderLayout.SOUTH);
    	f.add(panel);
    	f.pack();
    	f.setVisible(true);
    	f.setSize(470, 650);

    }

    /**
     * Key events used in this method allow for the interaction of the game within it. This is done by adding in 
     * a key listener into the JFrame in order to take input from the user.
     */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		boolean looking = false;
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_C)
		{
			JOptionPane.showMessageDialog(f,"DEBUG MODE ACTIVATED (CHEATER MODE ACTIVE MANG.)");
			engine.setReveal(true);
			looking = true;
			debugPaint(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_H)
		{
			JOptionPane.showMessageDialog(f,"DEBUG MODE DEACTIVATED (HIDE SHAME MODE ACTIVE.)");
			engine.setReveal(false);
			looking = true;
			paint(0);
		}
		
		if( e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			engine.movePlayer(4);
			if(engine.checkForPlayer() == true)
			{
				JOptionPane.showMessageDialog(f, "YOU WERE STABBED!!");
				if(engine.getLife() == 0)
				{
					JOptionPane.showMessageDialog(f, "0 LIVES! GAME OVER!!");
					JOptionPane.showMessageDialog(f,"LEVELS CLEARED: " + (engine.getLevels() - 1) + "\nBULLETS ON DEATH: " + engine.getBullets()
							+ "\nENEMIES KILLED: " + engine.getEnemiesKilled() + "\nPOWERUPS OBTAINED: " + engine.getPowerupsObtained(), "Score", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				if(engine.isDebugging() == true)
				{
					debugPaint(0);
				}
				else
				{
					paint(0);
				}
			}
			engine.moveEnemy();
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(0);
			}
		}
		else if( e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			engine.movePlayer(3);
			if(engine.checkForPlayer() == true)
			{
				JOptionPane.showMessageDialog(f, "YOU WERE STABBED!!");
				if(engine.getLife() == 0)
				{
					JOptionPane.showMessageDialog(f, "0 LIVES! GAME OVER!!");
					JOptionPane.showMessageDialog(f,"LEVELS CLEARED: " + (engine.getLevels() - 1) + "\nBULLETS ON DEATH: " + engine.getBullets()
							+ "\nENEMIES KILLED: " + engine.getEnemiesKilled() + "\nPOWERUPS OBTAINED: " + engine.getPowerupsObtained(), "Score", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				if(engine.isDebugging() == true)
				{
					debugPaint(0);
				}
				else
				{
					paint(0);
				}
			}
			engine.moveEnemy();
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(0);
			}
		}
		else if( e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			engine.movePlayer(2);
			if(engine.checkForPlayer() == true)
			{
				JOptionPane.showMessageDialog(f, "YOU WERE STABBED!!");
				if(engine.getLife() == 0)
				{
					JOptionPane.showMessageDialog(f, "0 LIVES! GAME OVER!!");
					JOptionPane.showMessageDialog(f,"LEVELS CLEARED: " + (engine.getLevels() - 1) + "\nBULLETS ON DEATH: " + engine.getBullets()
							+ "\nENEMIES KILLED: " + engine.getEnemiesKilled() + "\nPOWERUPS OBTAINED: " + engine.getPowerupsObtained(), "Score", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				if(engine.isDebugging() == true)
				{
					debugPaint(0);
				}
				else
				{
					paint(0);
				}
			}
			engine.moveEnemy();
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(0);
			}
		}
		else if( e.getKeyCode() == KeyEvent.VK_UP)
		{
			engine.movePlayer(1);
			if(engine.checkForPlayer() == true)
			{
				JOptionPane.showMessageDialog(f, "YOU WERE STABBED!!");
				if(engine.getLife() == 0)
				{
					JOptionPane.showMessageDialog(f, "0 LIVES! GAME OVER!!");
					JOptionPane.showMessageDialog(f,"LEVELS CLEARED: " + (engine.getLevels() - 1) + "\nBULLETS ON DEATH: " + engine.getBullets()
							+ "\nENEMIES KILLED: " + engine.getEnemiesKilled() + "\nPOWERUPS OBTAINED: " + engine.getPowerupsObtained(), "Score", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				if(engine.isDebugging() == true)
				{
					debugPaint(0);
				}
				else
				{
					paint(0);
				}
			}
			engine.moveEnemy();
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(0);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_4)
		{
			if(engine.look(4) == true)
			{
				JOptionPane.showMessageDialog(f, "NINJA SPOTTED", "WARNING",JOptionPane.ERROR_MESSAGE);
			}
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(4);
			}
			looking = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_3)
		{
			if(engine.look(3) == true)
			{
				JOptionPane.showMessageDialog(f, "NINJA SPOTTED", "WARNING",JOptionPane.ERROR_MESSAGE);
			}
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(3);
			}
			looking = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_2)
		{
			if(engine.look(2) == true)
			{
				JOptionPane.showMessageDialog(f, "NINJA SPOTTED", "WARNING",JOptionPane.ERROR_MESSAGE);
			}
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(2);
			}
			looking = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_1)
		{
			if(engine.look(1) == true)
			{
				JOptionPane.showMessageDialog(f, "NINJA SPOTTED", "WARNING",JOptionPane.ERROR_MESSAGE);
			}
			if(engine.isDebugging() == true)
			{
				debugPaint(0);
			}
			else
			{
				paint(1);
			}
			looking = true;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_W)
		{
			looking = true;
			if( engine.getBullets() != 0)
			{
				JOptionPane.showMessageDialog(f, "BAM!!");
				JOptionPane.showMessageDialog(f, engine.shoot(1) + " NINJAS WERE REKT!!");
			}
			else
			{
				JOptionPane.showMessageDialog(f, "NO AMMUNITION!! ", "Warning", JOptionPane.ERROR_MESSAGE );
			}
		}
		else if( e.getKeyCode() == KeyEvent.VK_D)
		{
			looking = true;
			if( engine.getBullets() != 0)
			{
				JOptionPane.showMessageDialog(f, "BAM!!");
				JOptionPane.showMessageDialog(f, engine.shoot(4) + " NINJAS WERE REKT!!");
			}
			else
			{
				JOptionPane.showMessageDialog(f, "NO AMMUNITION!! ", "Warning", JOptionPane.ERROR_MESSAGE );
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			looking = true;
			if( engine.getBullets() != 0)
			{
				JOptionPane.showMessageDialog(f, "BAM!!");
				JOptionPane.showMessageDialog(f, engine.shoot(3) + " NINJAS WERE REKT!!");
			}
			else
			{
				JOptionPane.showMessageDialog(f, "NO AMMUNITION!! ", "Warning", JOptionPane.ERROR_MESSAGE );
			}
		}
		else if( e.getKeyCode() == KeyEvent.VK_S)
		{
			looking = true;
			if( engine.getBullets() != 0)
			{
				JOptionPane.showMessageDialog(f, "BAM!!");
				JOptionPane.showMessageDialog(f, engine.shoot(2) + " NINJAS WERE REKT!!");
			}
			else
			{
				JOptionPane.showMessageDialog(f, "NO AMMUNITION!! ", "Warning", JOptionPane.ERROR_MESSAGE );
			}
		}

		
		if(engine.getCheckRoom() == true)
		{
			if( engine.getDocFound() == true)
			{
				JOptionPane.showMessageDialog(f,"DOCUMENT FOUND.");
				JOptionPane.showMessageDialog(f,"LEVEL CLEARED!!");
				engine.levelUp();
				if(engine.isDebugging() == true)
				{
					debugPaint(0);
				}
				else
				{
					paint(0);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(f,"NOTHING FOUND IN THIS ROOM!!");
				engine.getCheckRoom(false);
			}
		}
		
		setHUD();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}
	
	/**
	 * The Heads Up Display. This is used for the user to see their current status in the game. This HUD will display the 
	 * number of bullets currently, how many lives the player has, the current level, and how many turns of invincibility the player
	 * has.
	 */
	public void setHUD()
	{
		HUD.removeAll();
	    JLabel HUDLives = new JLabel("Lives: " + engine.getLife());
	    JLabel HUDBullets = new JLabel("Bullets: " + engine.getBullets());
	    JLabel HUDInv = new JLabel("Turns of invincibility: " + engine.getTurns());
	    JLabel HUDLevel = new JLabel("Level: " + engine.getLevels());
		HUD.add(HUDLives, BorderLayout.PAGE_START);
		HUD.add(HUDBullets, BorderLayout.SOUTH);
		HUD.add(HUDInv, BorderLayout.CENTER);
		HUD.add(HUDLevel, BorderLayout.EAST);
		HUD.revalidate();
	}
}
