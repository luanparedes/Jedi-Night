package com.SunnylightStudios.world;

import java.awt.image.BufferedImage;

import com.SunnylightStudios.controls.Visibility;
import com.SunnylightStudios.main.Game;

public class TileFloor extends Tile 
{
	public static BufferedImage TILE_GRASS = Game.spritesheetVillage.getSprite(0, 64, 32, 32);
	public static BufferedImage TILE_ROCK = Game.spritesheetVillage.getSprite(0, 32, 32, 32);

	public TileFloor(int x, int y, BufferedImage sprite)
	{
		super(x, y, sprite);

		this.VISIBLE = Visibility.FLOOR_TILE;
	}
}
