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
import java.util.Scanner;
/**
 * User interface is inteded to display and run the game in a text format for the user. Its sole purpose is to 
 * gather all of the data from the engine class and determines how to display the information onto the screen
 * for the player, or user to look at. Not only this, the interface can also call upon the save and load methods 
 * for the intention of saving at crucial points of the game.
 * @author Mario Garcia,   Anuja Joshi,   Michelle Duoung, Matthew Musquiz, Kristin Adachi
 *
 */
public class UserInterface {                                     // this is to be the UI for the game...

	/**
	 * This class is intended to call upon its save and load methods in order to record the progress of the player in 
	 * his/her game.
	 */
	private SaveLoad saveLoad = new SaveLoad();
	/**
	 * The random class is used as a helper for the aid in displaying random quotes after the player is brutally killed by an enemy.
	 */
	private Random rm = new Random();
	/**
	 * Engine class that the interface cannot run without. This class is used as a beacon for information, where the interface uses 
	 * this object to gather information on the status of the game and the current situation the player, enemies, document, and power ups
	 * are currently located at.
	 */
	private Engine engine = null;
	/**
	 * This object calls for to scan the keyboard and the input that the user sends to the program. 
	 */
	
	private Scanner kb = new Scanner( System.in);
	
	/**
	 * Displays a string representation of the game grid, by which it uses the current locations of all of the objects inside as
	 * coordinates in which it can place the symbol of that object.
	 * @param number - This variable is user input, which is used to determine the direction the player wants to look into.
	 */
	public void display( int number)
	{	
		for( int i = 0; i < 9; i++)
		{
			for( int j = 0; j < 9; j++)
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
							System.out.print("[" + engine.getRoomSymbol(r) + "]  ");
						}
					}
					if( i == engine.getPlayerRow() && j == engine.getPlayerCol() && limit < 1)
					{
						System.out.print("[" + engine.getPlayerSymbol() + "]  ");
						limit++;
					}
					for(int e = 0; e < 6; e++)
					{
						if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e) && limit < 1 && engine.isEnemyAlive(e) == true)
						{
							System.out.print("[" + engine.getEnemySymbol(e) + "]  ");
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
								System.out.print("[" + engine.getPowerupSymbol(p) + "]  ");
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
							System.out.print("[ ]  ");
						}
						else if( i == engine.getPlayerRow() - 2 && j == engine.getPlayerCol())
						{
							System.out.print("[ ]  ");
						}
						else
						{
							System.out.print("[X]  ");
						}
					}
					else if(number == 2)
					{
						if( i == engine.getPlayerRow() + 1 && j == engine.getPlayerCol())
						{
							System.out.print("[ ]  ");
						}
						else if( i == engine.getPlayerRow() + 2 && j == engine.getPlayerCol())
						{
							System.out.print("[ ]  ");
						}
						else
						{
							System.out.print("[X]  ");
						}
					}
					else if(number == 3)
					{
						if( i == engine.getPlayerRow() && j == engine.getPlayerCol() - 1)
						{
							System.out.print("[ ]  ");
						}
						else if( i == engine.getPlayerRow() && j == engine.getPlayerCol() - 2)
						{
							System.out.print("[ ]  ");
						}
						else
						{
							System.out.print("[X]  ");
						}
					}
					else if(number == 4)
					{
						if( i == engine.getPlayerRow() && j == engine.getPlayerCol() + 1)
						{
							System.out.print("[ ]  ");
						}
						else if( i == engine.getPlayerRow() && j == engine.getPlayerCol() + 2)
						{
							System.out.print("[ ]  ");
						}
						else
						{
							System.out.print("[X]  ");
						}
					}
					else
					{
						System.out.print("[X]  ");
					}
				}
			}
			System.out.println("\n\n");
		}
	

		System.out.println("+------------------------------------------");
		System.out.println("|LIFE: [" + engine.getLife() + "]");
		System.out.println("|BULLETS: [" + engine.getBullets() + "]");
		System.out.println("|INVINCIBLE TURNS LEFT: [" + engine.getTurns() + "]");
		System.out.println("|LEVEL: [" + engine.getLevels() + "]");
		System.out.println("+------------------------------------------");
	}
	
	/**
	 * Displays a string representation of the game grid, by which it uses the current locations of all of the objects inside as
	 * coordinates in which it can place the symbol of that object. This display method is also used to debug the game, so it will 
	 * reveal the enemy locations, powerups, and document locations on the grid. Not only this, the entire grid display will not be 
	 * covered in the "X" fog. To debug the program ingame, enter 42.
	 * @param number - This variable is user input, which is used to determine the direction the player wants to look into.
	 */
	public void debugDisplay(int number)
	{
		for( int i = 0; i < 9; i++)
		{
			for( int j = 0; j < 9; j++)
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
							System.out.print("[" + engine.getRoomSymbol(r) + "]  ");
						}
					}
					if( i == engine.getPlayerRow() && j == engine.getPlayerCol() && limit < 1)
					{
						System.out.print("[" + engine.getPlayerSymbol() + "]  ");
						limit++;
					}
					for(int e = 0; e < 6; e++)
					{
						if( i == engine.getEnemyRow(e) && j == engine.getEnemyCol(e) && limit < 1  && engine.isEnemyAlive(e) == true)
						{
							engine.revealEnemy(e);
							System.out.print("[" + engine.getEnemySymbol(e) + "]  ");
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
								System.out.print("[" + engine.getPowerupSymbol(p) + "]  ");
							}
						}
					}
				}
				else
				{
					System.out.print("[ ]  ");
				}
			}
			System.out.println("\n\n");
		}
		System.out.println("+------------------------------------------");
		System.out.println("|LIFE: [" + engine.getLife() + "]");
		System.out.println("|BULLETS: [" + engine.getBullets() + "]");
		System.out.println("|INVINCIBLE TURNS LEFT: [" + engine.getTurns() + "]");
		System.out.println("|LEVEL: [" + engine.getLevels() + "]");
		System.out.println("+------------------------------------------");
	}
	
	public void randomQuote()
	{
		int randomQuote;
		randomQuote = rm.nextInt(11) + 1;
		if(randomQuote == 1)
		{
			System.out.print("GAME OF THRONES, BY TELLTALE GAMES...");
		}
		else if(randomQuote == 2)
		{
			System.out.print("LOL JUST NO...");
		}
		else if(randomQuote == 3)
		{
			System.out.print("WE RUN ON VALVETIME...");
		}
		else if(randomQuote == 4)
		{
			System.out.print("THIS ISN'T </SCORPION>, THIS IS NOT A PERFECTLY CODED PROGRAM...");
		}
		else if(randomQuote == 5)
		{
			System.out.print("I AM A COMPUTER, AND I EAT PROGRAMS OMNOMNOMNOM...");
		}
		else if(randomQuote == 6)
		{
			System.out.print("GAME IS EASY KAPPA...");
		}
		else if(randomQuote == 7)
		{
			System.out.print("This game is sponsored by Mountain Dew and Doritos...");
		}
		else if(randomQuote == 8)
		{
			System.out.print("ILLUMINATI IS OUT TO GET ME...");
		}
		else if( randomQuote == 9)
		{
			System.out.print("People still play Quake? ");
		}
		else if(randomQuote == 10)
		{
			System.out.print("The right man in the wrong place can make all the difference in the world... ");
		}
		else if(randomQuote == 11)
		{
			System.out.print("Half-Life 3 confirmed, This game is proof...");
		}
	}
	
	/**
	 * The menu of the game, this is what the player will see in the first point of the game. Not only this, the player is given the 
	 * choice to load a previous save, quit the game, or start up a new game.
	 */
	public void startMenu()
	{
		boolean play = false;
		do
		{
			int number;
			randomQuote();
			System.out.println("JUST KIDDING ITS A NEWB GAME MENU.");
			System.out.println("1=New game 2= load game 3=quit): ");
			number = kb.nextInt();
			if( number == 1)
			{
				System.out.println("FIND THE DOCUMENT HIDDEN IN ONE OF THESE LOVELY NINE ROOMS.\nBE CAREFUL THOUGH, NINJAS WANT TO HURT YOU SO BAD.");
				engine = new Engine();
				play = true;
			}
			else if( number == 2)
			{
				String exitLoad = "exit";
				boolean success = false;
				do
				{
					kb.nextLine();
					String filename;
					System.out.print("file name you want to load(type \"exit\" to exit load): ");
					filename = kb.nextLine();

					if(!filename.equals(exitLoad))
					{
						System.out.println("Loading game...");
						engine = saveLoad.load(filename);
						if( engine == null)
						{
							System.err.println(" Try again...");
							System.err.println("Press enter to continue...");
						}
						else
						{

							success = true;
							play = true;
						}
					}
					else
						success = true;
			}while(!success);
		}
		else if( number == 3)
		{
			System.out.println("GAME HAS QUIT!!! WHY??? IS IT NOT FUN? FINE... LEAVE... DON'T COME BACK!!!................plz come back...");
			System.exit(0);
		}
		}while(!play);
	}
	
	/**
	 * This method is where the game begins, everything that makes the game possible starts here. The interface fully interacts with the game engine,
	 * it also gathers information from the player, and it determines how to display the grid, by the user's choice.
	 */
	public void playGame() 
	{
		int number;
		boolean endGame = false;
		startMenu();
		do
		{
			boolean success;
			if( engine.isDebugging() == true)
			{
				System.out.println("DEBUG MODE ACTIVATED!! CHEATER!! HACKER!!!");
				debugDisplay(0);
			}
			else
			{
				display(0);
			}
			do
			{
				success = true;
				System.out.print("What would you like to do?(0=shoot! 1=move up 2=move down 3=move left 4=move right 5=look 6=save game 7=quit): ");
				number = kb.nextInt();
				if( number == 42)
				{
					engine.setReveal(true);
				}
				else if( number == 24 )
				{
					System.out.println("RESET BACK TO HIDDING!! THAT'S RIGHT, HIDE YOUR SHAME OF CHEATING!!");
					engine.setReveal(false);
				}
				if( number == 5)
				{
					System.out.print("Where would you like to look? (1=up 2=down 3=left 4=right): ");
					number = kb.nextInt();
					boolean looking = engine.look(number);
					if(looking == true)
					{
						
						System.out.println("NINJA SPOTTED!! WATCH OUT!!!");
					}
					else 
					{
						System.out.println("ALL CLEAR... for now...");
					}
					if( engine.isDebugging() == true)
					{
						debugDisplay(number);
						success = false;
					}
					else
					{
						display(number);
						success = false;
					}
				}
				if( number == 6)
				{
					kb.nextLine();
					String filename;
					System.out.print("Name of file to save: ");
					filename = kb.nextLine();
					saveLoad.save(filename, engine);
					System.out.println("Game saved!");
					success = false;
				}
				else if( number == 7)
				{
					System.out.println("GAME HAS QUIT!! WELL, AT LEAST YOU HAD THE DECENCY TO PLAY IT!! THANKS, I GUESS...");
					System.exit(0);
				}
			}while(success == false);
			
			engine.movePlayer(number);
		
			if(engine.getCheckRoom() == true)
			{
				if( engine.getDocFound() == true)
				{
					System.out.println("DOCUMENT FOUND.");
					System.out.println("LEVEL CLEARED!!! YOU LEGITIMATELY HAVE NO LIFE!!");
					engine.levelUp();
					continue;
				}
				else
				{
					System.out.println("NOTHING FOUND IN THIS ROOM!!");
					engine.getCheckRoom(false);
				}
			}
			if(number == 0 && engine.getBullets() != 0)
			{
				int ninjasDead = 0;
				System.out.println("Where would you like to shoot?(1=up 2=down 3=left 4=right): ");
				number = kb.nextInt();
				System.out.println("BAM!!");
				ninjasDead = engine.shoot(number);
				//nub
				if( ninjasDead == 1)
				{
					System.out.println("1 NINJA WAS REKT!!");
				}
				else if( ninjasDead == 2)
				{
					System.out.println( 2 + " NINJAS WERE REKT!!");
					System.out.println("MULTI-KILL!!!!!!!");
				}

				else if( ninjasDead == 3)
				{
					System.out.println( 3 + " NINJAS WERE REKT!!");
					System.out.println("ULTRA-KILL!!!");
				}
				else if( ninjasDead == 4)
				{
					System.out.println( 4 + " NINJAS WERE REKT!!");
					System.out.println("MONSTERKILLLLLLLLLL!!!!");
				}		
			}
			else if( engine.getBullets() == 0 && number == 0)
			{
				System.out.println("DARN, NO BULLETS LEFT. (TURN SKIPPED)");
			}
			if(engine.checkForPlayer() == true)
			{
				int saySomething;
				int insults = rm.nextInt(4) + 1;
				System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\nYOU HAVE BEEN REKT BY A NINJA.");
				if( insults == 1)
				{
					System.out.println(" YOU NEED TO TRY HARDER!!");
				}
				else if( insults == 2)
				{
					System.out.println(" with great power comes great responsibility... UNLESS YOU'RE A SCRUB!!");
				}
				else if( insults == 3)
				{
					System.out.println(" HE WASN'T EVEN TRYING!!");
				}
				else if( insults == 4)
				{
					System.out.println(" SOMETIMES I THINK YOU AREN'T EVEN TRYING... BUT THEN AGAIN YOUR FACE SAYS OTHERWISE...");
				}
				if( engine.isAlive() == false)
				{
					System.out.println("0 LIVES!! GAME OVER SCRUB!!\n\n\n\n\n\n\n\n\n\n\n");
					System.out.println("SCORE:\nLevels cleared: " + (engine.getLevels() - 1) + "\nPowerups obtained: " + engine.getPowerupsObtained() + "\nEnemies killed: " + engine.getEnemiesKilled());
					System.out.println("\n\n");
					startMenu();
					continue;
				}
				kb.nextLine();
				System.out.print("\n\n\n\nGET BACK THERE AND SHOW THEM WHO'S BAUSS...\n(0=I obey you, oh great propheet 1=ok 2=whatever 3=you're not the boss of me 4=pleaserino...): ");
				saySomething = kb.nextInt();
			}
			engine.moveEnemy();

		}while(endGame == false);
	}

}