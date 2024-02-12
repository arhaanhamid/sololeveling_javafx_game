package com.sololeveling.firstLevel;

import java.util.ArrayList;

import com.sololeveling.Main;
import com.sololeveling.Menu;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FirstLevel {
	static int CURRENT_LEVEL;
	
	private static MediaPlayer bgMusicPlayer;
	private double mediaVol;
	
	private BorderPane gameMap;
		
	private static ImageView player;
	private static ImageView arrowAttack;
	private static Image pImg;
	private static Rectangle playerNode;
	private Light.Distant pLight;
	
	public static int enemies;
	private static double pHP;
	
	private HBox statPane;
    private ImageView eStatImg;
    private ImageView pStatImg;
    private static Text eLives;
 	private ProgressBar playerHP;
	
	
	private ArrayList<ImageView> enemy;
	private ArrayList<ProgressBar> enemyHP;
	private ArrayList<Light.Distant> eDistLight;
	private ArrayList<Rectangle> enemyNode;
		
	private double enemyDMG;
	private double playerDMG;
	private double playerREGEN;
	public static boolean arrowHit = false;

	private int pOutOfCombat = 0;
		
	public static int EP_COLLISION_INDEX = 0;
	public static boolean[] EP_COLLISION = new boolean[20];

	
	private AudioClip defeat = new AudioClip(this.getClass().getResource("/resources/music/sounds/defeat.wav").toString());
	private AudioClip victory = new AudioClip(this.getClass().getResource("/resources/music/sounds/victory.wav").toString());
	private AudioClip eDeath;
	

	public void gameScene(Scene scene, double musVol, int currLevel) {
		Group gameRoot = new Group();
		scene.setCursor(Cursor.NONE);
		
		CURRENT_LEVEL = currLevel;		
		pHP = 1.0;
		enemies = LevelResources.getEnemies();
		enemyDMG = LevelResources.setEnemyDMG();
		playerDMG = LevelResources.setPlayerDMG();
		playerREGEN = LevelResources.setPlayerREGEN();
		eDeath = new AudioClip(this.getClass().getResource(LevelResources.eDeathSound()).toString());

		
		//ADDING BACKGROUND MUSIC
		mediaVol = musVol;
		startBGMusic(mediaVol);
	
		//ADDING BACKGROUND IMAGE/MAP
		GameMap objGameMap = new GameMap();
		objGameMap.gameMap();		
		gameMap = objGameMap.getGameMap();
		
		//ADDING OBSTACLES TO MAP
		Obstacles objObstacles = new Obstacles();
		objObstacles.obstacles();		
		for(int i=0; i<objObstacles.getAllObstacles().size(); i++) {
			gameMap.getChildren().addAll(objObstacles.getAllObstacles().get(i), objObstacles.getObstacleTop().get(i), objObstacles.getObstacleBottom().get(i), objObstacles.getObstacleLeft().get(i), objObstacles.getObstacleRight().get(i));			
		}
		
		//ADDING PLAYER TO GAME
		Player objPlayer = new Player();
		objPlayer.createPlayer();		
		player 		= objPlayer.getPlayer();
		pImg 		= objPlayer.getPlayerImg();
		pLight 		= objPlayer.getpLight();
		playerNode	= objPlayer.getPlayerNode();
		arrowAttack = objPlayer.getArrow();
        gameMap.getChildren().addAll(player, playerNode, arrowAttack);
		
        //ADDING STAS TO GAME
		Stats objStats = new Stats();
		objStats.getStat(pHP);
		statPane	= objStats.getStatPane();
		eStatImg	= objStats.geteStatImg();
		pStatImg	= objStats.getpStatImg();
		playerHP	= objStats.getPlayerHP();
		eLives		= objStats.geteLives();		
        statPane.getChildren().addAll(pStatImg, playerHP, eStatImg, eLives);

        //ADDING ENEMIES TO GAME
        Enemy objEnemy = new Enemy();        
        objEnemy.getEnemy(enemies);
        enemy		= objEnemy.getEnemy();
    	enemyHP		= objEnemy.getEnemyHP();
    	eDistLight	= objEnemy.geteDistLight();
    	enemyNode	= objEnemy.getEnemyNode();
		for(int i=0; i<enemies; i++) {
			gameMap.getChildren().addAll(enemy.get(i), enemyNode.get(i), objEnemy.getEnemyTop().get(i), objEnemy.getEnemyBottom().get(i), objEnemy.getEnemyLeft().get(i), objEnemy.getEnemyRight().get(i));
		}
		
		//ADDING PLAYER CONTROLS AND EVENT TO MOVE PLAYER
		PlayerMovement objPLayerMovement = new PlayerMovement();
		objPLayerMovement.getControls();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {  				
				objPLayerMovement.getPlayerMovement(event.getCode().toString(), player, pImg, playerNode, arrowAttack);
			}
		}); 
		
		//TIMELINE TO MOVE ENEMIES RANDOMLY AND INDEFINITELY
		EnemyMovement objEnemyMovement = new EnemyMovement();
		objEnemyMovement.startEnemyMovement(enemy, enemyHP, enemyNode);
		
		
		//Time line to check for collision
		Timeline collisionLoop = new Timeline();
		collisionLoop.setCycleCount(Timeline.INDEFINITE);
        	        
        KeyFrame cl = new KeyFrame(
            Duration.millis(50),               
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) { 
                	pLight.setColor(null);	
                	
                	for(int i=0; i<enemies; i++) {   
                		EP_COLLISION[i] = false;


                		eDistLight.get(i).setColor(null);
                		
                		//LEVEL 3 SPECIFIC COMBAT SYSTEM
                		if( CURRENT_LEVEL == 3) {
                			if( arrowAttack.intersects(enemyNode.get(i).getBoundsInLocal())) {
                    			enemyHP.get(i).setProgress(enemyHP.get(i).getProgress() - playerDMG);
                    			eDistLight.get(i).setAzimuth(1.0); 
                    			eDistLight.get(i).setColor(Color.RED);
                    			arrowHit = true;
                    			pOutOfCombat = 0;
                			}
                		}                		
	                	//Check for PLAYER collision with ENEMY                		
                		if(playerNode.intersects(enemyNode.get(i).getBoundsInLocal())) {
	                		pLight.setAzimuth(1.0); 
	                		pLight.setColor(Color.RED);                			

	                		pOutOfCombat = 0;
	                		EP_COLLISION_INDEX = i;
	                		EP_COLLISION[i] = true;
	                		
	                		playerHP.setProgress(playerHP.getProgress() - enemyDMG);
	                		pHP = playerHP.getProgress(); 
	                		
	                		//Check if PlAYER died, change SCENE
	                		if (playerHP.getProgress() <= 0){
	                			collisionLoop.stop();	            				
	                			playerDied(scene);
	                		}                    			
	                			
	                		if( CURRENT_LEVEL != 3) {
	                		//Check if PLAYER hit ENEMY
		                		if(PlayerMovement.enemyHit == "SPACE" || PlayerMovement.enemyHit == PlayerMovement.pATTACK) {
		                			enemyHP.get(i).setProgress(enemyHP.get(i).getProgress() - playerDMG);
		                			eDistLight.get(i).setAzimuth(1.0); 
		                			eDistLight.get(i).setColor(Color.RED);
		                		}	                		
	                		}
                		}
	                	//PLAYER HP REGENRATION
	                	else {
	                		
	                		if(playerHP.getProgress() < 1.0) { 
	                		pOutOfCombat += 1;
	                		
		                		if (pOutOfCombat > 2000) {
		                			playerHP.setProgress(playerHP.getProgress() + playerREGEN);
		                			
	                				if(playerHP.getProgress() == 1.0) { 
	                					pOutOfCombat = 0;
	                				}
		                		}
	                		}
	                	}

                		//Check if ENEMY died, remove dead
                		if (enemyHP.get(i).getProgress() <= 0){
                			Stats objStats = new Stats();                			
                			eDeath.play();
                			gameMap.getChildren().remove(enemy.get(i));
                			gameRoot.getChildren().remove(enemyHP.get(i));
                			enemies--;
                			enemy.remove(i);
                			enemyHP.remove(i);
                			enemyNode.remove(i);                			
                			
                			objStats.getStat(pHP);
                			statPane.getChildren().remove(eLives);

                			eLives = objStats.geteLives();
                			statPane.getChildren().add(eLives);
                		}
	                			
                		//Check if all ENEMIES died, change SCENE
                		if(enemies == 0) {	
                			collisionLoop.stop();
                			gameMap.getChildren().clear();
                			gameRoot.getChildren().clear();
                			nextLevel(scene);	
                		}

                	}                	
                }
            });
        
        collisionLoop.getKeyFrames().add( cl );
        collisionLoop.play();
        
		
		gameRoot.getChildren().addAll(gameMap, statPane);
		
		for(int i=0; i<enemies; i++) {
		    gameRoot.getChildren().add(enemyHP.get(i));
		}

		scene.setRoot(gameRoot);
		Main.getStage().setScene(scene);
	}
	
	
	public void startBGMusic(double mediaVol){
		Media bgMusic = new Media(this.getClass().getResource(LevelResources.bgMusicPath()).toExternalForm());
		bgMusicPlayer = new MediaPlayer(bgMusic);
		bgMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusicPlayer.setAutoPlay(true);
		bgMusicPlayer.setVolume(mediaVol);	
	}
	
	 
	public void playerDied(Scene scene) {
		bgMusicPlayer.stop();		
		defeat.play();
		
		BorderPane defeatPane = new BorderPane();
		scene.setRoot(defeatPane);
		
		Label labelDied = new Label();
		labelDied.setId("labelDied");
		labelDied.setText("You Died? At this Stupid game? Just Kill Yourself!");
		labelDied.setOpacity(0.0);

		FadeTransition fTrans = new FadeTransition();
		fTrans.setDuration(Duration.seconds(3));
		fTrans.setFromValue(0.0);
		fTrans.setToValue(1.0);
		fTrans.setCycleCount(2); 
		fTrans.setAutoReverse(true);
		fTrans.setNode(labelDied);
		fTrans.play();
		
		Label labelMenu = new Label();
		labelMenu.setText("Press any key to exit to Main Menu!");
		labelMenu.setId("labelMenu");
		labelMenu.setOpacity(0.0);
		
		PauseTransition labelPause = new PauseTransition(Duration.seconds(6));
		labelPause.setOnFinished(event -> {			
			
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(0.0);
			fTrans.setToValue(1.0);
			fTrans.setCycleCount(1); 
			fTrans.setAutoReverse(false);
			fTrans.setNode(labelMenu);
			fTrans.play();
			defeatPane.setCenter(labelMenu);
		});		
		labelPause.play();
		
		defeatPane.setCenter(labelDied);
		
		//Pause event to wait for labels to show then exit onkeypress
		PauseTransition menuPause = new PauseTransition(Duration.seconds(6));
		menuPause.setOnFinished(event -> {
				
				scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event) {
						exitToMainMenu(scene);
																		
					}					
				});						
		});
		menuPause.play();		
	}	 
	
	private void exitToMainMenu(Scene scene) {
		Menu objMenu = new Menu();
		Main objMain = new Main();
		VBox menuBar = new VBox();
		
		objMenu.menu();
		
		MediaView bgVidView;
		bgVidView = objMenu.getMediaView();
		
		StackPane rootStack = new StackPane();
		scene.setRoot(rootStack);
		
		objMain.menuPane(scene);	
		menuBar = Main.menuBar;
		
		rootStack.getChildren().addAll(bgVidView, menuBar);
	}
	 

	public void nextLevel(Scene scene) {
		bgMusicPlayer.stop();
		victory.play();
		
		StackPane victoryPane = new StackPane();		
		
		Label conLabel = new Label();
		conLabel.setId("conLabel");
		conLabel.setOpacity(0.0);
		
		Label levLabel = new Label();
		levLabel.setId("levLabel");
		levLabel.setOpacity(0.0);
		
		switch(CURRENT_LEVEL) {
		case 1: conLabel.setText("You Are Victorious!");
				levLabel.setText("In The Icy Fields of Himalayas!");
				break;
		case 2: conLabel.setText("You Are Victorious!");
				levLabel.setText("In The Dungeon Deep in The Abyss!");
				break;
		case 3: conLabel.setText("You Are Victorious!");
				levLabel.setText("Congratulation! You finished all the Levels...");
				break;
		}
		
		FadeTransition fTrans = new FadeTransition();
		fTrans.setDuration(Duration.seconds(3));
		fTrans.setFromValue(0.0);
		fTrans.setToValue(1.0);
		fTrans.setCycleCount(2); 
		fTrans.setAutoReverse(true);
		fTrans.setNode(conLabel);
		fTrans.play();	
		
		PauseTransition conDelay = new PauseTransition(Duration.seconds(6));
		conDelay.setOnFinished( event -> {
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(0.0);
			fTrans.setToValue(1.0);
			fTrans.setCycleCount(2); 
			fTrans.setAutoReverse(true);
			fTrans.setNode(levLabel);
			fTrans.play();	
		});
		conDelay.play();			
		
		victoryPane.getChildren().addAll(conLabel, levLabel);
		
		scene.setRoot(victoryPane);
		
		PauseTransition gameDelay = new PauseTransition(Duration.seconds(12));
		gameDelay.setOnFinished( event -> {
			if (CURRENT_LEVEL == 3) {
				exitToMainMenu(scene);
			}
			else {
			gameScene(scene, mediaVol, ++CURRENT_LEVEL);
			}
		}
		 );
		gameDelay.play();	
	}
}
