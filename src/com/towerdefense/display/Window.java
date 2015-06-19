package com.towerdefense.display;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.towerdefense.display.load.PanelLoad;
import com.towerdefense.towerdefense.GlobalVariables;

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String title = "Tower Defense";
	private static Dimension sizeDimension = new Dimension(804, 650);
	private static CardLayout cardManager;

	public static JPanel main;
	public static JMenuItem pause = new JMenuItem("Pause");
	public static JMenuItem resume = new JMenuItem("Resume");

	public static JMenuItem menu = new JMenuItem("Menu");
	public static JMenuItem save = new JMenuItem("Save");
	public static JMenuItem exit = new JMenuItem("Exit");

	public static void changePanel(String name) {
		cardManager.show(main, name);
	}

	public static void disableMenuItem() {
		Window.pause.setEnabled(false);
		Window.save.setEnabled(false);
		Window.resume.setEnabled(false);
	}

	public static void disablePauseItem() {
		Window.pause.setEnabled(false);
	}

	public static void disableResumeItem() {
		Window.resume.setEnabled(true);
	}

	public static void enableMenuItem() {
		Window.pause.setEnabled(true);
		Window.save.setEnabled(true);
	}

	public static void enablePauseItem() {
		Window.resume.setEnabled(true);
	}

	public static void enableResumeItem() {
		Window.resume.setEnabled(true);
	}

	public JPanel[] panels;

	private int nbrOfPanels = 4;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu game = new JMenu("Game");
	private JMenu window = new JMenu("Window");
	BufferedImage img = null;

	public Window() {
		this.initPanels();
		this.initMenuBar();
		this.setJMenuBar(this.menuBar);
		this.init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0, 50));
		g.fillRect(50, 50, 27, 5);
		g.fillRect(75, 50, 27, 5);
	}

	private void init() {
		this.setTitle(Window.title);
		this.setSize(Window.sizeDimension);
		this.setResizable(false);
		/*try {
			this.img = ImageIO.read(this.getClass().getResource("/res/images/icon.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}*/
		this.setIconImage(this.img);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(main);
		this.setVisible(true);
	}

	private void initExitItem() {
		Window.exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		this.window.add(Window.exit);
	}

	private void initMenuBar() {

		Window.disableMenuItem();
		this.initPauseItem();
		this.initResumeItem();

		this.game.addSeparator();

		this.initSaveItem();

		this.menuBar.add(this.game); // ********************

		this.initMenuItem();
		this.initExitItem();

		this.menuBar.add(this.window); // ******************
	}

	private void initMenuItem() {
		Window.menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.disableMenuItem();
				Window.changePanel("panelMenu");
				// Window.this.panels[1].disable();
			}
		});
		this.window.add(Window.menu);
	}

	private void initPanels() {
		main = new JPanel(cardManager = new CardLayout());
		this.panels = new JPanel[this.nbrOfPanels];

		this.panels[0] = new PanelMenu();
		main.add(this.panels[0], "panelMenu");

		this.panels[1] = new PanelGame();
		main.add(this.panels[1], "panelGame");

		this.panels[2] = new PanelLoad();
		main.add(this.panels[2], "panelLoad");

		this.panels[3] = new PanelLeaderBoard();
		main.add(this.panels[3], "panelLeaderBoard");

		this.add(main, BorderLayout.CENTER);
	}

	public void initPauseItem() {
		Window.pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PanelMenu.stopwatch.pause();
				GlobalVariables.timer.stop();
				enableResumeItem();
			}
		});
		this.game.add(Window.pause);
	}

	public void initResumeItem() {
		Window.resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelMenu.stopwatch.resume();
				GlobalVariables.timer.start();
				Window.enablePauseItem();
				Window.disableResumeItem();
			}
		});
		this.game.add(Window.resume);
	}

	private void initSaveItem() {
		Window.save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PanelMenu.stopwatch.pause();
				GlobalVariables.timer.stop();

				if (GlobalVariables.nickname == null) {
					NicknameAsker nicknameAsker = new NicknameAsker();
				}
			}
		});

		this.game.add(Window.save);
	}
}
