package com.towerdefense.towerdefense.entities;

import java.awt.Image;

public abstract class Entity implements CanDieAttack {
	private int health;
	private int damageValue;
	private int rangeValue;
	private EntityType type;
	private int identifier;
	private int cooldown;
	private Image image;

	public int getIdentifier() {
		return identifier;
	}

	public EntityType getType() {
		return type;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

}
