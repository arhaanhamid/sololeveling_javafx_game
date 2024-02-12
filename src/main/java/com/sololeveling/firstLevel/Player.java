package com.sololeveling.firstLevel;

import javafx.geometry.Rectangle2D;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Player {
	private ImageView player = new ImageView();
	private Image pImg = new Image(this.getClass().getResource(LevelResources.pMovementPath()).toExternalForm());
	
	Image aAttack = new Image(this.getClass().getResource("/resources/images/arrow.png").toExternalForm());
	ImageView arrowAttack = new ImageView(aAttack);
	
	private Lighting pLighting = new Lighting();
	private Light.Distant pLight = new Light.Distant();
	private Rectangle playerNode = new Rectangle();

	
	public void createPlayer() {	
		playerNode.setX(0);
		playerNode.setY(0);
		playerNode.setWidth(32);
		playerNode.setHeight(52);
		playerNode.setOpacity(0);
		
		player.setFitWidth(64);
		player.setFitHeight(64);
		//get initial image of Player
		player.setImage(pImg);
		player.setViewport(new Rectangle2D(0,128,64,64));				
		player.setX(playerNode.getX() - 16);
		player.setY(player.getY() - 12);
		
		player.setEffect(pLighting);
		pLighting.setLight(pLight);
		
		arrowAttack.setX(-50);
		arrowAttack.setY(-50);
	}
	
	public ImageView getPlayer() {
		return player;
	}	
	public Image getPlayerImg() {
		return pImg;
	}
	public Lighting getpLighting() {
		return pLighting;
	}
	public Light.Distant getpLight() {
		return pLight;
	}
	public Rectangle getPlayerNode() {
		return playerNode;
	}
	public ImageView getArrow() {
		return arrowAttack;
	}	
}
