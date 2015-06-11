package com.towerdefense.display;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.database.DBLink;

public class MapAsker extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel chooseMap;

	public JOptionPane optionPaneVerif;

	private JButton validButton = new JButton("Ok");

	ArrayList<Map> allMaps;
	JComboBox<String> mapList;

	public MapAsker() {
		this.setSize(100, 130);
		this.setTitle("Map choice");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		this.chooseMap = new JLabel();
		this.chooseMap.setText("Choose a map");
		this.add(this.chooseMap);

		this.mapList = new JComboBox();
		DBLink dbLink = new DBLink();
		this.allMaps = dbLink.selectAllMapsProc();
		for (Map map : this.allMaps) {
			this.mapList.addItem(map.getName());
		}
		this.add(this.mapList);

		this.validButton.setActionCommand("Ok");
		this.add(this.validButton);
		this.validButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		if (actionString.equals("Ok")) {

			for (Map map : this.allMaps) {
				if (this.mapList.getSelectedItem().equals(map.getName())) {
					Map.setSelectedMap(map);
					map.init();
					PanelMenu.stopwatch.start();
				}
			}
			Window.changePanel("panelGame");
			this.setVisible(false);
		}
	}
}
