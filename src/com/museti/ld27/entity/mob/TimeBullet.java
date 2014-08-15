package com.museti.ld27.entity.mob;

import com.museti.ld27.Config;
import com.museti.ld27.Game;
import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.level.Level;

public class TimeBullet extends Mob {

	private boolean moving = false;
	public int flip = -1;
	public int flipr = -1;
	public int typeFired = -1;

	public TimeBullet(Level level, int x, int y, int flip, int typeFired) {
		super(level);
		this.x = x;
		this.y = y;
		this.flip = flip;
		this.typeFired = typeFired;
		this.health = 100;
		this.isBullet = true;
	}

	int fireR = 5, fireO = 0, hR = 850, hO = 0;

	public void update() {
		int xa = 0, ya = 0;
		if (hO >= hR)
			this.health = -1;
		else
			hO++;
		if (fireO >= fireR) {
			if (flip == 0) {
				ya--;
			}
			if (flip == 1)
				xa++;
			if (flip == 2)
				ya++;
			if (flip == 3) {
				xa--;
				// flipr = 1;
			}
			fireO = 0;
		} else {
			fireO++;
		}
		for (int i = 0; i < Game.mobs.size(); i++) {
			if (Game.mobs.get(i).isEnemy && typeFired == 0) {
				if (Game.mobs.get(i).x == this.x + 15 && this.y <= Game.mobs.get(i).y + 31 && this.y >= Game.mobs.get(i).y - 16) {
					Game.mobs.get(i).health -= 50;
					this.health = -1;
				}
				if (Game.mobs.get(i).x + 15 == this.x && this.y <= Game.mobs.get(i).y + 31 && this.y >= Game.mobs.get(i).y - 16) {
					Game.mobs.get(i).health -= 50;
					this.health = -1;
				}
				if (Game.mobs.get(i).y == this.y + 15 && this.x >= Game.mobs.get(i).x - 16 && this.x <= Game.mobs.get(i).x + 31) {
					Game.mobs.get(i).health -= 50;
					this.health = -1;
				}
				if (Game.mobs.get(i).y + 15 == this.y && this.x >= Game.mobs.get(i).x - 16 && this.x <= Game.mobs.get(i).x + 31) {
					Game.mobs.get(i).health -= 50;
					this.health = -1;
				}
			}
		}
		if (typeFired == 1) {
			if (!Config.debug) {
				if (Config.x >= this.x && Config.x <= this.x + 15 && Config.y >= this.y && Config.y <= this.y + 15) {
					Config.playerHealth -= 10;
					this.health = -1;
				}
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			moving = true;
		} else {
			moving = false;
		}
	}

	public void render(Screen screen) {
		if (flip == 0)
			flipr = 4;
		if (flip == 3)
			flipr = 1;

		screen.renderNPC(Sprite.timeBullet_, x, y, flipr);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 15;
		int yMin = 0;
		int yMax = 15;
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMin)) {
				this.health = -1;
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolid(input, xa, ya, x, yMax)) {
				this.health = -1;
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMin, y)) {
				this.health = -1;
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolid(input, xa, ya, xMax, y)) {
				this.health = -1;
				return true;
			}
		}
		return false;
	}
}
