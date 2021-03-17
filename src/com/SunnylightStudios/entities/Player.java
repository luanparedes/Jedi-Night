package com.SunnylightStudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.SunnylightStudios.main.Game;

public class Player extends Entity{

	private boolean left, up, right, down, moved;
	private int speed = 2;
	private int frames = 0;
	private int maxFrames = 12;
	private int index = 0;
	private int maxIndex = 3;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage lastImage;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		createPlayerSprites();
	}
	
	@Override
	public void render(Graphics g) {
			if(moved == false) {
				g.drawImage(lastImage, this.getX(), this.getY(), null);
			}	
			if(right) {
				g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
				lastImage = rightPlayer[0];
			}
			else if(left) {
				g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
				lastImage = leftPlayer[0];
			}
			if(up) {
				g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
				lastImage = upPlayer[0];
			}
			else if(down) {
				g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
				lastImage = downPlayer[0];
			}
		}
	
	@Override
	public void tick() {
		moved = false;
		
		if(left) {
			moved = true;
			this.setX(this.getX() - this.getSpeed());
		}
		else if(right) {
			moved = true;
			this.setX(this.getX() + this.getSpeed());
		}
		if(up) {
			moved = true;
			this.setY(this.getY() - this.getSpeed());
		}
		else if(down) {
			moved = true;
			this.setY(this.getY() + this.getSpeed());
		}
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	private void createPlayerSprites() {
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		downPlayer[0] = Game.spritesheetPlayer1.getSprite(0, 0, 32, 48);
		downPlayer[1] = Game.spritesheetPlayer1.getSprite(32, 0, 32, 48);
		downPlayer[2] = Game.spritesheetPlayer1.getSprite(64, 0, 32, 48);
		downPlayer[3] = Game.spritesheetPlayer1.getSprite(96, 0, 32, 48);
		
		leftPlayer[0] = Game.spritesheetPlayer1.getSprite(0, 48, 32, 48);
		leftPlayer[1] = Game.spritesheetPlayer1.getSprite(32, 48, 32, 48);
		leftPlayer[2] = Game.spritesheetPlayer1.getSprite(64, 48, 32, 48);
		leftPlayer[3] = Game.spritesheetPlayer1.getSprite(96, 48, 32, 48);
		
		rightPlayer[0] = Game.spritesheetPlayer1.getSprite(0, 96, 32, 48);
		rightPlayer[1] = Game.spritesheetPlayer1.getSprite(32, 96, 32, 48);
		rightPlayer[2] = Game.spritesheetPlayer1.getSprite(64, 96, 32, 48);
		rightPlayer[3] = Game.spritesheetPlayer1.getSprite(96, 96, 32, 48);
		
		upPlayer[0] = Game.spritesheetPlayer1.getSprite(0, 144, 32, 48);
		upPlayer[1] = Game.spritesheetPlayer1.getSprite(32, 144, 32, 48);
		upPlayer[2] = Game.spritesheetPlayer1.getSprite(64, 144, 32, 48);
		upPlayer[3] = Game.spritesheetPlayer1.getSprite(96, 144, 32, 48);
		
		lastImage = downPlayer[0];
	}
	
	//Getters & Setters
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getMaxFrames() {
		return maxFrames;
	}

	public void setMaxFrames(int maxFrames) {
		this.maxFrames = maxFrames;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public BufferedImage[] getRightPlayer() {
		return rightPlayer;
	}

	public void setRightPlayer(BufferedImage[] rightPlayer) {
		this.rightPlayer = rightPlayer;
	}

	public BufferedImage[] getLeftPlayer() {
		return leftPlayer;
	}

	public void setLeftPlayer(BufferedImage[] leftPlayer) {
		this.leftPlayer = leftPlayer;
	}

	public BufferedImage[] getUpPlayer() {
		return upPlayer;
	}

	public void setUpPlayer(BufferedImage[] upPlayer) {
		this.upPlayer = upPlayer;
	}

	public BufferedImage[] getDownPlayer() {
		return downPlayer;
	}

	public void setDownPlayer(BufferedImage[] downPlayer) {
		this.downPlayer = downPlayer;
	}

	public BufferedImage getLastImage() {
		return lastImage;
	}

	public void setLastImage(BufferedImage lastImage) {
		this.lastImage = lastImage;
	}
}
