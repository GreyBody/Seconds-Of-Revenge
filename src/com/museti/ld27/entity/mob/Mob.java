package com.museti.ld27.entity.mob;

import com.museti.ld27.Config;
import com.museti.ld27.entity.Entity;
import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.input.Keyboard;
import com.museti.ld27.level.Level;
import com.museti.ld27.tile.Tile;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected Keyboard input;
	protected int dir = 2;
	protected boolean moving = false;
	protected boolean isAmmo = false;
	protected boolean isBullet = false;
	protected boolean isEnemy = false;
	protected int health = -1;
	protected int mobId = -1;
	protected int ammo = -1;

	/*
	 * Dir 0 - North + Dir 1 - East + Dir 2 - South + Dir 3 - West
	 */

	public Mob(Level level) {
		super(level);
	}

	public void move(int xa, int ya) {
		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if (!hasCollided(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
	}

	public boolean isAlive() {
		if(isEnemy) {
			if(health <= 0) {
				Config.enemies--;
				return false;
			} else {
				return true;
			}
		}
		if (isAmmo) {
			if (ammo <= 0)
				return false;
			else
				return true;
		}
		if (health <= 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isAmmo() {
		if (isBullet)
			return true;
		else
			return false;
	}

	public boolean isEnemy() {
		if (isEnemy)
			return true;
		else
			return false;
	}

	public void setMobId(int id) {
		this.mobId = id;
	}

	public void doAction() {
	}

	private boolean collision() {
		return false;
	}

	public abstract boolean hasCollided(int xa, int ya);

	protected boolean isSolid(Keyboard input, int xa, int ya, int x, int y) {
		if (level == null)
			return false;
		Tile lastUnderTile = level.getTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile lastOverTile = level.getOverTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile lastObjectTile = level.getObjectTile((this.x + x) >> 4, (this.y + y) >> 4);
		Tile newObjectTile = level.getObjectTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		Tile newUnderTile = level.getTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		Tile newOverTile = level.getOverTile((this.x + x + xa) >> 4, (this.y + y + ya) >> 4);
		if (!lastUnderTile.equals(newUnderTile) && newUnderTile.solid()) {
				return true;
		}
		if (!lastObjectTile.equals(newObjectTile) && newObjectTile.solid()) {
			return true;
		}
		if (!lastOverTile.equals(newOverTile) && newOverTile.solid()) {
				return true;
		}
		return false;
	}

	public void render() {
	}
}