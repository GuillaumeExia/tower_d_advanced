package com.towerdefense.display;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.towerdefense.towerdefense.Save;
import com.towerdefense.towerdefense.database.DataBase;

public class PanelLoad extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel test;
	JButton btnLoad = new JButton("Load");
	JPanel load = new JPanel();
	ArrayList<Save> allSaves = new ArrayList<Save>();
	JComboBox<Integer> saveList = new JComboBox();

	public PanelLoad() {
		this.init();
		this.initLoadPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		if (actionString.equals("OK")) {
			for (Save save : this.allSaves) {
				if (this.saveList.getSelectedItem().equals(save.getId_save())) {
					Save.setSelectedSave(save);
				}
			}
			System.out.println("" + Save.getSelectedSave().getId_map() + Save.getSelectedSave().getPseudo());

		}

	}

	public void init() {
		Window.disableMenuItem();
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(this.load), BorderLayout.CENTER);
		this.add(this.btnLoad, BorderLayout.SOUTH);

	}

	public void initLoadPanel() {
		this.test = new JLabel("Je suis le panel load");
		this.load.add(this.test);
		this.saveList.setSize(500, 200);
		this.load.add(this.saveList);
		DataBase dataBase = new DataBase();
		this.allSaves = dataBase.selectAllSaves();
		for (Save save : this.allSaves) {
			this.saveList.addItem(save.getId_save());
		}
		this.add(this.saveList);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
