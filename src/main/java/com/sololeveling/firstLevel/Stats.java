package com.sololeveling.firstLevel;

import com.sololeveling.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Stats {
	private HBox statPane = new HBox();
    private ImageView eStatImg = new ImageView(new Image(this.getClass().getResource("/resources/images/eStatImg.png").toExternalForm()));
    private ImageView pStatImg = new ImageView(new Image(this.getClass().getResource("/resources/images/pStatImg.png").toExternalForm()));

 	private Text eLives = new Text();
 	private ProgressBar playerHP= new ProgressBar();
 	
	public void getStat(double pHP){
     	eLives.setText("" + FirstLevel.enemies);
        eLives.setFill(Color.BLACK);
        
        playerHP.setProgress(pHP);
        playerHP.setMaxSize(200.0, 15.0);
		playerHP.setMinSize(200.0, 15.0);
		playerHP.setId("playerHP");
		

		statPane.setSpacing(10);
		statPane.setPadding(new Insets(5));
		statPane.setAlignment(Pos.CENTER);		
        statPane.setId("statPane");
        statPane.setLayoutX((Main.screenBounds.getWidth()/100)*30);          
     }
	
	public HBox getStatPane() {
		return statPane;
	}
	public ImageView geteStatImg() {
		return eStatImg;
	}
	public ImageView getpStatImg() {
		return pStatImg;
	}
	public ProgressBar getPlayerHP() {
		return playerHP;
	}	
	public Text geteLives() {
		return eLives;
	}
}
