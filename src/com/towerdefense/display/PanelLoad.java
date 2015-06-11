package com.towerdefense.display;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.Save;
import com.towerdefense.towerdefense.database.DataBase;

public class PanelLoad extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel test;
	JButton btnLoad = new JButton("Load");
	JPanel load = new JPanel();
	ArrayList<Save> allSaves = new ArrayList<Save>();
	JComboBox<String> saveList = new JComboBox();

	public PanelLoad() {
		this.init();
		this.initLoadPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		if (actionString.equals("Ok")) {
			for (Save save : this.allSaves) {
				if (this.saveList.getSelectedItem().equals(save.getStringSave())) {
					Save.setSelectedSave(save);

					System.out.println("ID Map :" + save.getIdMap() + "/ID Player :" + save.getIdPlayer() + "Wave : " + save.getWave() + "Time :" + save.getTime() + "Money : " + save.getMoney()
							+ "Life : " + save.getLife() + "Pseudo : " + save.getPseudo());
				}

			}
			Map map = new Map(Save.getSelectedSave());
			Window.changePanel("panelGame");
			PanelMenu.stopwatch.start();
			this.setVisible(false);
		}

	}

	public void init() {
		Window.disableMenuItem();
		this.setLayout(new FlowLayout());
		this.add(this.load);
		this.btnLoad.setActionCommand("Ok");
		this.add(this.btnLoad, BorderLayout.SOUTH);
		this.btnLoad.addActionListener(this);

	}

	public void initLoadPanel() {
		this.saveList.setSize(500, 200);
		this.load.add(this.saveList);
		DataBase dataBase = new DataBase();
		this.allSaves = dataBase.selectAllSaves();
		for (Save save : this.allSaves) {
			this.saveList.addItem(save.getStringSave());
		}
		this.add(this.saveList);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
