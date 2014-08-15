package com.museti.ld27;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.museti.ld27.entity.mob.AmmoCrate;
import com.museti.ld27.entity.mob.EnemySoldier;
import com.museti.ld27.entity.mob.HealthCrate;
import com.museti.ld27.entity.mob.Mob;
import com.museti.ld27.entity.mob.Player;
import com.museti.ld27.entity.mob.TimeBullet;
import com.museti.ld27.graphics.Screen;
import com.museti.ld27.input.Keyboard;
import com.museti.ld27.input.Mouse;
import com.museti.ld27.level.Level;
import com.museti.ld27.level.RoundManager;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 10;
	public static int scale = 3;

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private static Mouse mouse;
	private static Level level;
	private Player player;
	private RoundManager rm;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public static List<Mob> mobs = new ArrayList<Mob>();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		level = level.world;
		player = new Player(level, 16 * 15, 16 * 18, key);
		Config.level = 1;
		rm = new RoundManager(Config.level);
		addKeyListener(key);
		rm.round1();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (Config.debug)
					frame.setTitle(Config.title + " - " + frames + "fps || Ticks " + updates + " || " + Config.version);
				updates = 0;
				frames = 0;
			}
		}
	}

	int x = 0, y = 0;

	public void update() {
		if (!Config.paused) {
			key.update();
			player.update();
			level.update();
			rm.roundHandler();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;

		level.render(xScroll, yScroll, screen);
		level.renderObject(xScroll, yScroll, screen);
		if(!Config.paused) {
			updateMobObjects();
		}
		player.render(screen);
		level.renderOver(xScroll, yScroll, screen);
		player.renderOver(screen);
		level.renderTop(xScroll, yScroll, screen);

		player.renderHealth(screen);

		if (Config.playerAmmo <= 0) {
			Config.playerAmmo = 0.0;
		}
		if (Config.level > 3) {
			screen.drawTextDialog("Congrats", "You've Won!", "", "", "", 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);
			Config.paused = true;
		}
		if(Config.playerHealth < 1) {
			screen.drawTextDialog("Sorry, but you have lost!", "", "", "", "", 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);
			Config.paused = true;
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.white);
		Font fon = new Font("Arial", 1, 15);
		g.setFont(fon);
		if (Config.debug) {
			g.drawString("Width: " + getWidth() + " - Height: " + getHeight(), 5, 12);
			int xCoord = player.x / 16;
			int yCoord = player.y / 16;
			g.drawString("X: " + xCoord + " Y: " + yCoord, 5, 26);
		}
		double curAmmo = (Config.playerAmmo / 1000);
		g.drawString("Health: " + Config.playerHealth + "/100", 792, 12);
		g.drawString("Time: " + curAmmo + "/10.0", 795, 26);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Config.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);

		game.frame.setVisible(true);
		game.start();
		game.addMouseListener(mouse);
		game.addMouseMotionListener(mouse);
	}

	public void updateMobObjects() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
			mobs.get(i).update();
			if (Config.playerHealth < 100 || Config.playerAmmo < 10000) {
				if (player.x >= mobs.get(i).x && player.x < mobs.get(i).x + 16 && player.y >= mobs.get(i).y && player.y < mobs.get(i).y + 16) {
					mobs.get(i).doAction();
				}
			}
			if (!mobs.get(i).isAlive()) {
				mobs.remove(i);
				i--;
			}
		}
	}

	static int mobCount = -1;

	public static void spawn_HealthCrate(int x, int y) {
		HealthCrate healthc = new HealthCrate(level, (x * 16), (y * 16), 20);
		mobs.add(healthc);
		mobCount++;
		mobs.get(mobCount).setMobId(mobCount);
	}

	public static void spawn_AmmoCrate(int x, int y) {
		AmmoCrate ammoc = new AmmoCrate(level, 16 * x, 16 * y, 1000);
		mobs.add(ammoc);
		mobCount++;
		mobs.get(mobCount).setMobId(mobCount);
	}

	public static void spawn_bullet(int x, int y, int flip, int typeFired) {
		TimeBullet bullet = new TimeBullet(level, x, y, flip, typeFired);
		mobs.add(bullet);
	}

	public static void spawn_enemySoldier(int x, int y) {
		EnemySoldier soldier = new EnemySoldier(level, 16 * x, 16 * y);
		mobs.add(soldier);
		mobCount++;
		Config.enemies++;
		// mobs.get(mobCount).setMobId(mobCount);
	}
}
