package com.sololeveling.firstLevel;

import com.sololeveling.Main;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class GameMap {	
	public final Rectangle MAP_TOP = new Rectangle(1,1,Main.screenBounds.getWidth(),1);
	public final Rectangle MAP_LEFT = new Rectangle(1,1,1,Main.screenBounds.getHeight() );
	public final Rectangle MAP_BOTTOM = new Rectangle(1,Main.screenBounds.getHeight() ,Main.screenBounds.getWidth(),1);
	public final Rectangle MAP_RIGHT = new Rectangle(Main.screenBounds.getWidth() ,1,1,Main.screenBounds.getHeight());
	
	private BorderPane gameMap = new BorderPane();	  

		public void gameMap() {
			//Game Border effects
			/*Lighting lightBorder = new Lighting();		
			lightBorder.setDiffuseConstant(2.0);
			lightBorder.setSpecularConstant(2.0);
			lightBorder.setSpecularExponent(40.0);
			lightBorder.setSurfaceScale(10.0);		
			gameBorder.setEffect(lightBorder);*/	

			gameMap.setStyle("-fx-background-image:url(" + this.getClass().getResource(LevelResources.gameMap()).toExternalForm() + ");");
			gameMap.setLayoutX(0);
			gameMap.setLayoutY(0);
			gameMap.setMinWidth(Main.screenBounds.getWidth());
			gameMap.setMinHeight(Main.screenBounds.getHeight());			
		}
		
		public BorderPane getGameMap() {
			return gameMap;
		}
}
