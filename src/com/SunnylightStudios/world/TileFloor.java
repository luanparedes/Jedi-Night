package com.SunnylightStudios.world;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Visibility;

public class TileFloor extends Tile {

	public TileFloor(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		
		this.VISIBLE = Visibility.FLOOR_TILE;
	}
}
