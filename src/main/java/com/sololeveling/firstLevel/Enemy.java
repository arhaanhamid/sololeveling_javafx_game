package com.sololeveling.firstLevel;

import java.util.ArrayList;

import com.sololeveling.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Light;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Enemy {
	private ArrayList<ImageView> enemy = new ArrayList<ImageView>();
	private ArrayList<ProgressBar> enemyHP = new ArrayList<ProgressBar>();
	private ArrayList<Lighting> eLighting = new ArrayList<Lighting>();	
	private ArrayList<Light.Distant> eDistLight = new ArrayList<Light.Distant>();
	private ArrayList<Rectangle> enemyNode = new ArrayList<Rectangle>();
	
	private static ArrayList<Rectangle> ENEMY_TOP = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> ENEMY_LEFT = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> ENEMY_BOTTOM = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> ENEMY_RIGHT = new ArrayList<Rectangle>();

	
	public void getEnemy(int enemies) {
		clearAll();	
		
		for(int e=0; e<enemies; e++) {
			eLighting.add(new Lighting());
			eDistLight.add(new Light.Distant());
			
			enemy.add(new ImageView(new Image(this.getClass().getResource(LevelResources.eImagePath()).toExternalForm())));
			enemy.get(e).setViewport(new Rectangle2D(0, 64, 64, 64));
			enemy.get(e).setFitWidth(64);
			enemy.get(e).setFitHeight(64);
			enemy.get(e).setX(enemy.get(e).getFitWidth() + 10 + (Math.random() * (Main.MAP_RIGHT - enemy.get(e).getFitWidth() -10)));  
			enemy.get(e).setY(Main.MAP_BOTTOM - enemy.get(e).getFitHeight() - 30);
			
			enemy.get(e).setEffect(eLighting.get(e));
    		eLighting.get(e).setLight(eDistLight.get(e));                		               

			
			ENEMY_TOP.add(new Rectangle());
			ENEMY_LEFT.add(new Rectangle());
			ENEMY_BOTTOM.add(new Rectangle());
			ENEMY_RIGHT.add(new Rectangle());
			
			setEnemyBorder(enemy.get(e), e);			
			
			enemyNode.add(new Rectangle());			
			enemyNode.get(e).setWidth(32);
			enemyNode.get(e).setHeight(64);
			enemyNode.get(e).setX(enemy.get(e).getX() + 16);
			enemyNode.get(e).setY(enemy.get(e).getY() + 16);
			enemyNode.get(e).setOpacity(0);

			
			//Adding enemy HP Bar
			enemyHP.add(new ProgressBar());
			enemyHP.get(e).setProgress(1f);
	    	enemyHP.get(e).setMaxSize(50.0, 5.0);
	    	enemyHP.get(e).setLayoutX(enemy.get(e).getX());  
			enemyHP.get(e).setLayoutY(enemy.get(e).getY());
	    	enemyHP.get(e).setId("enemyHP");
		}		
	}
	
	private void clearAll() {
		enemy.clear();
		enemyHP.clear();
		eLighting.clear();
		eDistLight.clear();
		enemyNode.clear();	
		ENEMY_TOP.clear();
		ENEMY_LEFT.clear();
		ENEMY_BOTTOM.clear();
		ENEMY_RIGHT.clear();
		}
	
	public ArrayList<ImageView> getEnemy() {
		return enemy;
	}
	public ArrayList<ProgressBar> getEnemyHP(){
		return enemyHP;
	}
	public ArrayList<Lighting> geteLighting(){
		return eLighting;		
	}
	public ArrayList<Distant> geteDistLight(){
		return eDistLight;
	}
	public ArrayList<Rectangle> getEnemyNode() {
		return enemyNode;
	}	
	public ArrayList<Rectangle> getEnemyTop() {
		return ENEMY_TOP;
	}
	public ArrayList<Rectangle> getEnemyLeft() {
		return ENEMY_LEFT;
	}
	public ArrayList<Rectangle> getEnemyBottom() {
		return ENEMY_BOTTOM;
	}
	public ArrayList<Rectangle> getEnemyRight() {
		return ENEMY_RIGHT;
	}
	
	public void setEnemyBorder(ImageView enemy, int e) {	
		//TOP ENEMY BORDER
		ENEMY_TOP.get(e).setX(enemy.getX() + 16);
		ENEMY_TOP.get(e).setY(enemy.getY());
		ENEMY_TOP.get(e).setWidth(32);
		ENEMY_TOP.get(e).setHeight(7);
		ENEMY_TOP.get(e).setOpacity(0);
		
		//LEFT ENEMY BORDER
		ENEMY_LEFT.get(e).setX(enemy.getX() + 16);
		ENEMY_LEFT.get(e).setY(enemy.getY());
		ENEMY_LEFT.get(e).setWidth(7);
		ENEMY_LEFT.get(e).setHeight(64);
		ENEMY_LEFT.get(e).setOpacity(0);
		
		//BOTTOM ENEMY BORDER
		ENEMY_BOTTOM.get(e).setX(enemy.getX() + 16);
		ENEMY_BOTTOM.get(e).setY(enemy.getY() + 64);
		ENEMY_BOTTOM.get(e).setWidth(32);
		ENEMY_BOTTOM.get(e).setHeight(7);
		ENEMY_BOTTOM.get(e).setOpacity(0);
		
		//RIGHT ENEMY BORDER
		ENEMY_RIGHT.get(e).setX(enemy.getX() + 16 + 32);
		ENEMY_RIGHT.get(e).setY(enemy.getY());
		ENEMY_RIGHT.get(e).setWidth(7);
		ENEMY_RIGHT.get(e).setHeight(64);	
		ENEMY_RIGHT.get(e).setOpacity(0);
	}
}
