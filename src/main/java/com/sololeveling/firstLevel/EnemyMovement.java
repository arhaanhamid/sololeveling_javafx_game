package com.sololeveling.firstLevel;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class EnemyMovement {
	private final int ENEMY_MOVESTEP = 5;
	private static int er, el, eu, ed = 0;
	
	private static int enemySpeed = (FirstLevel.CURRENT_LEVEL * 15);
	
	private final int E_DIR_INTERVAL = 2;
	private final int E_SPEED_COUNT = E_DIR_INTERVAL * enemySpeed; 	
	private final double E_SPEED = (double) E_DIR_INTERVAL/E_SPEED_COUNT;
	
	public static boolean eAttackImg = false;
	public static boolean eCollided = false;
	private static int[] eDirect = new int[20];
		

	public void getEnemyMovement(ImageView enemy, ProgressBar enemyHP, Rectangle enemyNode, int enemyNum) {		
		Collision objCollision = new Collision();
		Timeline enemyMove = new Timeline();
        enemyMove.setCycleCount(E_SPEED_COUNT);
        KeyFrame up = new KeyFrame(Duration.seconds(E_SPEED),new EventHandler<ActionEvent>() {	
            public void handle(ActionEvent ae) {
            	
            	if (PlayerMovement.resetEnemy == true) {
            		enemy.setX(500);
            		enemy.setY(500);
            		PlayerMovement.resetEnemy = false;
            	}

            	if(FirstLevel.EP_COLLISION[enemyNum] == true && FirstLevel.EP_COLLISION_INDEX == enemyNum) {
            		eAttackImg = true;
            	}
            	
            	int eDir = objCollision.checkEOCollision(enemy, eDirect[enemyNum], enemyNum);
            	
            	if(eCollided == true) {
            		eDirect[enemyNum] = eDir;
            		eCollided = false;
            	}
            	
		    	switch(eDir) {
		    	case 1:	if (eAttackImg == true) {
							if (eu < 448 || eu > 576) {eu = 448;}		    				
		    			} 		    	
				    	else if	(eu > 384) {eu = 0;}
		    	
		            	enemy.setViewport(new Rectangle2D(eu, 128, 64, 64));   
		                eu+=64;
		
	                	enemy.setY(enemy.getY() - ENEMY_MOVESTEP);
	                	enemy.setX(enemy.getX());		                
		                
		                enemyNode.setX(enemy.getX() + 16);
		                enemyNode.setY(enemy.getY());
		                
		                enemyHP.setLayoutX(enemy.getX());
		                enemyHP.setLayoutY(enemy.getY());
		                
		                break; 
                    
		    	case 2: if (eAttackImg == true) {
		    				if (el < 448 || el > 576) {el = 448;}		    				
		    			} 		    	
		    			else if(el > 384) {el = 0;}
		    	
		            	enemy.setViewport(new Rectangle2D(el, 192, 64, 64));
						el+=64;		
		        
	            		enemy.setX(enemy.getX() - ENEMY_MOVESTEP);
	            		enemy.setY(enemy.getY());		            	
		               
		                enemyNode.setX(enemy.getX() + 16);
		                enemyNode.setY(enemy.getY());
		                
						enemyHP.setLayoutX(enemy.getX());
		                enemyHP.setLayoutY(enemy.getY());			             
		                
		                break;
		              
		    	case 3: if (eAttackImg == true) {
							if (ed < 448 || ed > 576) {ed = 448;}
		    			} 		    	
				    	else if	(ed > 384) {ed = 0;}
		    	
		            	enemy.setViewport(new Rectangle2D(ed, 0, 64, 64));
						ed+=64;	
						
	            		enemy.setY(enemy.getY() + ENEMY_MOVESTEP); 
	            		enemy.setX(enemy.getX()); 
		            
		                enemyNode.setX(enemy.getX() + 16);
		                enemyNode.setY(enemy.getY());
		                
						enemyHP.setLayoutX(enemy.getX());
		                enemyHP.setLayoutY(enemy.getY());	
		                
		                break;
                    
		    	case 4: if (eAttackImg == true) {
							if (er < 448 || er > 576) {er = 448;}
		    			} 		    	
				    	else if	(er > 384) {er = 0;}
		            	enemy.setViewport(new Rectangle2D(er, 64, 64, 64));
						er+=64;		
		
	            		enemy.setX(enemy.getX() + ENEMY_MOVESTEP);
	            		enemy.setY(enemy.getY());		            	
						
						enemyNode.setX(enemy.getX() + 16);
		                enemyNode.setY(enemy.getY());
		                
						enemyHP.setLayoutX(enemy.getX());
		                enemyHP.setLayoutY(enemy.getY()); 
		                
		                break;
		                
		    	default: break;
		    	}
		    	eAttackImg = false;
		    }

        });
		enemyMove.getKeyFrames().add( up );
		enemyMove.play();
	}
	

	public void startEnemyMovement(ArrayList<ImageView> enemy, ArrayList<ProgressBar> enemyHP, ArrayList<Rectangle> enemyNode) {
		Timeline enemyMovement = new Timeline();	
		enemyMovement.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame em = new KeyFrame(
	            Duration.seconds(E_DIR_INTERVAL),             
	            new EventHandler<ActionEvent>()
	            {
	                public void handle(ActionEvent ae)
	                {     	      
	                	if(FirstLevel.enemies == 0) {	
	                		enemyMovement.stop();
	                	}
	                	for(int i=0; i<FirstLevel.enemies; i++) { 
	                		eDirect[i] = (int)(Math.random()*4) + 1;
	                		getEnemyMovement(enemy.get(i), enemyHP.get(i), enemyNode.get(i), i);
	                	}

	                }
	            });        
	        enemyMovement.getKeyFrames().add( em );
	        enemyMovement.play();		
	}	
}
