/*
 * Author: Jared Wal
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapGUI extends JPanel implements ActionListener {
	Timer t = new Timer(20, this);

	
	private BufferedImage image;
	private BufferedImage imageStr;

	
	public static int updateDataMapsCount = 0;
	
	public static Tile[][] mainMap; 

	
	public static int nonWaterTilesCount=0;
	
	
	// int to keep track of arrays 
	//uses first element in each array to keep track of each colonies data
	public int[][] statTracker;
	
	public int turnsHighestTileStrength;
	public int turnsHighestAvgColonyStrength;

	public static int mapModeCounter;
	
	
	
	public int startingColonies = 15;
	public int mutationRate = 100;
	public int startingBirthRate = 10;
	public int startingStrength = 1;
	public int strenPopDivider = 10000;
	
	public String[] listColoniesTeams = new String[startingColonies];
	
	
	public Color[] colorArray = new Color[startingColonies];

	
    public Random rand = new Random(); //instance of random class

    
    // TODO make each child tild have a similar but random age limit
	
	public MapGUI(){
		
		// creates the Map background
		try {
			image = ImageIO.read(getClass().getResourceAsStream("worldMapConnected.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		mainMap = new Tile[image.getWidth()][image.getHeight()];
		
		//creates main map array 
		createMainMapArray();
		
		// place starting colonies
		
		
		
		// keeps track of each contries stats
		
		// team id | population | strength | Birth-Rate | Age-Limit 
		statTracker = new int[startingColonies][5];
		
		createRandomStartingColonies(startingColonies);
		
//		
//		
//		// California
//		Color col1Color = new Color(0xff1a44);				
//		createStartingColony(100,360,"Califonia", 0, col1Color,50, 20);
//		listColoniesTeams[0] = "California";
//		colorArray[0] = col1Color;
//
//		
//		// Texas
//		Color col2Color = new Color(0x396EEA);				
//		createStartingColony(580,590,"Texas", 1, col2Color, 50, 20);
//		listColoniesTeams[1] = "Texas";
//		colorArray[1] = col2Color;
//		
//		
//		// Montana
//		Color col3Color = new Color(0x00FEEA);				
//		createStartingColony(400,100,"Montana", 2, col3Color, 50, 20);
//		listColoniesTeams[2] = "Montana";
//		colorArray[2] = col3Color;
//		
//		
//		// New York
//		Color col4Color = new Color(0x8813a0);				
//		createStartingColony(1100,250,"New York", 3, col4Color, 50, 20);
//		listColoniesTeams[3] = "New York";
//		colorArray[3] = col4Color;
//
//		
//		//Nevada
//		Color col5Color = new Color(0xa07d13);				
//		createStartingColony(200,330,"Nevada", 4, col5Color,50, 20);
//		listColoniesTeams[4] = "Nevada";
//		colorArray[4] = col5Color;
//
//		//California
//		Color col1Color = new Color(0xff1944);				
//		createStartingColony(image.getWidth()/2,image.getHeight()/2,"Califonia", 0, col1Color,50, 20);
//		listColoniesTeams[0] = "California";
//		colorArray[0] = col1Color;
		
	}
	
	//resets statTracker 
	public void resetStatTracker() {
		for (int colony=0; colony<startingColonies; colony++) {
			for (int stat=0; stat<5; stat++) {
				statTracker[colony][stat] = 0;
			}
		}
		
		
	}
	
	public void createRandomStartingColonies(int colAmount) {
		// creates a nested array to keep track of nonWaterTile ints
		int[][] nonWaterArray = new int[nonWaterTilesCount][2];
		
		int tileCount = 0;
		for (int x=0; x<image.getWidth(); x++) {
			for (int y=0; y<image.getHeight(); y++) {
					
				//TODO create an array of non water tiles to place new starting civis
				
				if (mainMap[x][y].getIsWater() == false) {
					
					
					nonWaterArray[tileCount][0] = x;
					nonWaterArray[tileCount][1] = y;

					tileCount++;
				}
				
			}
		}
		
		for (int i=0; i<colAmount; i++) {
			
			int randomTile = rand.nextInt(nonWaterTilesCount);
			
			int placeX = nonWaterArray[randomTile][0];
			int placeY = nonWaterArray[randomTile][1];
			
			
			int randColorNum = rand.nextInt(0xffffff + 1);

			
			Color randomColor = new Color(randColorNum);
			
			System.out.printf("Color: %d | Cord(%d, %d) %n",randColorNum, placeX, placeY);
			createStartingColony(placeX, placeY,"Colony" + i, i, randomColor, startingStrength, startingBirthRate);
			
			// sets colonies random color into list so it can be used to draw text
			colorArray[i] = randomColor;
			
		}

		
	}
	
	public void paintComponent(Graphics g) {
		
		//draws background image
		g.drawImage(image, 0, 0, null);
		
		// iterates through all pixels and updates then
		for (int x=0; x<image.getWidth(); x++) {
			for (int y=0; y<image.getHeight(); y++) {
				Tile tile = mainMap[x][y];
				
				//check if there is a unit on the tile
				if(tile.isEmpty == false) { 
					tileIsNotEmpty(g, tile, x, y);
				}
				

				
				
				//TODO somthing like this but faster maybe once every 15 seconds or intervals
				
				// TODO draw a square Tile Hieght + 100 away and make it a proper color to represent tile Strength
//				
//				if (updateDataMapsCount >= 10) {
//					if (tile.isEmpty == false && tile.isWater == false) {
//						g.setColor(Color.red);
//						g.fillRect(x, y+image.getHeight(), 4, 4);
//					}
//
//				}
//			
//				updateDataMapsCount++;
			
				
			}
		}
		//findHighestColoniesAvgStrength();
		//findHighestMapModeStats();
		findHighestColoniesAvgStrength();
		
		drawStatTrackerData(g);
		resetStatTracker();		
	    //Calls timer to update GUI
	    t.start();
	    
	}
	
	// events for if tile is not empty
	public void tileIsNotEmpty(Graphics g,Tile tile, int x, int y) {
			
		// check if unit can reproduce
		// if birth number is lower than birth rate then it can reproduce
		int randomBirthNum = rand.nextInt(100);
		if (randomBirthNum <= tile.unitBirthPercent) {
			unitCanReproduce(g, x, y, tile);
		}
		
		// is unit is too old then it dies
		if (tile.unitAge >= tile.unitAgeLimit) {
			resetTileUnitData(tile);
			
		}else {
			drawUnitAtCords(g,x,y,tile);
		
		}
		
		updateStatTrackerWithTile(tile);
		
		
		
				
		//increases unit age by 1
		tile.unitAge ++;
	}
		
	public void drawStatTrackerData(Graphics g) {
		int yOffset = image.getHeight()+25;
		int xOffset = 50;
		
		int count = 0;
		for (int[] colony : statTracker) {
			
			int pop = colony[1];
			if (pop >=1) {
				if (yOffset > 1100) {
					yOffset = image.getHeight()+25;
					xOffset += 500;
					
				}
				String textToDraw = String.format("Colony: %d |Pop: %d | Strength: %d | Birth-Rate: %d | Age-Limit: %d %n", colony[0], pop,colony[2]/pop, colony[3]/pop, colony[4]/pop);
				//String textToDraw = String.format("Colony: %s |Pop: %d | Strength: %d | Birth-Rate: %d | Age-Limit: %d %n", listColoniesTeams[count], pop,colony[2]/pop, colony[3]/pop, colony[4]/pop);

				
				Color drawColor = colorArray[count];
				g.setColor(drawColor);
				g.drawString(textToDraw, xOffset, yOffset);
				yOffset+=15;
			}
		count++;
		}
		
		
	}
	
	// updates and adds tile value to proper colonies stats
	public void updateStatTrackerWithTile(Tile tile) {
		
		int tileId = tile.getTeamId();
		
		statTracker[tileId][0] = tileId; // might not be needed
		statTracker[tileId][1] += 1;
		statTracker[tileId][2] += tile.getUnitStrength();
		statTracker[tileId][3] += tile.getUnitBirthPercent();
		statTracker[tileId][4] += tile.getUnitAgeLimit();

	}
			
	public void unitCanReproduce(Graphics g, int x, int y, Tile tile) {
		
		// pick a random tile to place child 
		
		
		//create new ints to add to current tile to get neighbohirng tiles
		int addToX = ThreadLocalRandom.current().nextInt(-1, 2 );
		int addToY = ThreadLocalRandom.current().nextInt(-1, 2 );

		if (addToX == 0 && addToY == 0) {
			addToX = -1;
			
		}
		// if the new move to tile is the same as the previous tile then just shift to the right 1

			
		
		
		//tile that child might move to
		Tile moveToTile = mainMap[x+addToX][y+addToY];
		
		//checks if tile is not water
		if (moveToTile.isWater == false) {
			//check if tile is empty
			if (moveToTile.isEmpty) {
				// tile is empty so child is going to be placed
				createUnitAtCordsWithATR(g, x+addToX, y+addToY, tile);
			}
			// checks if the tile team does not equal the moveTO tile team 
			else if (!moveToTile.getUnitTeam().equals(tile.getUnitTeam())) {
				// different teams parent attacks other team's unit				
				//checks if parent tile is stronger than the move to tile
				
				//System.out.printf("%s at %d (attacking) %s at %d %n",tile.getUnitTeam(), tile.getUnitStrength(), moveToTile.getUnitTeam(), moveToTile.getUnitStrength());
				
				
				//NOTE damage defender after a battle and Attacker
				//TODO pottential have child born to a fight have less strength
				//TODO add prosperity -- may help with defence
				
				// checks if parent is stronger than defender if defender has a boost
				if (tile.getUnitStrength() > (int)(moveToTile.getUnitStrength()*1.1)) {
										
					// place child on moveToTile
					createUnitAtCordsWithATR(g, x+addToX, y+addToY, tile);
					
					//  Parent Unit takes damage after fighting
					tile.setUnitStrength(tile.getUnitStrength()- (int)(moveToTile.getUnitStrength()*1.1));
					
					// Parent unit dies
					resetTileUnitData(tile);
				}
				else {
					// defender tile looses health due to being attacks
					moveToTile.setUnitStrength(moveToTile.getUnitStrength()-tile.getUnitStrength());
					
				}
			}
		}
				


		
	}
		
	// creates a new unit at cords with parents attributes and has chance of mutation
	public void createUnitAtCordsWithATR(Graphics g, int x, int y, Tile parentTile) {
		Tile childTile = mainMap[x][y];
		
		//// child mutations
		
		//mutating strength
		
		double chanceMutate = ((ThreadLocalRandom.current().nextInt(0, 100 + 1)));
		
		if (chanceMutate<=mutationRate) {
			//creates and adds mutations to the child tile
			CreateAndAddMutationsForChild(childTile, parentTile);
		}else {
			// no mutation so set to parents stats
			childTile.setUnitStrength(parentTile.getUnitStrength());
			childTile.setUnitAgeLimit(parentTile.getUnitAgeLimit());
			childTile.setUnitBirthPercent(parentTile.getUnitBirthPercent());
			int childAgeLim = ThreadLocalRandom.current().nextInt(-3, 4 + 1);
			childAgeLim += parentTile.getUnitAgeLimit();
			childTile.setUnitAgeLimit(childAgeLim);
		}
		
		
		
		int teamPop = statTracker[parentTile.teamId][1];
		
		childTile.setUnitStrength(childTile.getUnitStrength()+teamPop/strenPopDivider);
		childTile.setIsEmpty(false);
		childTile.setUnitAge(0);
		childTile.setUnitColor(parentTile.getUnitColor());
		childTile.setUnitHaveDisease(false);
		childTile.setUnitTeam(parentTile.getUnitTeam());
		childTile.setTeamId(parentTile.getTeamId());
		
		// activate if you dont want the mutation to effect age limit
//		int childAgeLim = ThreadLocalRandom.current().nextInt(-3, 4 + 1);
//		childAgeLim += parentTile.getUnitAgeLimit();
//		childTile.setUnitAgeLimit(childAgeLim);
		
		drawUnitAtCords(g, x, y, childTile);
	}
	
	// creates new mutations and adds it a tile 
	public void CreateAndAddMutationsForChild(Tile childTile, Tile parentTile) {
		
		
		// strength mutation
		double randStrengthMod = ((ThreadLocalRandom.current().nextInt(-1,2 + 1))/100.0)+1;
		int childStrength = (int) (parentTile.getUnitStrength() * randStrengthMod)+1;
		
		childTile.setUnitStrength(childStrength);
		
		
		
		// age Limit mutation
		double randAgeLimitMod = ((ThreadLocalRandom.current().nextInt(-1, 1 + 1))/100.0)+1;
		int childAgeLimit = (int) (parentTile.getUnitAgeLimit() * randAgeLimitMod)+1;
		
		childTile.setUnitAgeLimit(childAgeLimit);
		
		
		
		double randBirthPercent = ((ThreadLocalRandom.current().nextInt(-1, 1 + 1))/100.0)+1;
		int childBirthPercent = (int) (parentTile.getUnitBirthPercent() * randBirthPercent)+1;
		
		childTile.setUnitBirthPercent(childBirthPercent);
		
	
		
		//System.out.printf("Strength: %d  Age Limit: %d  Birth Rate: %s", childStrength, childAgeLimit, childBirthPercent );

	}
	
	// resets the tiles unit data
	public void resetTileUnitData(Tile tile) {
		tile.isEmpty = true;
		tile.unitAge = 0;
		tile.unitAgeLimit = 0;
		tile.unitBirthPercent = 0;
		tile.unitStrength = 0;
		
		tile.unitHaveDisease = false;
		tile.unitTeam = null;
		tile.unitColor = null;
	}
	
	
	// change the map mode counter by 1
	public static void changeMapModeCounter() {
		mapModeCounter+=1;
		// if map mode counter goes over amount of map modes, then reset back to mode 0
		if (mapModeCounter>2) {
			mapModeCounter = 0;
		}
	}
	
	
	// draws the unit at the specific tile with the proper color
	public void drawUnitAtCords(Graphics g, int x, int y, Tile tile) {
			
		
		Color newCol = tile.getUnitColor();
		// draws tile's unit color
		if (mapModeCounter == 0) {
			newCol = tile.getUnitColor();
		}
		// draws tile's unit strength
		else if (mapModeCounter == 1) {
			
			//double strenDivider = turnsHighestTileStrength/10;

			double strenDouble = (double)tile.getUnitStrength()/ (double)turnsHighestAvgColonyStrength;

			
			int strenRBG =255 - ((int) (strenDouble * 255));
			
			
			if (strenRBG > 230){
				strenRBG = 230;
			}
			else if (strenRBG < 0){
				strenRBG = 0;
			}
			newCol = new Color(strenRBG, strenRBG, 255);
			
			
			
		}
		// draws the reproduce rate
		else if(mapModeCounter == 2) {
			newCol = new Color(tile.getUnitBirthPercent());
			
		}
		
		g.setColor(newCol);
		g.fillRect(x, y, 1, 1);
	}
	public void findHighestMapModeStats() {
		
		turnsHighestTileStrength = 0;
		
		Tile tile;
		for (int x=0; x<image.getWidth(); x++) {
			for (int y=0; y<image.getHeight(); y++) {
				tile = mainMap[x][y];
				if(tile.getUnitStrength()>turnsHighestTileStrength) {
					turnsHighestTileStrength = tile.getUnitStrength();
				}
				
				
			}
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//updates GUI 
		repaint();
		
	}
	
	public void findHighestColoniesAvgStrength() {
		int highestStren = 0;
		for (int[] col : statTracker) {
			if (col[1] > 0) {
				// if total strength divided by pop (avg strength)
				if (col[2]/col[1] > highestStren) {
					highestStren = col[2]/col[1];
				}
			}
		}
		turnsHighestAvgColonyStrength = highestStren;
		
	}
	
	
	public void createMainMapArray() {
		
		//iterates through Tile[][] array to add constructors to each tile
		for (int x=0; x<image.getWidth(); x++) {
			for (int y=0; y<image.getHeight(); y++) {
				
				// get color of pixel at (x,y)
				int clr = image.getRGB(x, y);
		        int red =   (clr & 0x00ff0000) >> 16;
		        int green = (clr & 0x0000ff00) >> 8;
		        int blue =   clr & 0x000000ff;
		        
		        
		        //checks if pixel is white
		        if (red == 255 && green == 255 && blue == 255) {
		        	//sets tile isWater to true
		        	Tile newTile = new Tile(x,y,true);
		        	mainMap[x][y] = newTile;
		        }
		        else { // pixel is not white
		        	//sets tile isWater to false
		        	Tile newTile = new Tile(x,y,false);
		        	mainMap[x][y] = newTile;
		        	nonWaterTilesCount++;
		        	
		        }
		        			
			}
		}
		
	}
	
	public void createStartingColony(int startX, int startY, String team, int teamId, Color color, int startingStrengthL, int startingBirthRateL){
		
		// assuming x and y are not not water
		
		Tile tile = mainMap[startX][startY];
				
		tile.setIsEmpty(false);
		tile.setUnitAge(0);
		tile.setUnitAgeLimit(50);
		tile.setUnitStrength(startingStrengthL);
		tile.setUnitBirthPercent(startingBirthRateL);
		
		
		tile.setUnitTeam(team);
		tile.setTeamId(teamId);
		tile.setUnitColor(color);
		tile.setUnitHaveDisease(false);
		
		
		
	}
}
