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

public class NicknameAsker extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static JTextField nickname;

	public static JTextField getNickname() {
		return nickname;
	}

	private JLabel enterNickname;

	public JOptionPane optionPaneVerif;

	private JButton validButton = new JButton("Ok");

	public NicknameAsker() {
		this.setSize(300, 100);
		this.setTitle("Nickname");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		this.enterNickname = new JLabel();
		this.enterNickname.setText("To save your game please enter your nickname");
		this.add(this.enterNickname);

		nickname = new JTextField();
		nickname.setColumns(10);
		this.add(nickname);

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
				this.optionPaneVerif.showMessageDialog(this, "Please enter a nickname", "Error", JOptionPane.ERROR_MESSAGE);
				Window.changePanel("panelGame");
				this.setVisible(false);
			}
			else {
				GlobalVariables.nickname = nickname.getText();
				Window.changePanel("panelMenu");
				Window.disableMenuItem();
				System.out.println("Je simule la sauvegarde ici !");
				this.setVisible(false);
			}
		}
	}
}
