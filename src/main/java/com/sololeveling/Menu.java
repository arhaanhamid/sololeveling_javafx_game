package com.sololeveling;


import java.util.ArrayList;

import com.sololeveling.firstLevel.FirstLevel;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Menu{
	private static MediaView bgVidView;
	private static MediaPlayer mediaPlayer;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	
	//Stuff for Settings menu
	ArrayList<Label> settLabels = new ArrayList<Label>();
	ArrayList<TextField> settTexts = new ArrayList<TextField>();
	Slider musSlider = new Slider();
	CheckBox musToggle = new CheckBox();
	
	public static String up = "W", left = "A", down = "S", right = "D", attack = "F";
	private static String u, l , d, r, a;
	private static boolean defControls = true;
	private static boolean musCheck = true;
	private static double lastVolume = 1.0f;
	private static double musicVol = 1.0;
	
	//Stuff for Credits menu
	ArrayList<Label> credLabels = new ArrayList<Label>();
	static int labLimit = 1;
	private static boolean pause1time = true;
	
	//Stuff for Tutorial menu
	ArrayList<Label> tutLabels = new ArrayList<Label>();
	
	public void menu() {		
		String menuVidPath = "/resources/videos/menuBGvid.mp4";
		Media menuVid = new Media(this.getClass().getResource(menuVidPath).toExternalForm());
		mediaPlayer = new MediaPlayer(menuVid);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		bgVidView = new MediaView(mediaPlayer);	
		bgVidView.setOpacity(0.0);
		
		//MediaView Effects
		FadeTransition fTrans = new FadeTransition();
		fTrans.setDuration(Duration.seconds(3));
		fTrans.setFromValue(0.0);
		fTrans.setToValue(1.0);
		fTrans.setNode(bgVidView);
		fTrans.play();		
		
		//this statement is used to get screen bounds/resolution
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		bgVidView.setFitWidth(screenBounds.getWidth());
		bgVidView.setFitHeight(screenBounds.getHeight());	
		
		mediaPlayer.setAutoPlay(true);			
	}

	public MediaView getMediaView(){
		return bgVidView;
	}
		
	public void startGame(Scene scene) {
		//Fade out effect of intro music
		Timeline fadeIntMusic = new Timeline();
		fadeIntMusic.setCycleCount(Timeline.INDEFINITE);        	        
        KeyFrame fm = new KeyFrame(
            Duration.seconds(0.3),               
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                	mediaPlayer.setVolume(mediaPlayer.getVolume() - 0.1); 
                	if(mediaPlayer.getVolume() == 0) {mediaPlayer.stop(); fadeIntMusic.stop();}
                }
            });        
        fadeIntMusic.getKeyFrames().add( fm );
        fadeIntMusic.play();		
		
		StackPane labelPane = new StackPane();
		
		Label firstLabel1 = new Label("A Walk In The Park");
		firstLabel1.setId("firstLabel1");
		firstLabel1.setOpacity(0.0);
		
		Label firstLabel2 = new Label("Deep in the Village of Konoha!");
		firstLabel2.setId("firstLabel2");
		firstLabel2.setOpacity(0.0);
		
		FadeTransition fTrans = new FadeTransition();
		fTrans.setDuration(Duration.seconds(3));
		fTrans.setFromValue(0.0);
		fTrans.setToValue(1.0);
		fTrans.setCycleCount(2); 
		fTrans.setAutoReverse(true);
		fTrans.setNode(firstLabel1);
		fTrans.play();	
		
		PauseTransition label1Delay = new PauseTransition(Duration.seconds(6));
		label1Delay.setOnFinished( event -> {
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(0.0);
			fTrans.setToValue(1.0);
			fTrans.setCycleCount(1); 
			fTrans.setNode(firstLabel2);
			fTrans.play();	
		}
		 );
		label1Delay.play();	
		
		PauseTransition label2Delay = new PauseTransition(Duration.seconds(12));
		label2Delay.setOnFinished( event -> {
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(1.0);
			fTrans.setToValue(0.0);
			fTrans.setCycleCount(1); 
			fTrans.setNode(firstLabel2);
			fTrans.play();	
		}
		 );
		label2Delay.play();	
		
		labelPane.getChildren().addAll(firstLabel1, firstLabel2);

		
		scene.setRoot(labelPane);
		
		PauseTransition gameDelay = new PauseTransition(Duration.seconds(16)); 
		gameDelay.setOnFinished( event -> {
			
			if(defControls == true) {
			getDefControls();
			}			
			
			FirstLevel objFirst = new FirstLevel();
			objFirst.gameScene(scene, musicVol, 1);
		});
		gameDelay.play();	
	}
	
	
	public void settings() {
		Main.getStage().getScene().getRoot().setEffect(new GaussianBlur());
		
		GridPane settRoot = new GridPane();
		Stage settStage = new Stage(StageStyle.TRANSPARENT);
		settStage.initOwner(Main.getStage());
		settStage.initModality(Modality.APPLICATION_MODAL);
		settRoot.setPadding(new Insets(0, 10, 10, 10));
		settRoot.setHgap(20);
		settRoot.setVgap(10);
		settRoot.setAlignment(Pos.CENTER);
		
		Lighting lightEffect = new Lighting();		
		lightEffect.setSurfaceScale(10.0);
		settRoot.setEffect(lightEffect);
				
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth((screenBounds.getWidth()/100)*15);
		col1.setPrefWidth((screenBounds.getWidth()/100)*15);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setMinWidth((screenBounds.getWidth()/100)*15);
		col2.setPrefWidth((screenBounds.getWidth()/100)*15);
		settRoot.getColumnConstraints().addAll(col1, col2);
		
		u = null; l = null; d = null; r = null; a = null;
			
		getLabelsTexts();		
	
    	settTexts.get(0).setOnKeyPressed(event ->{
			settTexts.get(0).setText(event.getCode().toString());
			u = event.getCode().toString();
    	});
    	
		settTexts.get(1).setOnKeyPressed(event ->{
			settTexts.get(1).setText(event.getCode().toString());
			l = event.getCode().toString();
    	});
		
		settTexts.get(2).setOnKeyPressed(event ->{
			settTexts.get(2).setText(event.getCode().toString());
			d = event.getCode().toString();
    	});
		
		settTexts.get(3).setOnKeyPressed(event ->{
			settTexts.get(3).setText(event.getCode().toString());
			r = event.getCode().toString();
    	});
		
		settTexts.get(4).setOnKeyPressed(event ->{
			settTexts.get(4).setText(event.getCode().toString());
			a = event.getCode().toString();

    	});		
		
		
		musSlider.setMin(0.0);
		musSlider.setMax(1.0);
		musSlider.setValue(lastVolume);		

		musToggle.setSelected(musCheck);
		
		//Timeline for music slider
		Timeline mediaVol = new Timeline();
		mediaVol.setCycleCount(Timeline.INDEFINITE);        	        
        KeyFrame em = new KeyFrame(
            Duration.seconds(0.1),               
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
            		if(musToggle.isSelected() == true) {
            			musSlider.setDisable(false);
                		mediaPlayer.setVolume(musSlider.getValue());            			
            		}
            		else {
            			musSlider.setDisable(true);
            			mediaPlayer.setVolume(0.0);            			
            		}
                }
            });        
        mediaVol.getKeyFrames().add( em );
        mediaVol.play();
        
		//SAVE NEW CONTROLS
		Button save = new Button("Save");
		save.setEffect(lightEffect);
		save.setId("save");
		save.setMaxWidth(screenBounds.getWidth()/6);
		
		save.setOnAction(svEvent ->{			
			defControls = false;
			
			getNewControls();

			musCheck = musToggle.isSelected();
			lastVolume = musSlider.getValue();
		});
		
		//RESET TO DEFAULT
		Button reset = new Button("Reset");
		reset.setEffect(lightEffect);
		reset.setId("reset");
		reset.setMaxWidth(screenBounds.getWidth()/6);
		reset.setOnAction(rst ->{
			resettoDefault();
		});
		
		//BACK TO MAIN MENU WITH/WITHOUT SAVE
		Button goBack = new Button("Back");	
		goBack.setEffect(lightEffect);
		goBack.setId("goBack");
		goBack.setMaxWidth(save.getMaxWidth() + reset.getMaxWidth() );
		GridPane.setColumnSpan(goBack, 2);
		GridPane.setHalignment(goBack, HPos.CENTER);
		
		goBack.setOnAction(b ->{
			Main.getStage().getScene().getRoot().setEffect(null);
			
			if(musCheck == false) {	mediaPlayer.setVolume(0.0);	}
			else {	mediaPlayer.setVolume(lastVolume); }
			musicVol = mediaPlayer.getVolume();

			mediaVol.stop();			
			settStage.hide();			
		});	
		

		settRoot.addRow(0, settLabels.get(0));
		settRoot.addRow(1, settLabels.get(1), settTexts.get(0));
		settRoot.addRow(2, settLabels.get(2), settTexts.get(1));
		settRoot.addRow(3, settLabels.get(3), settTexts.get(2));
		settRoot.addRow(4, settLabels.get(4), settTexts.get(3));
		settRoot.addRow(5, settLabels.get(5), settTexts.get(4));
		
		settRoot.addRow(6, settLabels.get(6));
		settRoot.addRow(7, settLabels.get(7), musToggle);
		settRoot.addRow(8, settLabels.get(8), musSlider);
		settRoot.addRow(9, save, reset);
		settRoot.addRow(10, goBack);
		
		settStage.setScene(new Scene(settRoot, Color.TRANSPARENT));
		settStage.getScene().getStylesheets().add(getClass().getResource("/com/sololeveling/css/menu.css").toExternalForm());
		settStage.show();
	}	
	
	public void credits() {
		Main.getStage().getScene().getRoot().setEffect(new GaussianBlur());		
		Group credRoot = new Group();
		Stage credStage = new Stage(StageStyle.TRANSPARENT);
		credStage.initOwner(Main.getStage());
		credStage.initModality(Modality.APPLICATION_MODAL);
		credStage.setScene(new Scene(credRoot, Color.TRANSPARENT));	
						
		getCredLabels();		
		
		//Adding all labels to root
		for(int l=0; l<credLabels.size(); l++) {
        	credRoot.getChildren().add(credLabels.get(l));
        }
		
		//Credits timeline to pause/show next label
		Timeline subCredTime = new Timeline();
		subCredTime.setCycleCount(Timeline.INDEFINITE);        	        
        KeyFrame cp = new KeyFrame(
            Duration.seconds(2),               
            new EventHandler<ActionEvent>()
            {
				@Override
				public void handle(ActionEvent event) {
					labLimit++;
				}  
            }); 			        
        subCredTime.getKeyFrames().add( cp );
        subCredTime.play();
        
		
		//Credit timeline for label transitions
		Timeline credTime = new Timeline();
		credTime.setCycleCount(Timeline.INDEFINITE);        	        
        KeyFrame ct = new KeyFrame(
            Duration.millis(8.3),       
            new EventHandler<ActionEvent>()
            {
				@Override
				public void handle(ActionEvent event) {
					moveLabels(labLimit);
					if(labLimit == credLabels.size() - 1) {
			        	subCredTime.stop();
						
						if(pause1time == true) {
				        	PauseTransition pause = new PauseTransition(Duration.seconds(8));
							pause.setOnFinished(eve ->{							
							
								credLabels.get(12).setLayoutY(screenBounds.getHeight()/2);
								pause.stop();
								credTime.stop();
	
							});
							pause.play();
						}
						pause1time = false;
			        }
				}  
            });         
        credTime.getKeyFrames().add( ct );
        credTime.play(); 
        
        		
		//Event to exit to main menu on key press
		credStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE) {
					
					credTime.stop();
					subCredTime.stop();
					pause1time = true;
					credLabels.clear();
					labLimit = 1;
					Main.getStage().getScene().getRoot().setEffect(null);			
					credStage.hide();
				}
			}			
		});
		
		credStage.show();
	}
	
	public void tutorial() {
		Main.getStage().getScene().getRoot().setEffect(new GaussianBlur());		
		VBox tutRoot = new VBox();
		Stage tutStage = new Stage(StageStyle.TRANSPARENT);
		tutStage.initOwner(Main.getStage());
		tutStage.initModality(Modality.APPLICATION_MODAL);
		tutStage.setScene(new Scene(tutRoot, Color.TRANSPARENT));
		tutRoot.setStyle("-fx-background-color : transparent;");
		
		getTutLabels();
		
		//Adding all labels to root
		for(int l=0; l<tutLabels.size(); l++) {
        	tutRoot.getChildren().add(tutLabels.get(l));
        }
		
		//Event to close window on key press
		tutStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ESCAPE) {
					
					Main.getStage().getScene().getRoot().setEffect(null);			
					tutStage.hide();
				}
			}			
		});
		
		tutStage.show();
	}
	
	private void getLabelsTexts(){
		settLabels.add(new Label("CONTROLS"));	//0
		GridPane.setHalignment(settLabels.get(0), HPos.CENTER);
		GridPane.setColumnSpan(settLabels.get(0), 2);
		settLabels.get(0).setId("controls");;		
		
		settLabels.add(new Label("Up"));		//1
		settLabels.add(new Label("Left"));		//2
		settLabels.add(new Label("Down"));		//3
		settLabels.add(new Label("Right"));		//4	
		settLabels.add(new Label("Attack"));	//5
		
		settLabels.add(new Label("SOUND"));		//6
		GridPane.setHalignment(settLabels.get(6), HPos.CENTER);
		GridPane.setColumnSpan(settLabels.get(6), 2);
		settLabels.get(6).setId("sound");
		
		settLabels.add(new Label("Music"));		//7
		settLabels.add(new Label("Volume"));	//8
		
		settTexts.add(new TextField());		//0
		settTexts.get(0).setText(up);
		settTexts.get(0).setContextMenu(new ContextMenu());
		settTexts.add(new TextField());		//1
		settTexts.get(1).setText(left);
		settTexts.get(1).setContextMenu(new ContextMenu());
		settTexts.add(new TextField());		//2
		settTexts.get(2).setText(down);
		settTexts.get(2).setContextMenu(new ContextMenu());
		settTexts.add(new TextField());		//3
		settTexts.get(3).setText(right);
		settTexts.get(3).setContextMenu(new ContextMenu());
		settTexts.add(new TextField());		//4
		settTexts.get(4).setText(attack);
		settTexts.get(4).setContextMenu(new ContextMenu());

		
		for(int i=0; i<5; i++) {
			settTexts.get(i).setEditable(false);
			settTexts.get(i).setAlignment(Pos.CENTER);
		}
		
			
	}

	public void resettoDefault() {
		settTexts.get(0).setText("W");		//0
		u = "W";
		settTexts.get(1).setText("A");		//1
		l = ("A");
		settTexts.get(2).setText("S");		//2
		d = ("S");
		settTexts.get(3).setText("D");		//3
		r = ("D");
		settTexts.get(4).setText("F");		//4
		a = ("F");
		
		musToggle.setSelected(true);
		musSlider.setValue(1.0);
	}
		
	
	public void getDefControls() {
		up = "W";
		left = "A";
		down = "S";
		right = "D";
		attack = "F";
		}
	
	public void getNewControls() {
		if(u == null) {}
		else {	up = u;	}
		
		if(l == null) {}
		else {	left = l;	}
		
		if(d == null) {}
		else {	down = d;	}
		
		if(r == null) {}
		else {	right = r;	}
		
		if(a == null) {}
		else {	attack = a;	}	
	}
	
	public void getCredLabels() {		
		credLabels.add(new Label("You Clicked?"));
		credLabels.add(new Label("Who? Who watches Credits???"));
		credLabels.add(new Label("Well, since you want to..."));
		credLabels.add(new Label("Let me tell you something about me"));
		credLabels.add(new Label("By 'Me', I mean the one who developed this Masterpiece!"));
		credLabels.add(new Label("The name is Arhaan, you might have heard already."));
		credLabels.add(new Label("AKA SoloK1NG"));
		credLabels.add(new Label("Team?"));
		credLabels.add(new Label("Why do you think I'm AKA SoloK1NG?"));
		credLabels.add(new Label("What else do I say about me?"));
		credLabels.add(new Label("Just wait couple years then learn from Google!"));
		credLabels.add(new Label("Credits End!"));
		credLabels.add(new Label("Press 'Escape' to exit to Main menu."));
		
		for(int i=0; i<credLabels.size(); i++) {
			credLabels.get(i).setLayoutY(screenBounds.getHeight() + 100);
			credLabels.get(i).setPrefWidth(screenBounds.getWidth());
			credLabels.get(i).setAlignment(Pos.CENTER);
			credLabels.get(i).setTextAlignment(TextAlignment.CENTER);
			credLabels.get(i).setStyle("-fx-text-fill : black;	-fx-font-weight:bold;	-fx-font-size:40px;	-fx-font-family: Papyrus, Times New Roman, Times, serif;	-fx-effect: dropshadow(three-pass-box, red, 50, 0.9, 0, 0);");
		}
		
	}
	
	public void moveLabels(int lab) {
		for(int i=0; i<lab; i++)
		credLabels.get(i).setLayoutY(credLabels.get(i).getLayoutY() - 1);
	}
	
	public void getTutLabels() {
		tutLabels.add(new Label("HOW TO CONTROL CHARACTER"));
		tutLabels.add(new Label("Use your Keyboard Keys to Move the Character"));
		tutLabels.add(new Label("Arrow Keys for Movement"));
		tutLabels.add(new Label("SpaceBar for Attacking"));		
		tutLabels.add(new Label("You can also go to settings and set up your own Control Keys"));
		
		tutLabels.add(new Label("HOW TO WIN"));
		tutLabels.add(new Label("Go near an Enemy and Attack using specified Key"));
		tutLabels.add(new Label("Enemy can dodge some Attacks"));
		tutLabels.add(new Label("So Smash that Attack Button to Kill an Enemy"));
		tutLabels.add(new Label("Kill all Enemies in a Level to Proceed to Next Level"));
		tutLabels.add(new Label("I'm too lazy to waste time on this stupid tutorial"));
		
		for(int i=0; i<tutLabels.size(); i++) {
			tutLabels.get(i).setLayoutY(screenBounds.getHeight() + 100);
			tutLabels.get(i).setPrefWidth(screenBounds.getWidth());
			tutLabels.get(i).setAlignment(Pos.CENTER);
			tutLabels.get(i).setTextAlignment(TextAlignment.CENTER);
			tutLabels.get(i).setStyle("-fx-text-fill : black;	-fx-font-weight:bold;	-fx-font-size:30px;	-fx-font-family: Papyrus, Times New Roman, Times, serif;	-fx-effect: dropshadow(three-pass-box, red, 50, 0.9, 0, 0);");
		}

	}
	

}
