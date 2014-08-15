package com.museti.ld27.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;
	public static int w, h;

	public static SpriteSheet special = new SpriteSheet("/sprites/special.png", 64);
	public static SpriteSheet sprites = new SpriteSheet("/sprites/sprites.png", 128);

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public int getPixel(int x, int y) {
		if (x < 0 || x >= w || y < 0 || y >= h)
			return 0xFF000000;
		return pixels[x + y * w];
	}

	private void load() {
		try {
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			w = img.getWidth();
			h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
