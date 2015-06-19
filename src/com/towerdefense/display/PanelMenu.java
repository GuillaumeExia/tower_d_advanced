package com.towerdefense.display;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.towerdefense.events.Stopwatch;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.objects.Ground;

public class PanelMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static Stopwatch stopwatch = new Stopwatch();
	JButton btnPlay = new JButton("Play");
	JButton btnLoad = new JButton("Load");
	JButton btnLeaderBoard = new JButton("LeaderBoard");
	JButton btnExit = new JButton("Exit");

	public PanelMenu() {
		initMenu();
		
		addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if(Map.getSelectedMap() != null){
                    for(Tower tower : Map.getSelectedMap().getTowers()){
                        tower = null;
                    }
                    for(Ground ground : Map.getSelectedMap().getGrounds()){
                         ground = null;
                    }
                }
                if(Workstation.getWorkstation() != null){
                    Workstation.setWorkstation(null);
                }
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();

		if (action.equals("Play")) {
			new MapAsker();
			Window.enableMenuItem();
			this.repaint();
			revalidate();
		} else if (action.equals("Load")) {
			Window.changePanel("panelLoad");
			Window.disableMenuItem();
			this.repaint();
			revalidate();
		} else if (action.equals("LeaderBoard")) {
			Window.changePanel("panelLeaderBoard");
			Window.disableMenuItem();
			this.repaint();
			revalidate();
		} else if (action.equals("Exit")) {
			System.exit(0);
		}

	}

	public void initMenu() {

		Window.disableMenuItem();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 5;
		gbc_btnPlay.gridy = 5;
		btnPlay.setActionCommand("Play");
		this.add(btnPlay, gbc_btnPlay);
		btnPlay.addActionListener(this);

		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 5;
		gbc_btnLoad.gridy = 6;
		btnLoad.setActionCommand("Load");
		this.add(btnLoad, gbc_btnLoad);
		btnLoad.addActionListener(this);

		GridBagConstraints gbc_btnLeaderBoard = new GridBagConstraints();
		gbc_btnLeaderBoard.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLeaderBoard.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeaderBoard.gridx = 5;
		gbc_btnLeaderBoard.gridy = 7;
		btnLeaderBoard.setActionCommand("LeaderBoard");
		this.add(btnLeaderBoard, gbc_btnLeaderBoard);
		btnLeaderBoard.addActionListener(this);

		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 5;
		gbc_btnExit.gridy = 8;
		btnExit.setActionCommand("Exit");
		this.add(btnExit, gbc_btnExit);
		btnExit.addActionListener(this);
	}
}
