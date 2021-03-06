package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;

public abstract class MobFactory {
	public static final int MOB_TYPE_AMOUNT = 5;

	public static Mob createMob(final int type) {
		if (type == Bug.MOB_IDENTIFIER) {
			return new Bug(0, 32 * (GlobalVariables.spawnpoint.getY() / 32));
		} else if (type == ForkBomb.MOB_IDENTIFIER) {
			return new ForkBomb(0,
					32 * (GlobalVariables.spawnpoint.getY() / 32));
		} else if (type == Virus.MOB_IDENTIFIER) {
			return new Virus(0, 32 * (GlobalVariables.spawnpoint.getY() / 32));
		} else if (type == Thunder.MOB_IDENTIFIER) {
			return new Thunder(0, 32 * (GlobalVariables.spawnpoint.getY() / 32));
		} else if (type == WebFiltering.MOB_IDENTIFIER) {
			return new WebFiltering(0,
					32 * (GlobalVariables.spawnpoint.getY() / 32));
		} else {
			return null;
		}
	}
}
