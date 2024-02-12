package com.sololeveling;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Intro {

	private static MediaView intMediaView;
	private static ImageView intImgView;
	
	public void intro() throws IOException {
			//Adding Video for intro into mediaplayer
			Media introVid = new Media(this.getClass().getResource("/resources/videos/introVideo.mp4").toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(introVid);
			mediaPlayer.setStopTime(Duration.seconds(10));
			intMediaView = new MediaView(mediaPlayer);	
			
			//Getting screen bounds/resolution for video to expand full
			Rectangle2D screenBounds = Screen.getPrimary().getBounds();
			intMediaView.setFitWidth(screenBounds.getWidth());
			intMediaView.setFitHeight(screenBounds.getHeight());
			mediaPlayer.setAutoPlay(true);
			
			//Adding Image for Intro
			Image intImg = new Image(this.getClass().getResource("/resources/images/intro.png").toExternalForm());
			intImgView = new ImageView(intImg);	
			intImgView.setOpacity(0.0);
			
			//Adding effects
			FadeTransition fTrans = new FadeTransition();
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(0.0);
			fTrans.setToValue(1);
			fTrans.setNode(intImgView);
			fTrans.play();
		
			PauseTransition introDelay = new PauseTransition(Duration.seconds(7));
			introDelay.setOnFinished( event -> {
			fTrans.setDuration(Duration.seconds(3));
			fTrans.setFromValue(1);
			fTrans.setToValue(0.0);
			fTrans.setNode(intImgView);
			fTrans.play();
			});
			introDelay.play();
	}
	
	public MediaView getMediaView(){
		return intMediaView;
	}
	
	public ImageView getImageView(){
		return intImgView;
	}
	
}
