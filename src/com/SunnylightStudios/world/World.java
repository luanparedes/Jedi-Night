package com.SunnylightStudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	private Tile[] tiles;
	public static  int WIDTH, HEIGHT;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			int[] pixels = new int[WIDTH * HEIGHT]; //Instance of BufferedImage has a method to know the size.
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					switch(pixels[xx + (yy * WIDTH)]) {
					case 0xff000000:
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
						break;
					case 0xffffffff:
						tiles[xx + (yy * WIDTH)] = new TileWall(xx * 32, yy *  32, TileFloor.TILE_ROCK);
						break;
					case 0xff00ff00:
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
						break;
					case 0xff001fff:
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
						break;
					case 0xffff0000:
						tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
						break;
					default:
							System.out.println("Chão");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}