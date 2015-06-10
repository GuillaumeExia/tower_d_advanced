package com.towerdefense.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;

public class StatusBar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer stopwatch;

	public StatusBar() {
		this.setBounds(0, 0, 808, 50);
		// this.setBackground(Color.red);
		setBackground(Color.CYAN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void drawLife(Graphics g) {
		g.drawImage(GlobalVariables.getSprite().getSubimage(128, 16, 17, 16),
				5, 3, null);
		g.drawString("" + GlobalVariables.life, 25, 15);
	}

	public void drawMoney(Graphics g) {
		g.drawImage(GlobalVariables.getSprite().getSubimage(128, 0, 17, 16),
				70, 3, null);
		g.drawString("" + GlobalVariables.money, 90, 15);
	}

	public void drawTime(Graphics g) {
		refreshTime();
		g.setFont(new Font("Digiface", Font.PLAIN, 20));
		g.drawString("" + PanelMenu.stopwatch.getTimeIs(), 730, 19);
	}

	public void drawWave(Graphics g) {
		g.drawImage(GlobalVariables.getSprite().getSubimage(128, 0, 17, 16),
				70, 3, null);
		g.drawString("" + Map.getWave(), 150, 15);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, 808, 25);
		g.setColor(new Color(0, 0, 0));
		drawLife(g);
		drawMoney(g);
		drawTime(g);
		drawWave(g);
	}

	public void refreshTime() {
		stopwatch = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelMenu.stopwatch.getTimeIs();
			}
		});
	}
}
