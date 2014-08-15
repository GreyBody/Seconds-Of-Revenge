package com.museti.ld27.graphics;

import com.museti.ld27.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;

	public final int MAP_SiZE = 16;
	public final int MAP_SiZE_MASK = MAP_SiZE - 1;
	public final int MAP_TILE = 16;
	public int[] tiles = new int[MAP_SiZE * MAP_SiZE];
	public SpriteSheet font;
	public static int nX, nY;

	public int xOffset, yOffset;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		font = new SpriteSheet("/sprites/font.png", 96);
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderTile(int xP, int yP, Tile tile) {
		xP -= xOffset;
		yP -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yP;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xP;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 8; y < 16; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 3) {
				ys = 15 - y;
			}
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = 15 - x;
				}
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * 16];
				nX = xa;
				nY = ya;
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderOverPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 3) {
				ys = 15 - y;
			}
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = 15 - x;
				}
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				// drawText(name, (xp+16)-(3*name.length()), yp-5, 0xFFFFFFFF);
				int col = sprite.pixels[xs + ys * 16];
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderNPC(Sprite sprite, int xp, int yp, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 3 || flip == 4) {
				ys = 15 - y;
			}
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = 15 - x;
				}
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * 16];
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderOverlay(int xp, int yp, Tile tile, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = tile.overlay.pixels[xs + ys * 16];
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderTopLayer(int xp, int yp, Tile tile, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = tile.overlay.pixels[xs + ys * 16];
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void drawTextDialog(String l1, String l2, String l3, String l4, String l5, int c1, int c2, int c3, int c4, int c5) {
		for (int y = 126; y < 171; y++) {
			for (int x = 5; x < 295; x++) {
				if (x < 0 || x >= width || y < 0 || y >= height)
					break;
				pixels[x + y * width] = 0xFF000000;
			}
		}
		for (int y = 125; y < 126; y++) {
			for (int x = 5; x < 295; x++) {
				if (x < 0 || x >= width || y < 0 || y >= height)
					break;
				pixels[x + y * width] = 0xFFFFFFFF;
			}
		}
		for (int y = 171; y < 172; y++) {
			for (int x = 5; x < 295; x++) {
				if (x < 0 || x >= width || y < 0 || y >= height)
					break;
				pixels[x + y * width] = 0xFFFFFFFF;
			}
		}
		for (int y = 125; y < 172; y++) {
			for (int x = 4; x < 5; x++) {
				if (x < 0 || x >= width || y < 0 || y >= height)
					break;
				pixels[x + y * width] = 0xFFFFFFFF;
			}
		}
		for (int y = 125; y < 172; y++) {
			for (int x = 295; x < 296; x++) {
				if (x < 0 || x >= width || y < 0 || y >= height)
					break;
				pixels[x + y * width] = 0xFFFFFFFF;
			}
		}
		if (l1.length() <= 47) {
			drawText(l1, 8, 130, c1);
		}
		if (l2.length() <= 47) {
			drawText(l2, 8, 138, c2);
		}
		if (l3.length() <= 47) {
			drawText(l3, 8, 146, c3);
		}
		if (l4.length() <= 47) {
			drawText(l4, 8, 154, c4);
		}
		if (l5.length() <= 47) {
			drawText(l5, 8, 162, c5);
		}
	}

	public void drawText(String text, int x, int y, int color) {
		if (text == "" || text == null)
			return;
		int topX = x;
		int topY = y;
		int tileX = 0;
		int tileY = 0;

		for (int i = 0; i < text.length(); i++) {
			int letter = text.charAt(i);
			tileX = letter % 16;
			tileY = letter / 16;
			drawLetter(topX, topY, tileX, tileY, color);
			topX += 6;
			if (text.charAt(i) == '\n') {
				topX = x;
				topY += 6;
			}
		}
	}

	public void drawLetter(int x, int y, int tileX, int tileY, int color) {
		for (int x0 = 0; x0 < 5; x0++) {
			for (int y0 = 0; y0 < 5; y0++) {
				int col = font.getPixel(tileX * 6 + x0, tileY * 6 + y0);
				if (col != 0xFFB200FF && col != 0xFFFF00DC)
					drawPixel(x + x0, y + y0, color);
			}
		}
	}

	public void drawPixel(int x, int y, int color) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return;
		pixels[x + y * width] = color;
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void drawSprite(int x, int y, int xOffset, int yOffset) {
	}
}
