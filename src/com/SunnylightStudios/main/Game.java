package com.SunnylightStudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.SunnylightStudios.entities.Entity;
import com.SunnylightStudios.entities.Player;
import com.SunnylightStudios.graphics.SpriteSheet;
import com.SunnylightStudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private static final int WIDTH = 480;
	private static final int HEIGHT = 360;
	private static final int SCALE = 2;
	
	private BufferedImage image;
	private Graphics g;
	
	//Entities
	public List<Entity> entities;
	public Player player1;
	public static SpriteSheet spritesheetPlayer1;
	public static SpriteSheet spritesheetEnemyBat;
	public static SpriteSheet spritesheetVillage;
	public static SpriteSheet spritesheetItems;
	
	//World
	public static World world;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this); //Which class is gonna receive KeyListener.
		this.initFrame();
		
		//Spritesheets
		spritesheetPlayer1 = new SpriteSheet("/spritesheet_player1.png");	
		spritesheetEnemyBat = new SpriteSheet("/spritesheet_enemy_bat.png");
		spritesheetVillage = new SpriteSheet("/spritesheet_village.png");
		spritesheetItems = new SpriteSheet("/spritesheet_village.png");
		
		//Initializing objects
		world = new World("/map1.png");
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		
		player1 = new Player(0, 0, 30, 48, spritesheetPlayer1.getSprite(0, 0, 32, 50));
		
		entities.add(player1);
	}
	
	public void initFrame() {
		frame = new JFrame("Jedi Nights");
		frame.add(this);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
	public synchronized void start() {
		this.run();
		thread = new Thread();
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
		
	public void update() {
		//TODO
	}
	
	public void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		g = image.getGraphics(); //Render screen images
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		world.render(g);
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e instanceof Player) {
				//Estou dando tick no player
			}
			e.tick();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime(); //Take actual time. Very precisely
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player1.setLeft(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player1.setRight(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player1.setUp(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player1.setDown(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { //When we stop do click keyboard
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player1.setLeft(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player1.setRight(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player1.setUp(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player1.setDown(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

