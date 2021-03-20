package com.SunnylightStudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.SunnylightStudios.entities.Enemy;
import com.SunnylightStudios.entities.EntityItems;
import com.SunnylightStudios.entities.Weapon;
import com.SunnylightStudios.main.Game;

public class World {

	public static List<TileWall> wallTiles;
	public static List<TileFloat> floatTiles;
	public static Tile[][] tiles;
	public static  int WIDTH, HEIGHT;
	public static int TILE_SIZE = 32;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			wallTiles = new ArrayList<TileWall>();
			floatTiles = new ArrayList<TileFloat>();
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			int[] pixels = new int[WIDTH * HEIGHT]; //Instance of BufferedImage has a method to know the size.
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			
			tiles = new Tile[WIDTH][HEIGHT];
					
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					tiles[xx][yy] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
					switch(pixels[xx + (yy * WIDTH)]) {
					case 0xffffffff:
						wallTiles.add(new TileWall(xx * 32, yy * 32, TileFloor.TILE_ROCK));
						break;
					case 0xff00ff00:
						Game.entities.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.COCACOLA));
						break;
					case 0xff001fff:
						Game.entities.add(Game.player1);
						Game.player1.setX(xx * 32);
						Game.player1.setY(yy * 32);
						break;
					case 0xffff0000:
						Game.entities.add(new Enemy(xx * 32, yy * 32, Enemy.ENEMY_BAT));
						break;
					case 0xfff7f119:
						Game.items.add(new Weapon(xx * 32, yy *  32, 32, 32, Weapon.SABER));
						break;
					case 0xff9d6e2d:
						Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.GOLD));
						break;
					case 0xffe329e9:
						floatTiles.add(new TileFloat(xx * 32, yy *  32, EntityItems.LIFEPACK_HEART));
						break;
					case 0xff606589:
						Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.CROSS));
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
	
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = xNext + (TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = yNext + (TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = xNext + (TILE_SIZE -1) / TILE_SIZE;
		int y4 = yNext + (TILE_SIZE - 1) / TILE_SIZE;
		
		return true;
	}
	
	public void renderFloor(Graphics g) {
		int xStart = Camera.x / 32;
		int yStart = Camera.y / 32;
		int xFinal = xStart + (Game.WIDTH / 32);
		int yFinal = yStart + (Game.HEIGHT / 32);
		
		for(int xx = xStart; xx <= xFinal; xx++) {
			for(int yy = yStart; yy <= yFinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx][yy];
				tile.render(g);
			}
		}
	}
	
	public void renderWalls(Graphics g) {
		for(TileWall i : wallTiles) {
			i.render(g);
		}
	}
	
	public void renderFloats(Graphics g) {
		for(TileFloat i : floatTiles) {
			i.render(g);
		}
	}
}
