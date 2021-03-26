package com.SunnylightStudios.entities;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Collider;
import com.SunnylightStudios.controls.Visibility;

public class EntityItems extends Entity {
	
	public EntityItems(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.collider = new Collider(x, y, width, height);
		
		this.VISIBLE = Visibility.ITEMS_TILE;
	}

}
