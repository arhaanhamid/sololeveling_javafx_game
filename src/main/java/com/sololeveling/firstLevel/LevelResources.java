package com.sololeveling.firstLevel;

public class LevelResources {
	
	public static String pMovementPath() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/images/pMovement_L1.png";
		case 2: return "/resources/images/pMovement_L2.png";
		case 3: return "/resources/images/pMovement_L3.png";
		}
		return null;
	}
	
	public static String pAttackPath() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/images/pAttack_L1.gif";
		case 2: return "/resources/images/pAttack_L2.gif";
		case 3: return "/resources/images/pAttack_L3.gif";
		}
		return null;
	}
	
	public static String eImagePath() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/images/enemy_L1.png";
		case 2: return "/resources/images/enemy_L2.png";
		case 3: return "/resources/images/enemy_L3.png";
		}
		return null;
	}
	
	public static String pWeaponSound() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/music/sounds/swordSound.mp3";
		case 2: return "/resources/music/sounds/spearSound.mp3";
		case 3: return "/resources/music/sounds/arrowSound.mp3";
		}
		return null;
	}
	
	public static String eDeathSound() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/music/sounds/goblinDead.mp3";
		case 2: return "/resources/music/sounds/banditDead.wav";
		case 3: return "/resources/music/sounds/dragonDead.mp3";
		}
		return null;
	}
	
	
	public static String bgMusicPath() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/music/bgMusic1.mp3";
		case 2: return "/resources/music/bgMusic2.mp3";
		case 3: return "/resources/music/bgMusic3.mp3";
		}
		return null;
	}
	
	public static String gameMap() { 
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return "/resources/bg01.png";
		case 2: return "/resources/bg02.png";
		case 3: return "/resources/bg03.png";
		}
		return null;
	}
	
	public static int getEnemies() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return 8; 
		case 2: return 12;
		case 3: return 16;
		default: return 0;
		}
	}
	
	public static double setEnemyDMG() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return 0.002; 
		case 2: return 0.005;
		case 3: return 0.008;
		default: return 0;
		}
	}
	
	public static double setPlayerDMG() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return 0.4; 
		case 2: return 0.3;
		case 3: return 0.2;
		default: return 0;
		}
	}
	
	public static double setPlayerREGEN() {
		switch(FirstLevel.CURRENT_LEVEL) {
		case 1: return 0.001; 
		case 2: return 0.005;
		case 3: return 0.010;
		default: return 0;
		}
	}
}
