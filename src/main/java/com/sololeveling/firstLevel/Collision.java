package com.sololeveling.firstLevel;

import javafx.scene.image.ImageView;

public class Collision extends FirstLevel{	
	
	public int checkEOCollision(ImageView enemy, int currDir, int enemyIndex) {
		GameMap objGameMap = new GameMap();
		Enemy objEnemy = new Enemy();
		Obstacles objObstacle = new Obstacles();		
		
		boolean obstacTopColl = false;
		boolean obstacBottomColl = false;
		boolean obstacLeftColl = false;
		boolean obstacRightColl = false;
		
		int notUp, notBot, notLeft, notRight;
		
		objEnemy.setEnemyBorder(enemy, enemyIndex);	
		
		for(int i=0; i<objObstacle.getAllObstacles().size(); i++) {
			if(objEnemy.getEnemyTop().get(enemyIndex).intersects(objObstacle.getObstacleBottom().get(i).getBoundsInLocal())){
				obstacBottomColl = true;
				break;
	        }
			else if(objEnemy.getEnemyLeft().get(enemyIndex).intersects(objObstacle.getObstacleRight().get(i).getBoundsInLocal())){
				obstacRightColl = true;
				break;
	        }
			else if(objEnemy.getEnemyBottom().get(enemyIndex).intersects(objObstacle.getObstacleTop().get(i).getBoundsInLocal())){
				obstacTopColl = true;
				break;
	        } 
			else if(objEnemy.getEnemyRight().get(enemyIndex).intersects(objObstacle.getObstacleLeft().get(i).getBoundsInLocal())){
				obstacLeftColl = true;
				break;
	        }
		}
				
		if(objEnemy.getEnemyTop().get(enemyIndex).intersects(objGameMap.MAP_TOP.getBoundsInLocal())				||
			obstacBottomColl == true){
			obstacBottomColl = false;
			EnemyMovement.eCollided = true;
			do {
				notUp = (int)(Math.random()*4) + 1;	
			}
			while(notUp == 1);
			return notUp; 		//3
        }
		else if(objEnemy.getEnemyLeft().get(enemyIndex).intersects(objGameMap.MAP_LEFT.getBoundsInLocal())		||
				obstacRightColl == true){
				obstacRightColl = false;
				EnemyMovement.eCollided = true;
				do {
					notLeft = (int)(Math.random()*4) + 1;	
				}
				while(notLeft == 2);
				return notLeft;			//4
        }
		else if(objEnemy.getEnemyBottom().get(enemyIndex).intersects(objGameMap.MAP_BOTTOM.getBoundsInLocal())	||
				obstacTopColl == true){
				obstacTopColl = false;
				EnemyMovement.eCollided = true;
				do {
					notBot = (int)(Math.random()*4) + 1;	
				}
				while(notBot == 3);
				return notBot;			//1
        } 
		else if(objEnemy.getEnemyRight().get(enemyIndex).intersects(objGameMap.MAP_RIGHT.getBoundsInLocal())	||
				obstacLeftColl == true){
			obstacLeftColl = false;
			EnemyMovement.eCollided = true;
				do {
					notRight = (int)(Math.random()*4) + 1;	
				}
				while(notRight == 4);
				return notRight;			//2
        } 
        else {
        	return  currDir;
        }       
	}	
}
