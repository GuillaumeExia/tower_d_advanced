package com.towerdefense.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.towerdefense.events.MouseHandler;

public class PanelSave extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static Point mouse = new Point(0, 0);
    JLabel test = new JLabel("Je suis le panel save");

    public PanelSave() {
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
        this.setBackground(Color.PINK);
        this.setLayout(new BorderLayout());
        this.add(this.test, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
