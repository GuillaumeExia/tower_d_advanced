package com.towerdefense.display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.towerdefense.towerdefense.GlobalVariables;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	public StatusBar() {
		this.setBounds(0, 0, 808, 50);
		// this.setBackground(Color.red);
		this.setBackground(Color.CYAN);
	}

	public void drawLife(Graphics g) {
		g.drawImage(GlobalVariables.getSprite().getSubimage(128, 16, 17, 16), 5, 3, null);
		g.drawString("" + GlobalVariables.life, 25, 15);
	}

	public void drawMoney(Graphics g) {
		g.drawImage(GlobalVariables.getSprite().getSubimage(128, 0, 17, 16), 70, 3, null);
		g.drawString("" + GlobalVariables.money, 90, 15);
	}

	public void drawTime(Graphics g) {
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, 808, 25);
		g.setColor(new Color(0, 0, 0));
		this.drawLife(g);
		this.drawMoney(g);
	}
}
