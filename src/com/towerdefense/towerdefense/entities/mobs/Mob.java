package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.entities.CanDieMoveAttack;
import com.towerdefense.towerdefense.entities.EntityType;

import java.awt.*;

public abstract class Mob {

	private int healthPoints;
	private int damageValue;
	private int movementSpeed;
	private int rangeValue;
	private int reward;
	private EntityType type;
    public static int previousMobSpawnTime = 0;

    public void setSpawnTime(int spawnTime) {
        this.spawnTime = previousMobSpawnTime + spawnTime;
        previousMobSpawnTime = this.spawnTime;
    }

    private int protection;
    private int spawnTime = 0;

    public int getSpawnTime() {
        return spawnTime;
    }

    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private int lastX = 0;

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    private int lastY = 0;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) {
        this.height = height;
    }

    //@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	//@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	public int getDamageValue() {
		return damageValue;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public int getProtection() {
		return protection;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public int getReward() {
		return reward;
	}

	public EntityType getType() {
		return type;
	}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //@Override
	public void move(int dx, int dy) {
        this.lastX = this.x;
        this.lastY = this.y;

        this.x += movementSpeed * dx;
        this.y += movementSpeed * dy;
	}

	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

    public Mob(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(width, height, x, y);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Point getCenterPoint(){
        return new Point(width/2 + x, height/2 + y);
    }

    public void draw(Graphics g){
        g.drawImage(image, this.x, this.y, null);
    }

}
