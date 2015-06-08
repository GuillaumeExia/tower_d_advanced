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
import javax.swing.JTextField;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.database.DBLink;

public class NicknameAsker extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static JTextField nickname;

	public static JTextField getNickname() {
		return nickname;
	}

	private JLabel enterNickname;

	private JLabel chooseMap;

	public JOptionPane optionPaneVerif;

	private JButton validButton = new JButton("Ok");

	ArrayList<Map> allMaps;
	JComboBox<String> mapList;

	public NicknameAsker() {
		this.setSize(150, 150);
		this.setTitle("Nickname");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new FlowLayout());

		this.enterNickname = new JLabel();
		this.enterNickname.setText("Enter your nickname");
		this.add(this.enterNickname);

		nickname = new JTextField();
		nickname.setColumns(10);
		this.add(nickname);

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

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		if (actionString.equals("Ok")) {
			if (NicknameAsker.getNickname().getText().equals("")) {
				this.optionPaneVerif = new JOptionPane();
				this.optionPaneVerif.showMessageDialog(this, "Please enter a nickname", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else {
				for (Map map : this.allMaps) {
					if (this.mapList.getSelectedItem().equals(map.getName())) {
						Map.setSelectedMap(map);
						map.init();
					}
				}
				GlobalVariables.nickname = nickname.getText();
				Window.changePanel("panelGame");
				this.setVisible(false);
			}
		}
	}

}
