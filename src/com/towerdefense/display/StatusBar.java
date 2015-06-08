package com.towerdefense.display;

import com.towerdefense.towerdefense.GlobalVariables;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel{

    public StatusBar() {
        this.setBounds(0, 0, 400, 50);
        //this.setBackground(Color.red);
        this.setBackground(Color.CYAN);
    }

    public void drawLife(Graphics g){
        g.drawImage(GlobalVariables.getSprite().getSubimage(128, 16, 17, 16), 5, 3, null);
        g.drawString("" + GlobalVariables.life, 25, 15);
    }

    public void drawMoney(Graphics g){
        g.drawImage(GlobalVariables.getSprite().getSubimage(128, 0, 17, 16), 70, 3, null);
        g.drawString("" + GlobalVariables.money, 90, 15);
    }

    public void drawTime(Graphics g){}

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(0, 0, 384, 25);
        g.setColor(new Color(0, 0, 0));
        drawLife(g);
        drawMoney(g);
    }
}
