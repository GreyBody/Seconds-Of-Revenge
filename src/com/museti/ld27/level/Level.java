package com.museti.ld27.level;

import com.museti.ld27.graphics.Screen;
import com.museti.ld27.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level world = new World("/world/world.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		genRanLevel();
	}

	public Level(String path) {
		loadLevel(path);
		genRanLevel();
	}

	protected void genRanLevel() {
	}

	protected void loadLevel(String path) {
	}

	public void update() {
	}

	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderObject(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getObjectTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderOver(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getOverTile(x, y).render(x, y, screen);
			}
		}
	}

	public void renderTop(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTopTile(x, y).render(x, y, screen);
			}
		}
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)// Void Tile
			return Tile.voidTile;

		if (tiles[x + y * width] == 0xFF7F3300)// Dirt Tile
			return Tile.dirt;
		
		if (tiles[x + y * width] == 0xFF404040)// Wall Top Left Corner
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3F3F3F)// Wall Top Middle
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3E3E3E)// Wall Top Right Corner
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3D3D3D)// Wall Left Middle
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3C3C3C)// Wall Right Middle
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3B3B3B)// Wall Bottom Left Corner
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF3A3A3A)// Wall Bottom Middle
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF393939)// Wall Bottom Right Corner
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF383838)// Wall Right Filled Only
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF373737)// Wall Bottom Filled Only
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF363636)// Wall Top Filled Only
			return Tile.dirt;
		if (tiles[x + y * width] == 0xFF353535)// Wall Left Filled Only
			return Tile.dirt;
		return Tile.voidTile;
	}

	public Tile getObjectTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)// Void Tile
			return Tile.voidTile;

		if (tiles[x + y * width] == 0xFF404040)// Wall Top Left Corner
			return Tile.wall_tlc;
		if (tiles[x + y * width] == 0xFF3F3F3F)// Wall Top Middle
			return Tile.wall_tm;
		if (tiles[x + y * width] == 0xFF3E3E3E)// Wall Top Right Corner
			return Tile.wall_trc;
		if (tiles[x + y * width] == 0xFF3D3D3D)// Wall Left Middle
			return Tile.wall_lm;
		if (tiles[x + y * width] == 0xFF3C3C3C)// Wall Right Middle
			return Tile.wall_rm;
		if (tiles[x + y * width] == 0xFF3B3B3B)// Wall Bottom Left Corner
			return Tile.wall_blc;
		if (tiles[x + y * width] == 0xFF3A3A3A)// Wall Bottom Middle
			return Tile.wall_bm;
		if (tiles[x + y * width] == 0xFF393939)// Wall Bottom Right Corner
			return Tile.wall_brc;
		if (tiles[x + y * width] == 0xFF383838)// Wall Right Filled Only
			return Tile.wall_rightF;
		if (tiles[x + y * width] == 0xFF373737)// Wall Bottom Filled Only
			return Tile.wall_bottomF;
		if (tiles[x + y * width] == 0xFF363636)// Wall Top Filled Only
			return Tile.wall_topF;
		if (tiles[x + y * width] == 0xFF353535)// Wall Left Filled Only
			return Tile.wall_leftF;
		return Tile.trans;
	}

	public Tile getOverTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)// Void Tile
			return Tile.voidTile;
		return Tile.trans;
	}

	public Tile getTopTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF000000)// Void Tile
			return Tile.voidTile;
		return Tile.trans;
	}
}
