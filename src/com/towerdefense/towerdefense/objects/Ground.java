package com.towerdefense.towerdefense.objects;

import java.awt.*;

public abstract class Ground {
	private Image image;
    private int x;
    private int y;
    private int type;
    private static int WIDTH = 32;
    private static int HEIGHT = 32;
	private boolean walkable;
    public boolean debug = false;
    public Color debugColor = Color.green;

    public Ground(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Image getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

	public boolean isWalkable() {
		return walkable;
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setImage(Image image) {
		this.image = image;
	}

	public void setType(int type) {
		this.type = type;
	}

    public Point getCenterPoint(){ return new Point(WIDTH/2 + x, HEIGHT/2 + y); }

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

    public Rectangle getBounds(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

	public void draw(Graphics g){
        g.drawImage(image, this.x, this.y, null);

        /*if(debug) {
            g.setColor(debugColor);
            g.fillRect(x, y, WIDTH, HEIGHT);
            g.setColor(Color.DARK_GRAY);
        }*/
    }

}
