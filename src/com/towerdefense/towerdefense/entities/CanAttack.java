package com.towerdefense.towerdefense.entities;

import java.util.ArrayList;

import com.towerdefense.towerdefense.entities.towers.Tower;

public interface CanAttack {

	public void attack(ArrayList<Tower> towers);

}
