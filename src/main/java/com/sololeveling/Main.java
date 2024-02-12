package com.sololeveling;
	
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	//Creating static stage which holds Primary Stage so that every class can access it
	private static Stage guiStage;
	
	public static Stage getStage() {
		return guiStage;
	}	
	
	public static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	public static final double MAP_TOP = 0;
	public static final double MAP_LEFT = 0;
	public static final double MAP_BOTTOM = screenBounds.getHeight();
	public static final double MAP_RIGHT = screenBounds.getWidth();
	
	private ImageView logoView;
	
	private final Image stageIcon = new Image(this.getClass().getResource("/resources/images/logoIcon.png").toExternalForm());
	private final URL resMenuClick = getClass().getResource("/resources/music/sounds/menuClick.wav");
	
	private Menu objMenu = new Menu();
	public static VBox menuBar;
	private ArrayList<Button> menuButs = new ArrayList<Button>();
	private AudioClip clickMP;	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		guiStage = primaryStage;
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			scene.setFill(Color.BLACK);
			scene.getStylesheets().add(getClass().getResource("/com/sololeveling/css/main.css").toExternalForm());
					
			//Calling Logo Class
			Logo objLogo = new Logo();			
			logoView = objLogo.getLogoView();
			root.setCenter(logoView);
			
			
			//PAUSING -> Calling Intro Class
			PauseTransition logoDelay = new PauseTransition(Duration.seconds(6));
			logoDelay.setOnFinished( event -> {
				Intro objIn = new Intro();	
					try {
						objIn.intro();
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				//Changing root node for Intro
				StackPane rootIntro = new StackPane();
				scene.setRoot(rootIntro);
				
				MediaView intMedView;
				intMedView = objIn.getMediaView();
				
				ImageView intImageView;
				intImageView = objIn.getImageView();				
				rootIntro.getChildren().addAll(intMedView, intImageView);
			});
			logoDelay.play();
			
			//PAUAING -> Calling Menu Class
			PauseTransition introDelay = new PauseTransition(Duration.seconds(10));
			introDelay.setOnFinished( event -> {
				
				objMenu.menu();				
				MediaView bgVidView;
				bgVidView = objMenu.getMediaView();
				
				StackPane rootStack = new StackPane();
				scene.setRoot(rootStack);
			
				menuPane(scene);
				rootStack.getChildren().addAll(bgVidView, menuBar);			
			});
			introDelay.play();
			
			
			primaryStage.getIcons().add(stageIcon);
			primaryStage.setFullScreenExitHint("");
			primaryStage.setFullScreen(true);
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH );
			primaryStage.setScene(scene);
			primaryStage.setTitle("SoloLeveling");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	//Method containing Menu
	public void menuPane(Scene scene) {		
		menuBar = new VBox();
		menuBar.setPrefSize(300, 500);
		menuBar.setMaxSize(300, 500);
		menuBar.setSpacing(10);
		
		scene.setCursor(Cursor.DEFAULT);
		
		clickMP = new AudioClip(resMenuClick.toString());
		
		Lighting menuLight = new Lighting();
		menuLight.setSurfaceScale(10);
				
		menuButs.add(new Button("Start"));
		menuButs.add(new Button("Settings"));
		menuButs.add(new Button("Tutorial"));
		menuButs.add(new Button("Credits"));
		menuButs.add(new Button("Exit"));
		
		for(int i=0; i<menuButs.size(); i++) {
			menuButs.get(i).setMinWidth(menuBar.getPrefWidth());
			menuButs.get(i).setTextFill(Color.BLACK);
			menuButs.get(i).setEffect(menuLight);
		}
		
		menuButs.get(0).setOnAction(e -> {
			clickMP.play();
			objMenu.startGame(scene);
		});
		
		menuButs.get(1).setOnAction(e -> {
			clickMP.play();
			objMenu.settings();
		});
		
		menuButs.get(2).setOnAction(e -> {
			clickMP.play();
			objMenu.tutorial();
		});
		
		menuButs.get(3).setOnAction(e -> {
			clickMP.play();
			objMenu.credits();
		});
		
		menuButs.get(4).setOnAction(e -> {
			clickMP.play();
			System.exit(1);
		});
		
		for(int i=0; i<menuButs.size(); i++) {
			menuBar.getChildren().add(menuButs.get(i));
		}
		menuBar.setAlignment(Pos.CENTER);
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}
