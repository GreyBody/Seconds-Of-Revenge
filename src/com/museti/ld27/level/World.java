package com.museti.ld27.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.tile.Tile;


public class World extends Level {

	private int anim = 0;
	private Tile tile;

	public World(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage img = ImageIO.read(World.class.getResource(path));
			int w = width = img.getWidth();
			int h = height = img.getHeight();
			tiles = new int[w * h];
			img.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file!");
		}
	}

	protected void generateLevel() {
	}

	public void update() {
		/*if (anim < 7500)
			anim++;
		else
			anim = 0;
		
		if (anim % 50 > 35) {
			tile = Tile.lava_anim_a;
		} else {
			tile = Tile.lava_anim_b;
		}
		Tile.lava = tile;*/
	}
}