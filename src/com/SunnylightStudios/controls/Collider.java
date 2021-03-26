package com.SunnylightStudios.controls;

import java.awt.Rectangle;

public class Collider extends Rectangle {
	private static final long serialVersionUID = 1L;

	public Collider(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Collider(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
