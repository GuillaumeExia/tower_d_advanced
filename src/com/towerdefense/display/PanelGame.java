package com.towerdefense.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;

public class PanelGame extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private StatusBar statusBar;
	private Timer timer;

	public PanelGame() {
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		this.statusBar = new StatusBar();
		this.add(this.statusBar);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				TowerShop.getTowerShop().hide();
				MouseHandler.fireMouseClicked(e);
			}
		});

		this.refreshPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void drawPause(Graphics g) {
		g.setColor(new Color(0, 0, 0, 75));
		g.fillRect(325, 150, 50, 200);
		g.fillRect(425, 150, 50, 200);
	}

	public StatusBar getStatusBar() {
		return this.statusBar;
	}

	public Timer getTimer() {
		return this.timer;
	}

	@Override
	public void paintComponent(Graphics g) {

		Map.getSelectedMap().draw(g);
		if (GlobalVariables.timer.isRunning() == false) {
			this.drawPause(g);
		}

	}

	public void refreshPanel() {
		GlobalVariables.timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelGame.this.repaint();
			}
		});
		GlobalVariables.timer.start();
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}