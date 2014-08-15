package com.museti.ld27.entity.mob;

import com.museti.ld27.Config;
import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.level.Level;

public class AmmoCrate extends Mob {

	public AmmoCrate(Level level, int x, int y, int ammo) {
		super(level);
		this.x = x;
		this.y = y;
		this.ammo = ammo;
		this.isAmmo = true;
	}

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderNPC(Sprite.ammo_crate, x, y, 0);
	}

	public void doAction() {
		Config.playerAmmo += this.ammo;
		this.ammo = -1;
	}
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 15;
		int yMin = 0;
		int yMax = 15;
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}
}
