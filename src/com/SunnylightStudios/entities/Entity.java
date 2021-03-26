package com.SunnylightStudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Collider;
import com.SunnylightStudios.controls.Visibility;
import com.SunnylightStudios.main.Game;
import com.SunnylightStudios.world.Camera;

public class Entity {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	public Collider collider;
	public Visibility VISIBLE;
	
	public static BufferedImage SABER = Game.spritesheetItems.getSprite(128, 0, 32, 32);
	public static BufferedImage LASER_GUN = Game.spritesheetItems.getSprite(160, 0, 32, 32);
	public static BufferedImage LIFEPACK_HEART = Game.spritesheetItems.getSprite(96, 0, 32, 32);
	public static BufferedImage COCACOLA = Game.spritesheetItems.getSprite(0, 0, 32, 32);
	public static BufferedImage GOLD = Game.spritesheetItems.getSprite(32, 0, 32, 32);
	public static BufferedImage CROSS = Game.spritesheetItems.getSprite(256, 0, 32, 32);
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setSprite(sprite);
	}
	
	public Entity(int x, int y, BufferedImage sprite) {
		this.setX(x);
		this.setY(y);
		this.setSprite(sprite);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	public void tick() {
		
	}
	
	// Getters & Setters
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
}
