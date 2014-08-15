package com.museti.ld27.tile;

import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	public Sprite object;
	public Sprite overlay;
	public Sprite top;

	public static Tile voidTile = new VoidTile(Sprite.trans);
	public static Tile trans = new TransTile(Sprite.trans);
	
	public static Tile dirt = new DirtTile(Sprite.dirt);
	public static Tile wall_tlc = new VoidTile(Sprite.wall_ltcorner);
	public static Tile wall_tm = new VoidTile(Sprite.wall_tmid);
	public static Tile wall_trc = new VoidTile(Sprite.wall_rtcorner);
	public static Tile wall_lm = new VoidTile(Sprite.wall_lmid);
	public static Tile wall_rm = new VoidTile(Sprite.wall_rmid);
	public static Tile wall_blc = new VoidTile(Sprite.wall_lbcorner);
	public static Tile wall_bm = new VoidTile(Sprite.wall_bmid);
	public static Tile wall_brc = new VoidTile(Sprite.wall_rbcorner);
	public static Tile wall_rightF = new VoidTile(Sprite.wall_rightF);
	public static Tile wall_bottomF = new VoidTile(Sprite.wall_bottomF);
	public static Tile wall_topF = new VoidTile(Sprite.wall_topF);
	public static Tile wall_leftF = new VoidTile(Sprite.wall_leftF);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public Tile(Sprite sprite, Sprite overlay) {
		this.sprite = sprite;
		this.overlay = overlay;
	}
	
	public Tile(Sprite sprite, Sprite object, Sprite overlay) {
		this.sprite = sprite;
		this.object = object;
		this.overlay = overlay;
	}
	
	public Tile(Sprite sprite, Sprite object, Sprite overlay, Sprite top) {
		this.sprite = sprite;
		this.object = object;
		this.overlay = overlay;
		this.top = top;
	}

	public void render(int x, int y, Screen screen) {
	}
	
	public void renderObject(int x, int y, Screen screen) {
	}

	public void renderOver(int x, int y, Screen screen) {
	}
	
	public void renderTop(int x, int y, Screen screen) {
	}
	
	public boolean teleportEnabled() {
		return false;
	}
	
	public int teleportX() {
		return 0;
	}
	
	public int teleportY() {
		return 0;
	}
	
	public boolean solid() {
		return false;
	}

	public boolean interact() {
		return false;
	}
	
	public boolean water() {
		return false;
	}
	
	public boolean applyDamage() {
		return false;
	}
	
	public int damage() {
		return 0;
	}
	
	public boolean realm() {
		return false;
	}
	
	public boolean realmTele() {
		return false;
	}
}
