package com.towerdefense.towerdefense.entities.towers;


public class TowerFactory {

	public static final int TOWER_TYPE_AMOUNT = 4;

	public static Tower createTower(int type, int x, int y) {
		if (type == WaterCoolerTower.TOWER_IDENTIFIER) {
			return new WaterCoolerTower(x, y);
		} else if (type == FireWallTower.TOWER_IDENTIFIER) {
			return new FireWallTower(x, y);
		} else if (type == VPNTower.TOWER_IDENTIFIER) {
			return new VPNTower(x, y);
		} else if (type == PatcherTower.TOWER_IDENTIFIER) {
			return new PatcherTower(x, y);
		} else {
			return null;
		}
	}
}
