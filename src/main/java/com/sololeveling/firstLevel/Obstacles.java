package com.sololeveling.firstLevel;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Obstacles {
	private static ArrayList<ImageView> TREEL1_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> TREEL1_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> TREEL1_03 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> TREEL2_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> TREEL2_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL1_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL1_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL2_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL2_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL2_03 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL2_04 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL3_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL3_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> HOUSEL3_03 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> ROCKL3_01 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> ROCKL3_02 = new ArrayList<ImageView>();
	private static ArrayList<ImageView> ROCKL3_03 = new ArrayList<ImageView>();


	private static ArrayList<ImageView> ALL_OBSTACLES = new ArrayList<ImageView>();
	
	private static ArrayList<Rectangle> OBSTACLE_TOP = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> OBSTACLE_LEFT = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> OBSTACLE_BOTTOM = new ArrayList<Rectangle>();
	private static ArrayList<Rectangle> OBSTACLE_RIGHT = new ArrayList<Rectangle>();
	
	private static int currentObstac = 0;
	private static boolean obstacleOverlapped = false;
	private final int obstaclesPerSet = 3;		//Total 5 sets

	public void obstacles() {
		clearAll();		
		
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: addObstaclesLevel1();
		break;
		case 2: addObstaclesLevel2();
		break;
		case 3: addObstaclesLevel3();
		break;
		default: break;
		}
		
		setObstacleLocat();
		
		for(int i=0; i<ALL_OBSTACLES.size(); i++) {
			setObstacleBorder(ALL_OBSTACLES.get(i), i);
		}
	}	
	
	private void clearAll() {
		currentObstac = 0;
		ALL_OBSTACLES.clear();
		obstacleOverlapped = false;
		OBSTACLE_TOP.clear();
		OBSTACLE_LEFT.clear();
		OBSTACLE_BOTTOM.clear();
		OBSTACLE_RIGHT.clear();
	}

	public ArrayList<ImageView> getAllObstacles() {
		return ALL_OBSTACLES;
	}	
	public ArrayList<Rectangle> getObstacleTop() {
		return OBSTACLE_TOP;
	}
	public ArrayList<Rectangle> getObstacleLeft() {
		return OBSTACLE_LEFT;
	}
	public ArrayList<Rectangle> getObstacleBottom() {
		return OBSTACLE_BOTTOM;
	}
	public ArrayList<Rectangle> getObstacleRight() {
		return OBSTACLE_RIGHT;
	}
	
	
	public void addObstaclesLevel1() {
		for(int i=0; i<obstaclesPerSet; i++) {
			TREEL1_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/TREEL1_01.png").toExternalForm())));
			TREEL1_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/TREEL1_02.png").toExternalForm())));
			TREEL1_03.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/TREEL1_03.png").toExternalForm())));
			HOUSEL1_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL1_01.png").toExternalForm())));
			HOUSEL1_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL1_02.png").toExternalForm())));
		}
		for(int j=0; j<obstaclesPerSet; j++) {
			ALL_OBSTACLES.add(TREEL1_01.get(j));
			ALL_OBSTACLES.add(TREEL1_02.get(j));
			ALL_OBSTACLES.add(TREEL1_03.get(j));
			ALL_OBSTACLES.add(HOUSEL1_01.get(j));
			ALL_OBSTACLES.add(HOUSEL1_02.get(j));
		}
		
		for(int i=0; i<ALL_OBSTACLES.size(); i++) {
			OBSTACLE_TOP.add(new Rectangle());
			OBSTACLE_LEFT.add(new Rectangle());
			OBSTACLE_BOTTOM.add(new Rectangle());
			OBSTACLE_RIGHT.add(new Rectangle());
		}
	}
	
	public void addObstaclesLevel2() {
		for(int i=0; i<obstaclesPerSet; i++) {
			TREEL2_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/TREEL2_01.png").toExternalForm())));
			TREEL2_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/TREEL2_02.png").toExternalForm())));
			HOUSEL2_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL2_01.png").toExternalForm())));
			HOUSEL2_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL2_02.png").toExternalForm())));
			HOUSEL2_03.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL2_03.png").toExternalForm())));
			HOUSEL2_04.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL2_04.png").toExternalForm())));
		}
		for(int j=0; j<obstaclesPerSet; j++) {
			ALL_OBSTACLES.add(TREEL2_01.get(j));
			ALL_OBSTACLES.add(TREEL2_02.get(j));
			ALL_OBSTACLES.add(HOUSEL2_01.get(j));
			ALL_OBSTACLES.add(HOUSEL2_02.get(j));
			ALL_OBSTACLES.add(HOUSEL2_03.get(j));
			ALL_OBSTACLES.add(HOUSEL2_04.get(j));
		}
		
		for(int i=0; i<ALL_OBSTACLES.size(); i++) {
			OBSTACLE_TOP.add(new Rectangle());
			OBSTACLE_LEFT.add(new Rectangle());
			OBSTACLE_BOTTOM.add(new Rectangle());
			OBSTACLE_RIGHT.add(new Rectangle());
		}
	}
	
	public void addObstaclesLevel3() {
		for(int i=0; i<obstaclesPerSet; i++) {
			HOUSEL3_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL3_01.png").toExternalForm())));
			HOUSEL3_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL3_02.png").toExternalForm())));
			HOUSEL3_03.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/HOUSEL3_03.png").toExternalForm())));
			ROCKL3_01.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/ROCKL3_01.png").toExternalForm())));
			ROCKL3_02.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/ROCKL3_02.png").toExternalForm())));
			ROCKL3_03.add(new ImageView(new Image(this.getClass().getResource("/resources/images/obstacles/ROCKL3_03.png").toExternalForm())));
		}
		
		for(int j=0; j<obstaclesPerSet; j++) {
			ALL_OBSTACLES.add(HOUSEL3_01.get(j));
			ALL_OBSTACLES.add(HOUSEL3_02.get(j));
			ALL_OBSTACLES.add(HOUSEL3_03.get(j));
			ALL_OBSTACLES.add(ROCKL3_01.get(j));
			ALL_OBSTACLES.add(ROCKL3_02.get(j));
			ALL_OBSTACLES.add(ROCKL3_03.get(j));

		}
		
		for(int i=0; i<ALL_OBSTACLES.size(); i++) {
			OBSTACLE_TOP.add(new Rectangle());
			OBSTACLE_LEFT.add(new Rectangle());
			OBSTACLE_BOTTOM.add(new Rectangle());
			OBSTACLE_RIGHT.add(new Rectangle());
		}
	}
	
	public void setObstacleLocat(){
		GameMap objGameMap = new GameMap();

		for(int i=currentObstac; i<ALL_OBSTACLES.size(); i++) {
			currentObstac = i;
			ALL_OBSTACLES.get(i).setX(100 + (Math.random() * (objGameMap.MAP_RIGHT.getX() - 200)));
			ALL_OBSTACLES.get(i).setY(100 + (Math.random() * (objGameMap.MAP_BOTTOM.getY() - 200)));
			
			checkObstacleOverlap(ALL_OBSTACLES.get(i));	
			if(obstacleOverlapped == true) {
				obstacleOverlapped = false;
				setObstacleLocat();
				break;
			}
		}
	}
	
	
	public void setObstacleBorder(ImageView obstac, int o) {	
		//TOP ENEMY BORDER
		obstac.getLayoutBounds();
		double obstacHeight = obstac.getLayoutBounds().getHeight();
		double obstacWidth = obstac.getLayoutBounds().getWidth();


		OBSTACLE_TOP.get(o).setX(obstac.getX());
		OBSTACLE_TOP.get(o).setY(obstac.getY());
		OBSTACLE_TOP.get(o).setWidth(obstacWidth);
		OBSTACLE_TOP.get(o).setHeight(1);
		OBSTACLE_TOP.get(o).setOpacity(0);
		
		//LEFT ENEMY BORDER
		OBSTACLE_LEFT.get(o).setX(obstac.getX());
		OBSTACLE_LEFT.get(o).setY(obstac.getY());
		OBSTACLE_LEFT.get(o).setWidth(1);
		OBSTACLE_LEFT.get(o).setHeight(obstacHeight);
		OBSTACLE_LEFT.get(o).setOpacity(0);
		
		//BOTTOM ENEMY BORDER
		OBSTACLE_BOTTOM.get(o).setX(obstac.getX());
		OBSTACLE_BOTTOM.get(o).setY(obstac.getY() + obstacHeight);
		OBSTACLE_BOTTOM.get(o).setWidth(obstacWidth);
		OBSTACLE_BOTTOM.get(o).setHeight(1);
		OBSTACLE_BOTTOM.get(o).setOpacity(0);
		
		//RIGHT ENEMY BORDER
		OBSTACLE_RIGHT.get(o).setX(obstac.getX() + obstacWidth);
		OBSTACLE_RIGHT.get(o).setY(obstac.getY());
		OBSTACLE_RIGHT.get(o).setWidth(1);
		OBSTACLE_RIGHT.get(o).setHeight(obstacHeight);	
		OBSTACLE_RIGHT.get(o).setOpacity(0);
	}
	
	public void checkObstacleOverlap(ImageView obstac){
		
		for(int i=0; i<ALL_OBSTACLES.size(); i++) {
			if(i == currentObstac) {	continue;	}
			
			if(obstac.intersects(ALL_OBSTACLES.get(i).getBoundsInLocal())) {
				obstacleOverlapped = true;
				break;
			}
		}
	}	
}
