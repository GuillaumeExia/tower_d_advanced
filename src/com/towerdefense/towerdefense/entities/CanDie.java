package com.towerdefense.towerdefense.entities;

public interface CanDie {

	public void die();

	public void dropHealth(final int amount);

	public int getHealth();

	public void setHealth(final int amount);

}
