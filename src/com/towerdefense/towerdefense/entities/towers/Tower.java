package com.towerdefense.towerdefense.entities.towers;

import java.awt.*;

public abstract class Tower {

    private int width;
    private int height;
    private Rectangle actionZone;
    private Image image;
    private int x;
    private int y;

	/*private int cost;

	private int damageValue;

	private int reloadCooldown;

	private int rangeValue;

	private int healthPoints;

	private EntityType damageType;

	public int getCost() {
		return cost;
	}

	public EntityType getDamageType() {
		return damageType;
	}

	public int getDamageValue() {
		return damageValue;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public int getReloadCooldown() {
		return reloadCooldown;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setDamageType(EntityType damageType) {
		this.damageType = damageType;
	}

	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public void setReloadCooldown(int reloadCooldown) {
		this.reloadCooldown = reloadCooldown;
	}*/

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) { this.y = y; }

    public void setX(int x) { this.x = x; }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

    public Rectangle getBounds(){ return new Rectangle(x, y, width, height); }

    public void setImage(Image image) { this.image = image; }

    public Point getCenterPoint(){ return new Point(width/2 + x, height/2 + y); }

	public void setActionZone(int[] zone){
        actionZone = new Rectangle(getCenterPoint().x - zone[0]/2, getCenterPoint().y - zone[1]/2, zone[0], zone[1]);
    }

    public Rectangle getActionZone(){ return actionZone; }

    public void draw(Graphics g){
        g.drawImage(image, this.x, this.y, null);
    }

}
