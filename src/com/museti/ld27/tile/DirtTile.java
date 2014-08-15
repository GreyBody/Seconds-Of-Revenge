package com.museti.ld27.tile;

import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;

public class DirtTile extends Tile {

	public DirtTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, this);
	}

	public boolean solid() {
		return false;
	}
}
