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

public class NicknameAsker extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static JTextField nickname;

	public static JTextField getNickname() {
		return nickname;
	}

	private DataBase database = new DataBase();

	private JLabel enterNickname;

	public JOptionPane optionPaneVerif;

	private JButton validButton = new JButton("Ok");

	public NicknameAsker() {
		this.setSize(300, 100);
		setTitle("Nickname");
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new FlowLayout());

		enterNickname = new JLabel();
		enterNickname.setText("To save your game please enter your nickname");
		this.add(enterNickname);

		nickname = new JTextField();
		nickname.setColumns(10);
		this.add(nickname);

		validButton.setActionCommand("Ok");
		this.add(validButton);
		validButton.addActionListener(this);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionString = e.getActionCommand();

		int test;
		if (actionString.equals("Ok")) {
			if (NicknameAsker.getNickname().getText().equals("")) {
				optionPaneVerif = new JOptionPane();
				optionPaneVerif.showMessageDialog(this,
						"Please enter a nickname", "Error",
						JOptionPane.ERROR_MESSAGE);
				Window.changePanel("panelGame");
				setVisible(false);//
			} else {
				GlobalVariables.nickname = nickname.getText();
				Window.changePanel("panelMenu");
				Window.disableMenuItem();
				System.out.println("Je simule la sauvegarde ici !");
				// faire ici la procedure de sauvegarde de la partie
				database.save(nickname.getText(), Map.getSelectedMap());

				setVisible(false);
			}
		}
	}
}
