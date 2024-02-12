package com.sololeveling.firstLevel;

import com.sololeveling.Menu;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PlayerMovement {
	private static String UP, LEFT, DOWN, RIGHT;
	
	private static String keyPressed;
	public static String enemyHit , pATTACK;

	private final double PLAYER_MOVESPEED = 5;
	
	private static int r, l, u, d = 0;
	private int arrX;
	private int arrY;
	private boolean arrowDestReach = true;
	public static boolean resetEnemy = false;

	private AudioClip weaponSound = new AudioClip(this.getClass().getResource(LevelResources.pWeaponSound()).toString());

	public void getPlayerMovement(String mov, ImageView player, Image pImg, Rectangle playerNode, ImageView arrowAttack) {	
		GameMap objGameMap = new GameMap();
		Obstacles objObstacle = new Obstacles();	
		
		if(mov == "F12" ) {
			resetEnemy = true;			
		}
		
		if(mov == "UP" || mov == UP) {			 
			 keyPressed = mov;
			 if(u == 512) {u=0;}
			 player.setImage(pImg);
			 player.setViewport(new Rectangle2D(u, 0, 64, 64));			
			 u+= 64;
			 
			 playerNode.setX(playerNode.getX());
			 playerNode.setY(playerNode.getY() - PLAYER_MOVESPEED);
			 
			 player.setX(playerNode.getX() - 16);
			 player.setY(playerNode.getY() - 12);
				
			 for(int i=0; i<objObstacle.getAllObstacles().size(); i++) {
				if(playerNode.intersects(objObstacle.getObstacleBottom().get(i).getBoundsInLocal())){
					playerNode.setY(objObstacle.getObstacleBottom().get(i).getY() + 1);
					break;
		        }
			 }			 
			 if(playerNode.intersects(objGameMap.MAP_TOP.getBoundsInLocal())) {
				 playerNode.setX(playerNode.getX());
				 playerNode.setY(playerNode.getY() + PLAYER_MOVESPEED);
			 }		 		

		 }
		 
		 //MOVE LEFT
		 if(mov == "LEFT" || mov == LEFT) {
			keyPressed = mov;
			if(l == 512) {l=0;}
			player.setImage(pImg);
			player.setViewport(new Rectangle2D(l, 64, 64, 64));			
			l+=64;		
			
			playerNode.setX(playerNode.getX() - PLAYER_MOVESPEED);
			playerNode.setY(playerNode.getY());
			
			player.setX(playerNode.getX() - 16);
			player.setY(playerNode.getY() - 12);
			
			for(int i=0; i<objObstacle.getAllObstacles().size(); i++) {
				if(playerNode.intersects(objObstacle.getObstacleRight().get(i).getBoundsInLocal())){
					playerNode.setX(objObstacle.getObstacleRight().get(i).getX() + 1);
					break;
		        }
			}
			if(playerNode.intersects(objGameMap.MAP_LEFT.getBoundsInLocal()))	{
				playerNode.setX(playerNode.getX() + PLAYER_MOVESPEED);
				playerNode.setY(playerNode.getY());
			}
			
		 }
	
		 
		 
		 //MOVE DONW
		 if(mov == "DOWN" || mov == DOWN) {
			keyPressed = mov;
			if(d == 512) {d=0;}		
			player.setImage(pImg);
			player.setViewport(new Rectangle2D(d, 128, 64, 64));			
			d+=64;
			
			playerNode.setX(playerNode.getX());
			playerNode.setY(playerNode.getY() + PLAYER_MOVESPEED);
			
			player.setX(playerNode.getX() - 16);
			player.setY(playerNode.getY() - 12);
			
			for(int i=0; i<objObstacle.getAllObstacles().size(); i++) {
				 if(playerNode.intersects(objObstacle.getObstacleTop().get(i).getBoundsInLocal())){
					playerNode.setY(objObstacle.getObstacleTop().get(i).getY() - playerNode.getHeight() - 1);
					break;
		        } 
			}
			if(playerNode.intersects(objGameMap.MAP_BOTTOM.getBoundsInLocal()))	{
				playerNode.setX(playerNode.getX());
				playerNode.setY(playerNode.getY() - PLAYER_MOVESPEED);;
			}
		}
		 
		 //MOVE RIGHT
		if(mov == "RIGHT" || mov == RIGHT) {
			keyPressed = mov; 
			if(r == 512) {r=0;}
			player.setImage(pImg);
			player.setViewport(new Rectangle2D(r, 192, 64, 64));			
			r+=64;		
			
			playerNode.setX(playerNode.getX() + PLAYER_MOVESPEED);
			playerNode.setY(playerNode.getY());
			
			player.setX(playerNode.getX() - 16);
			player.setY(playerNode.getY() - 12);
			
			for(int i=0; i<objObstacle.getAllObstacles().size(); i++) {
				if(playerNode.intersects(objObstacle.getObstacleLeft().get(i).getBoundsInLocal())){
					playerNode.setX(objObstacle.getObstacleLeft().get(i).getX() - playerNode.getWidth() - 1);
					break;
		        }
			}
			if(playerNode.intersects(objGameMap.MAP_RIGHT.getBoundsInLocal()))	{
				playerNode.setX(playerNode.getX() - PLAYER_MOVESPEED);
				playerNode.setY(playerNode.getY());	

			}
		}
		 
		 //ATTACK
		 if(mov == "SPACE" || mov == pATTACK) {
			 enemyHit = mov;
			 if(FirstLevel.enemies !=0) {	weaponSound.play();	}
		 
			 if (keyPressed == "UP" || keyPressed == UP) {				 
				if (FirstLevel.CURRENT_LEVEL == 3) {
					if(arrowDestReach == true) {
						player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
						player.setViewport(new Rectangle2D(0, 0, 64, 64));
						setArrowProjectile(arrowAttack, player, 0, "UP");
						arrowDestReach = false;
					}
				}
				else {
					player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
					player.setViewport(new Rectangle2D(0, 0, 64, 64));
				}
			 }
			 
			 if (keyPressed == "LEFT" || keyPressed == LEFT) {				 
		         if (FirstLevel.CURRENT_LEVEL == 3) {		        	 
					if (arrowDestReach == true) {						
						player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
				        player.setViewport(new Rectangle2D(0, 64, 64, 64));
						setArrowProjectile(arrowAttack, player, 270, "LEFT");
						arrowDestReach = false;
					}
				}
		         else {
		        	 player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
			         player.setViewport(new Rectangle2D(0, 64, 64, 64));
		         }
			 }
			 
			 if (keyPressed == "DOWN" || keyPressed == DOWN) {		         
		         if (FirstLevel.CURRENT_LEVEL == 3) {
					if (arrowDestReach == true) {
						player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
				        player.setViewport(new Rectangle2D(0, 128, 64, 64));
						setArrowProjectile(arrowAttack, player, 180, "DOWN");
						arrowDestReach = false;
					}
				 }
		         else {
		        	 player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
			         player.setViewport(new Rectangle2D(0, 128, 64, 64));
		         }
			 }
			 
			 if (keyPressed == "RIGHT" || keyPressed == RIGHT) {
				if (FirstLevel.CURRENT_LEVEL == 3) {
					if (arrowDestReach == true) {
						player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
						player.setViewport(new Rectangle2D(0, 192, 64, 64));
						setArrowProjectile(arrowAttack, player, 90, "RIGHT");
						arrowDestReach = false;
					}
				}
				 else {
					 player.setImage(new Image(this.getClass().getResource(LevelResources.pAttackPath()).toExternalForm()));
					 player.setViewport(new Rectangle2D(0, 192, 64, 64));
				 }
			 }		 
		 
			 PauseTransition hitHold = new PauseTransition(Duration.millis(10));
		  	 hitHold.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						enemyHit = "";
					}		        	  			
		  		});
		  		hitHold.play();		  		
		}
		 player.setX(playerNode.getX() - 16);
		 player.setY(playerNode.getY() - 12);
		 	 
	}
	
	public void setArrowProjectile(ImageView arrowAttack, ImageView player, int arrRotate, String arrDirec) {

		arrowAttack.setRotate(arrRotate);
		
		switch(arrDirec) {
		case "UP": arrX = 0; arrY = -10; break;
		case "LEFT": arrX = -10; arrY = 0; break;
		case "DOWN": arrX = 0; arrY = 10; break;
		case "RIGHT": arrX = 10; arrY = 0; break;
		default : break;
		}
		
		arrowAttack.setX(player.getX() + (player.getFitWidth()/2));
		arrowAttack.setY(player.getY() + (player.getFitHeight()/2));
		
		Timeline arrowProjectile = new Timeline();	
		arrowProjectile.setCycleCount(40);
		
		KeyFrame em = new KeyFrame(
	            Duration.seconds(.005),             
	            new EventHandler<ActionEvent>()
	            {
	                public void handle(ActionEvent ae)
	                {     	
	                	arrowAttack.setX(arrowAttack.getX() + arrX);
	                	arrowAttack.setY(arrowAttack.getY() + arrY);
	                	if(FirstLevel.arrowHit == true)
	                	{
	                		arrowProjectile.stop();
	                		arrowDestReach = true;
	            			FirstLevel.arrowHit = false;
	            			arrowAttack.setX(-50);
	            			arrowAttack.setY(-50);
	                	}

	                }
	            });        
		arrowProjectile.getKeyFrames().add( em );
		arrowProjectile.play();	
		arrowProjectile.setOnFinished(event -> {
			arrowDestReach = true;
			FirstLevel.arrowHit = false;
			arrowAttack.setX(-50);
			arrowAttack.setY(-50);
		});
		
	}
	
	public void getControls() {
		UP = Menu.up;
		LEFT = Menu.left;
		DOWN = Menu.down;
		RIGHT = Menu.right;
		pATTACK = Menu.attack;			
	}
}
