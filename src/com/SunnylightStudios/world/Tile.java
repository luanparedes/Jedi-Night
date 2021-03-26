package com.SunnylightStudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Collider;
import com.SunnylightStudios.controls.Visibility;
import com.SunnylightStudios.main.Game;

public class Tile {

	public Visibility VISIBLE;
	public Collider collider;
	
	public static BufferedImage TILE_GRASS = Game.spritesheetVillage.getSprite(0, 64, 32, 32);
	public static BufferedImage TILE_ROCK = Game.spritesheetVillage.getSprite(0, 32, 32, 32);
	public static BufferedImage TILE_BARRIL = Game.spritesheetVillage.getSprite(32, 224, 32, 32);
	
	protected int x, y, width, height;
	private BufferedImage sprite;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
}
