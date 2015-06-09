package com.towerdefense.towerdefense.entities;

import java.util.ArrayList;

public interface CanDie {

	public void die(ArrayList<?> list);

	public void dropHealth(ArrayList<?> list, int amount);

	public int getHealth();

	public void setHealth(final int amount);

}
