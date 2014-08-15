package com.museti.ld27.entity.mob;

import com.museti.ld27.Config;
import com.museti.ld27.Game;
import com.museti.ld27.graphics.Screen;
import com.museti.ld27.graphics.Sprite;
import com.museti.ld27.input.Keyboard;
import com.museti.ld27.input.Sound;
import com.museti.ld27.level.Level;
import com.museti.ld27.tile.Tile;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private Tile tile;
	private Tile objectTile;
	public int health = 0;
	public int money = 0;
	public boolean dead = false;

	public Player(Level level, Keyboard input) {
		super(level);
		this.input = input;
		sprite = Sprite.player_a;
		Config.playerHealth = 100;
		Config.playerAmmo = 10000;
	}

	public Player(Level level, int x, int y, Keyboard input) {
		super(level);
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_a;
		Config.playerHealth = 100;
		Config.playerAmmo = 10000;
	}

	int fireR = 1, fireY = 0;

	public void update() {
		tile = level.getTile((this.x) >> 4, (this.y) >> 4);
		objectTile = level.getObjectTile((this.x) >> 4, (this.y) >> 4);
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		Config.x = this.x;
		Config.y = this.y;

		if (input.up && !input.left && !input.right && !dead)
			ya--;
		if (input.down && !input.left && !input.right && !dead)
			ya++;
		if (input.left && !input.up && !input.down && !dead)
			xa--;
		if (input.right && !input.up && !input.down && !dead)
			xa++;

		if (input.fire && Config.playerAmmo >= 1) {
			if (fireY >= fireR) {
				Game.spawn_bullet(this.x, this.y, this.dir, 0);
				Sound.playSound();
				Config.playerAmmo -= 200;
				fireR = 10;
				fireY = 0;
			} else {
				fireY++;
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		if (anim % 20 > 18) {
			if (tile.applyDamage()) {
				checkHealth();
			}
		}
	}

	int flip = 0;

	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_a;
		}
		if (dir == 1) {
			sprite = Sprite.player_b;
		}
		if (dir == 2) {
			sprite = Sprite.player_a;
			flip = 2;
		}
		if (dir == 3) {
			sprite = Sprite.player_b;
			flip = 1;
		}
		screen.renderPlayer(x, y, sprite, flip);
	}

	public void renderOver(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_a;
		}
		if (dir == 1) {
			sprite = Sprite.player_b;
		}
		if (dir == 2) {
			sprite = Sprite.player_a;
			flip = 2;
		}
		if (dir == 3) {
			sprite = Sprite.player_b;
			flip = 1;
		}
		screen.renderOverPlayer(x, y, sprite, flip);
	}

	public void renderHealth(Screen screen) {
		// screen.drawHP(Sprite.health_alive, getHealth(), 220, 0, 5);
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

	public void killPlayer(Screen screen) {
		if (getHealth() <= 0) {
			// screen.drawDeath(Sprite.sword_crys, 62, 62);
			dead = true;
			return;
		}
	}

	public void checkHealth() {
		if (getHealth() <= 0) {
			return;
		}
		int dmg = tile.damage();
		health -= dmg;
		System.out.println("Damage Applied: " + dmg + " Health: " + getHealth());
	}

	public void teleport(Level lvl, int realmId, int newX, int newY) {
		this.x = newX * 16;
		this.y = newY * 16;
		this.level = lvl;
		System.out.println("Teleport Initiated - X: " + newX + " Y: " + newY + " Realm: " + realmId);
	}

	public void setPlayerGfx(Screen screen, int xc, int yc) {
	}

	public int getHealth() {
		return Config.playerHealth;
	}
}