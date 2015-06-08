package com.towerdefense.towerdefense.objects;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TowerZone extends Ground {
    public final static int GROUND_TYPE = 5;
    public final static Rectangle SPRITE_RECTANGLE = new Rectangle(96, 0, 32, 32);
    public final static boolean WALKABLE = false;
    public boolean busy = false;
    public TowerZone self;

	public TowerZone(int x, int y) {
		super(x, y, GROUND_TYPE);
        this.self = this;
        setWalkable(WALKABLE);
        setImage(GlobalVariables.getSprite().getSubimage(SPRITE_RECTANGLE.x, SPRITE_RECTANGLE.y, SPRITE_RECTANGLE.width, SPRITE_RECTANGLE.height));

        MouseHandler.addEventObserver(new MouseHandler(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getBounds().contains(e.getPoint())){
                    TowerShop.getTowerShop().setXY(getCenterPoint().x, getCenterPoint().y);
                    TowerShop.getTowerShop().show(TowerShop.TOWER, self);
                }
            }
        });
	}

    @Override
    public void draw(Graphics g){
        super.draw(g);
    }

}