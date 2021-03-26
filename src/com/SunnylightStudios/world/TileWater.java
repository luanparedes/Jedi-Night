package com.SunnylightStudios.world;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Collider;
import com.SunnylightStudios.controls.Visibility;

public class TileWater extends Tile {

	public Collider collider;
	
	public TileWater(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		this.collider = new Collider(this.x, this.y);
		this.VISIBLE = Visibility.WATER_TILE;
	}
}
