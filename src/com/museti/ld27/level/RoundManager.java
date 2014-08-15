package com.museti.ld27.level;

import com.museti.ld27.Config;
import com.museti.ld27.Game;

public class RoundManager {

	public int round;
	public boolean r1 = true, r2 = false, r3 = false, r4 = false, r5 = false;

	public RoundManager(int round) {
		this.round = round;
	}

	int rT = 1000, rA = 0;

	public void roundHandler() {
		if (Config.enemies < 0 && round == 5) {
			Config.enemies = -1;
			round = 6;
		}
		if (Config.enemies < 0 && round == 4) {
			Config.enemies = -1;
			round = 5;
		}
		if (Config.enemies < 0 && round == 3) {
			Config.enemies = -1;
			round = 4;
		}
		if (Config.enemies < 0 && round == 2) {
			Config.enemies = -1;
			round = 3;
		}
		if (Config.enemies < 0 && round < 2) {
			Config.enemies = -1;
			round = 2;
		}
		if (round == 1 && !r1) {
			round1();
			r1 = true;
			rA = 0;
		}
		if (round == 2 && !r2) {
			round2();
			r2 = true;
			rA = 0;
		}
		if (round == 3 && !r3) {
			r3 = true;
		}
		if (round == 4 && !r4) {
			r4 = true;
		}
		if (round == 5 && !r5) {
			r5 = true;
		}
		rA++;
	}

	public void round1() {
		Game.spawn_enemySoldier(6, 13);
		Game.spawn_enemySoldier(3, 5);
		Game.spawn_enemySoldier(9, 22);
		Game.spawn_enemySoldier(24, 23);
		Game.spawn_enemySoldier(25, 7);
		Game.spawn_HealthCrate(3, 28);
		Game.spawn_HealthCrate(28, 2);
		Game.spawn_AmmoCrate(6, 7);
		Game.spawn_AmmoCrate(0, 14);
	}

	public void round2() {
		Game.spawn_enemySoldier(11, 10);
		Game.spawn_enemySoldier(3, 24);
		Game.spawn_enemySoldier(12, 22);
		Game.spawn_enemySoldier(20, 18);
		Game.spawn_enemySoldier(20, 11);
		Game.spawn_enemySoldier(8, 7);
		Game.spawn_HealthCrate(3, 28);
		Game.spawn_HealthCrate(28, 2);
		Game.spawn_HealthCrate(5, 16);
		Game.spawn_AmmoCrate(6, 7);
		Game.spawn_AmmoCrate(0, 14);
	}

	public void round3() {
		Game.spawn_enemySoldier(11, 10);
		Game.spawn_enemySoldier(3, 24);
		Game.spawn_enemySoldier(12, 22);
		Game.spawn_enemySoldier(20, 18);
		Game.spawn_enemySoldier(20, 11);
		Game.spawn_enemySoldier(8, 7);
		Game.spawn_HealthCrate(3, 28);
		Game.spawn_HealthCrate(28, 2);
		Game.spawn_HealthCrate(5, 16);
		Game.spawn_HealthCrate(19, 14);
		Game.spawn_AmmoCrate(6, 7);
		Game.spawn_AmmoCrate(0, 14);
	}

	public void round4() {
		Config.level = round;
	}

	public void round5() {
	}
}
