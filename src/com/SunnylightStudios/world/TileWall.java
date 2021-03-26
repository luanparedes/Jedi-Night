package com.SunnylightStudios.world;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Collider;
import com.SunnylightStudios.controls.Visibility;

public class TileWall extends Tile{
	
	public TileWall(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, sprite);
		this.collider = new Collider(this.x, this.y, width, height);
		this.VISIBLE = Visibility.CONTACT_TILE;
	}
}
