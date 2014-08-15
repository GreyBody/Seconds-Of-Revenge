package com.museti.ld27.entity.mob;

import com.museti.ld27.Config;
import com.museti.ld27.Game;
import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.input.Sound;
import com.museti.ld27.level.Level;

public class EnemySoldier extends Mob {

	public int flipr = -1;
	public boolean fi0 = false;
	private boolean walking = false;

	public EnemySoldier(Level level, int x, int y) {
		super(level);
		this.x = x;
		this.y = y;
		this.health = 100;
		this.isEnemy = true;
	}

	int fr = 85, fo = 0;

	public void update() {
		int xa = 0, ya = 0;
		
		if (Config.y >= this.y - (16 * 5) && Config.y <= this.y - 8 && Config.x >= this.x - 16 && Config.x <= this.x + 16) {
			if (fo >= fr) {
				this.dir = 0;
				Game.spawn_bullet(x, y, this.dir, 1);
				Sound.playSound();
				fo = 0;
			} else {
				fo++;
			}
		}
		if (Config.y <= this.y + (16 * 6 - 1) && Config.y >= this.y + 15 && Config.x >= this.x - 16 && Config.x <= this.x + 16) {
			if (fo >= fr) {
				this.dir = 2;
				Game.spawn_bullet(x, y, this.dir, 1);
				Sound.playSound();
				fo = 0;
			} else {
				fo++;
			}
		}
		if (Config.x >= this.x - (16 * 5) && Config.x <= this.x - 8 && Config.y >= this.y - 16 && Config.y <= this.x + 16) {
			if (fo >= fr) {
				this.dir = 3;
				Game.spawn_bullet(x, y, this.dir, 1);
				Sound.playSound();
				fo = 0;
			} else {
				fo++;
			}
		}
		if (Config.x <= this.x + (16 * 6 - 1) && Config.x >= this.x + 15 && Config.y >= this.y - 16 && Config.y <= this.x + 16) {
			if (fo >= fr) {
				this.dir = 1;
				Game.spawn_bullet(x, y, this.dir, 1);
				Sound.playSound();
				fo = 0;
			} else {
				fo++;
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		if (dir == 0 || dir == 2) {
			screen.renderNPC(Sprite.soldier_a, x, y, 0);
		}
		if (dir == 1 || dir == 3) {
			screen.renderNPC(Sprite.soldier_b, x, y, 0);
		}
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
