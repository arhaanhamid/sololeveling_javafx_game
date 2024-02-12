package com.sololeveling;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Logo {
	private ImageView logoView;
	private Image logo = new Image(this.getClass().getResource("/resources/images/logo.png").toExternalForm());
	
	public ImageView getLogoView() {
		
		logoView = new ImageView(logo);
		logoView.setOpacity(0.0);

		FadeTransition fTrans = new FadeTransition();
		fTrans.setDuration(Duration.seconds(3));
		fTrans.setFromValue(0.0);
		fTrans.setToValue(1.0);
		fTrans.setCycleCount(2); 
		fTrans.setAutoReverse(true);
		fTrans.setNode(logoView);
		fTrans.play();		
		
		return logoView;
	}

}
