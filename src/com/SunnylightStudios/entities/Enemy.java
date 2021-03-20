package com.SunnylightStudios.entities;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.main.Game;

public class Enemy extends Entity {
	
	public static BufferedImage ENEMY_BAT = Game.spritesheetEnemyBat.getSprite(0, 0, 32, 32);
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public Enemy(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

}
