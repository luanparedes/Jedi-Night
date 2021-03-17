package com.SunnylightStudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setSprite(sprite);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
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
