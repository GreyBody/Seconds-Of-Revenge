package com.museti.ld27.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int w = SpriteSheet.w, h = SpriteSheet.h;
	public int[] pixels;
	private SpriteSheet sheet;

	// Special Sprites
	public static Sprite voidSprite = new Sprite(16, 0, 0, SpriteSheet.special);
	public static Sprite trans = new Sprite(16, 1, 0, SpriteSheet.special);

	public static Sprite dirt = new Sprite(16, 0, 0, SpriteSheet.sprites);
	public static Sprite wall_ltcorner = new Sprite(16, 1, 0, SpriteSheet.sprites);
	public static Sprite wall_tmid = new Sprite(16, 2, 0, SpriteSheet.sprites);
	public static Sprite wall_rtcorner = new Sprite(16, 3, 0, SpriteSheet.sprites);
	public static Sprite wall_lmid = new Sprite(16, 1, 1, SpriteSheet.sprites);
	public static Sprite wall_rmid = new Sprite(16, 3, 1, SpriteSheet.sprites);
	public static Sprite wall_lbcorner = new Sprite(16, 1, 2, SpriteSheet.sprites);
	public static Sprite wall_bmid = new Sprite(16, 2, 2, SpriteSheet.sprites);
	public static Sprite wall_rbcorner = new Sprite(16, 3, 2, SpriteSheet.sprites);
	public static Sprite wall_rightF = new Sprite(16, 0, 1, SpriteSheet.sprites);
	public static Sprite wall_bottomF = new Sprite(16, 0, 2, SpriteSheet.sprites);
	public static Sprite wall_topF = new Sprite(16, 0, 3, SpriteSheet.sprites);
	public static Sprite wall_leftF = new Sprite(16, 0, 4, SpriteSheet.sprites);

	public static Sprite health_crate = new Sprite(16, 4, 0, SpriteSheet.sprites);
	public static Sprite ammo_crate = new Sprite(16, 4, 1, SpriteSheet.sprites);
	public static Sprite timeBullet_ = new Sprite(16, 5, 0, SpriteSheet.sprites);

	public static Sprite player_a = new Sprite(16, 0, 7, SpriteSheet.sprites);
	public static Sprite player_b = new Sprite(16, 1, 7, SpriteSheet.sprites);
	public static Sprite soldier_a = new Sprite(16, 2, 7, SpriteSheet.sprites);
	public static Sprite soldier_b = new Sprite(16, 3, 7, SpriteSheet.sprites);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
