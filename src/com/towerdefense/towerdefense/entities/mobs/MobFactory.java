package com.towerdefense.towerdefense.entities.mobs;

public abstract class MobFactory {
	public static final int MOB_TYPE_AMOUNT = 4;

	public static Mob createMob(final int type) {
		if (type == Bug.MOB_IDENTIFIER) {
			return new Bug(0,64);
		} else if (type == ForkBomb.MOB_IDENTIFIER) {
			return new ForkBomb(0,64);
		} else if (type == Virus.MOB_IDENTIFIER) {
			return new Virus(0,64);
		} else if (type == Thunder.MOB_IDENTIFIER) {
			return new Thunder(0,64);
		} else if (type == WebFiltering.MOB_IDENTIFIER) {
			return new WebFiltering(0,64);
		} else {
			return null;
		}
	}

}
