package com.museti.ld27.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.museti.ld27.Config;


public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right,fire;

	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		fire = keys[KeyEvent.VK_SPACE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
