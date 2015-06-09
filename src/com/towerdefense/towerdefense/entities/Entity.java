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

	@Override
	public void dropHealth(int amount) {
		// TODO Auto-generated method stub

	}

	public int getCooldown() {
		return cooldown;
	}

	public int getDamageValue() {
		return damageValue;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	public int getIdentifier() {
		return identifier;
	}

	public Image getImage() {
		return image;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public EntityType getType() {
		return type;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}

	@Override
	public void setHealth(int amount) {
		health = amount;

	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public void setType(EntityType type) {
		this.type = type;
	}
}
