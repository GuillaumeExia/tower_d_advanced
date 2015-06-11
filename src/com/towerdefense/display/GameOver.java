package com.towerdefense.display;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class GameOver extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	
	private JButton validButton = new JButton("Ok");
	
	public GameOver() {
		
		this.setSize(300, 100);
		this.setTitle("GameOver");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		this.validButton.setActionCommand("Ok");
		this.add(this.validButton);
		this.validButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
