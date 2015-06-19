package com.towerdefense.display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;

import com.towerdefense.events.MouseHandler;
import com.towerdefense.events.Stopwatch;
import com.towerdefense.towerdefense.database.DBLink;

public class PanelLeaderBoard extends JPanel implements ActionListener {

	public PanelLeaderBoard() {
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
		this.setBackground(new Color(13, 14, 14));
		this.setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTitle(g2D);
        drawTableHeader(g2D);
        drawTableContent(g2D);
    }

	public void drawTitle(Graphics2D g){
        g.setColor(new Color(221, 153, 29));
        g.setFont(new Font("Calibri", Font.BOLD, 25));
        g.drawString("Leaderboard", 120, 40);
    }

    public void drawTableHeader(Graphics2D g){
        g.setColor(new Color(221, 153, 29));
        g.fillRoundRect(120, 60, 550, 40, 10, 10);
        g.setColor(Color.black);
        g.fillRoundRect(122, 62, 546, 36, 10, 10);

        g.setColor(Color.white);
        g.setFont(new Font("Calibri", Font.BOLD, 18));
        g.drawString("Rank", 160, 86);

        g.drawString("Player name", 245, 86);

        g.drawString("Time", 385, 86);

        g.drawString("Wave", 465, 86);

        g.drawString("Killed mobs", 545, 86);
    }

    public void drawTableContent(Graphics2D g){
        DBLink dbLink = new DBLink();
        dbLink.open();

        ResultSet score = dbLink.getScore();

        try {
            for (int i = 0; score.next() && i < 10; i++){
                int y = i*44 + 110;

                g.setColor(new Color(36, 36, 36));
                g.fillRoundRect(122, y, 550, 36, 10, 10);

                g.setColor(Color.white);
                g.setFont(new Font("Calibri", Font.BOLD, 16));
                g.drawString("" + (i + 1), 160, y + 25);

                g.drawString(score.getString("PSEUDO"), 245, y + 25);

                g.drawString(Stopwatch.timeToString(score.getInt("TIMEE")), 385, y + 25);

                g.drawString("" + score.getInt("WAVE"), 465, y + 25);

                g.drawString("" + score.getInt("KILLED_MOBS"), 545, y + 25);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbLink.close();
    }

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}