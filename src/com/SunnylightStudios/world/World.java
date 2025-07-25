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

public class World 
{
	public static Tile[] tiles;
	public static List<TileWall> wallTiles;
	public static List<TileWater> waterTiles;
	public static List<TileFloat> floatTiles;
	public static int[] pixels;
	public static  int WIDTH, HEIGHT;
	public static int TILE_SIZE = 32;

	public World(String path) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			pixels = new int[WIDTH * HEIGHT]; //Instance of BufferedImage has a method to know the size.
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			
			tiles = new Tile[WIDTH * HEIGHT];
			wallTiles = new ArrayList<TileWall>();
			waterTiles = new ArrayList<TileWater>();
			floatTiles = new ArrayList<TileFloat>();

			for(int xx = 0; xx < WIDTH; xx++)
			{
				for(int yy = 0; yy < HEIGHT; yy++) 
				{
					switch(pixels[xx + (yy * WIDTH)]) 
					{
						case MapColor.BLACK_1:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							break;
						case MapColor.YELLOW_1:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							wallTiles.add(new TileWall(xx * 32, yy * 32, 30, 30, TileFloor.TILE_ROCK));
							break;
						case MapColor.YELLOW_2:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							wallTiles.add(new TileWall(xx * 32, yy * 32, 30, 30, TileWall.TILE_BARRIL));
							break;
						case MapColor.BLUE_1:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.COCACOLA));
							break;
						case MapColor.GREEN_1:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.entities.add(Game.player1);
							Game.player1.setX(xx * 32);
							Game.player1.setY(yy * 32);
							break;
						case MapColor.RED_1:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.entities.add(new Enemy(xx * 32, yy * 32, Enemy.ENEMY_BAT));
							break;
						case MapColor.BLUE_2:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.items.add(new Weapon(xx * 32, yy *  32, 32, 32, Weapon.SABER));
							break;
						case MapColor.BLUE_3:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.GOLD));
							break;
						case MapColor.BLUE_4:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.LIFEPACK_HEART));
							break;
						case MapColor.BLUE_5:
							tiles[xx + (yy * WIDTH)] = new TileFloor(xx * 32, yy *  32, TileFloor.TILE_GRASS);
							Game.items.add(new EntityItems(xx * 32, yy *  32, 32, 32, EntityItems.CROSS));
							break;
						default:
							System.out.println("Nothing");
					}
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xMap, int yMap) 
	{
		int x1 = xMap / TILE_SIZE;
		int y1 = yMap / TILE_SIZE;
		
		int x2 = (xMap + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yMap / TILE_SIZE;
		
		int x3 = xMap / TILE_SIZE;
		int y3 = (yMap + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xMap + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yMap + TILE_SIZE - 1) / TILE_SIZE;
		
		return !(pixels[x1 + (y1 * WIDTH)] == MapColor.YELLOW_1 || pixels[x1 + (y1 * WIDTH)] == MapColor.YELLOW_2 ||
				 pixels[x2 + (y2 * WIDTH)] == MapColor.YELLOW_1 || pixels[x2 + (y2 * WIDTH)] == MapColor.YELLOW_2 ||
				 pixels[x3 + (y3 * WIDTH)] == MapColor.YELLOW_1 || pixels[x3 + (y3 * WIDTH)] == MapColor.YELLOW_2 ||
				 pixels[x4 + (y4 * WIDTH)] == MapColor.YELLOW_1 || pixels[x4 + (y4 * WIDTH)] == MapColor.YELLOW_2);
	}
		
	public static void renderWater(Graphics g) 
	{
		for(TileWater tile : waterTiles) 
		{
			tile.render(g);
		}
	}
	
	public static void renderFloor(Graphics g) 
	{
		int xStart = Camera.x / 32;
		int yStart = Camera.y / 32;
		int xFinal = xStart + (Game.WIDTH / 32);
		int yFinal = yStart + (Game.HEIGHT / 32);
		
		for(int xx = xStart; xx <= xFinal; xx++) 
		{
			for(int yy = yStart; yy <= yFinal; yy++) 
			{
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) 
				{
					continue;
				}
				
				Tile tile = tiles[xx + (yy * WIDTH)];
				
				if(tile instanceof TileFloor) 
				{
					tile.render(g);
				}		
			}
		}
	}
	
	public static void renderColliderTiles(Graphics g) 
	{
		for(TileWall tile : wallTiles) 
		{
			tile.render(g);
		}
	}
	
	public static void renderFloats(Graphics g) 
	{
		for(TileFloat tile : floatTiles) 
		{
			tile.render(g);
		}
	}
}
