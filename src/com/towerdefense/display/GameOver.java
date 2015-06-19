package com.towerdefense.display;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.database.DataBase;

public class GameOver extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private static JTextField nickname;
	
	private DataBase database = new DataBase();
	
	private JLabel enterNickname;
	public JOptionPane optionPaneVerif;
	
	private JButton validButton = new JButton("Save score");
	private JButton unvalidButton = new JButton("Cancel");
	
	public GameOver() {
		Window.changePanel("panelMenu");
		this.setSize(300, 150);
		this.setTitle("GameOver");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		enterNickname = new JLabel();
		enterNickname.setText("To save your score please enter your nickname");
		this.add(enterNickname);
		
		nickname = new JTextField();
		nickname.setColumns(10);
		this.add(nickname);
		
		this.validButton.setActionCommand("Save score");
		this.add(this.validButton);
		this.validButton.addActionListener(this);
		
		this.unvalidButton.setActionCommand("Cancel");
		this.add(this.unvalidButton);
		this.unvalidButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();
		
		if (actionString.equals("Save score")){
			if (nickname.getText().equals("")) {
				optionPaneVerif = new JOptionPane();
				optionPaneVerif.showMessageDialog(this, "Please enter a nickname", "Error", JOptionPane.ERROR_MESSAGE);
				Window.changePanel("panelGame");
				setVisible(false);
			} else {
			GlobalVariables.nickname = nickname.getText();
			Window.changePanel("panelMenu");
			Window.disableMenuItem();
			database.saveScores(nickname.getText(), Map.getSelectedMap());
			setVisible(false);
			}
		}
		if(actionString.equals("Cancel")){
			this.dispose();
		}
		
	}
}
