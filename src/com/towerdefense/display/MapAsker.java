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
import com.towerdefense.towerdefense.database.DataBase;

public class MapAsker extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel chooseMap;

	public JOptionPane optionPaneVerif;

	private JButton validButton = new JButton("Ok");

	ArrayList<Map> allMaps;
	JComboBox<String> mapList;

	public MapAsker() {
		this.setSize(100, 130);
		setTitle("Map choice");
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new FlowLayout());

		chooseMap = new JLabel();
		chooseMap.setText("Choose a map");
		this.add(chooseMap);

		mapList = new JComboBox();
		DataBase database = new DataBase();
		allMaps = database.selectAllMaps();
		for (Map map : allMaps) {
			mapList.addItem(map.getName());
		}
		this.add(mapList);

		validButton.setActionCommand("Ok");
		this.add(validButton);
		validButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		if (actionString.equals("Ok")) {

			for (Map map : allMaps) {
				if (mapList.getSelectedItem().equals(map.getName())) {
					Map.setSelectedMap(map);
					map.init();
					PanelMenu.stopwatch.start();
				}
			}
			Window.changePanel("panelGame");
			setVisible(false);
		}
	}
}
