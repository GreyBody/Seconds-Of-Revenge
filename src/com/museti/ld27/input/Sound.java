package com.museti.ld27.input;

import java.awt.Frame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static synchronized void playSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputs = AudioSystem.getAudioInputStream(Frame.class.getResourceAsStream("/audio/gun.wav"));
					clip.open(inputs);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
}
